// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdVClip extends Command
{
    public CmdVClip() {
        super("vclip ", "[Amount]", "Teleports you up/down a specified amount");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("vclip ");
        final int posY = Integer.parseInt(args[1].trim());
        this.mc.thePlayer.setLocationAndAngles(this.mc.thePlayer.posX, posY + this.mc.thePlayer.posY, this.mc.thePlayer.posZ, this.mc.thePlayer.rotationYaw, this.mc.thePlayer.rotationPitch);
        Resilience.getInstance().getLogger().infoChat("Teleported you " + ((posY < 0) ? "down " : "up ") + Math.abs(posY) + " block" + ((Math.abs(posY) == 1) ? "" : "s"));
        return true;
    }
}
