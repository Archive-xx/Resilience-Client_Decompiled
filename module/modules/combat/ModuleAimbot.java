// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.game.EntityUtils;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAimbot extends DefaultModule
{
    private Entity target;
    private EntityUtils entityUtils;
    
    public ModuleAimbot() {
        super("Aimbot", 0);
        this.entityUtils = new EntityUtils();
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically aims at the closest target");
    }
    
    public void onUpdate(final EventOnUpdate event) {
        this.target = this.entityUtils.getClosestEntity((Entity)Resilience.getInstance().getWrapper().getPlayer(), Resilience.getInstance().getValues().players.getState(), Resilience.getInstance().getValues().mobs.getState(), Resilience.getInstance().getValues().animals.getState(), Resilience.getInstance().getValues().invisibles.getState(), Resilience.getInstance().getValues().propBlocks.getState());
        try {
            if (this.target != null && this.entityUtils.isWithinRange(Resilience.getInstance().getValues().range.getValue(), this.target)) {
                this.entityUtils.faceEntity(this.target);
            }
        }
        catch (Exception ex) {}
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
