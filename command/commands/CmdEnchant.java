// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdEnchant extends Command
{
    public CmdEnchant() {
        super("enchant", "", "Forces max enchantments on an item in creative");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        if (!Resilience.getInstance().getInvoker().isInCreativeMode()) {
            Resilience.getInstance().getLogger().warningChat("Error! Player must be in creative mode");
            return true;
        }
        final ItemStack item = Resilience.getInstance().getInvoker().getCurrentItem();
        if (item != null) {
            Enchantment[] enchantList;
            for (int length = (enchantList = Resilience.getInstance().getInvoker().getEnchantList()).length, i = 0; i < length; ++i) {
                final Enchantment e = enchantList[i];
                if (e != null) {
                    Resilience.getInstance().getInvoker().addEnchantment(item, e, 127);
                }
            }
            Resilience.getInstance().getLogger().infoChat("Enchanted your " + item.getDisplayName());
            return true;
        }
        Resilience.getInstance().getLogger().warningChat("Error! No item in hand found");
        return true;
    }
}
