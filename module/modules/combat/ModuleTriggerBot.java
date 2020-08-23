// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.Timer;
import com.krispdev.resilience.utilities.game.EntityUtils;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleTriggerBot extends DefaultModule
{
    private EntityUtils entityUtils;
    private Timer timer;
    
    public ModuleTriggerBot() {
        super("TriggerBot", 0);
        this.entityUtils = new EntityUtils();
        this.timer = new Timer();
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically hits the entity you hover over");
    }
    
    public void onUpdate(final EventOnUpdate event) {
        if (this.invoker.getObjectMouseOver() != null) {
            final Entity e = this.invoker.getObjectMouseOver().entityHit;
            if (e != null && this.entityUtils.canHit(e, Resilience.getInstance().getValues().range.getValue()) && !this.entityUtils.isEntityFriend(e) && this.timer.hasTimePassed(1000L / (long)Resilience.getInstance().getValues().speed.getValue())) {
                this.entityUtils.hitEntity(e, Resilience.getInstance().getValues().autoBlockEnabled);
            }
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
