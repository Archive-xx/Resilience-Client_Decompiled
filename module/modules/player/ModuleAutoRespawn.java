// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.player;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoRespawn extends DefaultModule
{
    public ModuleAutoRespawn() {
        super("Auto Respawn", 0);
        this.setCategory(ModuleCategory.PLAYER);
        this.setDescription("Automatically respawns you when you die");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().autoRespawnEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().autoRespawnEnabled = false;
    }
}
