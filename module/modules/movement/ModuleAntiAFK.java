// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.Timer;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAntiAFK extends DefaultModule
{
    private Timer timer;
    
    public ModuleAntiAFK() {
        super("AntiAFK", 0);
        this.timer = new Timer();
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Stops AFK plugins from catching you by jumping every X seconds");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        if (this.timer.hasTimePassed((long)Resilience.getInstance().getValues().antiAFKSeconds.getValue() * 1000L) && Resilience.getInstance().getWrapper().getPlayer() != null && this.invoker.isOnGround()) {
            this.timer.reset();
            this.invoker.jump();
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
