// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import net.minecraft.client.gui.GuiButton;
import java.util.Iterator;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.gui.objects.buttons.CheckBox;
import net.minecraft.client.gui.GuiScreen;

public class GuiResilienceOptions extends GuiScreen
{
    private GuiScreen parentScreen;
    private CheckBox loadEnabledMods;
    private CheckBox loadGuiSettings;
    private CheckBox loadKeybinds;
    private CheckBox loadXrayBlocks;
    private CheckBox loadFriends;
    private CheckBox loadEnemies;
    private CheckBox loadConfigs;
    private CheckBox loadWaypoints;
    private CheckBox loadMacros;
    private CheckBox useGlobalCustomButtons;
    
    public GuiResilienceOptions(final GuiScreen screen) {
        this.parentScreen = screen;
    }
    
    public void initGui() {
        CheckBox.checkBoxList.clear();
        this.buttonList.clear();
        this.buttonList.add(new ResilienceButton(0, (float)(Resilience.getInstance().getInvoker().getWidth() - 58), (float)(Resilience.getInstance().getInvoker().getHeight() - 28), 50.0f, 20.0f, "Done"));
        this.loadEnabledMods = new CheckBox(8.0f, 64.0f, Resilience.getInstance().getFileManager().loadEnabledMods);
        this.loadGuiSettings = new CheckBox(8.0f, 78.0f, Resilience.getInstance().getFileManager().loadGui);
        this.loadKeybinds = new CheckBox(8.0f, 92.0f, Resilience.getInstance().getFileManager().loadKeybinds);
        this.loadXrayBlocks = new CheckBox(8.0f, 106.0f, Resilience.getInstance().getFileManager().loadXrayBlocks);
        this.loadFriends = new CheckBox(8.0f, 120.0f, Resilience.getInstance().getFileManager().loadFriends);
        this.loadEnemies = new CheckBox(8.0f, 134.0f, Resilience.getInstance().getFileManager().loadEnemies);
        this.loadConfigs = new CheckBox(8.0f, 148.0f, Resilience.getInstance().getFileManager().loadConfigs);
        this.loadWaypoints = new CheckBox(8.0f, 162.0f, Resilience.getInstance().getFileManager().loadWaypoints);
        this.loadMacros = new CheckBox(8.0f, 176.0f, Resilience.getInstance().getFileManager().loadMacros);
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        drawRect(0, 0, Resilience.getInstance().getInvoker().getWidth(), Resilience.getInstance().getInvoker().getHeight(), -14671840);
        for (final CheckBox box : CheckBox.checkBoxList) {
            box.draw(i, j);
        }
        Resilience.getInstance().getPanelTitleFont().drawCenteredString("Client options:", (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 8.0f, -16755201);
        Resilience.getInstance().getStandardFont().drawString("Load Enabled Mods", 20.0f, 62.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load GUI Settings", 20.0f, 76.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load Keybinds", 20.0f, 90.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load Xray Blocks", 20.0f, 104.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load Friends", 20.0f, 118.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load Enemies", 20.0f, 132.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load Configs", 20.0f, 146.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load Waypoints", 20.0f, 160.0f, -1);
        Resilience.getInstance().getStandardFont().drawString("Load Macros", 20.0f, 174.0f, -1);
        super.drawScreen(i, j, f);
    }
    
    public void actionPerformed(final GuiButton btn) {
        this.mc.displayGuiScreen(this.parentScreen);
    }
    
    public void mouseClicked(final int i, final int j, final int k) {
        for (final CheckBox box : CheckBox.checkBoxList) {
            box.clicked(i, j);
        }
        Resilience.getInstance().getFileManager().loadEnabledMods = this.loadEnabledMods.isChecked();
        Resilience.getInstance().getFileManager().loadGui = this.loadGuiSettings.isChecked();
        Resilience.getInstance().getFileManager().loadKeybinds = this.loadKeybinds.isChecked();
        Resilience.getInstance().getFileManager().loadXrayBlocks = this.loadXrayBlocks.isChecked();
        Resilience.getInstance().getFileManager().loadFriends = this.loadFriends.isChecked();
        Resilience.getInstance().getFileManager().loadEnemies = this.loadEnemies.isChecked();
        Resilience.getInstance().getFileManager().loadConfigs = this.loadConfigs.isChecked();
        Resilience.getInstance().getFileManager().loadWaypoints = this.loadWaypoints.isChecked();
        Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
        super.mouseClicked(i, j, k);
    }
}
