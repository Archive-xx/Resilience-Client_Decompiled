// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleNames extends DefaultModule
{
    public ModuleNames() {
        super("Names", 0);
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Shows larger (better?) nametags");
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().namesEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().namesEnabled = false;
    }
}
