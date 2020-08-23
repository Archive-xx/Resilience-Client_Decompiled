// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleSneak extends DefaultModule
{
    public ModuleSneak() {
        super("Sneak", 0);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Automatically sneaks");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        this.invoker.setSneakKeyPressed(true);
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onDisable() {
        this.invoker.setSneakKeyPressed(false);
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
