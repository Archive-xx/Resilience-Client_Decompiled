// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdTrackSet extends Command
{
    public CmdTrackSet() {
        super("track set ", "[Username]", "Sets the Track username to the username specified");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("set ");
        Resilience.getInstance().getValues().trackName = args[1].trim();
        Resilience.getInstance().getLogger().infoChat("Set the Track username to " + args[1]);
        return true;
    }
}
