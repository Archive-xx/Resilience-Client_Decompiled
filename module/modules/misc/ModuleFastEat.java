// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import com.krispdev.resilience.event.events.player.EventOutwardPacket;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleFastEat extends DefaultModule
{
    public ModuleFastEat() {
        super("FastEat", 0);
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Allows really fast food eating");
    }
    
    public void onOutwardPacket(final EventOutwardPacket event) {
        if (!this.invoker.isOnGround()) {
            return;
        }
        final Packet packet = event.getPacket();
        if (packet instanceof C08PacketPlayerBlockPlacement) {
            if (!(this.invoker.getCurrentItem().getItem() instanceof ItemFood)) {
                return;
            }
            final C08PacketPlayerBlockPlacement packetBlockPlacement = (C08PacketPlayerBlockPlacement)packet;
            if (packetBlockPlacement.func_149576_c() != -1 || packetBlockPlacement.func_149571_d() != -1 || packetBlockPlacement.func_149570_e() != -1 || packetBlockPlacement.func_149568_f() != 255) {
                return;
            }
            event.addPacketToList((Packet)new C09PacketHeldItemChange(Minecraft.getMinecraft().thePlayer.inventory.currentItem));
            for (int i = 0; i < 40; ++i) {
                event.addPacketToList((Packet)new C03PacketPlayer(false));
            }
            event.addPacketToList((Packet)new C07PacketPlayerDigging(5, 0, 0, 0, 255));
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
