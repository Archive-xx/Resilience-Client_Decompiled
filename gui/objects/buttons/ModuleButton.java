// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.buttons;

import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.gui.objects.screens.DefaultPanel;
import java.util.Iterator;
import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.gui.objects.ClickGui;
import com.krispdev.resilience.utilities.Utils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.gui.objects.screens.ModulePanel;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleButton extends DefaultButton
{
    private DefaultModule mod;
    private ModulePanel panel;
    private boolean overButton;
    private int colour1;
    private int colour2;
    private int colour3;
    private int colour4;
    private int colour5;
    private int colour6;
    private int colour7;
    private int slideCount;
    private int textLength;
    private int modExtraCount;
    private boolean infoOpened;
    private boolean changeBindOpen;
    
    public ModuleButton(final int x, final int y, final int x1, final int y1, final ModulePanel panel, final DefaultModule mod) {
        super(x, y, x1, y1);
        this.overButton = false;
        this.colour1 = 1428521673;
        this.colour2 = 1431655765;
        this.colour3 = 1430559709;
        this.colour4 = 1432840039;
        this.colour5 = 1427990174;
        this.colour6 = 1426089425;
        this.colour7 = 1145982719;
        this.textLength = 0;
        this.modExtraCount = 0;
        this.infoOpened = false;
        this.changeBindOpen = false;
        this.panel = panel;
        this.mod = mod;
        this.slideCount = (int)(-Resilience.getInstance().getButtonExtraFont().getWidth(mod.getDescription()));
    }
    
    @Override
    public void draw(final int i, final int j) {
        GL11.glPushMatrix();
        GL11.glDisable(2896);
        this.colour7 = -1157627904;
        final boolean overChange = i >= this.getX1() + this.panel.getDragX() + 6 && i <= this.getX1() + this.panel.getDragX() + 48 && j >= this.getY() + this.panel.getDragY() + 78 && j <= this.getY() + this.panel.getDragY() + 91;
        final boolean overClear = i >= this.getX1() + this.panel.getDragX() + 49 && i <= this.getX1() + this.panel.getDragX() + 90 && j >= this.getY() + this.panel.getDragY() + 78 && j <= this.getY() + this.panel.getDragY() + 91;
        final boolean overModName = i >= this.getX1() + this.panel.getDragX() + 6 && i <= this.getX1() + this.panel.getDragX() + 90 && j >= this.getY() + this.panel.getDragY() + 21 && j <= this.getY() + this.panel.getDragY() + 36;
        final boolean overToggle = i >= this.getX1() + this.panel.getDragX() + 6 && i <= this.getX1() + this.panel.getDragX() + 90 && j >= this.getY() + this.panel.getDragY() + 37 && j <= this.getY() + this.panel.getDragY() + 51;
        final boolean isMouseDownOverToggle = Mouse.isButtonDown(0) && overToggle;
        final boolean isMouseDownOverButton = Mouse.isButtonDown(0) && this.overButton;
        if (this.mod.isEnabled()) {
            Utils.drawRect((float)(this.getX() + this.panel.getDragX()), (float)(this.getY() + this.panel.getDragY() + 21), (float)(this.getX1() + this.panel.getDragX()), (float)(this.getY1() + this.panel.getDragY() + 38), this.overButton ? (isMouseDownOverButton ? this.colour5 : this.colour3) : this.colour1);
        }
        else {
            Utils.drawRect((float)(this.getX() + this.panel.getDragX()), (float)(this.getY() + this.panel.getDragY() + 21), (float)(this.getX1() + this.panel.getDragX()), (float)(this.getY1() + this.panel.getDragY() + 38), this.overButton ? (isMouseDownOverButton ? this.colour6 : this.colour4) : this.colour2);
        }
        Resilience.getInstance().getStandardFont().drawCenteredString(this.mod.getName(), (float)((this.getX() + this.getX1()) / 2 + this.panel.getDragX()), (float)(this.getY() + this.panel.getDragY() + 23), -1);
        if (this.infoOpened) {
            Resilience.getInstance().getPanelTitleFont().drawString("<", (float)(this.getX1() + this.panel.getDragX() - 9), (float)(this.getY() + this.panel.getDragY() + 21), -1);
            Utils.drawRect((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.panel.getDragY() + this.getY() + 21), (float)(this.getX1() + this.panel.getDragX() + 90), (float)(this.panel.getDragY() + this.getY() + 91 + 12 * this.modExtraCount + ((this.mod.guiExtras.size() == 0) ? 0 : 8)), this.colour7);
            this.modExtraCount = 0;
            Utils.drawSmallHL((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.getY() + this.panel.getDragY() + 21), (float)(this.getX1() + this.panel.getDragX() + 90), -9934593);
            Utils.drawSmallHL((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.panel.getDragY() + this.getY() + 91 + 12 * this.modExtraCount + ((this.mod.guiExtras.size() == 0) ? 0 : 20)), (float)(this.getX1() + this.panel.getDragX() + 90), -9934593);
            Utils.drawSmallHL((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.getY() + this.panel.getDragY() + 36), (float)(this.getX1() + this.panel.getDragX() + 90), 1723184639);
            Utils.drawSmallHL((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.getY() + this.panel.getDragY() + 51), (float)(this.getX1() + this.panel.getDragX() + 90), 1723184639);
            Utils.drawSmallHL((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.getY() + this.panel.getDragY() + 91), (float)(this.getX1() + this.panel.getDragX() + 90), (this.mod.guiExtras.size() == 0) ? 0 : 1723184639);
            Utils.drawRect((float)(this.getX1() + this.panel.getDragX() + 90), (float)(this.getY() + this.panel.getDragY() + 21), this.getX1() + this.panel.getDragX() + 90 + Resilience.getInstance().getButtonExtraFont().getWidth(this.mod.getDescription()) + this.slideCount, (float)(this.getY() + this.panel.getDragY() + 36), -872415232);
            if (overModName) {
                Utils.drawRect((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.getY() + this.panel.getDragY() + 21), (float)(this.getX1() + this.panel.getDragX() + 90), (float)(this.getY() + this.panel.getDragY() + 36), 587202559);
                if (this.slideCount < 4) {
                    if (this.slideCount + 12 > 4) {
                        this.slideCount = 4;
                    }
                    else {
                        this.slideCount += 12;
                    }
                }
                else {
                    ClickGui.hasHovered = true;
                    Resilience.getInstance().getButtonExtraFont().drawString(this.mod.getDescription(), (float)(this.getX1() + this.panel.getDragX() + 92), (float)(this.getY() + this.panel.getDragY() + 23), -4868609);
                }
            }
            else if (this.slideCount > -Resilience.getInstance().getButtonExtraFont().getWidth(this.mod.getDescription())) {
                if (this.slideCount - 12 < -Resilience.getInstance().getButtonExtraFont().getWidth(this.mod.getDescription())) {
                    this.slideCount = (int)(-Resilience.getInstance().getButtonExtraFont().getWidth(this.mod.getDescription()));
                }
                else {
                    this.slideCount -= 12;
                }
            }
            if (overToggle) {
                Utils.drawRect((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.getY() + this.panel.getDragY() + 36), (float)(this.getX1() + this.panel.getDragX() + 90), (float)(this.getY() + this.panel.getDragY() + 51), isMouseDownOverToggle ? 584965597 : 587202559);
            }
            Resilience.getInstance().getStandardFont().drawCenteredString(this.mod.getName(), (float)(this.getX1() + 48 + this.panel.getDragX()), (float)(this.getY() + this.panel.getDragY() + 23), -1907998);
            Resilience.getInstance().getStandardFont().drawCenteredString(this.mod.isEnabled() ? "Enabled" : "Disabled", (float)(this.getX1() + this.panel.getDragX() + 48), (float)(this.getY() + this.panel.getDragY() + 38), this.mod.isEnabled() ? -16711936 : -65536);
            Resilience.getInstance().getStandardFont().drawCenteredString("Current Keybind:", (float)(this.getX1() + this.panel.getDragX() + 48), (float)(this.getY() + this.panel.getDragY() + 53), -4868609);
            Resilience.getInstance().getStandardFont().drawCenteredString(Keyboard.getKeyName(this.mod.getKeyCode()), (float)(this.getX1() + this.panel.getDragX() + 48), (float)(this.getY() + this.panel.getDragY() + 65), -1);
            Utils.drawRect((float)(this.getX1() + this.panel.getDragX() + 6), (float)(this.getY() + this.panel.getDragY() + 78), (float)(this.getX1() + this.panel.getDragX() + 48), (float)(this.getY() + this.panel.getDragY() + 91), overChange ? 1154733055 : 1153022463);
            Resilience.getInstance().getButtonExtraFont().drawCenteredString("CHANGE", (float)(this.getX1() + this.panel.getDragX() + 26), (float)(this.getY() + this.panel.getDragY() + 80), -1);
            Utils.drawSmallVL((float)(this.getY() + this.panel.getDragY() + 78), (float)(this.getX1() + this.panel.getDragX() + 48), (float)(this.getY() + this.panel.getDragY() + 91), -1);
            Utils.drawRect((float)(this.getX1() + this.panel.getDragX() + 48), (float)(this.getY() + this.panel.getDragY() + 78), (float)(this.getX1() + this.panel.getDragX() + 90), (float)(this.getY() + this.panel.getDragY() + 91), overClear ? 1154733055 : 1153022463);
            Resilience.getInstance().getButtonExtraFont().drawCenteredString("CLEAR", (float)(this.getX1() + this.panel.getDragX() + 69), (float)(this.getY() + this.panel.getDragY() + 80), -1);
            if (this.changeBindOpen) {
                Utils.drawBetterRect((double)(Resilience.getInstance().getClickGui().getWidth() / 2 - 75), (double)(Resilience.getInstance().getClickGui().getHeight() / 2 - 50), (double)(Resilience.getInstance().getClickGui().getWidth() / 2 + 75), (double)(Resilience.getInstance().getClickGui().getHeight() / 2 + 50), 1711302097, -1727855869);
                Resilience.getInstance().getPanelTitleFont().drawCenteredString("Press the Key to Bind", (float)(Resilience.getInstance().getClickGui().getWidth() / 2), (float)(Resilience.getInstance().getClickGui().getHeight() / 2 - 40), -1);
                Resilience.getInstance().getPanelTitleFont().drawCenteredString(">>          ENTER          <<", (float)(Resilience.getInstance().getClickGui().getWidth() / 2), (float)(Resilience.getInstance().getClickGui().getHeight() / 2 - 12), -1776412);
            }
            for (final ModOptionBox box : this.mod.guiExtras) {
                box.setX(this.getX1() + this.panel.getDragX() + 10);
                box.setY(this.panel.getDragY() + this.getY() + 12 * this.modExtraCount + 100);
                box.draw(i, j);
                ++this.modExtraCount;
            }
        }
        else {
            Resilience.getInstance().getPanelTitleFont().drawString(">", (float)(this.getX1() + this.panel.getDragX() - 9), (float)(this.getY() + this.panel.getDragY() + 21), -1);
        }
        GL11.glPopMatrix();
    }
    
    @Override
    public void onClick(final int i, final int j, final int k) {
        if (this.changeBindOpen) {
            return;
        }
        if (this.overButton && k == 0) {
            this.panel.setFocused(true);
            if (this.panel.isFocused()) {
                this.mod.toggle();
            }
        }
        else if (i >= this.getX() + this.panel.getDragX() && i <= this.getX1() + this.panel.getDragX() && j >= this.getY() + this.panel.getDragY() + 21 && j <= this.getY1() + this.panel.getDragY() + 38 && k == 1) {
            this.infoOpened = !this.infoOpened;
            ClickGui.hasRightClicked = true;
            Resilience.getInstance().getClickGui().focusWindow((DefaultPanel)this.panel);
            Resilience.getInstance().getClickGui();
            for (final DefaultPanel panel : ClickGui.windows) {
                if (panel instanceof ModulePanel) {
                    final ModulePanel modulePanel = (ModulePanel)panel;
                    for (final DefaultButton button : modulePanel.buttons) {
                        if (button instanceof ModuleButton) {
                            final ModuleButton btn = (ModuleButton)button;
                            if (btn == this) {
                                continue;
                            }
                            btn.setInfoOpened(false);
                        }
                    }
                }
            }
        }
        else if (i >= this.getX1() + this.panel.getDragX() + 6 && i <= this.getX1() + this.panel.getDragX() + 48 && j >= this.getY() + this.panel.getDragY() + 78 && j <= this.getY() + this.panel.getDragY() + 91 && this.infoOpened) {
            this.changeBindOpen = true;
            this.panel.setFocused(true);
        }
        else if (i >= this.getX1() + this.panel.getDragX() + 49 && i <= this.getX1() + this.panel.getDragX() + 90 && j >= this.getY() + this.panel.getDragY() + 78 && j <= this.getY() + this.panel.getDragY() + 91 && this.infoOpened && this.mod.getCategory() != ModuleCategory.GUI) {
            this.mod.setKeyBind(0);
            Resilience.getInstance().getFileManager().saveBinds(new String[0]);
            this.panel.setFocused(true);
        }
        else if (i >= this.getX1() + this.panel.getDragX() + 6 && i <= this.getX1() + this.panel.getDragX() + 90 && j >= this.getY() + this.panel.getDragY() + 36 && j <= this.getY() + this.panel.getDragY() + 51 && this.infoOpened && this.mod.getCategory() != ModuleCategory.GUI) {
            this.mod.toggle();
            this.panel.setFocused(true);
        }
        if (this.infoOpened) {
            for (final ModOptionBox box : this.mod.guiExtras) {
                box.clicked(i, j);
            }
        }
    }
    
    @Override
    public void keyTyped(final char c, final int i) {
        if (this.changeBindOpen) {
            if (i != 1) {
                this.mod.setKeyBind(i);
                Resilience.getInstance().getFileManager().saveBinds(new String[0]);
            }
            this.changeBindOpen = false;
        }
    }
    
    public void setInfoOpened(final boolean flag) {
        this.infoOpened = flag;
    }
    
    public void setOverButton(final boolean flag) {
        this.overButton = flag;
    }
}
