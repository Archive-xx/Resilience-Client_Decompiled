// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import org.lwjgl.Sys;
import net.minecraft.client.gui.GuiButton;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.wrappers.MethodInvoker;
import net.minecraft.client.gui.GuiScreen;

public class GuiAskDonate extends GuiScreen
{
    private MethodInvoker invoker;
    private GuiScreen parent;
    
    public GuiAskDonate(final GuiScreen parent) {
        this.invoker = Resilience.getInstance().getInvoker();
        this.parent = parent;
    }
    
    public void initGui() {
        this.invoker.clearButtons((GuiScreen)this);
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(0, 8.0f, (float)(this.invoker.getHeight() - 28), 100.0f, 20.0f, "Remind Me Later"));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(1, (float)(this.invoker.getWidth() / 2 - 75), (float)(this.invoker.getHeight() - 28), 150.0f, 20.0f, "Donate!"));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(2, (float)(this.invoker.getWidth() - 108), (float)(this.invoker.getHeight() - 28), 100.0f, 20.0f, "Never >:("));
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        drawRect(0, 0, this.invoker.getWidth(), this.invoker.getHeight(), -15724528);
        Resilience.getInstance().getPanelTitleFont().drawCenteredString("Donate to " + Resilience.getInstance().getName() + "!", (float)(this.invoker.getWidth() / 2), 8.0f, -1);
        Resilience.getInstance().getStandardFont().drawCenteredString("Donators who paid $5 or more to " + Resilience.getInstance().getName() + " receive: A cape in-game (seen by everybody using the client),", (float)(this.invoker.getWidth() / 2), 24.0f, -43521);
        Resilience.getInstance().getStandardFont().drawCenteredString("A spot on the donation credits (In the" + Resilience.getInstance().getName() + " menu under \"Donate\"),", (float)(this.invoker.getWidth() / 2), 42.0f, -43521);
        Resilience.getInstance().getStandardFont().drawCenteredString("Eternal happiness!", (float)(this.invoker.getWidth() / 2), 96.0f, -43521);
        Resilience.getInstance().getStandardFont().drawCenteredString("More features in the future (such as VIP mods),", (float)(this.invoker.getWidth() / 2), 60.0f, -43521);
        Resilience.getInstance().getStandardFont().drawCenteredString("The removal of this screen,", (float)(this.invoker.getWidth() / 2), 78.0f, -43521);
        super.drawScreen(i, j, f);
    }
    
    public void actionPerformed(final GuiButton btn) {
        if (this.invoker.getId(btn) == 0) {
            this.invoker.displayScreen(this.parent);
        }
        else if (this.invoker.getId(btn) == 1) {
            Sys.openURL("http://resilience.krispdev.com/donate");
            this.invoker.displayScreen(this.parent);
            Resilience.getInstance().getFileManager().saveOptions("0");
        }
        else {
            Resilience.getInstance().getFileManager().saveOptions("-1");
            this.invoker.displayScreen(this.parent);
        }
    }
}
