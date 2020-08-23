// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.event.events.player;

import java.util.Iterator;
import com.krispdev.resilience.event.listeners.ModListener;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import net.minecraft.block.Block;
import com.krispdev.resilience.event.events.Event;
import com.krispdev.resilience.event.events.Cancellable;

public class EventBlockClick extends Cancellable implements Event
{
    private Block block;
    private int x;
    private int y;
    private int z;
    private int side;
    
    public EventBlockClick(final int x, final int y, final int z, final int side, final Block block) {
        this.block = block;
        this.x = x;
        this.y = y;
        this.z = z;
        this.side = side;
    }
    
    @Override
    public void onEvent() {
        if (!Resilience.getInstance().isEnabled()) {
            return;
        }
        try {
            for (final Listener l : Resilience.getInstance().getEventManager().eventListeners) {
                if (l instanceof ModListener) {
                    final ModListener mod = (ModListener)l;
                    mod.onBlockClicked(this);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getZ() {
        return this.z;
    }
    
    public int getSide() {
        return this.side;
    }
}
