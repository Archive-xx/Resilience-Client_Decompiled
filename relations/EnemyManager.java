// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.relations;

import java.util.Iterator;
import net.minecraft.util.StringUtils;

public class EnemyManager
{
    public static boolean isEnemy(final String username) {
        for (final Enemy friend : Enemy.enemyList) {
            if (friend.getName().trim().equalsIgnoreCase(StringUtils.stripControlCodes(username.trim()))) {
                return true;
            }
        }
        return false;
    }
}
