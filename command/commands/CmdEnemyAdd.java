// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.relations.Enemy;
import com.krispdev.resilience.relations.EnemyManager;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.relations.Friend;
import com.krispdev.resilience.command.Command;

public class CmdEnemyAdd extends Command
{
    public CmdEnemyAdd() {
        super("enemy add ", "[Username]", "Adds an enemy to the enemy list");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("add ");
        for (final Friend friend : Friend.friendList) {
            if (friend.getName().equalsIgnoreCase(args[1])) {
                Friend.friendList.remove(Friend.friendList.indexOf(friend));
                Resilience.getInstance().getFileManager().saveFriends(new String[0]);
                break;
            }
        }
        if (!EnemyManager.isEnemy(args[1])) {
            Enemy.enemyList.add(new Enemy(args[1]));
            Resilience.getInstance().getFileManager().saveEnemies(new String[0]);
            Resilience.getInstance().getLogger().infoChat("User added to the enemy list");
            return true;
        }
        Resilience.getInstance().getLogger().warningChat("§cError! §fUser already on the enemy list");
        return true;
    }
}
