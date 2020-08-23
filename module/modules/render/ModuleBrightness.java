// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.event.events.player.EventGameShutdown;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleBrightness extends DefaultModule
{
    private float prevGammaSetting;
    private float target;
    private float fadeSpeed;
    private boolean shouldFadeOut;
    
    public ModuleBrightness() {
        super("Brightness", 48);
        this.prevGammaSetting = -1.0f;
        this.target = 8.0f;
        this.fadeSpeed = 0.1f;
        this.shouldFadeOut = false;
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Lights up the world");
    }
    
    @Override
    public void onEnable() {
        if (Resilience.getInstance().getWrapper().getWorld() != null && Resilience.getInstance().getWrapper().getPlayer() != null) {
            this.prevGammaSetting = this.invoker.getGammaSetting();
            this.shouldFadeOut = false;
        }
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        this.fadeSpeed = 0.3f;
        float gammaSetting = this.invoker.getGammaSetting();
        if (gammaSetting < this.target && !this.shouldFadeOut) {
            if (gammaSetting < 1.0f) {
                gammaSetting = 1.0f;
            }
            this.invoker.setGammaSetting(gammaSetting + this.fadeSpeed);
        }
        if (this.shouldFadeOut) {
            if (gammaSetting > 1.0f) {
                if (gammaSetting - this.fadeSpeed < 1.0f) {
                    this.invoker.setGammaSetting(1.0f);
                    Resilience.getInstance().getEventManager().unregister((Listener)this);
                    this.shouldFadeOut = false;
                    return;
                }
                this.invoker.setGammaSetting(gammaSetting - this.fadeSpeed);
            }
            else {
                this.shouldFadeOut = false;
                Resilience.getInstance().getEventManager().unregister((Listener)this);
            }
        }
    }
    
    @Override
    public void onDisable() {
        this.shouldFadeOut = true;
    }
    
    @Override
    public void onGameShutdown(final EventGameShutdown event) {
        if (this.prevGammaSetting != -1.0f) {
            this.invoker.setGammaSetting(this.prevGammaSetting);
        }
    }
}
