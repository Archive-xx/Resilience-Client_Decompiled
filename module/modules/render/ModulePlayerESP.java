// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import java.util.Iterator;
import com.krispdev.resilience.utilities.RenderUtils;
import net.minecraft.entity.player.EntityPlayer;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModulePlayerESP extends DefaultModule
{
    public ModulePlayerESP() {
        super("PlayerESP", 25);
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Draws a box around players");
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        for (final Object o : this.invoker.getEntityList()) {
            if (o instanceof EntityPlayer) {
                RenderUtils.drawPlayerESP((EntityPlayer)o);
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
