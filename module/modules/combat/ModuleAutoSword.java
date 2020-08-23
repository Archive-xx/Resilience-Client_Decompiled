// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MovingObjectPosition;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnClick;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoSword extends DefaultModule
{
    public ModuleAutoSword() {
        super("AutoSword", 0);
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically switches to your swords when you hit an entity");
    }
    
    public void onClick(final EventOnClick event) {
        if (event.getButton() == 0 && Resilience.getInstance().getWrapper().getPlayer() != null && this.invoker.getObjectMouseOver() != null && this.invoker.getObjectMouseOver().typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            for (int i = 0; i < 9; ++i) {
                if (this.invoker.getItemAtSlotHotbar(i) != null) {
                    final Item item = this.invoker.getItemAtSlotHotbar(i).getItem();
                    if (item != null && item instanceof ItemSword) {
                        this.invoker.setInvSlot(i);
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
