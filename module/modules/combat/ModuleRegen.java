// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.wrappers.MethodInvoker;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleRegen extends DefaultModule
{
    public ModuleRegen() {
        super("Regen", 0);
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Regenerates your health");
    }
    
    public void onUpdate(final EventOnUpdate event) {
        if (this.invoker.getFoodLevel() > 17 && !this.invoker.isInCreativeMode() && this.invoker.getHealth() < 19.0f) {
            new ModuleRegen$1(this).start();
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
