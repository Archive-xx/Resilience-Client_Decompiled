// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleEnabledMods extends DefaultModule
{
    public ModuleEnabledMods() {
        super("Enabled Mods", 0);
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Shows all enabled mods in the left hand side of the screen");
        this.setVisible(false);
    }
    
    public void onEnable() {
        Resilience.getInstance().getValues().enabledModsEnabled = true;
    }
    
    public void onDisable() {
        Resilience.getInstance().getValues().enabledModsEnabled = false;
    }
}
