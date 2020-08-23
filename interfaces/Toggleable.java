// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.interfaces;

public interface Toggleable
{
    void onEnable();
    
    void onDisable();
    
    void onToggle();
    
    void toggle();
    
    void setEnabled(final boolean p0);
}
