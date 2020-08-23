// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import com.krispdev.resilience.event.events.player.EventPacketReceive;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleNoKnockback extends DefaultModule
{
    public ModuleNoKnockback() {
        super("NoKnockback", 0);
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Prevents knockback from entities");
    }
    
    public void onPacketReceive(final EventPacketReceive event) {
        final Packet eventPacket = event.getPacket();
        if (eventPacket instanceof S12PacketEntityVelocity) {
            event.setCancelled(true);
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
