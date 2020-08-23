// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event;

import com.krispdev.resilience.event.listeners.Listener;
import java.util.ArrayList;

public class EventManager implements Manageable
{
    public ArrayList<Listener> eventListeners;
    public ArrayList<Listener> gameListeners;
    
    public EventManager() {
        this.eventListeners = new ArrayList();
        this.gameListeners = new ArrayList();
    }
    
    public void registerGameListener(final Listener l) {
        if (!this.gameListeners.contains(l)) {
            this.gameListeners.add(l);
        }
    }
    
    public void unregisterGameListener(final Listener l) {
        try {
            if (this.gameListeners.contains(l)) {
                this.gameListeners.remove(this.gameListeners.indexOf(l));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void register(final Listener l) {
        if (!this.eventListeners.contains(l)) {
            this.eventListeners.add(l);
        }
    }
    
    public void unregister(final Listener l) {
        try {
            if (this.eventListeners.contains(l)) {
                this.eventListeners.remove(this.eventListeners.indexOf(l));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clear() {
        this.eventListeners.clear();
    }
}
