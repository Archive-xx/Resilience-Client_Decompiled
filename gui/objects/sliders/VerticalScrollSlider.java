// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.sliders;

import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.utilities.value.values.NumberValue;

public class VerticalScrollSlider
{
    private NumberValue value;
    private float min;
    private float max;
    private int posX;
    private int posY;
    private boolean dragging;
    private int width;
    private int height;
    private int lastClickY;
    private int dragY;
    private boolean round;
    
    public VerticalScrollSlider(final NumberValue value, final int posX, final int posY, final int posX1, final int posY1) {
        this.dragging = false;
        this.width = 99;
        this.height = 20;
        this.dragY = 0;
        this.value = value;
        this.min = value.getMin();
        this.max = value.getMax();
        this.posX = posX;
        this.posY = posY;
        this.width = posX1;
        this.height = posY1 - 5;
        this.setVal(value.getValue());
    }
    
    public void draw(final int i, final int j) {
        if (this.dragging) {
            this.drag(j);
        }
        if (this.dragY < 0) {
            this.dragY = 0;
        }
        if (this.dragY > this.height - 10) {
            this.dragY = this.height - 10;
        }
        Utils.drawBetterRect((double)this.getPosX(), (double)this.getPosY(), (double)(this.getPosX() + this.width), (double)(this.getPosY() + this.height), -16777216, -2013253806);
        Utils.drawBetterRect((double)this.getPosX(), (double)(this.getPosY() + this.getDragY()), (double)(this.getPosX() + this.width), (double)(this.getPosY() + this.getDragY() + 10), -16777216, -1147495681);
        this.value.setValue(this.dragY / (this.height / (this.max - this.min)) + this.min);
    }
    
    public void drag(final int i) {
        this.dragY = i - this.lastClickY;
    }
    
    public void setVal(float val) {
        val -= this.min;
        final float dif = this.max - this.min;
        final float f = this.height / dif;
        this.value.setValue(val);
        this.dragY = (int)(f * val);
    }
    
    public void mouseClicked(final int i, final int j, final int k) {
        if (k == 0) {
            if (i >= this.posX && i <= this.posX + this.width && j >= this.posY && j <= this.posY + this.height) {
                this.dragY = j - (this.posY + 1);
                this.lastClickY = j - this.dragY;
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
        }
    }
    
    public int getDragY() {
        return this.dragY;
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
        return i >= this.posX && i <= this.posX + this.width && j >= this.posY && j <= this.posY + this.height;
    }
    
    public boolean overNob(final int i, final int j) {
        return i >= this.posX && i <= this.posX + this.width && j >= this.posY && j <= this.posY + this.height;
    }
    
    public void setX(final int newX) {
        this.posX = newX;
    }
    
    public void setY(final int newY) {
        this.posY = newY;
    }
}
