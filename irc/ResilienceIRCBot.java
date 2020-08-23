// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.irc;

import com.krispdev.resilience.irc.src.NickAlreadyInUseException;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.irc.src.PircBot;

public class ResilienceIRCBot extends PircBot implements Runnable
{
    private String channel;
    private String server;
    public static String username;
    public static IRCBotClient bot;
    
    static {
        ResilienceIRCBot.username = Resilience.getInstance().getInvoker().getSessionUsername();
    }
    
    public ResilienceIRCBot(final String username) {
        this.channel = "#ResilienceClient";
        this.server = "irc.freenode.net";
        ResilienceIRCBot.username = username;
    }
    
    public void run() {
        try {
            (ResilienceIRCBot.bot = new IRCBotClient(ResilienceIRCBot.username)).setVerbose(true);
            ResilienceIRCBot.bot.connect(this.server);
            ResilienceIRCBot.bot.joinChannel(this.channel);
        }
        catch (NickAlreadyInUseException e2) {
            ResilienceIRCBot.username = String.valueOf(ResilienceIRCBot.username) + "_";
        }
        catch (Exception e) {
            Resilience.getInstance().getLogger().warningChat("Error in IRC chat");
            e.printStackTrace();
        }
    }
    
    public String getUsername() {
        return ResilienceIRCBot.username;
    }
    
    public void setUsername(final String s) {
        this.setName(ResilienceIRCBot.username = s);
    }
}
