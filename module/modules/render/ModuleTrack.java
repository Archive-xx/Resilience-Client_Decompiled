// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.utilities.RenderUtils;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.event.events.player.EventOnRender;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.Resilience;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleTrack extends DefaultModule
{
    public ModuleTrack() {
        super("Track", 0);
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Draws a line behind the specified player");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        for (final Object o : this.invoker.getEntityList()) {
            if (o instanceof EntityOtherPlayerMP) {
                final EntityOtherPlayerMP player = (EntityOtherPlayerMP)o;
                if (!this.invoker.getPlayerName((EntityPlayer)player).equalsIgnoreCase(Resilience.getInstance().getValues().trackName)) {
                    continue;
                }
                Resilience.getInstance().getValues().trackPosList.add(new Double[] { this.invoker.getPosX((Entity)player), this.invoker.getPosY((Entity)player), this.invoker.getPosZ((Entity)player) });
            }
        }
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        GL11.glPushMatrix();
        RenderUtils.setup3DLightlessModel();
        GL11.glBegin(3);
        for (final Double[] pos : Resilience.getInstance().getValues().trackPosList) {
            GL11.glColor4f(1.0f, 0.0f, 0.0f, 0.7f);
            GL11.glVertex3d(pos[0] - this.invoker.getRenderPosX(), pos[1] - this.invoker.getRenderPosY(), pos[2] - this.invoker.getRenderPosZ());
        }
        GL11.glEnd();
        RenderUtils.shutdown3DLightlessModel();
        GL11.glPopMatrix();
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
