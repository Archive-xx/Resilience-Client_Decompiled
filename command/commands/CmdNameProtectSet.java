// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdNameProtectSet extends Command
{
    public CmdNameProtectSet() {
        super("nameprotect set ", "[Name]", "Sets the name protect name");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("nameprotect set ");
        Resilience.getInstance().getValues().nameProtectAlias.setValue(args[1]);
        Resilience.getInstance().getLogger().infoChat("Set the nameprotect alias to " + args[1]);
        Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
        return true;
    }
}
