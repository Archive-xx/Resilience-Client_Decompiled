// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities;

public class Timer
{
    private long prevTime;
    
    public Timer() {
        this.prevTime = 0L;
    }
    
    public boolean hasTimePassed(final long milSec) {
        return this.getTime() - this.prevTime >= (float)milSec;
    }
    
    public void reset() {
        this.prevTime = this.getTime();
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
}
