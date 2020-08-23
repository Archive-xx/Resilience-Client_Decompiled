// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.gui;

import net.minecraft.client.gui.GuiScreen;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleGui extends DefaultModule
{
    public ModuleGui() {
        super("Gui", 54);
        this.setCategory(ModuleCategory.GUI);
        this.setDescription("The user interface for the client");
    }
    
    public void onEnable() {
        if (Resilience.getInstance().getWrapper().getWorld() == null || Resilience.getInstance().getWrapper().getPlayer() == null) {
            return;
        }
        this.invoker.displayScreen((GuiScreen)Resilience.getInstance().getClickGui());
    }
    
    public void onDisable() {
    }
}
