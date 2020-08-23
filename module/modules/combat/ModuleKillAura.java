// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.event.events.player.EventPostMotion;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventPreMotion;
import com.krispdev.resilience.module.categories.ModuleCategory;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.utilities.Timer;
import com.krispdev.resilience.utilities.game.EntityUtils;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleKillAura extends DefaultModule
{
    private EntityUtils entityUtils;
    private Timer timer;
    private Entity target;
    
    public ModuleKillAura() {
        super("KillAura", 19);
        this.entityUtils = new EntityUtils();
        this.timer = new Timer();
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically attacks entities");
    }
    
    public void onPreMotion(final EventPreMotion event) {
        this.target = this.entityUtils.getClosestEntity((Entity)Resilience.getInstance().getWrapper().getPlayer(), Resilience.getInstance().getValues().players.getState(), Resilience.getInstance().getValues().mobs.getState(), Resilience.getInstance().getValues().animals.getState(), Resilience.getInstance().getValues().invisibles.getState(), Resilience.getInstance().getValues().propBlocks.getState());
        if (this.target != null && this.entityUtils.canHit(this.target)) {
            this.invoker.setSprinting(false);
            this.entityUtils.faceEntity(this.target);
        }
    }
    
    public void onPostMotion(final EventPostMotion event) {
        if (this.target != null && this.timer.hasTimePassed(1000L / (long)Resilience.getInstance().getValues().speed.getValue()) && this.entityUtils.isWithinRange(Resilience.getInstance().getValues().range.getValue(), this.target) && this.entityUtils.canHit(this.target)) {
            this.entityUtils.hitEntity(this.target, Resilience.getInstance().getValues().autoBlockEnabled);
            this.timer.reset();
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getValues().killAuraEnabled = true;
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getValues().killAuraEnabled = false;
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
