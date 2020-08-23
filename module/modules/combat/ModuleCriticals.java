// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.util.MovingObjectPosition;
import com.krispdev.resilience.event.events.player.EventOnClick;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleCriticals extends DefaultModule
{
    public ModuleCriticals() {
        super("Criticals", 0);
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically lands criticals when you click");
    }
    
    public void onClick(final EventOnClick event) {
        if (event.getButton() == 0 && this.invoker.isOnGround() && !this.invoker.isInWater() && ((this.invoker.getObjectMouseOver() != null && this.invoker.getObjectMouseOver().typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) || Resilience.getInstance().getValues().killAuraEnabled)) {
            this.invoker.setMotionY(0.29);
            this.invoker.setFallDistance(0.289f);
            this.invoker.setOnGround(false);
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
