// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.account;

import java.util.ArrayList;

public class Account
{
    public static ArrayList<Account> accountList;
    private String username;
    private String password;
    private boolean premium;
    
    static {
        Account.accountList = new ArrayList<Account>();
    }
    
    public Account(String username) {
        if (username.equalsIgnoreCase("krisp")) {
            username = String.valueOf(username) + "_NotRealKrisp";
        }
        this.username = username;
        this.password = "";
        this.premium = false;
    }
    
    public Account(String username, final String password) {
        if (username.equalsIgnoreCase("krisp")) {
            username = String.valueOf(username) + "_NotRealKrisp";
        }
        this.username = username;
        this.password = password;
        if (password != null && !password.equals("")) {
            this.premium = true;
        }
        else {
            this.premium = false;
        }
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public boolean isPremium() {
        return this.premium;
    }
}
