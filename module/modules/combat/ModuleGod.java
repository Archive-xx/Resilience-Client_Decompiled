// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventPreMotion;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleGod extends DefaultModule
{
    private int ticks;
    
    public ModuleGod() {
        super("God", 0);
        this.ticks = 0;
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Makes you invinsible!");
    }
    
    public void onPreMotion(final EventPreMotion event) {
        ++this.ticks;
        if (this.ticks > -1) {
            this.ticks = 0;
            event.setCancelled(true);
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
