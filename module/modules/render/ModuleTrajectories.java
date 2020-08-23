// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.event.listeners.Listener;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.Item;
import java.util.Iterator;
import com.krispdev.resilience.utilities.Utils;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.event.events.player.EventOnRender;
import net.minecraft.util.Vec3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemBow;
import net.minecraft.util.MathHelper;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import net.minecraft.util.MovingObjectPosition;
import java.util.ArrayList;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleTrajectories extends DefaultModule
{
    private ArrayList<Double[]> linePoints;
    private MovingObjectPosition hit;
    
    public ModuleTrajectories() {
        super("Trajectories", 0);
        this.linePoints = new ArrayList<Double[]>();
        this.hit = null;
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Draws a line showing the path of a projectile");
        this.setSave(false);
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        this.linePoints.clear();
        final EntityLivingBase p = (EntityLivingBase)Resilience.getInstance().getWrapper().getMinecraft().thePlayer;
        if (Resilience.getInstance().getWrapper().getMinecraft().thePlayer.getCurrentEquippedItem() != null && this.isValidItem(Resilience.getInstance().getWrapper().getMinecraft().thePlayer.getCurrentEquippedItem().getItem())) {
            double x = p.lastTickPosX + (p.posX - p.lastTickPosX) - MathHelper.cos((float)Math.toRadians(p.rotationYaw)) * 0.16f;
            double y = p.lastTickPosY + (p.posY - p.lastTickPosY) + p.getEyeHeight() - 0.100149011612;
            double z = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) - MathHelper.sin((float)Math.toRadians(p.rotationYaw)) * 0.16f;
            float con = 1.0f;
            if (!(Resilience.getInstance().getWrapper().getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow)) {
                con = 0.4f;
            }
            double motionX = -MathHelper.sin((float)Math.toRadians(p.rotationYaw)) * MathHelper.cos((float)Math.toRadians(p.rotationPitch)) * con;
            double motionZ = MathHelper.cos((float)Math.toRadians(p.rotationYaw)) * MathHelper.cos((float)Math.toRadians(p.rotationPitch)) * con;
            double motionY = -MathHelper.sin((float)Math.toRadians(p.rotationPitch)) * con;
            final double ssum = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
            motionX /= ssum;
            motionY /= ssum;
            motionZ /= ssum;
            if (Resilience.getInstance().getWrapper().getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow) {
                float pow = (72000 - Resilience.getInstance().getWrapper().getMinecraft().thePlayer.getItemInUseCount()) / 20.0f;
                pow = (pow * pow + pow * 2.0f) / 3.0f;
                if (pow > 1.0f) {
                    pow = 1.0f;
                }
                if (pow <= 0.1f) {
                    pow = 1.0f;
                }
                pow *= 2.0f;
                pow *= 1.5f;
                motionX *= pow;
                motionY *= pow;
                motionZ *= pow;
            }
            else {
                motionX *= 1.5;
                motionY *= 1.5;
                motionZ *= 1.5;
            }
            boolean hasHitBlock = false;
            final double grav = this.getGravity(this.invoker.getCurrentItem().getItem());
            double lastX = x;
            double lastY = y;
            double lastZ = z;
            final boolean entity = false;
            int q = 0;
            while (!hasHitBlock) {
                lastX = x;
                lastY = y;
                lastZ = z;
                final double rx = x * 1.0 - RenderManager.renderPosX;
                final double ry = y * 1.0 - RenderManager.renderPosY;
                final double rz = z * 1.0 - RenderManager.renderPosZ;
                this.linePoints.add(new Double[] { rx, ry, rz });
                x += motionX;
                y += motionY - 0.05;
                z += motionZ;
                motionX *= 0.99;
                motionY *= 0.99;
                motionZ *= 0.99;
                motionY -= grav;
                final Vec3 now = Resilience.getInstance().getWrapper().getMinecraft().theWorld.getWorldVec3Pool().getVecFromPool(lastX, lastY, lastZ);
                final Vec3 after = Resilience.getInstance().getWrapper().getMinecraft().theWorld.getWorldVec3Pool().getVecFromPool(x, y, z);
                final MovingObjectPosition possibleHit = Resilience.getInstance().getWrapper().getWorld().func_147447_a(now, after, false, true, false);
                hasHitBlock = (possibleHit != null);
                if (hasHitBlock) {
                    this.hit = possibleHit;
                }
                ++q;
            }
        }
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(3.0f);
        GL11.glColor4f(0.2f, 0.2f, 1.0f, 1.0f);
        GL11.glBegin(3);
        for (final Double[] vertex : this.linePoints) {
            GL11.glVertex3d((double)vertex[0], (double)vertex[1], (double)vertex[2]);
        }
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
        if (this.hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glEnable(2848);
            GL11.glDisable(2929);
            GL11.glDisable(2896);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.19f);
            GL11.glEnableClientState(32884);
            GL11.glVertexPointer(3, 0, Utils.getBox(Utils.getAABB(this.hit.blockX, this.hit.blockY, this.hit.blockZ)));
            GL11.glDrawElements(7, Utils.getSides());
            GL11.glLineWidth(1.0f);
            GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
            GL11.glDrawElements(1, Utils.getSides());
            GL11.glDisableClientState(32884);
            GL11.glDisable(3042);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(2848);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glPopMatrix();
        }
    }
    
    public double getGravity(final Item item) {
        if (item instanceof ItemBow) {
            return 0.05;
        }
        return 0.03;
    }
    
    public boolean isValidItem(final Item item) {
        return item instanceof ItemBow || item instanceof ItemSnowball || item instanceof ItemEgg || item instanceof ItemEnderPearl;
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
