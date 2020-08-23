// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.irc;

import com.krispdev.resilience.donate.Donator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.irc.src.PircBot;

public class IRCBotClient extends PircBot
{
    private String username;
    
    public IRCBotClient(final String username) {
        this.setName(this.username = username);
    }
    
    public void onMessage(final String channel, String sender, final String login, final String hostname, final String message) {
        if (!Resilience.getInstance().getValues().ircEnabled) {
            return;
        }
        final boolean krisp = sender.equals("Krisp");
        final boolean nick = sender.startsWith("XxXN");
        if (nick) {
            sender = sender.replaceFirst("XxXN", "");
        }
        final boolean vip = Donator.isDonator(sender, 5.0f);
        Resilience.getInstance().getLogger().irc(String.valueOf(sender) + ": " + message);
        Resilience.getInstance().getLogger().ircChat(String.valueOf(nick ? "§f[§3NickName§f]§b " : "") + (krisp ? "§f[§cOwner§f] §b" : (vip ? "§f[§6VIP§f]§b " : "§b")) + sender + "§8:" + (krisp ? "§c " : (vip ? "§6 " : "§f ")) + message);
    }
    
    public void setUsername(final String s) {
        this.setName(this.username = s);
    }
}
