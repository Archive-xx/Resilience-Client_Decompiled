// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import java.util.Iterator;
import com.krispdev.resilience.utilities.RenderUtils;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.game.EntityUtils;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleTracers extends DefaultModule
{
    private EntityUtils entityUtils;
    
    public ModuleTracers() {
        super("Tracers", 0);
        this.entityUtils = new EntityUtils();
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Draws lines to all players around you");
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        for (final Object o : this.invoker.getEntityList()) {
            if (o instanceof EntityOtherPlayerMP) {
                final EntityOtherPlayerMP player = (EntityOtherPlayerMP)o;
                final double posX = this.invoker.getLastTickPosX((Entity)player) - RenderManager.renderPosX;
                final double posY = this.invoker.getLastTickPosY((Entity)player) - RenderManager.renderPosY;
                final double posZ = this.invoker.getLastTickPosZ((Entity)player) - RenderManager.renderPosZ;
                final boolean friend = this.entityUtils.isEntityFriend((Entity)player);
                final boolean enemy = this.entityUtils.isEntityEnemy((Entity)player);
                RenderUtils.drawTracer(0.0, 0.0, 0.0, posX, posY + this.invoker.getEntityHeight((Entity)player) / 2.0f, posZ, (double)(friend ? 0 : 1), (double)((!friend && !enemy) ? 1 : 0), (double)(enemy ? 0 : 1), 1.0);
            }
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
