// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleNoFireEffect extends DefaultModule
{
    public ModuleNoFireEffect() {
        super("NoFireEffect", 0);
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Stops the fire effect");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().noFireEffectEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().noFireEffectEnabled = false;
    }
}
