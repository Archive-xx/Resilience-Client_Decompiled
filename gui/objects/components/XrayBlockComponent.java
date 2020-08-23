// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.components;

import com.krispdev.resilience.Resilience;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import com.krispdev.resilience.utilities.Utils;
import org.lwjgl.input.Mouse;

public class XrayBlockComponent extends Component
{
    private int blockId;
    private boolean enabled;
    
    public XrayBlockComponent(final float x, final float y, final float x1, final float y1, final int blockId, final boolean enabled) {
        super(x, y, x1, y1);
        this.blockId = blockId;
        this.enabled = enabled;
    }
    
    @Override
    public void draw(final int x, final int y) {
        final boolean overComponent = x >= this.getX() && x <= this.getX1() && y >= this.getY() && y <= this.getY1();
        final boolean mouseDownOverComponent = overComponent && Mouse.isButtonDown(0);
        if (this.enabled) {
            Utils.drawRect(this.getX(), this.getY(), this.getX1(), this.getY1(), mouseDownOverComponent ? 1427990174 : (overComponent ? 1430559709 : 1428521673));
        }
        else {
            Utils.drawRect(this.getX(), this.getY(), this.getX1(), this.getY1(), mouseDownOverComponent ? 1426089425 : (overComponent ? 1432840039 : 1431655765));
        }
        final ItemStack is = new ItemStack(Block.getBlockById(this.blockId));
        GL11.glPushMatrix();
        GL11.glDisable(3042);
        GL11.glEnable(32826);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDepthMask(true);
        GL11.glDisable(2929);
        try {
            Resilience.getInstance().getInvoker().renderItemIntoGUI(is, (int)this.getX() + 3, (int)this.getY() + 3);
            Resilience.getInstance().getInvoker().renderItemOverlayIntoGUI(is, (int)this.getX() + 3, (int)this.getY() + 3);
        }
        catch (Exception ex) {}
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }
    
    @Override
    public void onComponentClicked(final int x, final int y, final int btn) {
        if (btn == 0) {
            if (!(this.enabled = !this.enabled)) {
                Resilience.getInstance().getXrayUtils().removeBlock(this.blockId);
                if (Resilience.getInstance().getXrayUtils().xrayEnabled) {
                    Resilience.getInstance().getInvoker().loadRenderers();
                }
                Resilience.getInstance().getFileManager().saveXrayBlocks(new String[0]);
            }
            else {
                Resilience.getInstance().getXrayUtils().addBlock(this.blockId);
                if (Resilience.getInstance().getXrayUtils().xrayEnabled) {
                    Resilience.getInstance().getInvoker().loadRenderers();
                }
                Resilience.getInstance().getFileManager().saveXrayBlocks(new String[0]);
            }
        }
    }
}
