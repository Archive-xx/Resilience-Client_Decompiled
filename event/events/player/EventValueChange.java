// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.player;

import java.util.Iterator;
import com.krispdev.resilience.event.listeners.ModListener;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.value.Value;
import com.krispdev.resilience.event.events.Event;
import com.krispdev.resilience.event.events.Cancellable;

public class EventValueChange extends Cancellable implements Event
{
    private Value value;
    
    public EventValueChange(final Value value) {
        this.value = value;
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
                    mod.onValueChange(this);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public Value getValue() {
        return this.value;
    }
}
