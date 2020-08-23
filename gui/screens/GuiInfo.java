// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import net.minecraft.client.gui.GuiButton;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.gui.GuiScreen;

public class GuiInfo extends GuiScreen
{
    private GuiScreen parentScreen;
    
    public GuiInfo(final GuiScreen screen) {
        this.parentScreen = screen;
    }
    
    public void initGui() {
        Resilience.getInstance().getInvoker().clearButtons((GuiScreen)this);
        this.buttonList.add(new ResilienceButton(0, 8.0f, 8.0f, 50.0f, 20.0f, "Back"));
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        Utils.drawRect(0.0f, 0.0f, (float)Resilience.getInstance().getInvoker().getWidth(), (float)Resilience.getInstance().getInvoker().getHeight(), -14671840);
        Resilience.getInstance().getStandardFont().drawCenteredString("This client was coded by Krisp from HackForums.", (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 18.0f, -1776412);
        Resilience.getInstance().getStandardFont().drawCenteredString("Optifine is currently included in this update", (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 30.0f, -1776412);
        Resilience.getInstance().getStandardFont().drawCenteredString("If you have any questions, comments, or suggestions feel free to contact me:", (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 42.0f, -1776412);
        Resilience.getInstance().getStandardFont().drawCenteredString("krisphf@gmail.com", (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 54.0f, -16755201);
        Resilience.getInstance().getStandardFont().drawCenteredString("Credits: Aarow - Bow aimbot, Ownage - Font Renderer", (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 70.0f, -1776412);
        super.drawScreen(i, j, f);
    }
    
    public void actionPerformed(final GuiButton btn) {
        if (Resilience.getInstance().getInvoker().getId(btn) == 0) {
            Resilience.getInstance().getInvoker().displayScreen(this.parentScreen);
        }
    }
}
