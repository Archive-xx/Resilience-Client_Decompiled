// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.relations.Enemy;
import com.krispdev.resilience.command.Command;

public class CmdEnemyDel extends Command
{
    public CmdEnemyDel() {
        super("enemy del ", "[Username]", "Deletes an enemy from the enemy list");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("del ");
        for (final Enemy enemy : Enemy.enemyList) {
            if (enemy.getName().trim().equalsIgnoreCase(args[1])) {
                Enemy.enemyList.remove(Enemy.enemyList.indexOf(enemy));
                Resilience.getInstance().getFileManager().saveEnemies(new String[0]);
                Resilience.getInstance().getLogger().infoChat("Deleted " + args[1] + " from the enemy list");
                return true;
            }
        }
        Resilience.getInstance().getLogger().warningChat("User not found!");
        return true;
    }
}
