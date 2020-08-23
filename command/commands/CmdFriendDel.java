// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.relations.Friend;
import com.krispdev.resilience.command.Command;

public class CmdFriendDel extends Command
{
    public CmdFriendDel() {
        super("friend del ", "[Username/Alias]", "Deletes the specified user from the friend list");
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("del ");
        for (final Friend friend : Friend.friendList) {
            if (friend.getName().trim().equalsIgnoreCase(args[1]) || friend.getAlias().trim().equalsIgnoreCase(args[1])) {
                Friend.friendList.remove(Friend.friendList.indexOf(friend));
                Resilience.getInstance().getLogger().infoChat("User deleted from the friend list");
                return true;
            }
        }
        Resilience.getInstance().getLogger().warningChat("User not found");
        return true;
    }
}
