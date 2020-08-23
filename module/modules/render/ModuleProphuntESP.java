// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.block.Block;
import java.util.Iterator;
import com.krispdev.resilience.utilities.RenderUtils;
import org.lwjgl.opengl.GL11;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.module.categories.ModuleCategory;
import net.minecraft.entity.item.EntityFallingBlock;
import java.util.ArrayList;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleProphuntESP extends DefaultModule
{
    private ArrayList<EntityFallingBlock> entities;
    private int[] badIds;
    private int ticks;
    
    public ModuleProphuntESP() {
        super("Prophunt ESP", 0);
        this.entities = new ArrayList<EntityFallingBlock>();
        this.badIds = new int[] { 78 };
        this.ticks = 0;
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Draws a coloured box around Prohunt objects");
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        this.entities.clear();
        for (final Object o : this.invoker.getEntityList()) {
            if (o instanceof EntityFallingBlock) {
                final EntityFallingBlock e = (EntityFallingBlock)o;
                if (this.entities.contains(e)) {
                    continue;
                }
                this.entities.add(e);
            }
        }
        for (final EntityFallingBlock e2 : this.entities) {
            final Block block = this.invoker.getBlock((int)this.invoker.getPosX((Entity)e2) - 1, (int)this.invoker.getPosY((Entity)e2), (int)this.invoker.getPosZ((Entity)e2));
            GL11.glPushMatrix();
            RenderUtils.setup3DLightlessModel();
            RenderUtils.drawESP(false, this.invoker.getPosX((Entity)e2) - this.invoker.getRenderPosX() - 0.5, this.invoker.getPosY((Entity)e2) - this.invoker.getRenderPosY() - 0.5, this.invoker.getPosZ((Entity)e2) - this.invoker.getRenderPosZ() - 0.5, this.invoker.getPosX((Entity)e2) - this.invoker.getRenderPosX() + 0.5, this.invoker.getPosY((Entity)e2) - this.invoker.getRenderPosY() + 0.5, this.invoker.getPosZ((Entity)e2) - this.invoker.getRenderPosZ() + 0.5, 0.5, 0.5, 1.0, 0.19, 0.5, 0.5, 1.0, 1.0);
            RenderUtils.shutdown3DLightlessModel();
            GL11.glPopMatrix();
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
