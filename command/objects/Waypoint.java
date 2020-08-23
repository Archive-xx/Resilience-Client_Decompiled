// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.objects;

import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.utilities.RenderUtils;
import com.krispdev.resilience.Resilience;
import java.util.ArrayList;
import java.util.List;
import com.krispdev.resilience.wrappers.MethodInvoker;

public class Waypoint
{
    private MethodInvoker invoker;
    public static List<Waypoint> waypointsList;
    private int x;
    private int y;
    private int z;
    private float r;
    private float g;
    private float b;
    String name;
    
    static {
        Waypoint.waypointsList = new ArrayList();
    }
    
    public Waypoint(final String name, final int x, final int y, final int z, final float r, final float b, final float g) {
        this.invoker = Resilience.getInstance().getInvoker();
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.g = g;
        this.b = b;
        this.name = name;
    }
    
    public void draw() {
        this.invoker.setEntityLight(false);
        RenderUtils.drawESP(false, this.x - this.invoker.getRenderPosX() - 0.5, this.y - this.invoker.getRenderPosY() - 0.5, this.z - this.invoker.getRenderPosZ() - 0.5, this.x - this.invoker.getRenderPosX() + 0.5, this.y - this.invoker.getRenderPosY() + 0.5, this.z - this.invoker.getRenderPosZ() + 0.5, this.r, this.g, this.b, 0.183, this.r, this.g, this.b, 1.0);
        final float f = 3.0f;
        final int color = -1;
        final float scale = f / 150.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.x - this.invoker.getRenderPosX()), (float)(this.y - this.invoker.getRenderPosY()) + 1.0f, (float)(this.z - this.invoker.getRenderPosZ()));
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-this.invoker.getPlayerViewY(), 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.invoker.getPlayerViewX(), 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-scale, -scale, scale);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        final byte byte0 = 0;
        final int i = (int)(Resilience.getInstance().getWaypointFont().getWidth(this.name) / 2.0f);
        Resilience.getInstance().getWaypointFont().drawString(this.name, -Resilience.getInstance().getWaypointFont().getWidth(this.name) / 2.0f, byte0, color);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glEnable(2896);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        try {
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.0f);
            GL11.glDisable(2896);
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(1.0f);
            final double posX = this.x - RenderManager.renderPosX;
            final double posY = this.y - RenderManager.renderPosY;
            final double posZ = this.z - RenderManager.renderPosZ;
            GL11.glColor4d((double)this.r, (double)this.g, (double)this.b, 1.0);
            GL11.glBegin(2);
            GL11.glVertex3d(0.0, 0.0, 0.0);
            GL11.glVertex3d(posX, posY, posZ);
            GL11.glEnd();
            GL11.glDisable(2848);
            GL11.glEnable(3553);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
        catch (Exception ex) {}
        this.invoker.setEntityLight(true);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getZ() {
        return this.z;
    }
    
    public float getR() {
        return this.r;
    }
    
    public float getG() {
        return this.g;
    }
    
    public float getB() {
        return this.b;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return "Name: §b" + this.name + " §fX: §b" + this.x + " §fY: §b" + this.y + " §fZ: §b" + this.z;
    }
}
