// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.utilities.RenderUtils;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleBreadcrumbs extends DefaultModule
{
    public ModuleBreadcrumbs() {
        super("Breadcrumbs", 48);
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Draws a line behind you");
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        if (this.invoker.getMotionX() != 0.0 || this.invoker.getMotionY() != 0.0 || this.invoker.getMotionZ() != 0.0) {
            Resilience.getInstance().getValues().breadcrumbPosList.add(new Double[] { this.invoker.getPosX(), this.invoker.getPosY(), this.invoker.getPosZ() });
        }
    }
    
    @Override
    public void onRender(final EventOnRender render) {
        if (!this.isEnabled()) {
            return;
        }
        GL11.glPushMatrix();
        RenderUtils.setup3DLightlessModel();
        GL11.glLineWidth(1.0f);
        GL11.glBegin(3);
        for (final Double[] pos : Resilience.getInstance().getValues().breadcrumbPosList) {
            GL11.glColor4f(0.0f, 0.0f, 1.0f, 0.7f);
            GL11.glVertex3d(pos[0] - this.invoker.getRenderPosX(), pos[1] - this.invoker.getRenderPosY() - this.invoker.getEntityHeight((Entity)Resilience.getInstance().getWrapper().getPlayer()), pos[2] - this.invoker.getRenderPosZ());
        }
        GL11.glEnd();
        RenderUtils.shutdown3DLightlessModel();
        GL11.glPopMatrix();
    }
    
    @Override
    public void onEnable() {
    }
    
    @Override
    public void onDisable() {
    }
}
