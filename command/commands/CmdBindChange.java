// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.module.modules.DefaultModule;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdBindChange extends Command
{
    public CmdBindChange() {
        super("bind set ", "[Mod] [Key]", "Changes the current keybind of a mod");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split(" ");
        final String modName = args[2];
        final String code = args[3].toUpperCase();
        for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
            if (mod.getName().equalsIgnoreCase(modName)) {
                mod.setKeyBind(Keyboard.getKeyIndex(code));
                Resilience.getInstance().getLogger().infoChat("Set the keybind of the mod §b" + mod.getName() + " §fto §b" + Keyboard.getKeyName(mod.getKeyCode()) + "§f. Next time right click on the mod's button in the GUI and change the bind from there!");
                Resilience.getInstance().getFileManager().saveBinds(new String[0]);
                return true;
            }
        }
        Resilience.getInstance().getLogger().warningChat("Mod not found: §c" + modName + "§f. §bTry right clicking on the mod's button in the GUI and changing it from there!");
        return true;
    }
}
