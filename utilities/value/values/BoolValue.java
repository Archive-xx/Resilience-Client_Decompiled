// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities.value.values;

import com.krispdev.resilience.event.events.player.EventValueChange;
import com.krispdev.resilience.utilities.value.Value;

public class BoolValue extends Value
{
    private boolean state;
    
    public BoolValue(final String name, final boolean state) {
        super(name);
        this.state = state;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public void setState(final boolean state) {
        final EventValueChange eventChange = new EventValueChange((Value)this);
        eventChange.onEvent();
        if (!eventChange.isCancelled()) {
            this.state = state;
        }
        else {
            eventChange.setCancelled(false);
        }
    }
}
