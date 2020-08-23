// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.player;

import java.util.Iterator;
import com.krispdev.resilience.event.listeners.ModListener;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.network.Packet;
import com.krispdev.resilience.event.events.Event;
import com.krispdev.resilience.event.events.Cancellable;

public class EventPacketReceive extends Cancellable implements Event
{
    private Packet packet;
    
    public EventPacketReceive(final Packet packet) {
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
                    mod.onPacketReceive(this);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public Packet getPacket() {
        return this.packet;
    }
}
