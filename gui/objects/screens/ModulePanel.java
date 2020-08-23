// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import com.krispdev.resilience.gui.objects.buttons.DefaultButton;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.gui.objects.buttons.ModuleButton;
import java.util.Iterator;
import com.krispdev.resilience.module.modules.DefaultModule;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;

public class ModulePanel extends DefaultPanel
{
    private int count;
    private int buttonSize;
    
    public ModulePanel(final String title, final int x, final int y, final int x1, final int y1, final boolean visible, final ModuleCategory category) {
        super(title, x, y, x1, y1, visible);
        this.count = 0;
        this.buttonSize = 15;
        for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
            if (mod.getCategory() == category) {
                this.addButton(mod);
            }
        }
    }
    
    private void addButton(final DefaultModule mod) {
        this.buttons.add(new ModuleButton(this.getX() + 4 - 3, this.getY() + this.buttonSize * this.count + 1 - 4, this.getX1() - 4 + 3, this.getY() + this.buttonSize * this.count - 2 - 4, this, mod));
        ++this.count;
    }
    
    public void draw(final int i, final int j) {
        super.draw(i, j);
        if (this.isExtended()) {
            Utils.drawRect((float)this.getX(), (float)(this.getY() + 17), (float)this.getX1(), (float)(this.getY() + this.buttons.size() * this.buttonSize + 18), -1727790076);
            for (final DefaultButton btn : this.buttons) {
                btn.draw(i, j);
                if (btn instanceof ModuleButton) {
                    final ModuleButton button = (ModuleButton)btn;
                    if (i >= btn.getX() + this.getDragX() && i <= btn.getX1() + this.getDragX() && j >= btn.getY() + this.getDragY() + 21 && j <= btn.getY1() + this.getDragY() + 38) {
                        button.setOverButton(true);
                    }
                    else {
                        button.setOverButton(false);
                    }
                }
            }
        }
    }
    
    public void onClick(final int i, final int j, final int k) {
        if (this.isExtended()) {
            for (final DefaultButton btn : this.buttons) {
                btn.onClick(i, j, k);
            }
        }
        super.onClick(i, j, k);
    }
    
    public void keyTyped(final char c, final int i) {
        for (final DefaultButton btn : this.buttons) {
            btn.keyTyped(c, i);
        }
    }
}
