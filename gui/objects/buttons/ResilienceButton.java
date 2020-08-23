// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.buttons;

import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class ResilienceButton extends GuiButton
{
    private float x;
    private float y;
    private float x1;
    private float y1;
    
    public ResilienceButton(final int par1, final float par2, final float par3, final float par4, final float par5, final String par6Str) {
        super(par1, (int)par2, (int)par3, (int)par4, (int)par5, par6Str);
        this.x = par2;
        this.y = par3;
        this.x1 = par4;
        this.y1 = par5;
        this.displayString = par6Str;
    }
    
    public ResilienceButton(final int i, final int j, final int k, final String stringParams) {
        this(i, (float)j, (float)k, 200.0f, 20.0f, stringParams);
    }
    
    public void drawButton(final Minecraft p_146112_1_, final int mX, final int mY) {
        final boolean overButton = mX >= this.x && mX <= this.x + this.x1 && mY >= this.y && mY <= this.y + this.y1;
        Utils.drawRect(this.x, this.y, this.x + this.x1, this.y + this.y1, overButton ? 1429419007 : 1426063589);
        if (this.y1 < 30.0f) {
            Resilience.getInstance().getPanelTitleFont().drawCenteredString(this.displayString, (float)(int)(this.x + this.x1 / 2.0f), this.y + 2.0f, -1);
        }
        else {
            Resilience.getInstance().getPanelTitleFont().drawCenteredString(this.displayString, (float)(int)(this.x + this.x1 / 2.0f), (float)(int)(this.y + 11.5f), -1);
        }
    }
}
