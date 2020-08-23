// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleNoHurtcam extends DefaultModule
{
    public ModuleNoHurtcam() {
        super("NoHurtcam", 0);
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Prevents the hurtcam when you get hit");
    }
    
    public void onEnable() {
        Resilience.getInstance().getValues().noHurtcamEnabled = true;
    }
    
    public void onDisable() {
        Resilience.getInstance().getValues().noHurtcamEnabled = false;
    }
}
