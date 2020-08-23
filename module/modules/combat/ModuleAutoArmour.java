// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import net.minecraft.entity.player.EntityPlayer;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.Timer;
import com.krispdev.resilience.utilities.game.InventoryUtils;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoArmour extends DefaultModule
{
    int[] ids;
    int[] bootIds;
    int[] pantIds;
    int[] chestIds;
    int[] helmIds;
    private InventoryUtils utils;
    private int prevSlot;
    private Timer timer;
    
    public ModuleAutoArmour() {
        super("AutoArmor", 0);
        this.ids = new int[] { 298, 299, 300, 301, 314, 315, 316, 317, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313 };
        this.bootIds = new int[] { 313, 309, 305, 317, 301 };
        this.pantIds = new int[] { 312, 308, 304, 316, 300 };
        this.chestIds = new int[] { 311, 307, 303, 315, 299 };
        this.helmIds = new int[] { 310, 306, 302, 314, 298 };
        this.utils = new InventoryUtils();
        this.prevSlot = -1;
        this.timer = new Timer();
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically puts on armor when your old armor breaks");
    }
    
    public void onUpdate(final EventOnUpdate event) {
        if (!this.timer.hasTimePassed(170L)) {
            return;
        }
        this.timer.reset();
        if (this.prevSlot != -1) {
            this.invoker.setInvSlot(this.prevSlot);
            this.prevSlot = -1;
        }
        boolean boots = true;
        boolean pants = true;
        boolean shirt = true;
        boolean helm = true;
        for (int i = 0; i < this.invoker.getArmourInventory().length; ++i) {
            if (i == 0 && this.invoker.getArmourInventory()[i] == null) {
                boots = false;
            }
            if (i == 1 && this.invoker.getArmourInventory()[i] == null) {
                pants = false;
            }
            if (i == 2 && this.invoker.getArmourInventory()[i] == null) {
                shirt = false;
            }
            if (i == 3 && this.invoker.getArmourInventory()[i] == null) {
                helm = false;
            }
        }
        if (!boots) {
            boolean hot = false;
            boolean inv = false;
            int slot = -1;
            int[] bootIds;
            for (int length = (bootIds = this.bootIds).length, j = 0; j < length; ++j) {
                final int id = bootIds[j];
                final int invSlot = this.utils.getSlotOfInvItem(id);
                if (invSlot != -1) {
                    inv = true;
                    slot = invSlot;
                    break;
                }
                final int newSlot = this.utils.getSlotOfHotbarItem(id);
                if (newSlot != -1) {
                    hot = true;
                    slot = newSlot;
                    break;
                }
            }
            if (slot != -1 && inv) {
                this.utils.click(slot, 1);
            }
            else if (slot != -1 && hot) {
                this.prevSlot = this.invoker.getCurInvSlot();
                this.invoker.setInvSlot(slot);
                this.invoker.sendUseItem(this.invoker.getCurrentItem(), (EntityPlayer)Resilience.getInstance().getWrapper().getPlayer());
            }
        }
        if (!pants) {
            boolean hot = false;
            boolean inv = false;
            int slot = -1;
            int[] pantIds;
            for (int length2 = (pantIds = this.pantIds).length, k = 0; k < length2; ++k) {
                final int id = pantIds[k];
                final int invSlot = this.utils.getSlotOfInvItem(id);
                if (invSlot != -1) {
                    inv = true;
                    slot = invSlot;
                    break;
                }
                final int newSlot = this.utils.getSlotOfHotbarItem(id);
                if (newSlot != -1) {
                    hot = true;
                    slot = newSlot;
                    break;
                }
            }
            if (slot != -1 && inv) {
                this.utils.click(slot, 1);
            }
            else if (slot != -1 && hot) {
                this.prevSlot = this.invoker.getCurInvSlot();
                this.invoker.setInvSlot(slot);
                this.invoker.sendUseItem(this.invoker.getCurrentItem(), (EntityPlayer)Resilience.getInstance().getWrapper().getPlayer());
            }
        }
        if (!shirt) {
            boolean hot = false;
            boolean inv = false;
            int slot = -1;
            int[] chestIds;
            for (int length3 = (chestIds = this.chestIds).length, l = 0; l < length3; ++l) {
                final int id = chestIds[l];
                final int invSlot = this.utils.getSlotOfInvItem(id);
                if (invSlot != -1) {
                    inv = true;
                    slot = invSlot;
                    break;
                }
                final int newSlot = this.utils.getSlotOfHotbarItem(id);
                if (newSlot != -1) {
                    hot = true;
                    slot = newSlot;
                    break;
                }
            }
            if (slot != -1 && inv) {
                this.utils.click(slot, 1);
            }
            else if (slot != -1 && hot) {
                this.prevSlot = this.invoker.getCurInvSlot();
                this.invoker.setInvSlot(slot);
                this.invoker.sendUseItem(this.invoker.getCurrentItem(), (EntityPlayer)Resilience.getInstance().getWrapper().getPlayer());
            }
        }
        if (!helm) {
            boolean hot = false;
            boolean inv = false;
            int slot = -1;
            int[] helmIds;
            for (int length4 = (helmIds = this.helmIds).length, n = 0; n < length4; ++n) {
                final int id = helmIds[n];
                final int invSlot = this.utils.getSlotOfInvItem(id);
                if (invSlot != -1) {
                    inv = true;
                    slot = invSlot;
                    break;
                }
                final int newSlot = this.utils.getSlotOfHotbarItem(id);
                if (newSlot != -1) {
                    hot = true;
                    slot = newSlot;
                    break;
                }
            }
            if (slot != -1 && inv) {
                this.utils.click(slot, 1);
            }
            else if (slot != -1 && hot) {
                this.prevSlot = this.invoker.getCurInvSlot();
                this.invoker.setInvSlot(slot);
                this.invoker.sendUseItem(this.invoker.getCurrentItem(), (EntityPlayer)Resilience.getInstance().getWrapper().getPlayer());
            }
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
