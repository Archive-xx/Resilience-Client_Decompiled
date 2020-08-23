// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.utilities.XrayBlock;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdXrayAdd extends Command
{
    public CmdXrayAdd() {
        super("xray add ", "[Block ID]", "Adds the specified block to the xray list");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        if (!cmd.startsWith("xray add")) {
            return false;
        }
        final String[] args = cmd.split("add ");
        if (!this.containsId(Integer.parseInt(args[1]))) {
            Resilience.getInstance().getXrayUtils().xrayBlocks.add(new XrayBlock(Integer.parseInt(args[1])));
            Resilience.getInstance().getLogger().infoChat("Added block " + args[1] + " to the xray list");
            Resilience.getInstance().getFileManager().saveXrayBlocks(new String[0]);
            if (Resilience.getInstance().getXrayUtils().xrayEnabled) {
                Resilience.getInstance().getInvoker().loadRenderers();
            }
        }
        else {
            Resilience.getInstance().getLogger().warningChat("Block already on the xray list!");
        }
        return true;
    }
    
    public boolean containsId(final int id) {
        for (final XrayBlock block : Resilience.getInstance().getXrayUtils().xrayBlocks) {
            if (block.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
