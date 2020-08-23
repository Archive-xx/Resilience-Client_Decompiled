// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.objects.Macro;
import com.krispdev.resilience.command.Command;

public class CmdMacroClear extends Command
{
    public CmdMacroClear() {
        super("macros clear", "", "Clears the macros");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        Macro.macroList.clear();
        Resilience.getInstance().getLogger().infoChat("Cleared all macros");
        Resilience.getInstance().getFileManager().saveMacros(new String[0]);
        return true;
    }
}
