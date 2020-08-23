// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.module.modules.DefaultModule;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdToggle extends Command
{
    public CmdToggle() {
        super("t ", "[Mod]", "Toggles the specified mod");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("t ");
        for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
            if (mod.getName().equalsIgnoreCase(args[1].trim())) {
                mod.toggle();
                Resilience.getInstance().getLogger().infoChat("Toggled mod: §b" + mod.getName());
                return true;
            }
        }
        Resilience.getInstance().getLogger().warningChat("Mod not found: §c" + args[1]);
        return true;
    }
}
