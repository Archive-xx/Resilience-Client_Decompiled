// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.categories;

public enum ModuleCategory
{
    public static final ModuleCategory PLAYER;
    public static final ModuleCategory MOVEMENT;
    public static final ModuleCategory WORLD;
    public static final ModuleCategory COMBAT;
    public static final ModuleCategory RENDER;
    public static final ModuleCategory MISC;
    public static final ModuleCategory GUI;
    public static final ModuleCategory NONE;
    public static final ModuleCategory COMBAT_EXTENSION;
    
    static {
        ModuleCategory.PLAYER = new ModuleCategory("PLAYER", 0);
        ModuleCategory.MOVEMENT = new ModuleCategory("MOVEMENT", 1);
        ModuleCategory.WORLD = new ModuleCategory("WORLD", 2);
        ModuleCategory.COMBAT = new ModuleCategory("COMBAT", 3);
        ModuleCategory.RENDER = new ModuleCategory("RENDER", 4);
        ModuleCategory.MISC = new ModuleCategory("MISC", 5);
        ModuleCategory.GUI = new ModuleCategory("GUI", 6);
        ModuleCategory.NONE = new ModuleCategory("NONE", 7);
        ModuleCategory.COMBAT_EXTENSION = new ModuleCategory("COMBAT_EXTENSION", 8);
        ModuleCategory.ENUM$VALUES = new ModuleCategory[] { ModuleCategory.PLAYER, ModuleCategory.MOVEMENT, ModuleCategory.WORLD, ModuleCategory.COMBAT, ModuleCategory.RENDER, ModuleCategory.MISC, ModuleCategory.GUI, ModuleCategory.NONE, ModuleCategory.COMBAT_EXTENSION };
    }
    
    private ModuleCategory(final String s, final int n) {
    }
    
    public static ModuleCategory valueOf(final String s) {
        return Enum.valueOf(ModuleCategory.class, s);
    }
}
