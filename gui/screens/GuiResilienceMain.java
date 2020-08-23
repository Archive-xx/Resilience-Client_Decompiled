// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import org.lwjgl.Sys;
import java.util.Iterator;
import java.awt.Rectangle;
import net.minecraft.util.StringUtils;
import com.krispdev.resilience.utilities.Utils;
import org.lwjgl.input.Mouse;
import com.krispdev.resilience.account.GuiAccountScreen;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import net.minecraft.client.gui.GuiButton;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.gui.objects.buttons.CheckBox;
import com.krispdev.resilience.hooks.HookGuiMainMenu;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;

public class GuiResilienceMain extends GuiScreen
{
    public static GuiResilienceMain screen;
    private ArrayList<String> updateData;
    private int count;
    private GuiScreen parentScreen;
    private ArrayList<Object[]> links;
    private int scroll;
    private int maxScroll;
    
    static {
        GuiResilienceMain.screen = new GuiResilienceMain((GuiScreen)new HookGuiMainMenu());
    }
    
    public GuiResilienceMain(final GuiScreen parent) {
        this.updateData = new ArrayList();
        this.count = 0;
        this.links = new ArrayList();
        this.scroll = 0;
        this.maxScroll = 0;
        this.parentScreen = parent;
        this.getUpdateData();
    }
    
    public void initGui() {
        this.buttonList.clear();
        CheckBox.checkBoxList.clear();
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(1, 4.0f, 50.5f, 132.0f, 20.0f, "Info"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(2, 4.0f, 74.5f, 132.0f, 20.0f, "Options"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(3, 4.0f, 98.5f, 132.0f, 20.0f, "Accounts"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(4, 4.0f, 122.5f, 132.0f, 20.0f, "Donate!"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(0, 4.0f, 146.5f, 132.0f, 20.0f, "Back"));
    }
    
    public void getUpdateData() {
        try {
            final URL url = new URL("http://krispdev.com/Resilience-Update-Info");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String text;
            while ((text = in.readLine()) != null) {
                this.updateData.add(text);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(final GuiButton btn) {
        if (Resilience.getInstance().getInvoker().getId(btn) == 0) {
            Resilience.getInstance().getInvoker().displayScreen(this.parentScreen);
        }
        else if (Resilience.getInstance().getInvoker().getId(btn) == 1) {
            Resilience.getInstance().getInvoker().displayScreen((GuiScreen)new GuiInfo(this));
        }
        else if (Resilience.getInstance().getInvoker().getId(btn) == 2) {
            Resilience.getInstance().getInvoker().displayScreen((GuiScreen)new GuiResilienceOptions((GuiScreen)this));
        }
        else if (Resilience.getInstance().getInvoker().getId(btn) == 3) {
            Resilience.getInstance().getInvoker().displayScreen((GuiScreen)GuiAccountScreen.guiScreen);
        }
        else if (Resilience.getInstance().getInvoker().getId(btn) == 4) {
            Resilience.getInstance().getInvoker().displayScreen((GuiScreen)GuiDonateCredits.guiDonate);
        }
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        this.scroll += Mouse.getDWheel() / 12;
        if (this.scroll < this.maxScroll) {
            this.scroll = this.maxScroll;
        }
        if (this.scroll > 0) {
            this.scroll = 0;
        }
        this.links.clear();
        drawRect(0, 0, Resilience.getInstance().getInvoker().getWidth(), Resilience.getInstance().getInvoker().getHeight(), -16777216);
        drawRect(140, 50, Resilience.getInstance().getInvoker().getWidth() - 8, Resilience.getInstance().getInvoker().getHeight() - 8, -15724528);
        Resilience.getInstance().getLargeFont().drawString("Change Logs:", 148.0f, 54.0f, -16711681);
        Resilience.getInstance().getXLargeFont().drawString(Resilience.getInstance().getName(), 4.0f, 1.0f, -16763905);
        Utils.drawSmallHL(140.0f, 74.0f, (float)(Resilience.getInstance().getInvoker().getWidth() - 8), -1);
        for (final String s : this.updateData) {
            if (78 + this.count + this.scroll < Resilience.getInstance().getInvoker().getHeight() - 8 - 10 && 78 + this.count + this.scroll > 70) {
                Resilience.getInstance().getStandardFont().drawString(s.replaceAll("&", "§"), 148.0f, (float)(78 + this.count + this.scroll), -1);
                if (s.contains("http://")) {
                    this.links.add(new Object[] { new Rectangle(148, 78 + this.count - 4 + this.scroll, (int)Resilience.getInstance().getStandardFont().getWidth(StringUtils.stripControlCodes(s)) + 148, this.count + 4 + this.scroll), s });
                }
            }
            this.count += 12;
        }
        this.maxScroll = -(78 + this.count + 4 - (Resilience.getInstance().getInvoker().getHeight() - 8));
        this.count = 0;
        super.drawScreen(i, j, f);
    }
    
    public void mouseClicked(final int x, final int y, final int btn) {
        for (final Object[] o : this.links) {
            final Rectangle r = (Rectangle)o[0];
            if (x >= r.getX() && x <= r.getMaxX() && y >= r.getY() && y <= r.getMaxY()) {
                Sys.openURL((String)o[1]);
                break;
            }
        }
        super.mouseClicked(x, y, btn);
    }
}
