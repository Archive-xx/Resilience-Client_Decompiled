// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.player;

import java.util.Iterator;
import com.krispdev.resilience.event.listeners.ModListener;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.entity.player.EntityPlayer;
import com.krispdev.resilience.event.events.Event;
import com.krispdev.resilience.event.events.Cancellable;

public class EventPreMotion extends Cancellable implements Event
{
    private EntityPlayer ep;
    
    public EventPreMotion(final EntityPlayer ep) {
        this.ep = ep;
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
                    mod.onPreMotion(this);
                }
            }
        }
        catch (Exception ex) {}
    }
}
