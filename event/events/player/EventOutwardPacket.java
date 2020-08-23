// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.player;

import java.util.Iterator;
import com.krispdev.resilience.event.listeners.ModListener;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import java.util.ArrayList;
import net.minecraft.network.Packet;
import com.krispdev.resilience.event.events.Event;
import com.krispdev.resilience.event.events.Cancellable;

public class EventOutwardPacket extends Cancellable implements Event
{
    private Packet packet;
    private ArrayList<Packet> packetsList;
    
    public EventOutwardPacket(final Packet packet) {
        this.packetsList = new ArrayList<Packet>();
        this.packet = packet;
    }
    
    @Override
    public void onEvent() {
        if (!Resilience.getInstance().isEnabled()) {
            return;
        }
        try {
            for (final Listener l : Resilience.getInstance().getEventManager().eventListeners) {
                if (l instanceof ModListener) {
                    final ModListener mod = (ModListener)l;
                    mod.onOutwardPacket(this);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void addPacketToList(final Packet p) {
        this.packetsList.add(p);
    }
    
    public ArrayList<Packet> getPacketList() {
        return this.packetsList;
    }
}
