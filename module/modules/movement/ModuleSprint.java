// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleSprint extends DefaultModule
{
    public ModuleSprint() {
        super("Sprint", 46);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Forces sprint");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        if (!this.invoker.isSneaking() && !this.invoker.isCollidedHorizontally() && !this.invoker.isOnLadder() && this.invoker.moveForward() > 0.0f) {
            this.invoker.setSprinting(true);
        }
    }
    
    @Override
    public void onDisable() {
        this.invoker.setSprinting(false);
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
