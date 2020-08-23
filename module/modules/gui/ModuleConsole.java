// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.gui;

import net.minecraft.client.gui.GuiScreen;
import com.krispdev.resilience.gui.screens.ResilienceConsole;
import net.minecraft.client.gui.GuiChat;
import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleConsole extends DefaultModule
{
    public ModuleConsole() {
        super("Console", 12);
        this.setCategory(ModuleCategory.GUI);
        this.setDescription("Type commands and make the client do stuff");
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onUpdate(final EventOnUpdate event) {
        if (Keyboard.isKeyDown(this.getKeyCode()) && !(this.invoker.getCurrentScreen() instanceof GuiChat)) {
            this.invoker.displayScreen((GuiScreen)new ResilienceConsole());
        }
    }
    
    public void onEnable() {
    }
    
    public void onDisable() {
    }
}
