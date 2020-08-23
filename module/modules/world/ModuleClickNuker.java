// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.world;

import com.krispdev.resilience.event.listeners.Listener;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventBlockClick;
import com.krispdev.resilience.module.categories.ModuleCategory;
import net.minecraft.block.Block;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleClickNuker extends DefaultModule
{
    private int xPos;
    private int yPos;
    private int zPos;
    private Block selected;
    
    public ModuleClickNuker() {
        super("Click Nuker", 0);
        this.xPos = -1;
        this.yPos = -1;
        this.zPos = -1;
        this.setDescription("Automatically destroys blocks around you when you click");
        this.setCategory(ModuleCategory.WORLD);
    }
    
    @Override
    public void onBlockClicked(final EventBlockClick event) {
        if (!this.invoker.isInCreativeMode()) {
            return;
        }
        for (int y = (int)Resilience.getInstance().getValues().nukerRadius.getValue(); y >= (int)(-Resilience.getInstance().getValues().nukerRadius.getValue()); --y) {
            for (int z = (int)(-Resilience.getInstance().getValues().nukerRadius.getValue()); z <= Resilience.getInstance().getValues().nukerRadius.getValue(); ++z) {
                for (int x = (int)(-Resilience.getInstance().getValues().nukerRadius.getValue()); x <= Resilience.getInstance().getValues().nukerRadius.getValue(); ++x) {
                    this.xPos = Math.round((float)(event.getX() + x));
                    this.yPos = Math.round((float)(event.getY() + y));
                    this.zPos = Math.round((float)(event.getZ() + z));
                    final Block block = this.invoker.getBlock(this.xPos, this.yPos, this.zPos);
                    this.invoker.sendPacket((Packet)new C07PacketPlayerDigging(0, this.xPos, this.yPos, this.zPos, 1));
                    this.invoker.sendPacket((Packet)new C07PacketPlayerDigging(2, this.xPos, this.yPos, this.zPos, 1));
                }
            }
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
