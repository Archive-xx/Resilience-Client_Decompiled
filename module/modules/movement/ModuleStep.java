// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleStep extends DefaultModule
{
    public ModuleStep() {
        super("Step", 0);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Automatically steps up blocks like stairs");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        this.invoker.setStepHeight(Resilience.getInstance().getValues().stepHeight.getValue());
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onDisable() {
        this.invoker.setStepHeight(0.5f);
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
