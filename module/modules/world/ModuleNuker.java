// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.world;

import com.krispdev.resilience.event.listeners.Listener;
import java.util.Iterator;
import com.krispdev.resilience.utilities.RenderUtils;
import com.krispdev.resilience.event.events.player.EventOnRender;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventBlockClick;
import com.krispdev.resilience.module.categories.ModuleCategory;
import java.util.ArrayList;
import net.minecraft.block.Block;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleNuker extends DefaultModule
{
    private boolean shouldSelect;
    private int xPos;
    private int yPos;
    private int zPos;
    private Block selected;
    private ArrayList<Integer[]> positions;
    
    public ModuleNuker() {
        super("Nuker", 49);
        this.xPos = -1;
        this.yPos = -1;
        this.zPos = -1;
        this.positions = new ArrayList<Integer[]>();
        this.setDescription("Automatically destroys blocks around you");
        this.setCategory(ModuleCategory.WORLD);
    }
    
    @Override
    public void onBlockClicked(final EventBlockClick event) {
        if (this.invoker.isInCreativeMode()) {
            return;
        }
        if (this.selected == null) {
            Resilience.getInstance().getLogger().infoChat("Now nuking " + event.getBlock().getLocalizedName());
        }
        final Block previous = this.selected;
        this.selected = event.getBlock();
        if (previous != null && this.selected != null && !previous.getLocalizedName().equalsIgnoreCase(this.selected.getLocalizedName())) {
            Resilience.getInstance().getLogger().infoChat("Now nuking " + this.selected.getLocalizedName());
        }
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        this.shouldSelect = !this.invoker.isInCreativeMode();
        this.positions.clear();
        for (int y = (int)Resilience.getInstance().getValues().nukerRadius.getValue(); y >= (int)(-Resilience.getInstance().getValues().nukerRadius.getValue()); --y) {
            for (int z = (int)(-Resilience.getInstance().getValues().nukerRadius.getValue()); z <= Resilience.getInstance().getValues().nukerRadius.getValue(); ++z) {
                for (int x = (int)(-Resilience.getInstance().getValues().nukerRadius.getValue()); x <= Resilience.getInstance().getValues().nukerRadius.getValue(); ++x) {
                    this.xPos = (int)Math.round(this.invoker.getPosX() + x);
                    this.yPos = (int)((int)Math.round(this.invoker.getPosY() + y) - this.invoker.getEntityHeight((Entity)Resilience.getInstance().getWrapper().getPlayer()) / 2.0f);
                    this.zPos = (int)Math.round(this.invoker.getPosZ() + z);
                    final Block block = this.invoker.getBlock(this.xPos, this.yPos, this.zPos);
                    if (this.shouldSelect) {
                        if (block != null && this.selected != null && this.invoker.getIdFromBlock(this.selected) == this.invoker.getIdFromBlock(block)) {
                            this.positions.add(new Integer[] { this.xPos, this.yPos, this.zPos });
                            this.invoker.sendPacket((Packet)new C07PacketPlayerDigging(0, this.xPos, this.yPos, this.zPos, 1));
                            this.invoker.sendPacket((Packet)new C07PacketPlayerDigging(2, this.xPos, this.yPos, this.zPos, 1));
                        }
                    }
                    else if (this.invoker.getIdFromBlock(block) != 0) {
                        this.positions.add(new Integer[] { this.xPos, this.yPos, this.zPos });
                        this.invoker.sendPacket((Packet)new C07PacketPlayerDigging(0, this.xPos, this.yPos, this.zPos, 1));
                        this.invoker.sendPacket((Packet)new C07PacketPlayerDigging(2, this.xPos, this.yPos, this.zPos, 1));
                    }
                }
            }
        }
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        for (final Integer[] pos : this.positions) {
            RenderUtils.drawESP(false, pos[0] - this.invoker.getRenderPosX(), pos[1] - this.invoker.getRenderPosY(), pos[2] - this.invoker.getRenderPosZ(), pos[0] + 1 - this.invoker.getRenderPosX(), pos[1] + 1 - this.invoker.getRenderPosY(), pos[2] + 1 - this.invoker.getRenderPosZ(), 0.5, 0.5, 1.0, 0.15, 0.5, 0.5, 1.0, 0.15);
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
