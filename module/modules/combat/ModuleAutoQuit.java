// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventHealthUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.value.values.NumberValue;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoQuit extends DefaultModule
{
    public static NumberValue quitHealth;
    
    static {
        ModuleAutoQuit.quitHealth = new NumberValue(6.0f, 1.0f, 20.0f, "AutoQuit Health", true);
    }
    
    public ModuleAutoQuit() {
        super("AutoQuit", 0);
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically quits the game when your health gets low");
    }
    
    public void onHealthUpdate(final EventHealthUpdate event) {
        if (event.getHealth() <= ModuleAutoQuit.quitHealth.getValue()) {
            this.invoker.sendChatMessage("§bHello");
            this.setEnabled(false);
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
