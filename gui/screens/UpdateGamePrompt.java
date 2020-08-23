// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import com.krispdev.resilience.hooks.HookGuiMainMenu;
import com.krispdev.resilience.utilities.Utils;
import net.minecraft.client.gui.GuiButton;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.gui.GuiScreen;

public class UpdateGamePrompt extends GuiScreen
{
    private GuiScreen parentScreen;
    
    public UpdateGamePrompt(final GuiScreen screen) {
        this.parentScreen = screen;
    }
    
    public void initGui() {
        this.buttonList.clear();
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(0, 8.0f, (float)(Resilience.getInstance().getInvoker().getHeight() - 28), 108.0f, 20.0f, "Update Now"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(1, (float)(Resilience.getInstance().getInvoker().getWidth() - 108), (float)(Resilience.getInstance().getInvoker().getHeight() - 28), 100.0f, 20.0f, "Remind Me Later"));
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        Utils.drawRect(0.0f, 0.0f, (float)Resilience.getInstance().getInvoker().getWidth(), (float)Resilience.getInstance().getInvoker().getHeight(), -14671840);
        Resilience.getInstance().getPanelTitleFont().drawCenteredString("An update has been found for ".concat(Resilience.getInstance().getName()), (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 8.0f, -1);
        super.drawScreen(i, j, f);
    }
    
    public void actionPerformed(final GuiButton btn) {
        if (Resilience.getInstance().getInvoker().getId(btn) == 0) {
            this.mc.displayGuiScreen((GuiScreen)new GuiUpdating());
        }
        else {
            this.mc.displayGuiScreen((GuiScreen)new HookGuiMainMenu());
        }
    }
}
