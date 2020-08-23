// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.game.EntityUtils;
import com.krispdev.resilience.wrappers.MethodInvoker;

public final class RenderUtils
{
    private static MethodInvoker invoker;
    private static EntityUtils entityUtils;
    
    static {
        RenderUtils.invoker = Resilience.getInstance().getInvoker();
        RenderUtils.entityUtils = new EntityUtils();
    }
    
    public static void setup3DLightlessModel() {
        Resilience.getInstance().getInvoker().setEntityLight(false);
        GL11.glEnable(3042);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
    }
    
    public static void shutdown3DLightlessModel() {
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        Resilience.getInstance().getInvoker().setEntityLight(true);
    }
    
    public static void drawOutlinedCrossedBoundingBox(final AxisAlignedBB aa) {
        final Tessellator t = Tessellator.instance;
        t.startDrawing(3);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.draw();
        t.startDrawing(3);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.draw();
        t.startDrawing(1);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.draw();
        t.startDrawing(1);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.draw();
    }
    
    public static void drawOutlinedBoundingBox(final AxisAlignedBB aa) {
        final Tessellator t = Tessellator.instance;
        t.startDrawing(3);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.draw();
        t.startDrawing(3);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.draw();
        t.startDrawing(1);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.draw();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB aa) {
        final Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.draw();
        t.startDrawingQuads();
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.draw();
        t.startDrawingQuads();
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.draw();
        t.startDrawingQuads();
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.draw();
        t.startDrawingQuads();
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.draw();
        t.startDrawingQuads();
        t.addVertex(aa.minX, aa.maxY, aa.maxZ);
        t.addVertex(aa.minX, aa.minY, aa.maxZ);
        t.addVertex(aa.minX, aa.maxY, aa.minZ);
        t.addVertex(aa.minX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.minZ);
        t.addVertex(aa.maxX, aa.minY, aa.minZ);
        t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
        t.addVertex(aa.maxX, aa.minY, aa.maxZ);
        t.draw();
    }
    
    public static void drawESP(final boolean crossed, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ, final double r, final double g, final double b, final double a, final double r2, final double g2, final double b2, final double a2) {
        GL11.glPushMatrix();
        setup3DLightlessModel();
        RenderUtils.invoker.setEntityLight(false);
        GL11.glColor4d(r, g, b, a);
        drawBoundingBox(new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ));
        GL11.glColor4d(r2, g2, b2, a2);
        GL11.glLineWidth(0.5f);
        if (crossed) {
            drawOutlinedCrossedBoundingBox(new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ));
        }
        else {
            drawOutlinedBoundingBox(new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ));
        }
        RenderUtils.invoker.setEntityLight(true);
        shutdown3DLightlessModel();
        GL11.glPopMatrix();
    }
    
    public static void drawPlayerESP(final EntityPlayer player) {
        if (RenderUtils.entityUtils.isThePlayer((Entity)player)) {
            return;
        }
        final boolean friend = RenderUtils.entityUtils.isEntityFriend((Entity)player);
        final boolean enemy = RenderUtils.entityUtils.isEntityEnemy((Entity)player);
        drawESP(true, player.boundingBox.minX - RenderUtils.invoker.getRenderPosX() - 0.1, player.boundingBox.minY - RenderUtils.invoker.getRenderPosY(), player.boundingBox.minZ - RenderUtils.invoker.getRenderPosZ() - 0.1, player.boundingBox.maxX - RenderUtils.invoker.getRenderPosX() + 0.1, player.boundingBox.maxY + 0.15 - RenderUtils.invoker.getRenderPosY(), player.boundingBox.maxZ - RenderUtils.invoker.getRenderPosZ() + 0.1, friend ? 0.2 : ((double)(enemy ? 1 : 1)), (friend || enemy) ? 0.2 : 1.0, enemy ? 0.2 : 1.0, 0.19, friend ? 0.5 : ((double)(enemy ? 1 : 1)), (friend || enemy) ? 0.5 : 1.0, enemy ? 0.5 : 1.0, 1.0);
    }
    
    public static void drawTracer(final double bX, final double bY, final double bZ, final double eX, final double eY, final double eZ, final double r, final double g, final double b, final double alpha) {
        GL11.glPushMatrix();
        setup3DLightlessModel();
        GL11.glEnable(2848);
        GL11.glColor4d(r, g, b, alpha);
        GL11.glLineWidth(1.0f);
        GL11.glBegin(2);
        GL11.glVertex3d(bX, bY, bZ);
        GL11.glVertex3d(eX, eY, eZ);
        GL11.glEnd();
        shutdown3DLightlessModel();
        GL11.glPopMatrix();
    }
}
