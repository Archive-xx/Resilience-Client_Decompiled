// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFood;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.game.InventoryUtils;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoEat extends DefaultModule
{
    private InventoryUtils utils;
    private boolean goOnce;
    private int prevSlot;
    private boolean finished;
    
    public ModuleAutoEat() {
        super("AutoEat", 0);
        this.utils = new InventoryUtils();
        this.goOnce = false;
        this.prevSlot = -1;
        this.finished = false;
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Automatically eats food when you're hungry");
    }
    
    public void onUpdate(final EventOnUpdate event) {
        if (this.prevSlot != -1 && this.finished && this.goOnce) {
            this.invoker.setInvSlot(this.prevSlot);
            this.invoker.setUseItemKeyPressed(false);
            this.goOnce = false;
        }
        if (this.invoker.getFoodLevel() < 18) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack item = this.invoker.getItemAtSlotHotbar(i);
                if (item != null && item.getItem() instanceof ItemFood) {
                    this.prevSlot = this.invoker.getCurInvSlot();
                    this.invoker.setInvSlot(i);
                    this.invoker.setUseItemKeyPressed(true);
                    if (this.invoker.getFoodLevel() > 16) {
                        this.goOnce = true;
                        this.finished = true;
                    }
                }
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
