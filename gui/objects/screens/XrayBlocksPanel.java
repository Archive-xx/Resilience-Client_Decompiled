// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import java.util.Iterator;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.gui.objects.components.XrayBlockComponent;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.gui.objects.components.Component;
import java.util.ArrayList;

public class XrayBlocksPanel extends DefaultPanel
{
    private ArrayList<Component> components;
    private int[] blockIds;
    
    public XrayBlocksPanel(final String title, final int x, final int y, final int x1, final int y1, final boolean visible) {
        super(title, x, y, x1, y1, visible);
        this.components = new ArrayList();
        this.blockIds = new int[] { 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 21, 22, 23, 29, 33, 41, 42, 46, 47, 48, 49, 52, 56, 57, 58, 73, 116, 129, 133, 152, 158 };
        final int size = 22;
        int xPos = 4;
        int yPos = 4;
        for (int i = 0; i < this.blockIds.length; ++i) {
            this.components.add(new XrayBlockComponent((float)(xPos + this.getOriginalX()), (float)(yPos + 17 + this.getOriginalY()), (float)(xPos + size + this.getOriginalX()), (float)(yPos + size + 17 + this.getOriginalY()), this.blockIds[i], Resilience.getInstance().getXrayUtils().shouldRenderBlock(this.blockIds[i])));
            xPos += size + 4;
            if (xPos + size + 4 >= 240) {
                xPos = 4;
                yPos += size + 4;
            }
        }
    }
    
    public void draw(final int x, final int y) {
        if (this.isExtended()) {
            Utils.drawRect((float)this.getX(), (float)(this.getY() + 17), (float)this.getX1(), (float)(this.getY() + 110 + 15), -1727790076);
            for (final Component c : this.components) {
                c.setX((int)(c.getOX() + this.getDragX()));
                c.setY((int)(c.getOY() + this.getDragY()));
                c.setX1((int)(c.getOX1() + this.getDragX()));
                c.setY1((int)(c.getOY1() + this.getDragY()));
                c.draw(x, y);
            }
        }
        super.draw(x, y);
    }
    
    public void onClick(final int x, final int y, final int btn) {
        if (this.isExtended()) {
            for (final Component c : this.components) {
                c.onClick(x, y, btn);
            }
        }
        super.onClick(x, y, btn);
    }
    
    public void onMouseButtonUp(final int x, final int y, final int btn) {
        super.onMouseButtonUp(x, y, btn);
    }
}
