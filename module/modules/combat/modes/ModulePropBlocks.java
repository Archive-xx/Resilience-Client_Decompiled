// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat.modes;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModulePropBlocks extends DefaultModule
{
    public ModulePropBlocks() {
        super("Target PropBlocks", 0);
        this.setCategory(ModuleCategory.COMBAT_EXTENSION);
        this.setDescription("Do you want combat mods to target Prophunt blocks?");
        this.setVisible(false);
    }
    
    public void onEnable() {
        Resilience.getInstance().getValues().propBlocks.setState(true);
    }
    
    public void onDisable() {
        Resilience.getInstance().getValues().propBlocks.setState(false);
    }
}
