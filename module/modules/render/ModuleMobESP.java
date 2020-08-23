// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import java.util.Iterator;
import com.krispdev.resilience.utilities.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleMobESP extends DefaultModule
{
    public ModuleMobESP() {
        super("MobESP", 0);
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Draws colour coded boxes around mobs and animals");
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        for (final Object o : this.invoker.getEntityList()) {
            if (o instanceof EntityPlayer) {
                continue;
            }
            if (!(o instanceof EntityLivingBase)) {
                continue;
            }
            final EntityLivingBase entity = (EntityLivingBase)o;
            final boolean mob = entity instanceof EntityMob;
            RenderUtils.drawESP(false, this.invoker.getBoundboxMinX((Entity)entity) - this.invoker.getRenderPosX() - 0.1, this.invoker.getBoundboxMinY((Entity)entity) - this.invoker.getRenderPosY() - 0.1, this.invoker.getBoundboxMinZ((Entity)entity) - this.invoker.getRenderPosZ() - 0.1, this.invoker.getBoundboxMaxX((Entity)entity) - this.invoker.getRenderPosX() + 0.1, this.invoker.getBoundboxMaxY((Entity)entity) - this.invoker.getRenderPosY() + 0.1, this.invoker.getBoundboxMaxZ((Entity)entity) - this.invoker.getRenderPosZ() + 0.1, 1.0, 1.0, 1.0, 0.19, (double)(mob ? 1 : 0), (double)(mob ? 0 : 1), (double)(mob ? 0 : 1), 1.0);
        }
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
