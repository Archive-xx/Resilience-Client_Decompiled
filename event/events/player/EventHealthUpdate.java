// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.player;

import java.util.Iterator;
import com.krispdev.resilience.event.listeners.ModListener;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.Event;

public class EventHealthUpdate implements Event
{
    private float health;
    
    public EventHealthUpdate(final float health) {
        this.health = health;
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
                    mod.onHealthUpdate(this);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public float getHealth() {
        return this.health;
    }
}
