// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdTrackClear extends Command
{
    public CmdTrackClear() {
        super("track clear", "", "Clears the track line");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) {
        Resilience.getInstance().getValues().trackPosList.clear();
        Resilience.getInstance().getLogger().infoChat("Cleared the track line");
        return true;
    }
}
