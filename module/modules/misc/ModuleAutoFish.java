// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.wrappers.MethodInvoker;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import com.krispdev.resilience.event.events.player.EventPacketReceive;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoFish extends DefaultModule
{
    public ModuleAutoFish() {
        super("AutoFish", 0);
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Automatically fishes for you; casts and recasts.");
    }
    
    public void onPacketReceive(final EventPacketReceive event) {
        final Packet packet = event.getPacket();
        if (packet instanceof S12PacketEntityVelocity) {
            final S12PacketEntityVelocity p = (S12PacketEntityVelocity)packet;
            final Entity e = this.invoker.getEntityById(this.invoker.getPacketVelocityEntityId(p));
            if (e instanceof EntityFishHook) {
                final int x = this.invoker.getXMovePacketVel(p);
                final int y = this.invoker.getYMovePacketVel(p);
                final int z = this.invoker.getZMovePacketVel(p);
                if (x == 0 && y != 0 && z == 0) {
                    new ModuleAutoFish$1(this).start();
                }
            }
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
