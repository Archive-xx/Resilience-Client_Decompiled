// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import java.util.Random;
import com.krispdev.resilience.command.Command;

public class CmdSearchAdd extends Command
{
    private Random rand;
    
    public CmdSearchAdd() {
        super("search add ", "[Block Id]", "Adds a block to the search list");
        this.rand = new Random();
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        final int r = this.rand.nextInt(100) + 1;
        final int g = this.rand.nextInt(100) + 1;
        final int b = this.rand.nextInt(100) + 1;
        if (!this.containsId((float)Integer.parseInt(cmd.split("add ")[1].trim()))) {
            Resilience.getInstance().getValues().searchIds.add(new Float[] { Float.parseFloat(cmd.split("add ")[1]), r / 100.0f, g / 100.0f, b / 100.0f });
            Resilience.getInstance().getValues().ticksForSearch = 71;
            Resilience.getInstance().getLogger().infoChat("Added a block with id " + cmd.split("add ")[1] + " to the search list");
        }
        else {
            Resilience.getInstance().getLogger().warningChat("Block already on the list!");
        }
        return true;
    }
    
    public boolean containsId(final float id) {
        for (final Float[] list : Resilience.getInstance().getValues().searchIds) {
            if (list[0] == id) {
                return true;
            }
        }
        return false;
    }
}
