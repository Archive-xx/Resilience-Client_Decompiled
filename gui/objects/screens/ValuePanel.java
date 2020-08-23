// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import java.util.Iterator;
import com.krispdev.resilience.gui.objects.sliders.DefaultSlider;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.gui.objects.sliders.ModuleValueSlider;
import com.krispdev.resilience.utilities.value.values.NumberValue;

public class ValuePanel extends DefaultPanel
{
    private int count;
    
    public ValuePanel(final String title, final int x, final int y, final int x1, final int y1, final boolean visible, final NumberValue... val) {
        super(title, x, y, x1, y1, visible);
        this.count = 0;
        for (final NumberValue value : val) {
            this.addSlider(value);
        }
        this.setExtended(true);
    }
    
    private void addSlider(final NumberValue val) {
        this.sliders.add(new ModuleValueSlider(val, val.getMin(), val.getMax(), this.getX() + 4, this.getY() + 18 * this.count, (DefaultPanel)this, val.shouldRound()));
        ++this.count;
    }
    
    public void draw(final int i, final int j) {
        super.draw(i, j);
        if (this.isExtended()) {
            Utils.drawRect((float)this.getX(), (float)(this.getY() + 17), (float)this.getX1(), (float)(this.getY() + this.sliders.size() * 18 + 19), -1727790076);
            for (final DefaultSlider slider : this.sliders) {
                slider.draw(i, j);
            }
        }
    }
    
    public void onClick(final int i, final int j, final int k) {
        for (final DefaultSlider slider : this.sliders) {
            slider.mouseClicked(i, j, k);
        }
        super.onClick(i, j, k);
    }
    
    public void onMouseButtonUp(final int i, final int j, final int k) {
        for (final DefaultSlider slider : this.sliders) {
            slider.mouseMovedOrUp(i, j, k);
        }
        super.onMouseButtonUp(i, j, k);
    }
}
