// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.relations.Friend;
import com.krispdev.resilience.command.Command;

public class CmdFriendClear extends Command
{
    public CmdFriendClear() {
        super("friends clear", "", "Clears the friend list");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        Friend.friendList.clear();
        Resilience.getInstance().getFileManager().saveFriends(new String[0]);
        Resilience.getInstance().getLogger().infoChat("Cleared the friend list");
        return true;
    }
}
