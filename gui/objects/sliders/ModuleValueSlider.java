// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.sliders;

import java.text.DecimalFormat;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.gui.objects.screens.DefaultPanel;
import com.krispdev.resilience.utilities.value.values.NumberValue;

public class ModuleValueSlider extends DefaultSlider
{
    public ModuleValueSlider(final NumberValue value, final float min, final float max, final int posX, final int posY, final DefaultPanel valuePanel, final boolean round) {
        super(value, min, max, posX, posY, valuePanel, round);
    }
    
    public void draw(final int i, final int j) {
        final boolean overThingy = i >= this.getPosX() + this.getPanel().getDragX() + this.getDragX() && i <= this.getPosX() + this.getDragX() + 6 + this.getPanel().getDragX() && j >= this.getPosY() + this.getPanel().getDragY() + 20 && j <= this.getPosY() + 34 + this.getPanel().getDragY();
        final boolean overSlider = i >= this.getPosX() + this.getPanel().getDragX() && i <= this.getPosX() + 6 + this.getPanel().getDragX() + 94 && j >= this.getPosY() + this.getPanel().getDragY() + 20 && j <= this.getPosY() + 34 + this.getPanel().getDragY();
        Utils.drawBetterRect((double)(this.getPosX() + this.getPanel().getDragX()), (double)(this.getPosY() + this.getPanel().getDragY() + 20), (double)(this.getPosX() + 6 + this.getPanel().getDragX() + 94), (double)(this.getPosY() + 34 + this.getPanel().getDragY()), -16777216, overSlider ? -2013255598 : -2013253806);
        Utils.drawBetterRect((double)(this.getPosX() + this.getPanel().getDragX() + this.getDragX()), (double)(this.getPosY() + this.getPanel().getDragY() + 20), (double)(this.getPosX() + this.getDragX() + 6 + this.getPanel().getDragX()), (double)(this.getPosY() + 34 + this.getPanel().getDragY()), -16777216, overThingy ? -1145719297 : -1147495681);
        Resilience.getInstance().getButtonExtraFont().drawCenteredString(String.valueOf(this.getValue().getName()) + " - " + (this.shouldRound() ? new DecimalFormat("#").format(this.getValue().getValue()) : new DecimalFormat("#.#").format(this.getValue().getValue())), (float)(50 + this.getPanel().getDragX() + this.getPosX()), (float)(this.getPosY() + this.getPanel().getDragY() + 22), -1118482);
        super.draw(i, j);
    }
}
