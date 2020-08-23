// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.externals;

import java.util.Iterator;
import com.krispdev.resilience.interfaces.Bindable;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.Event;
import com.krispdev.resilience.event.events.Cancellable;

public class EventKeyDown extends Cancellable implements Event
{
    private final int keycode;
    
    public EventKeyDown(final int keycode) {
        this.keycode = keycode;
    }
    
    @Override
    public void onEvent() {
        try {
            for (final Listener l : Resilience.getInstance().getEventManager().gameListeners) {
                if (l instanceof Bindable) {
                    final Bindable mod = (Bindable)l;
                    mod.onKeyDown(this.keycode);
                }
            }
        }
        catch (Exception ex) {}
    }
}
