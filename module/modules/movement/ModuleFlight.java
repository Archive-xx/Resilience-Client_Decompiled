// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleFlight extends DefaultModule
{
    public ModuleFlight() {
        super("Flight", 33);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Allows you to fly");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().flightEnabled = true;
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        this.setDisplayName("Flight (" + Math.round(Resilience.getInstance().getValues().flySpeed.getValue()) + ")");
        this.invoker.setMotionX(0.0);
        this.invoker.setMotionY(0.0);
        this.invoker.setMotionZ(0.0);
        this.invoker.setLandMovementFactor(Resilience.getInstance().getValues().flySpeed.getValue() / 3.0f);
        this.invoker.setJumpMovementFactor(Resilience.getInstance().getValues().flySpeed.getValue() / 3.0f);
        if (Keyboard.isKeyDown(this.invoker.getJumpCode())) {
            this.invoker.setMotionY(this.invoker.getMotionY() + Resilience.getInstance().getValues().flySpeed.getValue() / 4.0f);
        }
        if (Keyboard.isKeyDown(this.invoker.getSneakCode())) {
            this.invoker.setMotionY(this.invoker.getMotionY() - Resilience.getInstance().getValues().flySpeed.getValue() / 4.0f);
        }
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().flightEnabled = false;
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
