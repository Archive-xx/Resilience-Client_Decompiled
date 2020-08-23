// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import net.minecraft.entity.EntityLivingBase;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdRemoteView extends Command
{
    public CmdRemoteView() {
        super("remoteview", " [Player]", "Renders the selected view. Toggle on/off by typing it again.");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        if (this.mc.renderViewEntity != this.mc.thePlayer) {
            this.mc.renderViewEntity = (EntityLivingBase)this.mc.thePlayer;
            Resilience.getInstance().getLogger().infoChat("Now viewing from your player");
            return true;
        }
        final String[] args = cmd.split("remoteview ");
        for (final Object o : Resilience.getInstance().getInvoker().getEntityList()) {
            if (o instanceof EntityOtherPlayerMP) {
                final EntityOtherPlayerMP otherPlayer = (EntityOtherPlayerMP)o;
                if (Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)otherPlayer).equalsIgnoreCase(args[1].trim())) {
                    this.mc.renderViewEntity = (EntityLivingBase)otherPlayer;
                    Resilience.getInstance().getLogger().infoChat("Now viewing from §b" + Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)otherPlayer) + "§f's perspective");
                    return true;
                }
                continue;
            }
        }
        Resilience.getInstance().getLogger().warningChat("Error, player not found");
        return false;
    }
}
