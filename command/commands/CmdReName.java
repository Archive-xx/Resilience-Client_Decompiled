// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import net.minecraft.item.ItemStack;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdReName extends Command
{
    public CmdReName() {
        super("rename ", "[Name]", "Renames your current item to a huge string in creative");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        if (Resilience.getInstance().getInvoker().isInCreativeMode()) {
            final String[] args = cmd.split("name ");
            final ItemStack item = Resilience.getInstance().getInvoker().getCurrentItem();
            Resilience.getInstance().getInvoker().setStackDisplayName(item, args[1]);
            for (int i = 0; i < 100; ++i) {
                Resilience.getInstance().getInvoker().setStackDisplayName(item, Resilience.getInstance().getInvoker().getItemDisplayName(item).concat(args[1]));
            }
            Resilience.getInstance().getLogger().infoChat("Renamed your current item");
            return true;
        }
        Resilience.getInstance().getLogger().infoChat("Error! You must be in creative");
        return false;
    }
}
