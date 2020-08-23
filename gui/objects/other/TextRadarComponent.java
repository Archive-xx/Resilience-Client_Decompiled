// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.other;

import com.krispdev.resilience.gui.objects.screens.DefaultPanel;
import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.relations.FriendManager;
import com.krispdev.resilience.utilities.Utils;
import org.apache.commons.lang3.StringUtils;
import com.krispdev.resilience.relations.Friend;
import java.util.ArrayList;
import com.krispdev.resilience.gui.objects.screens.TextRadarPanel;
import java.util.List;

public class TextRadarComponent implements Comparable<TextRadarComponent>
{
    public static List<TextRadarComponent> players;
    private String name;
    private int blocksAway;
    private float posX;
    private float yPos;
    private boolean isFriend;
    private String tempName;
    private TextRadarPanel panel;
    
    static {
        TextRadarComponent.players = new ArrayList<TextRadarComponent>();
    }
    
    public TextRadarComponent(final String name, final int blocksAway, final int x, final int y, final boolean isFriend, final TextRadarPanel panel) {
        this.tempName = this.name;
        this.name = name;
        this.tempName = name;
        this.blocksAway = blocksAway;
        this.posX = (float)x;
        this.yPos = (float)y;
        this.panel = panel;
    }
    
    public void draw(final int x, final int y) {
        for (final Friend friend : Friend.friendList) {
            if (StringUtils.containsIgnoreCase((CharSequence)this.tempName, (CharSequence)friend.getName())) {
                this.tempName = this.tempName.replaceAll("(?i)" + friend.getName(), friend.getAlias());
            }
        }
        Utils.drawBetterRect((double)this.posX, (double)this.yPos, (double)(this.posX + 104.0f), (double)(this.yPos + 13.0f), -1157562111, -1155127770);
        if (FriendManager.isFriend(this.name)) {
            Resilience.getInstance().getStandardFont().drawString(this.tempName, (float)((int)this.posX + 2), (int)this.yPos + 0.5f, -11141121);
        }
        else {
            Resilience.getInstance().getStandardFont().drawString(this.name, (float)((int)this.posX + 2), (int)this.yPos + 0.5f, -1);
        }
        Utils.drawBetterRect((double)(this.posX + 91.0f), (double)(this.yPos + 1.0f), this.posX + 103.5, this.yPos + 12.5, -16777216, FriendManager.isFriend(this.name) ? -1152627636 : -1155127770);
        if (FriendManager.isFriend(this.name)) {
            Resilience.getInstance().getStandardFont().drawString("F", (int)this.posX + 94.5f, (float)((int)this.yPos + 1), -11141121);
        }
        else {
            Resilience.getInstance().getStandardFont().drawString("F", (int)this.posX + 94.5f, (float)((int)this.yPos + 1), -1);
        }
        Resilience.getInstance().getStandardFont().drawString(new StringBuilder().append(this.blocksAway).toString(), (int)this.posX + 88.5f - Resilience.getInstance().getStandardFont().getWidth(new StringBuilder().append(this.blocksAway).toString()), (float)((int)this.yPos + 1), -1);
    }
    
    public void mouseClicked(final int x, final int y, final int b) {
        if (x >= this.posX + 91.0f && x <= this.posX + 103.5 && y >= this.yPos + 1.0f && y <= this.yPos + 12.5) {
            Resilience.getInstance().getClickGui().focusWindow((DefaultPanel)this.panel);
            if (FriendManager.isFriend(this.name)) {
                for (final Friend friend : Friend.friendList) {
                    if (friend.getName().trim().equalsIgnoreCase(this.name) || friend.getAlias().trim().equalsIgnoreCase(this.name)) {
                        Friend.friendList.remove(Friend.friendList.indexOf(friend));
                        Resilience.getInstance().getFileManager().saveFriends(new String[0]);
                    }
                }
            }
            else {
                Friend.friendList.add(new Friend(this.name, this.name));
            }
        }
    }
    
    public int getBlocksAway() {
        return this.blocksAway;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public int compareTo(final TextRadarComponent arg0) {
        return this.blocksAway - arg0.blocksAway;
    }
}
