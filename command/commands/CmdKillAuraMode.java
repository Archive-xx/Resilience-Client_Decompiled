// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdKillAuraMode extends Command
{
    public CmdKillAuraMode() {
        super("killaura mode ", "[Players/Mobs/Animals/All]", "Sets the KillAura target mode");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("mode ");
        if (args[1].trim().equalsIgnoreCase("players")) {
            Resilience.getInstance().getValues().players.setState(true);
            Resilience.getInstance().getValues().mobs.setState(false);
            Resilience.getInstance().getValues().animals.setState(false);
            Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
            Resilience.getInstance().getLogger().infoChat("Set the KillAura mode to §bPlayers");
            return true;
        }
        if (args[1].trim().equalsIgnoreCase("mobs")) {
            Resilience.getInstance().getValues().players.setState(false);
            Resilience.getInstance().getValues().mobs.setState(true);
            Resilience.getInstance().getValues().animals.setState(false);
            Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
            Resilience.getInstance().getLogger().infoChat("Set the KillAura mode to §bMobs");
            return true;
        }
        if (args[1].trim().equalsIgnoreCase("animals")) {
            Resilience.getInstance().getValues().animals.setState(true);
            Resilience.getInstance().getValues().players.setState(false);
            Resilience.getInstance().getValues().mobs.setState(false);
            Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
            Resilience.getInstance().getLogger().infoChat("Set the KillAura mode to §bAnimals");
            return true;
        }
        if (args[1].trim().equalsIgnoreCase("all")) {
            Resilience.getInstance().getValues().players.setState(true);
            Resilience.getInstance().getValues().mobs.setState(true);
            Resilience.getInstance().getValues().animals.setState(true);
            Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
            Resilience.getInstance().getLogger().infoChat("Set the KillAura mode to §bAll");
            return true;
        }
        Resilience.getInstance().getLogger().warningChat("Unknown mode \"" + args[1] + "\"");
        return true;
    }
}
