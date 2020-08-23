// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.hooks;

import net.minecraft.client.gui.GuiLabel;
import com.krispdev.resilience.donate.Donator;
import com.krispdev.resilience.utilities.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.lwjgl.Sys;
import com.krispdev.resilience.gui.screens.GuiResilienceMain;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import net.minecraft.client.resources.I18n;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.wrappers.MethodInvoker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.GuiMainMenu;

public class HookGuiMainMenu extends GuiMainMenu
{
    private final ResourceLocation backgroundImage;
    private final ResourceLocation titleImage;
    private static String version;
    private MethodInvoker invoker;
    private static boolean hasAsked;
    
    static {
        HookGuiMainMenu.version = "1.7.9";
        HookGuiMainMenu.hasAsked = false;
    }
    
    public HookGuiMainMenu() {
        this.backgroundImage = new ResourceLocation("assets/minecraft/textures/gui/title/resilience-background.jpg");
        this.titleImage = new ResourceLocation("assets/minecraft/textures/gui/title/resilience-title.png");
        this.invoker = Resilience.getInstance().getInvoker();
    }
    
    public void initGui() {
        this.mc.gameSettings.guiScale = 2;
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(1, 1.0f, 1.0f, (float)(this.invoker.getWidth() / 3 + 1), 20.0f, I18n.format("menu.singleplayer", new Object[0])));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(69, (float)(this.invoker.getWidth() / 3 + 3), 1.0f, (float)(this.invoker.getWidth() / 3 + 3), 41.0f, Resilience.getInstance().getName()));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(2, (float)(this.invoker.getWidth() / 3 * 2 + 7), 1.0f, (float)(this.invoker.getWidth() / 3 - 7), 20.0f, I18n.format("menu.multiplayer", new Object[0])));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(199, (float)(this.invoker.getWidth() / 2 - 50), (float)(this.invoker.getHeight() - 93), 100.0f, 20.0f, "Change to 1.7.6"));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(0, 1.0f, 22.0f, (float)(this.invoker.getWidth() / 3 + 1), 20.0f, I18n.format("menu.options", new Object[0])));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(14, (float)(this.invoker.getWidth() / 3 * 2 + 7), 22.0f, (float)(this.invoker.getWidth() / 3 - 7), 20.0f, I18n.format("menu.online", new Object[0])));
        this.invoker.addButton((GuiScreen)this, (GuiButton)new ResilienceButton(70, (float)(this.invoker.getWidth() / 2 - 50), this.invoker.getHeight() - 24.5f, 100.0f, 20.0f, "Suggest"));
    }
    
    protected void addSingleplayerMultiplayerButtons(final int par1, final int par2) {
        super.addSingleplayerMultiplayerButtons(par1, par2);
    }
    
    protected void actionPerformed(final GuiButton btn) {
        if (this.invoker.getId(btn) == 69) {
            this.mc.displayGuiScreen((GuiScreen)GuiResilienceMain.screen);
        }
        else if (this.invoker.getId(btn) == 70) {
            Sys.openURL("http://resilience.krispdev.com/suggest");
        }
        else if (this.invoker.getId(btn) == 199) {
            btn.displayString = "Change to " + HookGuiMainMenu.version;
            if (HookGuiMainMenu.version.equals("1.7.9")) {
                HookGuiMainMenu.version = "1.7.6";
                Resilience.getInstance().getValues().version = 4;
            }
            else if (HookGuiMainMenu.version.equals("1.7.6")) {
                HookGuiMainMenu.version = "1.7.9";
                Resilience.getInstance().getValues().version = 5;
            }
        }
        super.actionPerformed(btn);
    }
    
    public void drawScreen(final int par1, final int par2, final float par3) {
        this.drawGradientRect(0, 0, this.invoker.getWidth(), this.invoker.getHeight(), -1776412, -13421569);
        try {
            final Image img = new Image(this.backgroundImage.getResourcePath());
            img.draw(0.0f, 0.0f, (float)this.invoker.getWidth(), (float)this.invoker.getHeight());
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        try {
            final Image img = new Image(this.titleImage.getResourcePath());
            GL11.glEnable(3042);
            img.draw((float)(this.invoker.getWidth() / 2 - 90), (float)(this.invoker.getHeight() / 2 - 60), 200.0f, 80.0f);
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        Utils.drawBetterRect((double)(this.invoker.getWidth() / 2 - 150), (double)(this.invoker.getHeight() - 68), (double)(this.invoker.getWidth() / 2 + 150), (double)(this.invoker.getHeight() - 29), -1996488705, 1711276032);
        Resilience.getInstance().getStandardFont().drawCenteredString("Version: §7" + HookGuiMainMenu.version, (float)(this.invoker.getWidth() / 2), (float)(this.invoker.getHeight() - 65), -1);
        Resilience.getInstance().getStandardFont().drawCenteredString("Username: §7" + this.mc.session.getUsername(), (float)(this.invoker.getWidth() / 2), this.invoker.getHeight() - 54.5f, -1);
        Resilience.getInstance().getStandardFont().drawCenteredString("Account status: " + (Donator.isDonator(this.mc.session.getUsername(), 5.0f) ? "§aUpgraded" : "§8Not Upgraded"), (float)(this.invoker.getWidth() / 2), (float)(this.invoker.getHeight() - 44), -1);
        final String title = "§3" + Resilience.getInstance().getName() + "§f v" + Resilience.getInstance().getVersion();
        Resilience.getInstance().getStandardFont().drawStringWithShadow(title, 4.0f, (float)(this.invoker.getHeight() - 14), -1);
        Resilience.getInstance().getStandardFont().drawStringWithShadow("by Krisp", this.invoker.getWidth() - 4 - Resilience.getInstance().getStandardFont().getWidth("by Krisp"), (float)(this.invoker.getHeight() - 14), -13421569);
        for (int var4 = 0; var4 < this.buttonList.size(); ++var4) {
            this.buttonList.get(var4).drawButton(this.mc, par1, par2);
        }
        for (int var4 = 0; var4 < this.labelList.size(); ++var4) {
            this.labelList.get(var4).func_146159_a(this.mc, par1, par2);
        }
    }
}
