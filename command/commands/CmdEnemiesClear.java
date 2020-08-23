// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.relations.Enemy;
import com.krispdev.resilience.command.Command;

public class CmdEnemiesClear extends Command
{
    public CmdEnemiesClear() {
        super("enemies clear", "", "Clears the enemy list");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        Enemy.enemyList.clear();
        Resilience.getInstance().getLogger().infoChat("Cleared the enemy list");
        return true;
    }
}
