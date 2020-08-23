// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.movement;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleHighJump extends DefaultModule
{
    public ModuleHighJump() {
        super("HighJump", 0);
        this.setCategory(ModuleCategory.MOVEMENT);
        this.setDescription("Allows higher jumping");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().highJumpEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().highJumpEnabled = false;
    }
}
