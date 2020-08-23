// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.gui.objects.ClickGui;
import com.krispdev.resilience.gui.objects.sliders.DefaultSlider;
import com.krispdev.resilience.gui.objects.buttons.DefaultButton;
import java.util.ArrayList;
import com.krispdev.resilience.gui.objects.interfaces.Clickable;
import com.krispdev.resilience.gui.objects.interfaces.Dragable;

public class DefaultPanel implements Dragable, Clickable
{
    public ArrayList<DefaultButton> buttons;
    public ArrayList<DefaultSlider> sliders;
    private boolean isPinned;
    private boolean isExtended;
    private boolean isDragging;
    private boolean isFocused;
    private boolean isVisible;
    private int lastClickX;
    private int lastClickY;
    private int x;
    private int y;
    private int x1;
    private int y1;
    private int dragX;
    private int dragY;
    private String title;
    private int color1;
    private int color3;
    private int color4;
    
    public DefaultPanel(final String title, final int x, final int y, final int x1, final int y1, final boolean visible) {
        this.buttons = new ArrayList();
        this.sliders = new ArrayList();
        this.isPinned = false;
        this.isExtended = false;
        this.isDragging = false;
        this.isFocused = false;
        this.isVisible = false;
        this.color1 = -1157496318;
        this.color3 = -1157598484;
        this.color4 = -1149058817;
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.title = title;
        this.isVisible = visible;
        ClickGui.windows.add(this);
    }
    
    public void draw(final int i, final int j) {
        if (!this.isVisible) {
            return;
        }
        if (this.isDragging) {
            this.drag(i, j);
        }
        Utils.drawRect((float)this.getX(), (float)this.getY(), (float)this.getX1(), (float)(this.getY() + 17), this.color1);
        Utils.drawRect((float)(this.getX1() - 14), (float)(this.getY() + 14), (float)(this.getX1() - 3), (float)(this.getY() + 3), this.isExtended() ? this.color3 : this.color4);
        Utils.drawRect((float)(this.getX1() - 27), (float)(this.getY() + 14), (float)(this.getX1() - 16), (float)(this.getY() + 3), this.isPinned() ? this.color3 : this.color4);
        Resilience.getInstance().getPanelTitleFont().drawString(this.getTitle(), (float)(this.getX() + 4), (float)(this.getY() + 1), -1);
    }
    
    public void onClick(final int i, final int j, final int k) {
        if (!this.isVisible) {
            return;
        }
        if (i <= this.getX1() && i >= this.getX() && j <= this.getY() + 17 && j >= this.getY() && k == 0) {
            Resilience.getInstance().getClickGui().focusWindow(this);
            this.isDragging = true;
            this.lastClickX = i - this.dragX;
            this.lastClickY = j - this.dragY;
        }
        if (i >= this.getX1() - 15 && j <= this.getY() + 15 && i <= this.getX1() - 2 && j >= this.getY() + 2) {
            ClickGui.hasOpened = true;
            this.setExtended(!this.isExtended());
        }
        if (i >= this.getX1() - 27 && j <= this.getY() + 15 && i <= this.getX1() - 16 && j >= this.getY() + 2) {
            ClickGui.hasPinned = true;
            this.setPinned(!this.isPinned());
        }
    }
    
    public void onGuiClosed() {
        this.isDragging = false;
    }
    
    public void onMouseButtonUp(final int i, final int j, final int k) {
        if (k == 0) {
            this.isDragging = false;
        }
    }
    
    public void keyTyped(final char c, final int i) {
        if (!this.isVisible) {
            return;
        }
    }
    
    public int getX() {
        return this.x + this.dragX;
    }
    
    public int getY() {
        return this.y + this.dragY;
    }
    
    public int getX1() {
        return this.x1 + this.dragX;
    }
    
    public int getY1() {
        return this.y1 + this.dragY;
    }
    
    @Override
    public void drag(final int x, final int y) {
        if (!this.isVisible) {
            return;
        }
        this.dragX = x - this.lastClickX;
        this.dragY = y - this.lastClickY;
    }
    
    @Override
    public int getDragX() {
        return this.dragX;
    }
    
    @Override
    public int getDragY() {
        return this.dragY;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public void setDragX(final int i) {
        this.dragX = i;
    }
    
    @Override
    public void setDragY(final int i) {
        this.dragY = i;
    }
    
    public boolean isPinned() {
        return this.isPinned;
    }
    
    public boolean isExtended() {
        return this.isExtended;
    }
    
    public void setPinned(final boolean flag) {
        this.isPinned = flag;
    }
    
    public void setExtended(final boolean flag) {
        this.isExtended = flag;
    }
    
    public boolean isFocused() {
        return this.isFocused;
    }
    
    public boolean isVisible() {
        return this.isVisible;
    }
    
    public void setFocused(final boolean flag) {
        this.isFocused = flag;
    }
    
    public int getOriginalX() {
        return this.x;
    }
    
    public int getOriginalY() {
        return this.y;
    }
    
    public int getOriginalX1() {
        return this.x1;
    }
    
    public int getOriginalY1() {
        return this.y1;
    }
    
    public void setVisible(final boolean visible) {
        this.isVisible = visible;
    }
}
