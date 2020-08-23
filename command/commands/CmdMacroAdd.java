// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.command.objects.Macro;
import com.krispdev.resilience.command.Command;

public class CmdMacroAdd extends Command
{
    public CmdMacroAdd() {
        super("macro add ", "[Key] [Command]", "Adds a macro");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("macro add ");
        final String[] keybind = args[1].split(" ");
        final String[] args2 = args[1].split(keybind[0]);
        if (Character.isWhitespace(args2[1].charAt(0))) {
            args2[1] = args2[1].replaceFirst(" ", "");
        }
        Macro.macroList.add(new Macro(Keyboard.getKeyIndex(keybind[0].trim().toUpperCase()), args2[1]));
        Resilience.getInstance().getLogger().infoChat("Added a macro to \"§b" + keybind[0] + "§f\" that will say \"§b" + args2[1] + "§f\" in the chat");
        Resilience.getInstance().getFileManager().saveMacros(new String[0]);
        return true;
    }
}
