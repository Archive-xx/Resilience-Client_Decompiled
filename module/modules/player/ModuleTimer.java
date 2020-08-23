// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.player;

import com.krispdev.resilience.event.events.player.EventValueChange;
import java.text.DecimalFormat;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleTimer extends DefaultModule
{
    public ModuleTimer() {
        super("Speed", 0);
        this.setCategory(ModuleCategory.PLAYER);
        this.setDescription("Slows down/speeds up time");
        this.setSave(false);
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
        this.setDisplayName("Timer (" + new DecimalFormat("#.#").format(Resilience.getInstance().getValues().timerSpeed.getValue()) + ")");
        this.invoker.setTimerSpeed(Resilience.getInstance().getValues().timerSpeed.getValue());
    }
    
    @Override
    public void onValueChange(final EventValueChange event) {
        if (event.getValue() == Resilience.getInstance().getValues().timerSpeed) {
            this.invoker.setTimerSpeed(Resilience.getInstance().getValues().timerSpeed.getValue());
            this.setDisplayName("Timer (" + new DecimalFormat("#.#").format(Resilience.getInstance().getValues().timerSpeed.getValue()) + ")");
        }
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
        this.invoker.setTimerSpeed(1.0f);
    }
}
