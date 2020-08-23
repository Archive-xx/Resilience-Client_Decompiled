// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModulePhaze extends DefaultModule
{
    public ModulePhaze() {
        super("Phaze", 0);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Allows you to glitch through doors, etc.");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        this.invoker.setNoClip(true);
        this.invoker.setOnGround(true);
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
        if (Resilience.getInstance().getWrapper().getPlayer() == null) {
            return;
        }
        this.invoker.setNoClip(false);
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
}
