// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.world;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleFastBreak extends DefaultModule
{
    public ModuleFastBreak() {
        super("FastBreak", 0);
        this.setCategory(ModuleCategory.WORLD);
        this.setDescription("Allows you to breaks blocks at a fast speed");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().fastBreakEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().fastBreakEnabled = false;
    }
}
