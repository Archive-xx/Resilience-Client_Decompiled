// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.render;

import com.krispdev.resilience.wrappers.MethodInvoker;
import com.krispdev.resilience.event.listeners.Listener;
import java.util.Iterator;
import com.krispdev.resilience.utilities.RenderUtils;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.module.values.Values;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import java.util.ArrayList;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleSearch extends DefaultModule
{
    private ArrayList<Float[]> blocksList;
    
    public ModuleSearch() {
        super("Search", 0);
        this.blocksList = new ArrayList();
        this.setCategory(ModuleCategory.RENDER);
        this.setDescription("Lights up blocks that you say should be lit up");
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        final Values values = Resilience.getInstance().getValues();
        ++values.ticksForSearch;
        if (Resilience.getInstance().getValues().ticksForSearch > 70) {
            Resilience.getInstance().getValues().ticksForSearch = 0;
            this.blocksList.clear();
            new ModuleSearch$1(this).start();
        }
    }
    
    @Override
    public void onRender(final EventOnRender event) {
        for (final Float[] coords : this.blocksList) {
            GL11.glPushMatrix();
            RenderUtils.setup3DLightlessModel();
            RenderUtils.drawESP(false, coords[0] - this.invoker.getRenderPosX(), coords[1] - this.invoker.getRenderPosY(), coords[2] - this.invoker.getRenderPosZ(), coords[0] + 1.0f - this.invoker.getRenderPosX(), coords[1] + 1.0f - this.invoker.getRenderPosY(), coords[2] + 1.0f - this.invoker.getRenderPosZ(), (double)coords[3], (double)coords[4], (double)coords[5], 0.19, (double)coords[3], (double)coords[4], (double)coords[5], 1.0);
            RenderUtils.shutdown3DLightlessModel();
            GL11.glPopMatrix();
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
