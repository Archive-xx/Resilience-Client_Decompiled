// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdFakeChat extends Command
{
    public CmdFakeChat() {
        super("fakechat ", "[message]", "Makes a fake chat message appear. Can use & color codes");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("fakechat ");
        Resilience.getInstance().getInvoker().addChatMessage(args[1].replaceAll("&", "§"));
        return true;
    }
}
