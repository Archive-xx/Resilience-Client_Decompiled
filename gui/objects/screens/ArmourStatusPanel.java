// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import net.minecraft.item.ItemStack;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.Utils;

public class ArmourStatusPanel extends DefaultPanel
{
    private int count;
    
    public ArmourStatusPanel(final String title, final int x, final int y, final int x1, final int y1, final boolean visible) {
        super(title, x, y, x1, y1, visible);
        this.count = 4;
    }
    
    public void draw(final int i, final int j) {
        this.count = 0;
        if (this.isExtended()) {
            Utils.drawRect((float)this.getX(), (float)(this.getY() + 17), (float)this.getX1(), (float)(this.getY() + 50), -1727790076);
            ItemStack[] armourInventory;
            for (int length = (armourInventory = Resilience.getInstance().getInvoker().getArmourInventory()).length, k = 0; k < length; ++k) {
                final ItemStack item = armourInventory[k];
                if (item != null) {
                    Utils.drawItemTag(this.getX() + this.count + 84, this.getY() + 17 + 8, item);
                }
                this.count -= 25;
            }
        }
        super.draw(i, j);
    }
}
