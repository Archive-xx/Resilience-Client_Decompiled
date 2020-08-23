// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.relations;

import java.util.ArrayList;

public class Enemy
{
    public static ArrayList<Enemy> enemyList;
    private String name;
    
    static {
        Enemy.enemyList = new ArrayList();
    }
    
    public Enemy(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
