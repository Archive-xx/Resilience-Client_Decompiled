// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdIRCPrefixChange extends Command
{
    public CmdIRCPrefixChange() {
        super("ircprefix set ", "[New Prefix]", "Sets a new IRC prefix");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        Resilience.getInstance().setIRCPrefix(cmd.split("ircprefix set ")[1]);
        Resilience.getInstance().getLogger().infoChat("Set the IRC prefix to " + cmd.split("ircprefix set ")[1]);
        return true;
    }
}
