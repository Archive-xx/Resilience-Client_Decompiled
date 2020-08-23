// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.player;

import java.util.Iterator;
import com.krispdev.resilience.event.listeners.ModListener;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.Event;
import com.krispdev.resilience.event.events.Cancellable;

public class EventGameShutdown extends Cancellable implements Event
{
    private long nanoTime;
    
    public EventGameShutdown(final long nanoTime) {
        this.nanoTime = nanoTime;
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
                    mod.onGameShutdown(this);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public long getNanoTime() {
        return this.nanoTime;
    }
}
