// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.net.InetAddress;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.multiplayer.ServerAddress;
import com.krispdev.resilience.command.Command;

public class CmdGetIP extends Command
{
    public CmdGetIP() {
        super("getip", "", "Copy's the server IP to your clipboard as well as displaying it");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        if (this.mc.currentServerData != null) {
            final ServerAddress serverAddress = new ServerAddress(this.mc.currentServerData.serverIP, this.mc.currentServerData.field_82821_f);
            Resilience.getInstance().getLogger().infoChat("Server IP §b" + InetAddress.getByName(serverAddress.getIP()).getHostAddress() + " §f(§b" + this.mc.currentServerData.serverIP + "§f)" + " has been copied to you clipboard.");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(InetAddress.getByName(serverAddress.getIP()).getHostAddress()), null);
        }
        else {
            Resilience.getInstance().getLogger().warningChat("Error, Server not found!");
        }
        return true;
    }
}
