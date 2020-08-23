// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.buttons;

import com.krispdev.resilience.Resilience;

public class ModOptionBox
{
    private String text;
    private float x;
    private float y;
    private CheckBox checkBox;
    
    public ModOptionBox(final String text, final float y, final float x, final boolean startChecked) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.checkBox = new CheckBox(x, y, startChecked);
    }
    
    public void draw(final int mX, final int mY) {
        this.checkBox.draw((int)this.x, (int)this.y);
        Resilience.getInstance().getStandardFont().drawString(this.text, this.x + 12.0f, this.y - 2.0f, -1);
    }
    
    public void clicked(final int x, final int y) {
        this.checkBox.clicked(x, y);
    }
    
    public boolean isChecked() {
        return this.checkBox != null && this.checkBox.isChecked();
    }
    
    public void setX(final int x) {
        this.x = (float)x;
        this.checkBox.setX((float)x);
    }
    
    public void setY(final int y) {
        this.y = (float)y;
        this.checkBox.setY((float)y);
    }
    
    public void setChecked(final boolean checked) {
        this.checkBox.setChecked(checked);
    }
}
