// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.irc.ResilienceIRCBot;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdIRCNick extends Command
{
    public CmdIRCNick() {
        super("irc nick ", "[New Nick Name]", "Sets the IRC nickname.");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String nick = "XxXN" + cmd.split("irc nick ")[1].trim();
        if (nick.equalsIgnoreCase("Krisp") || nick.toLowerCase().contains("krisp") || nick.toLowerCase().contains("kirsp")) {
            Resilience.getInstance().getLogger().warningChat("But, but, but... You're not Krisp! :O");
            return true;
        }
        ResilienceIRCBot.bot.setAutoNickChange(true);
        ResilienceIRCBot.username = nick;
        ResilienceIRCBot.bot.setUsername(nick);
        Resilience.getInstance().getLogger().infoChat("Set your IRC nickname to " + cmd.split("irc nick")[1]);
        return true;
    }
}
