// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.game.EntityUtils;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleForceField extends DefaultModule
{
    private EntityUtils entityUtils;
    
    public ModuleForceField() {
        super("ForceField", 0);
        this.entityUtils = new EntityUtils();
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Attacks all entities at max speed. FOR NON-NOCHEAT SERVERS!");
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
        if (Resilience.getInstance().getWrapper().getPlayer() == null || Resilience.getInstance().getWrapper().getWorld() == null) {
            return;
        }
        Resilience.getInstance().getLogger().warningChat("ForceField does not bypass protection on most servers. Use KillAura instead!");
    }
    
    public void onUpdate(final EventOnUpdate event) {
        try {
            for (final Object o : this.invoker.getEntityList()) {
                EntityLivingBase entity = null;
                if (o instanceof EntityLivingBase) {
                    entity = (EntityLivingBase)o;
                }
                if (entity != null && this.entityUtils.isWithinRange(4.2f, (Entity)entity) && !this.entityUtils.isEntityFriend((Entity)entity) && !this.entityUtils.isEntityDead((Entity)entity) && !this.entityUtils.isThePlayer((Entity)entity)) {
                    this.entityUtils.hitEntity((Entity)entity, true);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
