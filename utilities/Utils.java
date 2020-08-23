// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL15;
import org.lwjgl.BufferUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.util.AxisAlignedBB;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import com.krispdev.resilience.donate.Donator;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import com.mojang.authlib.exceptions.AuthenticationException;
import net.minecraft.util.Session;
import com.krispdev.resilience.Resilience;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.net.Proxy;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import java.nio.ByteBuffer;

public final class Utils
{
    private static ByteBuffer boxSides;
    private static int cube;
    
    public static final void drawRect(float par0, float par1, float par2, float par3, final int par4) {
        if (par0 < par2) {
            final float var5 = par0;
            par0 = par2;
            par2 = var5;
        }
        if (par1 < par3) {
            final float var5 = par1;
            par1 = par3;
            par3 = var5;
        }
        final float var6 = (par4 >> 24 & 0xFF) / 255.0f;
        final float var7 = (par4 >> 16 & 0xFF) / 255.0f;
        final float var8 = (par4 >> 8 & 0xFF) / 255.0f;
        final float var9 = (par4 & 0xFF) / 255.0f;
        final Tessellator var10 = Tessellator.instance;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(var7, var8, var9, var6);
        var10.startDrawingQuads();
        var10.addVertex((double)par0, (double)par3, 0.0);
        var10.addVertex((double)par2, (double)par3, 0.0);
        var10.addVertex((double)par2, (double)par1, 0.0);
        var10.addVertex((double)par0, (double)par1, 0.0);
        var10.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public static final void drawBetterRect(final double x, final double y, final double x1, final double y1, final int color2, final int color) {
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        drawRect((float)(int)x, (float)(int)y, (float)(int)x1, (float)(int)y1, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawRect((float)((int)x * 2 - 1), (float)((int)y * 2), (float)((int)x * 2), (float)((int)y1 * 2 - 1), color2);
        drawRect((float)((int)x * 2), (float)((int)y * 2 - 1), (float)((int)x1 * 2), (float)((int)y * 2), color2);
        drawRect((float)((int)x1 * 2), (float)((int)y * 2), (float)((int)x1 * 2 + 1), (float)((int)y1 * 2 - 1), color2);
        drawRect((float)((int)x * 2), (float)((int)y1 * 2 - 1), (float)((int)x1 * 2), (float)((int)y1 * 2), color2);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static final void drawSmallHL(final float x, final float y, final float x2, final int colour) {
        drawRect(x, y, x2, y + 0.5f, colour);
    }
    
    public static final void drawSmallVL(final float y, final float x, final float y2, final int colour) {
        drawRect(x, y, x + 0.5f, y2, colour);
    }
    
    public static final String setSessionData(final String user, final String pass) {
        final YggdrasilAuthenticationService authentication = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)authentication.createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(user);
        auth.setPassword(pass);
        try {
            auth.logIn();
            Resilience.getInstance().getWrapper().getMinecraft().session = new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId(), auth.getAuthenticatedToken());
        }
        catch (AuthenticationException e) {
            return "Error!";
        }
        return "";
    }
    
    public static final void drawItemTag(final int x, final int y, final ItemStack item) {
        GL11.glPushMatrix();
        GL11.glDisable(2929);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        Resilience.getInstance().getInvoker().enableStandardItemLighting();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(32826);
        Resilience.getInstance().getInvoker().renderItemIntoGUI(item, x, y);
        Resilience.getInstance().getInvoker().renderItemOverlayIntoGUI(item, x, y);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
        GL11.glEnable(2929);
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }
    
    public static final float sqrt_double(final double par0) {
        return (float)Math.sqrt(par0);
    }
    
    public static final double wrapAngleTo180_double(double par0) {
        par0 %= 360.0;
        if (par0 >= 180.0) {
            par0 -= 360.0;
        }
        if (par0 < -180.0) {
            par0 += 360.0;
        }
        return par0;
    }
    
    public static final void initDonators() {
        try {
            Donator.donatorList.clear();
            final URL url = new URL("http://resilience.krispdev.com/Rerererencedonatorsx789");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String temp;
            while ((temp = in.readLine()) != null) {
                final String[] args = temp.split("BITCHEZBECRAYCRAY123WAYOVER30CHAR");
                if (Float.parseFloat(args[2]) >= 5.0f) {
                    Donator.donatorList.add(new Donator(args[0], args[1], Float.parseFloat(args[2]), args[3]));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static AxisAlignedBB getAABB(final int x, final int y, final int z) {
        final Entity p = (Entity)Minecraft.getMinecraft().thePlayer;
        final double var8 = p.lastTickPosX + (p.posX - p.lastTickPosX);
        final double var9 = p.lastTickPosY + (p.posY - p.lastTickPosY);
        final double var10 = p.lastTickPosZ + (p.posZ - p.lastTickPosZ);
        final float var11 = 0.002f;
        final Block block = Minecraft.getMinecraft().theWorld.getBlock(x, y, z);
        return block.getSelectedBoundingBoxFromPool((World)Minecraft.getMinecraft().theWorld, x, y, z).expand(0.0020000000949949026, 0.0020000000949949026, 0.0020000000949949026).getOffsetBoundingBox(-var8, -var9, -var10);
    }
    
    public static ByteBuffer getSides() {
        (Utils.boxSides = BufferUtils.createByteBuffer(24)).put(new byte[] { 0, 3, 2, 1, 2, 5, 6, 1, 6, 7, 0, 1, 0, 7, 4, 3, 4, 7, 6, 5, 2, 3, 4, 5 });
        Utils.boxSides.flip();
        GL15.glBindBuffer(34962, Utils.cube = GL15.glGenBuffers());
        GL15.glBufferData(34962, getBox(-0.5f, -0.5f, -0.5f, 0.5f, 0.5f, 0.5f), 35044);
        GL15.glBindBuffer(34962, 0);
        return Utils.boxSides;
    }
    
    public static FloatBuffer getBox(final AxisAlignedBB bound) {
        return getBox((float)bound.minX, (float)bound.minY, (float)bound.minZ, (float)bound.maxX, (float)bound.maxY, (float)bound.maxZ);
    }
    
    public static FloatBuffer getBox(final float minX, final float minY, final float minZ, final float maxX, final float maxY, final float maxZ) {
        final FloatBuffer vertices = BufferUtils.createFloatBuffer(24);
        vertices.put(new float[] { minX, minY, minZ, maxX, minY, minZ, maxX, maxY, minZ, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, minY, maxZ, minX, minY, maxZ });
        vertices.flip();
        return vertices;
    }
}
