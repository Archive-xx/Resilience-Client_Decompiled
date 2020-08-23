// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleSafeWalk extends DefaultModule
{
    public ModuleSafeWalk() {
        super("SafeWalk", 0);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Prevents you from falling off cliffs");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().safeWalkEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().safeWalkEnabled = false;
    }
}
