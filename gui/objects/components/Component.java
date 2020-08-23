// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.components;

import java.awt.Rectangle;
import com.krispdev.resilience.gui.objects.interfaces.Viewable;
import com.krispdev.resilience.gui.objects.interfaces.Clickable;

public class Component implements Clickable, Viewable
{
    private float x;
    private float y;
    private float x1;
    private float y1;
    private float oX;
    private float oY;
    private float oX1;
    private float oY1;
    
    public Component(final float x, final float y, final float x1, final float y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.oX = x;
        this.oY = y;
        this.oX1 = x1;
        this.oY1 = y1;
    }
    
    public void onComponentClicked(final int x, final int y, final int btn) {
    }
    
    public void draw(final int mX, final int mY) {
    }
    
    public void onClick(final int x, final int y, final int btn) {
        if (x >= this.x && x <= this.x1 && y >= this.y && y <= this.y1) {
            this.onComponentClicked(x, y, btn);
        }
    }
    
    public void onMouseButtonUp(final int x, final int y, final int btn) {
    }
    
    public boolean shouldDrawFromScroll(final Rectangle scrollArea, final int scrollFactor, final float error) {
        return this.x >= scrollArea.getMinX() && this.y + -error >= scrollArea.getMinY() && this.y1 + error <= scrollArea.getMaxY();
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float getY1() {
        return this.y1;
    }
    
    public float getX1() {
        return this.x1;
    }
    
    public float getOX() {
        return this.oX;
    }
    
    public float getOY() {
        return this.oY;
    }
    
    public float getOY1() {
        return this.oY1;
    }
    
    public float getOX1() {
        return this.oX1;
    }
    
    public void setX(final int newX) {
        this.x = (float)newX;
    }
    
    public void setY(final int newY) {
        this.y = (float)newY;
    }
    
    public void setX1(final int newX1) {
        this.x1 = (float)newX1;
    }
    
    public void setY1(final int newY1) {
        this.y1 = (float)newY1;
    }
}
