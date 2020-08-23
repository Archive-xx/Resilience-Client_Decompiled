// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.logger;

import com.krispdev.resilience.Resilience;

public class ResilienceLogger
{
    public void plain(final String s) {
        System.out.println("[" + Resilience.getInstance().getName() + "] " + s);
    }
    
    public void info(final String s) {
        System.out.println("[" + Resilience.getInstance().getName() + "] [INFO] " + s);
    }
    
    public void warning(final String s) {
        System.out.println("[" + Resilience.getInstance().getName() + "] [WARNING] " + s);
    }
    
    public void irc(final String s) {
        System.out.println("[" + Resilience.getInstance().getName() + "] [IRC] " + s);
    }
    
    public void plainChat(final String s) {
        Resilience.getInstance().getInvoker().addChatMessage("§f[§3" + Resilience.getInstance().getName() + "§f] " + s);
    }
    
    public void infoChat(final String s) {
        Resilience.getInstance().getInvoker().addChatMessage("§f[§3" + Resilience.getInstance().getName() + "§f] [§bINFO§f] " + s);
    }
    
    public void warningChat(final String s) {
        Resilience.getInstance().getInvoker().addChatMessage("§f[§3" + Resilience.getInstance().getName() + "§f] [§cWARNING§f] " + s);
    }
    
    public void ircChat(final String s) {
        Resilience.getInstance().getInvoker().addChatMessage("§f[§bIRC§f] " + s);
    }
}
