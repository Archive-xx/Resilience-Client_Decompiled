// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdAntiAfkSet extends Command
{
    public CmdAntiAfkSet() {
        super("antiafk set ", "[Delay in Seconds]", "Sets the AntiAFK delay");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("set ");
        Resilience.getInstance().getValues().antiAFKSeconds.setValue(Float.parseFloat(args[1]));
        Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
        Resilience.getInstance().getLogger().infoChat("Set the AntiAFK delay to " + args[1]);
        return true;
    }
}
