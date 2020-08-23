// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat.modes;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleInvisibles extends DefaultModule
{
    public ModuleInvisibles() {
        super("Target Invisibles", 0);
        this.setCategory(ModuleCategory.COMBAT_EXTENSION);
        this.setDescription("Do you want combat mods to target invisible players?");
        this.setVisible(false);
    }
    
    public void onEnable() {
        Resilience.getInstance().getValues().invisibles.setState(true);
    }
    
    public void onDisable() {
        Resilience.getInstance().getValues().invisibles.setState(false);
    }
}
