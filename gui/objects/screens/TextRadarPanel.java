// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import java.util.Iterator;
import com.krispdev.resilience.utilities.Utils;
import java.util.List;
import java.util.Collections;
import com.krispdev.resilience.relations.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.gui.objects.other.TextRadarComponent;
import java.util.ArrayList;

public class TextRadarPanel extends DefaultPanel
{
    private int count;
    ArrayList<TextRadarComponent> inOrder;
    
    public TextRadarPanel(final String title, final int x, final int y, final int x1, final int y1, final boolean visible) {
        super(title, x, y, x1, y1, visible);
        this.count = 17;
        this.inOrder = new ArrayList();
    }
    
    public void draw(final int i, final int j) {
        TextRadarComponent.players.clear();
        this.inOrder.clear();
        super.draw(i, j);
        for (final Object o : Resilience.getInstance().getInvoker().getEntityList()) {
            if (((Entity)o) instanceof EntityOtherPlayerMP) {
                final EntityOtherPlayerMP otherPlayer = (EntityOtherPlayerMP)o;
                if (Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)otherPlayer).equalsIgnoreCase(Resilience.getInstance().getInvoker().getSessionUsername())) {
                    continue;
                }
                this.inOrder.add(new TextRadarComponent(Resilience.getInstance().getInvoker().stripControlCodes(Resilience.getInstance().getInvoker().getPlayerName((EntityPlayer)otherPlayer)), (int)Resilience.getInstance().getInvoker().getDistanceToEntity((Entity)otherPlayer, (Entity)Resilience.getInstance().getWrapper().getPlayer()), this.getX() + 3, this.getY() + this.count, FriendManager.isFriend(otherPlayer.func_145748_c_().getUnformattedText()), this));
            }
        }
        Collections.sort((List<Comparable>)this.inOrder);
        for (final TextRadarComponent rad : this.inOrder) {
            TextRadarComponent.players.add(new TextRadarComponent(rad.getName(), rad.getBlocksAway(), this.getX() + 3, this.count + this.getY() + 4, FriendManager.isFriend(rad.getName()), this));
            this.count += 15;
        }
        if (this.inOrder.size() != 0 && this.isExtended()) {
            Utils.drawRect((float)this.getX(), (float)(this.getY() + 17), (float)this.getX1(), this.getY() + (15 * this.inOrder.size() + 20.5f), -1727790076);
        }
        for (final TextRadarComponent radar1 : TextRadarComponent.players) {
            if (this.isExtended()) {
                radar1.draw(i, j);
            }
        }
        this.count = 16;
    }
    
    public void onClick(final int i, final int j, final int k) {
        super.onClick(i, j, k);
        for (final TextRadarComponent radar : TextRadarComponent.players) {
            radar.mouseClicked(i, j, k);
        }
    }
}
