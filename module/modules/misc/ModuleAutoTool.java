// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.item.ItemStack;
import com.krispdev.resilience.event.events.player.EventBlockClick;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoTool extends DefaultModule
{
    public ModuleAutoTool() {
        super("AutoTool", 65);
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Automatically switches to the best tool on click");
    }
    
    public void onBlockClicked(final EventBlockClick event) {
        float compare = 1.0f;
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            try {
                final ItemStack item = this.invoker.getItemAtSlotHotbar(i);
                if (item != null) {
                    final float strength = this.invoker.getStrVsBlock(item, event.getBlock());
                    if (strength > compare) {
                        compare = strength;
                        slot = i;
                    }
                }
            }
            catch (Exception ex) {}
        }
        if (slot != -1) {
            this.invoker.setInvSlot(slot);
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
