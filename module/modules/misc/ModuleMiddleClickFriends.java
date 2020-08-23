// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.event.listeners.Listener;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.relations.Enemy;
import com.krispdev.resilience.Resilience;
import net.minecraft.entity.player.EntityPlayer;
import com.krispdev.resilience.relations.Friend;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import com.krispdev.resilience.event.events.player.EventOnClick;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleMiddleClickFriends extends DefaultModule
{
    public ModuleMiddleClickFriends() {
        super("Middle Click Friends", 0);
        this.setVisible(false);
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Adds the person you middle click on to your friends list");
    }
    
    @Override
    public void onClick(final EventOnClick event) {
        if (event.getButton() == 2) {
            final MovingObjectPosition obj = this.invoker.getObjectMouseOver();
            if (obj != null && obj.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
                final Entity e = obj.entityHit;
                if (e instanceof EntityOtherPlayerMP) {
                    final EntityOtherPlayerMP player = (EntityOtherPlayerMP)e;
                    for (final Friend friend : Friend.friendList) {
                        if (friend.getName().equalsIgnoreCase(this.invoker.getPlayerName((EntityPlayer)player))) {
                            Friend.friendList.remove(Friend.friendList.indexOf(friend));
                            Resilience.getInstance().getFileManager().saveFriends(new String[0]);
                            return;
                        }
                    }
                    for (final Enemy enemy : Enemy.enemyList) {
                        if (enemy.getName().equalsIgnoreCase(this.invoker.getPlayerName((EntityPlayer)player))) {
                            Enemy.enemyList.remove(Enemy.enemyList.indexOf(enemy));
                            Resilience.getInstance().getFileManager().saveEnemies(new String[0]);
                            break;
                        }
                    }
                    Friend.friendList.add(new Friend(this.invoker.getPlayerName((EntityPlayer)player), this.invoker.getPlayerName((EntityPlayer)player)));
                    Resilience.getInstance().getFileManager().saveFriends(new String[0]);
                }
            }
        }
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
