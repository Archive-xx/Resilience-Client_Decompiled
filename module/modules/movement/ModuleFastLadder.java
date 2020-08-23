// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleFastLadder extends DefaultModule
{
    private int ticks;
    
    public ModuleFastLadder() {
        super("FastLadder", 0);
        this.ticks = 0;
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Climbs up ladders faster than usual");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        ++this.ticks;
        if (this.invoker.isOnLadder() && Keyboard.isKeyDown(this.invoker.getForwardCode())) {
            this.invoker.setMotionY(0.25);
        }
        else if (this.invoker.isOnLadder() && !Keyboard.isKeyDown(this.invoker.getForwardCode())) {
            this.invoker.setMotionY(-0.25);
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
