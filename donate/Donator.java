// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.donate;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Donator implements Comparable<Donator>
{
    public static List<Donator> donatorList;
    private String nick;
    private String message;
    private String ign;
    private float amount;
    
    static {
        Donator.donatorList = new ArrayList<Donator>();
    }
    
    public Donator(final String nick, final String message, final float amount, final String ign) {
        this.nick = nick;
        this.message = message;
        this.amount = amount;
        this.ign = ign;
    }
    
    public String getNickname() {
        return this.nick;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public float getAmount() {
        return this.amount;
    }
    
    public String getIGN() {
        return this.ign;
    }
    
    @Override
    public int compareTo(final Donator arg0) {
        return (int)(arg0.getAmount() - this.getAmount());
    }
    
    public static boolean isDonator(final String str, final float amount) {
        for (final Donator d : Donator.donatorList) {
            if (d.getIGN().equalsIgnoreCase(str) && d.getAmount() >= amount) {
                return true;
            }
        }
        return false;
    }
}
