// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.components;

import java.util.Iterator;
import com.krispdev.resilience.utilities.Utils;
import java.awt.Rectangle;
import com.krispdev.resilience.gui.objects.sliders.VerticalScrollSlider;
import com.krispdev.resilience.utilities.value.values.NumberValue;
import java.util.ArrayList;
import com.krispdev.resilience.gui.objects.interfaces.Clickable;
import com.krispdev.resilience.gui.objects.interfaces.Viewable;

public class ScrollArea implements Viewable, Clickable
{
    private ArrayList<Component> components;
    private NumberValue scrollValue;
    private VerticalScrollSlider slider;
    private int scrollFactor;
    private Rectangle area;
    private int oldScroll;
    
    public ScrollArea(final Rectangle area) {
        this.components = new ArrayList<Component>();
        this.oldScroll = 0;
        this.area = area;
        this.scrollValue = new NumberValue(0.0f, 0.0f, 100.0f, "Scroll", false);
        this.slider = new VerticalScrollSlider(this.scrollValue, (int)area.getMinX(), (int)area.getMinY(), 10, (int)area.getMaxY() / 2 - 8);
    }
    
    public void draw(final int x, final int y) {
        if (this.components.size() < 1) {
            return;
        }
        final int neededSize = (int)this.components.get(this.components.size() - 1).getOY1();
        final float scrollPercent = this.scrollValue.getValue();
        this.scrollFactor = (int)(-(scrollPercent * 0.01 * neededSize));
        Utils.drawRect((float)this.area.getMinX(), (float)this.area.getMinY(), (float)this.area.getMaxX(), (float)this.area.getMaxY(), -1728053248);
        final int comSize = (int)(this.components.get(0).getOY1() - this.components.get(0).getOY());
        final boolean shouldApplyScroll = this.scrollFactor % comSize == 0;
        if (shouldApplyScroll) {
            this.oldScroll = this.scrollFactor / 10;
        }
        for (final Component c : this.components) {
            c.setX((int)(c.getOX() + this.area.getMinX()));
            c.setY((int)(c.getOY() + this.area.getMinY()) + this.oldScroll);
            c.setX1((int)(c.getOX1() + this.area.getMinX()));
            c.setY1((int)(c.getOY1() + this.area.getMinY()) + this.oldScroll);
            if (c.shouldDrawFromScroll(this.area, this.scrollFactor, 4.0f)) {
                c.draw(x, y);
            }
        }
        this.slider.setX((int)this.area.getMaxX() - 10);
        this.slider.setY((int)this.area.getMinY());
        this.slider.draw(x, y);
    }
    
    public void addComponent(final Component component1) {
        this.components.add(component1);
    }
    
    public void setX(final int newX) {
        this.area.x = newX;
    }
    
    public void setY(final int newY) {
        this.area.y = newY;
    }
    
    public void setX1(final int newX1) {
        this.area.width = newX1;
    }
    
    public void setY1(final int newY1) {
        this.area.height = newY1;
    }
    
    public void onClick(final int x, final int y, final int btn) {
        this.slider.mouseClicked(x, y, btn);
    }
    
    public void onMouseButtonUp(final int x, final int y, final int btn) {
        this.slider.mouseMovedOrUp(x, y, btn);
    }
    
    public int getX() {
        return (int)this.area.getX();
    }
    
    public int getY() {
        return (int)this.area.getY();
    }
    
    public int getX1() {
        return (int)this.area.getWidth() + (int)this.area.getX();
    }
    
    public int getY1() {
        return (int)this.area.getHeight() + (int)this.area.getY();
    }
    
    public void clearComponents() {
        this.components.clear();
    }
}
