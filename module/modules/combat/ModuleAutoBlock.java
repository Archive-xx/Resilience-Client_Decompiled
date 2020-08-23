// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoBlock extends DefaultModule
{
    public ModuleAutoBlock() {
        super("AutoBlock", 0);
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically blocks with your sword in KillAura");
    }
    
    public void onEnable() {
        Resilience.getInstance().getValues().autoBlockEnabled = true;
    }
    
    public void onDisable() {
        Resilience.getInstance().getValues().autoBlockEnabled = false;
    }
}
