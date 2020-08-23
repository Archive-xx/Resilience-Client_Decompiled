// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import com.krispdev.resilience.utilities.Utils;
import net.minecraft.client.gui.GuiButton;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.gui.GuiScreen;

public class GuiBanned extends GuiScreen
{
    public void initGui() {
        Resilience.getInstance().getInvoker().clearButtons((GuiScreen)this);
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(0, (float)(this.width / 2 - 50), (float)(this.height - 30), 100.0f, 20.0f, "Close Minecraft"));
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        Utils.drawRect(0.0f, 0.0f, (float)this.width, (float)this.height, -14277082);
        Resilience.getInstance().getLargeFont().drawCenteredString("Your account has been banned", (float)(this.width / 2), 10.0f, -65536);
        Resilience.getInstance().getPanelTitleFont().drawCenteredString("Ban Reason: " + Resilience.getInstance().getValues().banReason, (float)(this.width / 2), 34.0f, -256);
        Resilience.getInstance().getStandardFont().drawCenteredString("Ban Lasts for " + Resilience.getInstance().getValues().banTime + " days.", (float)(this.width / 2), 50.0f, -256);
        super.drawScreen(i, j, f);
    }
    
    public void actionPerformed(final GuiButton btn) {
        System.exit(0);
    }
}
