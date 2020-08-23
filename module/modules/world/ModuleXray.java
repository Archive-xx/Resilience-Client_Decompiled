// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.world;

import com.krispdev.resilience.event.events.player.EventGameShutdown;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleXray extends DefaultModule
{
    private int prevSmoothLighting;
    
    public ModuleXray() {
        super("Xray", 45);
        this.prevSmoothLighting = 2;
        this.setCategory(ModuleCategory.WORLD);
        this.setDescription("Allows you to see through blocks to find ores");
        this.setSave(false);
    }
    
    @Override
    public void onEnable() {
        this.prevSmoothLighting = this.invoker.getSmoothLighting();
        this.invoker.setSmoothLighting(2);
        Resilience.getInstance().getXrayUtils().xrayEnabled = true;
    }
    
    @Override
    public void onToggle() {
        Resilience.getInstance().getInvoker().loadRenderers();
    }
    
    @Override
    public void onDisable() {
        this.invoker.setSmoothLighting(this.prevSmoothLighting);
        Resilience.getInstance().getXrayUtils().xrayEnabled = false;
    }
    
    @Override
    public void onGameShutdown(final EventGameShutdown event) {
        this.invoker.setSmoothLighting(this.prevSmoothLighting);
    }
}
