// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import java.util.Iterator;
import com.krispdev.resilience.relations.EnemyManager;
import com.krispdev.resilience.relations.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import net.minecraft.entity.Entity;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.Utils;

public class GuiRadarPanel extends DefaultPanel
{
    private int length;
    
    public GuiRadarPanel(final String title, final int x, final int y, final int x1, final int y1, final boolean visible) {
        super(title, x, y, x1, y1, visible);
        this.length = 112;
    }
    
    public void draw(final int i, final int j) {
        this.length = 133;
        super.draw(i, j);
        if (this.isExtended()) {
            Utils.drawRect((float)this.getX(), (float)(this.getY() + 17), (float)this.getX1(), (float)(this.getY() + this.length), -1727790076);
            Utils.drawRect((float)(this.getX() + this.length / 2 - 1), (float)(this.getY() + this.length / 2 - 1 + 8 + 1), (float)(this.getX() + this.length / 2 - 1), (float)(this.getY() + (this.length / 2 + 1) + 8 + 1), -6710785);
            Utils.drawSmallHL(this.getX() + 0.5f, (float)(this.getY() + this.length / 2 + 8), this.getX() + this.length - 0.5f, 1155851492);
            Utils.drawSmallVL((float)(this.getY() + this.length), (float)(this.getX() + this.length / 2), this.getY() + 17.5f, 1155851492);
            Utils.drawSmallHL((float)this.getX(), (float)(this.getY() + 17), (float)(this.getX() + this.length), 1155851492);
            Utils.drawSmallVL((float)(this.getY() + this.length), (float)this.getX(), this.getY() + 17.5f, 1155851492);
            Utils.drawSmallHL((float)this.getX(), (float)(this.getY() + this.length), (float)(this.getX() + this.length), 1155851492);
            Utils.drawSmallVL((float)(this.getY() + this.length), this.getX() + this.length - 0.5f, this.getY() + 17.5f, 1155851492);
            for (final Object o : Resilience.getInstance().getInvoker().getEntityList()) {
                if (o instanceof EntityOtherPlayerMP) {
                    final EntityOtherPlayerMP entity = (EntityOtherPlayerMP)o;
                    final double diffX = Resilience.getInstance().getInvoker().getPosX() - Resilience.getInstance().getInvoker().getPosX((Entity)entity);
                    final double diffZ = Resilience.getInstance().getInvoker().getPosZ() - Resilience.getInstance().getInvoker().getPosZ((Entity)entity);
                    final double xzDiff = Math.sqrt(diffX * diffX + diffZ * diffZ);
                    final double angleDiff = Utils.wrapAngleTo180_double(Resilience.getInstance().getInvoker().getRotationYaw() - Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793);
                    final double finalX = Math.cos(Math.toRadians(angleDiff)) * xzDiff * 2.0;
                    final double finalY = -Math.sin(Math.toRadians(angleDiff)) * xzDiff * 2.0;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)(this.getX() + this.length / 2), (float)(this.getY() + this.length / 2 + 8), 0.0f);
                    if (xzDiff < 55.0 && !Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)entity).equalsIgnoreCase(Resilience.getInstance().getInvoker().getSessionUsername())) {
                        Resilience.getInstance().getRadarFont().drawCenteredString(String.valueOf(Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)entity)) + " §f[§b" + (int)Resilience.getInstance().getInvoker().getDistanceToEntity((Entity)Resilience.getInstance().getWrapper().getPlayer(), (Entity)entity) + "§f]", (float)finalX / 2.0f, (float)finalY / 2.0f + 2.0f, FriendManager.isFriend(Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)entity)) ? -11184641 : (EnemyManager.isEnemy(Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)entity)) ? -65536 : -1));
                        Utils.drawRect((float)finalX / 2.0f, (float)finalY / 2.0f, 2.0f + (float)finalX / 2.0f, 2.0f + (float)finalY / 2.0f, FriendManager.isFriend(Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)entity)) ? -12303207 : (EnemyManager.isEnemy(Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)entity)) ? -65536 : -1));
                        GL11.glScalef(0.5f, 0.5f, 0.5f);
                        final EntityPlayer p = (EntityPlayer)entity;
                        GL11.glScalef(1.0f, 0.5f, 1.0f);
                    }
                    GL11.glPopMatrix();
                }
            }
        }
    }
}
