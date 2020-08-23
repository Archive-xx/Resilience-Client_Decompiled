// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnClick;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.game.EntityUtils;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleClickAimbot extends DefaultModule
{
    private Entity selectedEntity;
    private EntityUtils utils;
    
    public ModuleClickAimbot() {
        super("Click Aimbot", 0);
        this.utils = new EntityUtils();
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically aims at the closest entity when you click");
    }
    
    public void onClick(final EventOnClick event) {
        if (event.getButton() == 0) {
            this.selectedEntity = this.utils.getClosestEntity((Entity)this.invoker.getWrapper().getPlayer(), Resilience.getInstance().getValues().players.getState(), Resilience.getInstance().getValues().mobs.getState(), Resilience.getInstance().getValues().animals.getState(), Resilience.getInstance().getValues().invisibles.getState(), Resilience.getInstance().getValues().propBlocks.getState());
            if (this.selectedEntity != null && this.utils.isWithinRange(4.0f, this.selectedEntity) && this.utils.canHit(this.selectedEntity) && !this.utils.isEntityFriend(this.selectedEntity)) {
                this.utils.faceEntity(this.selectedEntity);
                this.utils.hitEntity(this.selectedEntity);
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
