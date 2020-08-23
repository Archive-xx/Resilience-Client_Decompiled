// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.objects;

import com.krispdev.resilience.Resilience;
import java.util.ArrayList;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.interfaces.Bindable;

public class Macro implements Bindable, Listener
{
    public static ArrayList<Macro> macroList;
    private int key;
    private String command;
    
    static {
        Macro.macroList = new ArrayList<Macro>();
    }
    
    public Macro(final int key, final String command) {
        this.key = key;
        this.command = command;
        Resilience.getInstance().getEventManager().registerGameListener((Listener)this);
    }
    
    public int getKey() {
        return this.key;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public void onKeyPress(final int keyCode) {
    }
    
    @Override
    public void onKeyDown(final int keyCode) {
        if (keyCode == this.key) {
            Resilience.getInstance().getInvoker().sendChatMessage(this.command);
        }
    }
}
