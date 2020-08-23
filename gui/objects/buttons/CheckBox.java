// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.buttons;

import org.lwjgl.opengl.GL11;
import com.krispdev.resilience.utilities.Utils;
import org.lwjgl.input.Mouse;
import java.util.ArrayList;

public class CheckBox
{
    public static ArrayList<CheckBox> checkBoxList;
    private float x;
    private float y;
    private boolean checked;
    
    static {
        CheckBox.checkBoxList = new ArrayList<CheckBox>();
    }
    
    public CheckBox(final float x, final float y, final boolean checked) {
        this.x = x;
        this.y = y;
        this.checked = checked;
        CheckBox.checkBoxList.add(this);
    }
    
    public void draw(final int i, final int j) {
        final boolean overBox = i >= this.x && i <= this.x + 8.0f && j >= this.y && j <= this.y + 8.0f;
        Utils.drawBetterRect((double)this.x, (double)this.y, (double)(this.x + 8.0f), (double)(this.y + 8.0f), -13421773, (overBox && Mouse.isButtonDown(0)) ? -16185079 : (overBox ? -15592942 : -15724528));
        if (this.checked) {
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glColor3f(0.0f, 0.5f, 1.0f);
            GL11.glLineWidth(1.5f);
            GL11.glBegin(3);
            GL11.glVertex2f(this.x + 1.0f, this.y + 1.0f);
            GL11.glVertex2f(this.x + 4.0f, this.y + 6.0f);
            GL11.glVertex2f(this.x + 10.0f, this.y - 5.0f);
            GL11.glEnd();
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
    }
    
    public void clicked(final int i, final int j) {
        if (i >= this.x && i <= this.x + 8.0f && j >= this.y && j <= this.y + 8.0f) {
            this.checked = !this.checked;
        }
    }
    
    public boolean isChecked() {
        return this.checked;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
    
    public void setChecked(final boolean checked) {
        this.checked = checked;
    }
}
