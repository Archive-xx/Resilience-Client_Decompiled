// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.sliders;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.gui.objects.screens.DefaultPanel;
import com.krispdev.resilience.utilities.value.values.NumberValue;

public class DefaultSlider
{
    private NumberValue value;
    private float min;
    private float max;
    private int posX;
    private int posY;
    private boolean dragging;
    private int width;
    private int height;
    private int lastClickX;
    private int dragX;
    private DefaultPanel panel;
    private boolean round;
    
    public DefaultSlider(final NumberValue value, final float min, final float max, final int posX, final int posY, final DefaultPanel valuePanel, final boolean round) {
        this.dragging = false;
        this.width = 100;
        this.height = 20;
        this.dragX = 0;
        this.value = value;
        this.min = min;
        this.max = max;
        this.posX = posX;
        this.posY = posY;
        this.panel = valuePanel;
        this.round = round;
        this.setVal(value.getValue());
    }
    
    public void draw(final int i, final int j) {
        this.width = 99;
        if (this.dragging) {
            this.drag(i);
        }
        if (this.dragX < 0) {
            this.dragX = 0;
        }
        if (this.dragX > this.width - 5) {
            this.dragX = this.width - 5;
        }
        this.value.setValue(this.dragX / ((this.width - 5) / (this.max - this.min)) + this.min);
    }
    
    public void drag(final int i) {
        this.dragX = i - this.lastClickX;
    }
    
    public void setVal(float val) {
        val -= this.min;
        final float dif = this.max - this.min;
        final float f = (this.width - 5) / dif;
        this.value.setValue(val);
        this.dragX = (int)(f * val);
    }
    
    public void mouseClicked(final int i, final int j, final int k) {
        if (k == 0) {
            if (i >= this.posX + this.panel.getDragX() && i <= this.posX + 6 + this.panel.getDragX() + 94 && j >= this.posY + this.panel.getDragY() + 20 && j <= this.posY + this.panel.getDragY() + 34) {
                this.dragX = i - (this.panel.getDragX() + this.posX + 1);
                this.lastClickX = i - this.dragX;
                this.dragging = true;
            }
            else {
                this.dragging = false;
            }
        }
    }
    
    public void mouseMovedOrUp(final int i, final int j, final int k) {
        if (k == 0) {
            this.dragging = false;
            Resilience.getInstance().getFileManager().saveConfigs(new String[0]);
        }
    }
    
    public int getDragX() {
        return this.dragX;
    }
    
    public int getPosX() {
        return this.posX;
    }
    
    public int getPosY() {
        return this.posY;
    }
    
    public NumberValue getValue() {
        return this.value;
    }
    
    public boolean shouldRound() {
        return this.round;
    }
    
    public boolean overSlider(final int i, final int j) {
        return i >= this.posX + this.panel.getDragX() && i <= this.posX + 6 + this.panel.getDragX() + 94 && j >= this.posY + this.panel.getDragY() + 20 && j <= this.posY + 34 + this.panel.getDragY();
    }
    
    public boolean overNob(final int i, final int j) {
        return i >= this.posX + this.panel.getDragX() && i <= this.posX + 6 + this.panel.getDragX() + 94 && j >= this.posY + this.panel.getDragY() + 20 && j <= this.posY + 34 + this.panel.getDragY();
    }
    
    public DefaultPanel getPanel() {
        return this.panel;
    }
}
