// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event;

import com.krispdev.resilience.event.listeners.Listener;

public interface Manageable
{
    void register(final Listener p0);
    
    void unregister(final Listener p0);
    
    void clear();
}
