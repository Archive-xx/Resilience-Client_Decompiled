// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdInvSee extends Command
{
    public CmdInvSee() {
        super("invsee ", "[Player]", "Shows you the player's item in hand and enchantments");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("invsee ");
        for (final Object o : Resilience.getInstance().getInvoker().getEntityList()) {
            if (o instanceof EntityOtherPlayerMP) {
                final EntityOtherPlayerMP player = (EntityOtherPlayerMP)o;
                if (Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)player).equalsIgnoreCase(args[1].trim())) {
                    Resilience.getInstance().getWrapper().getInGameGui().displayInv((EntityPlayer)player);
                    Resilience.getInstance().getLogger().infoChat("Now viewing " + args[1]);
                    return true;
                }
                continue;
            }
        }
        Resilience.getInstance().getLogger().warningChat("Error! Player " + args[1] + " not found.");
        return true;
    }
}
