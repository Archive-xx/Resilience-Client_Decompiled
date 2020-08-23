// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.util.MovingObjectPosition;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoMine extends DefaultModule
{
    public ModuleAutoMine() {
        super("AutoMine", 0);
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Automatically mines when you hover over a block");
    }
    
    public void onUpdate(final EventOnUpdate event) {
        final MovingObjectPosition hover = this.invoker.getObjectMouseOver();
        if (hover.typeOfHit != null) {
            if (hover.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                this.invoker.setKeyBindAttackPressed(true);
            }
        }
        else {
            this.invoker.setKeyBindAttackPressed(false);
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        if (Resilience.getInstance().getWrapper().getGameSettings() != null && Resilience.getInstance().getWrapper().getPlayer() != null) {
            this.invoker.setKeyBindAttackPressed(false);
        }
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
