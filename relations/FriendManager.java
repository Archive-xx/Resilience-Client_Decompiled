// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.relations;

import java.util.Iterator;
import net.minecraft.util.StringUtils;

public class FriendManager
{
    public static boolean isFriend(final String username) {
        for (final Friend friend : Friend.friendList) {
            if (friend.getName().trim().equalsIgnoreCase(StringUtils.stripControlCodes(username.trim()))) {
                return true;
            }
        }
        return false;
    }
}
