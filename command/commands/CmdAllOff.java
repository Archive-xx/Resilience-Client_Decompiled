// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.module.modules.DefaultModule;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdAllOff extends Command
{
    public CmdAllOff() {
        super("alloff", "", "Turns off all enabled mods");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
            if (mod.isEnabled()) {
                mod.setEnabled(false);
            }
        }
        Resilience.getInstance().getFileManager().saveEnabledMods(new String[0]);
        return true;
    }
}
