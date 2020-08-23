// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.Command;

public class CmdSay extends Command
{
    public CmdSay() {
        super("say ", "[Msg]", "Sends a message to the server, eg. \".help\"");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("say ");
        Resilience.getInstance().getInvoker().sendPacket((Packet)new C01PacketChatMessage(args[1]));
        return true;
    }
}
