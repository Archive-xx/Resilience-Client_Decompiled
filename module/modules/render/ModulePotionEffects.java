// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModulePotionEffects extends DefaultModule
{
    public ModulePotionEffects() {
        super("PotionEffects", 0);
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Shows all your potion effects");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().potionEffectsEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().potionEffectsEnabled = false;
    }
}
