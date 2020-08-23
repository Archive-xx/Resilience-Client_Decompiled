// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities.game;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.wrappers.MethodInvoker;

public class InventoryUtils
{
    private MethodInvoker in;
    
    public InventoryUtils() {
        this.in = Resilience.getInstance().getInvoker();
    }
    
    public int getSlotOfHotbarItem(final int itemId) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack is = this.in.getItemAtSlotHotbar(i);
            if (is != null && this.in.getIdFromItem(is.getItem()) == itemId) {
                return i;
            }
        }
        return -1;
    }
    
    public int getSlotOfInvItem(final int itemId) {
        for (int i = 9; i < 36; ++i) {
            final ItemStack is = this.in.getItemAtSlot(i);
            if (is != null && this.in.getIdFromItem(is.getItem()) == itemId) {
                return i;
            }
        }
        return -1;
    }
    
    public int getFreeSlotInHotbar(final int itemId) {
        for (int i = 0; i < 9; ++i) {
            if (this.in.getItemAtSlot(i) == null) {
                return i;
            }
            if (this.in.getItemAtSlot(i) == null) {
                return i;
            }
            final ItemStack itemAtSlot = this.in.getItemAtSlotHotbar(itemId);
            final Item item = this.in.getItemById(itemId);
            final int idInSlot = this.in.getIdFromItem(itemAtSlot.getItem());
            if (itemAtSlot != null && itemAtSlot != null && idInSlot == itemId && itemAtSlot.stackSize < item.getItemStackLimit()) {
                return i;
            }
        }
        return -1;
    }
    
    public int getFreeSlotInInv(final int itemId) {
        for (int i = 36; i < 45; ++i) {
            if (this.in.getItemAtSlot(i) == null) {
                return i;
            }
            if (this.in.getItemAtSlot(i) != null) {
                ItemStack itemAtSlot = null;
                itemAtSlot = this.in.getItemAtSlot(i);
                if (itemAtSlot != null) {
                    final Item item = this.in.getItemById(itemId);
                    final int idInSlot = this.in.getIdFromItem(itemAtSlot.getItem());
                    if (itemAtSlot != null && item != null && idInSlot == itemId && itemAtSlot.stackSize < item.getItemStackLimit()) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public void click(final int slot, final int mode) {
        this.in.clickWindow(slot, mode, 0, (EntityPlayer)Resilience.getInstance().getWrapper().getPlayer());
    }
    
    public void sendItemUse(final ItemStack itemStack) {
        this.in.sendUseItem(itemStack, (EntityPlayer)Resilience.getInstance().getWrapper().getPlayer());
    }
}
