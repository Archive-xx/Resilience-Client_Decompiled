// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.relations;

import java.util.ArrayList;

public class Friend
{
    public static ArrayList<Friend> friendList;
    private String name;
    private String alias;
    
    static {
        Friend.friendList = new ArrayList();
    }
    
    public Friend(final String name, final String alias) {
        this.name = name;
        this.alias = alias;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(final String alias) {
        this.alias = alias;
    }
}
