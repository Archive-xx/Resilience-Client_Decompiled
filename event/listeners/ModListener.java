// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.listeners;

import com.krispdev.resilience.event.events.player.EventOutwardPacket;
import com.krispdev.resilience.event.events.player.EventValueChange;
import com.krispdev.resilience.event.events.player.EventOnClick;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.event.events.player.EventGameShutdown;
import com.krispdev.resilience.event.events.player.EventBlockClick;
import com.krispdev.resilience.event.events.player.EventPacketReceive;
import com.krispdev.resilience.event.events.player.EventHealthUpdate;
import com.krispdev.resilience.event.events.player.EventPostMotion;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.events.player.EventPreMotion;

public interface ModListener extends Listener
{
    void onPreMotion(final EventPreMotion p0);
    
    void onUpdate(final EventOnUpdate p0);
    
    void onPostMotion(final EventPostMotion p0);
    
    void onHealthUpdate(final EventHealthUpdate p0);
    
    void onPacketReceive(final EventPacketReceive p0);
    
    void onBlockClicked(final EventBlockClick p0);
    
    void onGameShutdown(final EventGameShutdown p0);
    
    void onRender(final EventOnRender p0);
    
    void onClick(final EventOnClick p0);
    
    void onValueChange(final EventValueChange p0);
    
    void onOutwardPacket(final EventOutwardPacket p0);
}
