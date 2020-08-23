// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities.value.values;

import com.krispdev.resilience.event.events.player.EventValueChange;
import com.krispdev.resilience.utilities.value.Value;

public class StringValue extends Value
{
    private String value;
    
    public StringValue(final String name, final String value) {
        super(name);
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        final EventValueChange eventChange = new EventValueChange((Value)this);
        eventChange.onEvent();
        if (!eventChange.isCancelled()) {
            this.value = value;
        }
        else {
            eventChange.setCancelled(false);
        }
    }
}
