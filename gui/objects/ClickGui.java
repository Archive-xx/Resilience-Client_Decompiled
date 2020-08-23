// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects;

import com.krispdev.resilience.module.modules.DefaultModule;
import com.krispdev.resilience.utilities.Utils;
import net.minecraft.client.gui.GuiButton;
import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.value.values.NumberValue;
import com.krispdev.resilience.module.categories.ModuleCategory;
import java.util.ArrayList;
import com.krispdev.resilience.gui.objects.screens.DefaultPanel;
import java.util.List;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.gui.objects.screens.XrayBlocksPanel;
import com.krispdev.resilience.gui.objects.screens.InfoPanel;
import com.krispdev.resilience.gui.objects.screens.ArmourStatusPanel;
import com.krispdev.resilience.gui.objects.screens.GuiRadarPanel;
import com.krispdev.resilience.gui.objects.screens.TextRadarPanel;
import com.krispdev.resilience.gui.objects.screens.ValuePanel;
import com.krispdev.resilience.gui.objects.screens.ModulePanel;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends GuiScreen
{
    public final ModulePanel world;
    public final ModulePanel combat;
    public final ModulePanel player;
    public final ModulePanel guiPanel;
    public final ModulePanel movement;
    public final ModulePanel misc;
    public final ModulePanel render;
    public final ValuePanel values;
    public final TextRadarPanel textRadar;
    public final GuiRadarPanel guiRadar;
    public final ArmourStatusPanel armorStatus;
    public final ModulePanel combatOptions;
    public final InfoPanel info;
    public final XrayBlocksPanel xrayPanel;
    public static boolean hasRightClicked;
    public static boolean hasHovered;
    public static boolean hasOpened;
    public static boolean hasPinned;
    private ResilienceButton enableButton;
    public static List<DefaultPanel> windows;
    
    static {
        ClickGui.hasRightClicked = false;
        ClickGui.hasHovered = false;
        ClickGui.hasOpened = false;
        ClickGui.hasPinned = false;
        ClickGui.windows = new ArrayList();
    }
    
    public ClickGui() {
        this.world = new ModulePanel("World", 120, 4, 228, 150, true, ModuleCategory.WORLD);
        this.combat = new ModulePanel("Combat", 4, 23, 114, 34, true, ModuleCategory.COMBAT);
        this.player = new ModulePanel("Player", 120, 42, 228, 51, true, ModuleCategory.PLAYER);
        this.guiPanel = new ModulePanel("GUI", 4, 61, 114, 68, true, ModuleCategory.GUI);
        this.movement = new ModulePanel("Movement", 4, 4, 114, 17, true, ModuleCategory.MOVEMENT);
        this.misc = new ModulePanel("Misc", 120, 23, 228, 34, true, ModuleCategory.MISC);
        this.render = new ModulePanel("Render", 4, 42, 114, 51, true, ModuleCategory.RENDER);
        this.values = new ValuePanel("Mod Values", 120, 61, 228, 68, true, (NumberValue[])Resilience.getInstance().getValues().numValues.toArray(new NumberValue[Resilience.getInstance().getValues().numValues.size()]));
        this.textRadar = new TextRadarPanel("Text Radar", 4, 80, 114, 85, true);
        this.guiRadar = new GuiRadarPanel("Gui Radar", 120, 80, 253, 85, true);
        this.armorStatus = new ArmourStatusPanel("Armor Status", 4, 99, 114, 102, true);
        this.combatOptions = new ModulePanel("Combat [More]", 120, 99, 228, 102, true, ModuleCategory.COMBAT_EXTENSION);
        this.info = new InfoPanel("Player Info", 4, 118, 114, 119, true);
        this.xrayPanel = new XrayBlocksPanel("Xray Blocks", 120, 118, 358, 119, true);
        Resilience.getInstance().getFileManager().loadGui();
    }
    
    public void focusWindow(final DefaultPanel panel) {
        if (ClickGui.windows.contains(panel)) {
            ClickGui.windows.remove(ClickGui.windows.indexOf(panel));
            ClickGui.windows.add(ClickGui.windows.size(), panel);
            for (final DefaultPanel window : ClickGui.windows) {
                window.setFocused(false);
            }
            panel.setFocused(true);
        }
    }
    
    public void initGui() {
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)(this.enableButton = new ResilienceButton(1, 4.0f, (float)(Resilience.getInstance().getInvoker().getHeight() - 24), 100.0f, 20.0f, String.valueOf(Resilience.getInstance().isEnabled() ? "Disable" : "Enable") + " ".concat(Resilience.getInstance().getName()))));
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        this.drawDefaultBackground();
        for (final DefaultPanel panel : ClickGui.windows) {
            panel.draw(i, j);
        }
        if (!ClickGui.hasRightClicked && Resilience.getInstance().isFirstTime()) {
            Utils.drawBetterRect((double)(this.getWidth() / 2 - 125), (double)(this.getHeight() / 2 - 50), (double)(this.getWidth() / 2 + 125), (double)(this.getHeight() / 2 + 50), -587136767, -586939388);
            Resilience.getInstance().getPanelTitleFont().drawCenteredString("§bTutorial [1/2]", (float)(this.getWidth() / 2), (float)(this.getHeight() / 2 - 45), -1);
            Resilience.getInstance().getStandardFont().drawCenteredString("§bWelcome to Resilience!", (float)(this.getWidth() / 2), (float)(this.getHeight() / 2 - 25), -1);
            Resilience.getInstance().getStandardFont().drawCenteredString(String.valueOf(ClickGui.hasOpened ? "§7" : "§b") + "To open a GUI panel hit the top right box.", (float)(this.getWidth() / 2), (float)(this.getHeight() / 2 - 13), -1);
            Resilience.getInstance().getStandardFont().drawCenteredString(String.valueOf(ClickGui.hasPinned ? "§7" : "§b") + "To \"pin\" (so it's seen ingame) hit the top left box.", (float)(this.getWidth() / 2), (float)(this.getHeight() / 2 - 1), -1);
            Resilience.getInstance().getStandardFont().drawCenteredString("§cTo continue:", (float)(this.getWidth() / 2), (float)(this.getHeight() / 2 - 1 + 12), -1);
            Resilience.getInstance().getStandardFont().drawCenteredString("§bRight click a button to get info & customization on the mod!", (float)(this.getWidth() / 2), (float)(this.getHeight() / 2 - 1 + 24), -1);
        }
        super.drawScreen(i, j, f);
    }
    
    public void mouseClicked(final int i, final int j, final int k) {
        try {
            for (final DefaultPanel panel : ClickGui.windows) {
                panel.onClick(i, j, k);
            }
            super.mouseClicked(i, j, k);
        }
        catch (Exception ex) {}
    }
    
    public void actionPerformed(final GuiButton btn) {
        if (Resilience.getInstance().getInvoker().getId(btn) == 1) {
            Resilience.getInstance().setEnabled(!Resilience.getInstance().isEnabled());
            if (Resilience.getInstance().isEnabled()) {
                this.enableButton.displayString = "Disable ".concat(Resilience.getInstance().getName());
                Resilience.getInstance().getFileManager().loadEnabledMods();
            }
            else {
                this.enableButton.displayString = "Enable ".concat(Resilience.getInstance().getName());
                for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
                    if (mod.getCategory() != ModuleCategory.COMBAT_EXTENSION) {
                        mod.setEnabled(false);
                    }
                }
                Resilience.getInstance().getInvoker().setGammaSetting(1.0f);
                for (int i = 0; i < 150; ++i) {
                    Resilience.getInstance().getInvoker().addChatMessage("");
                }
                Resilience.getInstance().getInvoker().displayScreen((GuiScreen)null);
            }
        }
    }
    
    public void mouseMovedOrUp(final int i, final int j, final int k) {
        for (final DefaultPanel panel : ClickGui.windows) {
            panel.onMouseButtonUp(i, j, k);
        }
        Resilience.getInstance().getFileManager().saveGui(new String[0]);
        super.mouseMovedOrUp(i, j, k);
    }
    
    public void keyTyped(final char c, final int i) {
        for (final DefaultPanel panel : ClickGui.windows) {
            panel.keyTyped(c, i);
        }
        super.keyTyped(c, i);
    }
    
    public void onGuiClosed() {
        Resilience.getInstance().getModuleManager().setModuleState("Gui", false);
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
}
