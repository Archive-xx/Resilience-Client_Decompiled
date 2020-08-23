// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import net.minecraft.client.renderer.entity.RenderManager;
import com.krispdev.resilience.utilities.RenderUtils;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.event.events.player.EventOnRender;
import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.event.events.player.EventPostMotion;
import net.minecraft.item.ItemBow;
import com.krispdev.resilience.event.events.player.EventPreMotion;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.utilities.game.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleBowAimbot extends DefaultModule
{
    private float pitch;
    private float yaw;
    private EntityLivingBase e;
    private EntityUtils entityUtils;
    
    public ModuleBowAimbot() {
        super("BowAimbot", 0);
        this.entityUtils = new EntityUtils();
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically aims your bow at entities");
    }
    
    public void onPreMotion(final EventPreMotion event) {
        if (this.invoker.getCurrentItem() != null && this.invoker.getCurrentItem().getItem() instanceof ItemBow) {
            this.e = this.getCursorEntity();
            if (this.e == null) {
                return;
            }
            this.pitch = this.invoker.getRotationPitch();
            this.yaw = this.invoker.getRotationYaw();
            this.silentAim(this.e);
        }
    }
    
    public void onPostMotion(final EventPostMotion event) {
        if (this.e != null && this.invoker.getCurrentItem() != null && this.invoker.getCurrentItem().getItem() instanceof ItemBow) {
            this.invoker.setRotationPitch(this.pitch);
            this.invoker.setRotationYaw(this.yaw);
        }
    }
    
    public void silentAim(final EntityLivingBase e) {
        final int bowCurrentCharge = this.invoker.getItemInUseDuration();
        float velocity = bowCurrentCharge / 20.0f;
        velocity = (velocity * velocity + velocity * 2.0f) / 3.0f;
        if (velocity < 0.1) {
            return;
        }
        if (velocity > 1.0f) {
            velocity = 1.0f;
        }
        final double x = this.invoker.getPosX((Entity)e) - this.invoker.getPosX();
        final double z = this.invoker.getPosZ((Entity)e) - this.invoker.getPosZ();
        final double h = this.invoker.getPosY((Entity)e) + this.invoker.getEyeHeight((Entity)e) - (this.invoker.getPosY() + this.invoker.getEyeHeight());
        final double h2 = Math.sqrt(x * x + z * z);
        final double h3 = Math.sqrt(h2 * h2 + h * h);
        final float f = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
        final float traj = -this.getTrajAngleSolutionLow((float)h2, (float)h, velocity);
        this.invoker.setRotationPitch(traj);
        this.invoker.setRotationYaw(f);
    }
    
    private float getTrajAngleSolutionLow(final float d3, final float d1, final float velocity) {
        final float g = 0.006f;
        final float sqrt = velocity * velocity * velocity * velocity - g * (g * (d3 * d3) + 2.0f * d1 * (velocity * velocity));
        return (float)Math.toDegrees(Math.atan((velocity * velocity - Math.sqrt(sqrt)) / (g * d3)));
    }
    
    public EntityLivingBase getCursorEntity() {
        EntityLivingBase poorEntity = null;
        double distance = 1000.0;
        for (final Object o : this.invoker.getEntityList()) {
            if (!(o instanceof Entity)) {
                continue;
            }
            final Entity e = (Entity)o;
            if (!(e instanceof EntityLivingBase) || this.entityUtils.isThePlayer(e) || e.getDistanceToEntity((Entity)Resilience.getInstance().getWrapper().getPlayer()) > 140.0f || !this.invoker.canEntityBeSeen(e) || ((EntityLivingBase)e).deathTime > 0) {
                continue;
            }
            if (this.entityUtils.isEntityFriend(e)) {
                continue;
            }
            if (poorEntity == null) {
                poorEntity = (EntityLivingBase)e;
            }
            final double x = e.posX - this.invoker.getPosX();
            final double z = e.posZ - this.invoker.getPosY();
            final double h = this.invoker.getPosY() + this.invoker.getEyeHeight() - (e.posY + this.invoker.getEntityHeight(e));
            final double h2 = Math.sqrt(x * x + z * z);
            final float f = (float)(Math.atan2(z, x) * 180.0 / 3.141592653589793) - 90.0f;
            final float f2 = (float)(Math.atan2(h, h2) * 180.0 / 3.141592653589793);
            final double xdist = this.getDistanceBetweenAngles(f, this.invoker.getRotationYaw() % 360.0f);
            final double ydist = this.getDistanceBetweenAngles(f2, this.invoker.getRotationPitch() % 360.0f);
            final double dist = Math.sqrt(xdist * xdist + ydist * ydist);
            if (dist >= distance) {
                continue;
            }
            poorEntity = (EntityLivingBase)e;
            distance = dist;
        }
        return poorEntity;
    }
    
    private float getDistanceBetweenAngles(final float par1, final float par2) {
        float angle = Math.abs(par1 - par2) % 360.0f;
        if (angle > 180.0f) {
            angle = 360.0f - angle;
        }
        return angle;
    }
    
    public void onRender(final EventOnRender event) {
        if (this.e != null && this.isEnabled()) {
            GL11.glPushMatrix();
            RenderUtils.setup3DLightlessModel();
            final double posX = this.e.lastTickPosX + (this.e.posX - this.e.lastTickPosX) - RenderManager.renderPosX;
            final double posY = this.e.lastTickPosY + 1.0 + (this.e.posY - this.e.lastTickPosY) - RenderManager.renderPosY;
            final double posZ = this.e.lastTickPosZ + (this.e.posZ - this.e.lastTickPosZ) - RenderManager.renderPosZ;
            GL11.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
            GL11.glBegin(2);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(posX, posY, posZ);
            GL11.glEnd();
            RenderUtils.shutdown3DLightlessModel();
            GL11.glPopMatrix();
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
