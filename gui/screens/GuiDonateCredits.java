// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import org.lwjgl.Sys;
import net.minecraft.client.gui.GuiButton;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import java.util.List;
import java.util.Collections;
import com.krispdev.resilience.donate.Donator;
import com.krispdev.resilience.hooks.HookGuiMainMenu;
import com.krispdev.resilience.donate.DonatorSlot;
import net.minecraft.client.gui.GuiScreen;

public class GuiDonateCredits extends GuiScreen
{
    public static GuiDonateCredits guiDonate;
    private DonatorSlot slot;
    private GuiScreen parent;
    
    static {
        GuiDonateCredits.guiDonate = new GuiDonateCredits((GuiScreen)new GuiResilienceMain((GuiScreen)new HookGuiMainMenu()));
    }
    
    public GuiDonateCredits(final GuiScreen parent) {
        this.parent = parent;
    }
    
    public void initGui() {
        this.buttonList.clear();
        Collections.sort((List<Comparable>)Donator.donatorList);
        (this.slot = new DonatorSlot(this.mc, (GuiScreen)this)).registerScrollButtons(7, 8);
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(0, 4.0f, 4.0f, 70.0f, 20.0f, "Back"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(69, (float)(Resilience.getInstance().getInvoker().getWidth() / 2 - 76), (float)(Resilience.getInstance().getInvoker().getHeight() - 51), 160.0f, 20.0f, "Donate"));
    }
    
    public void actionPerformed(final GuiButton btn) {
        if (btn.id == 0) {
            this.mc.displayGuiScreen(this.parent);
        }
        else {
            Sys.openURL("http://resilience.krispdev.com/donate");
        }
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        this.drawDefaultBackground();
        this.slot.drawScreen(i, j, f);
        Resilience.getInstance().getPanelTitleFont().drawCenteredString("Huge thanks to all these wonderful people!", (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 8.0f, -22016);
        super.drawScreen(i, j, f);
    }
}
