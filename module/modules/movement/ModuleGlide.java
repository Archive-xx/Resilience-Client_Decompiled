// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleGlide extends DefaultModule
{
    public ModuleGlide() {
        super("Glide", 34);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Gives you the ability to glide");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        if (this.invoker.getMotionY() <= -0.15 && !this.invoker.isInWater() && !this.invoker.isOnGround() && !this.invoker.isOnLadder() && !Resilience.getInstance().getValues().flightEnabled) {
            this.invoker.setMotionY(-0.15);
            this.invoker.setOnGround(true);
        }
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
