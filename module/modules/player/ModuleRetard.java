// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.player;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventPreMotion;
import com.krispdev.resilience.module.categories.ModuleCategory;
import java.util.Random;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleRetard extends DefaultModule
{
    private Random rand;
    private int ticks;
    
    public ModuleRetard() {
        super("Retard", 0);
        this.rand = new Random();
        this.ticks = 0;
        this.setCategory(ModuleCategory.PLAYER);
        this.setDescription("Spinns ur hed arund n mackes u luk funy");
    }
    
    @Override
    public void onPreMotion(final EventPreMotion event) {
        ++this.ticks;
        if (this.ticks > this.rand.nextInt(50)) {
            this.ticks = 0;
            this.invoker.swingItem();
        }
        if (this.invoker.moveForward() < 0.1) {
            final float yaw = (float)(this.rand.nextInt(360) - 180);
            final float pitch = (float)(this.rand.nextInt(360) - 180);
            this.invoker.setRotationPitch(pitch);
            this.invoker.setRotationYaw(yaw);
        }
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
