// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events;

public class Cancellable
{
    private boolean isCancelled;
    
    public Cancellable() {
        this.isCancelled = false;
    }
    
    public boolean isCancelled() {
        return this.isCancelled;
    }
    
    public void setCancelled(final boolean flag) {
        this.isCancelled = flag;
    }
}
