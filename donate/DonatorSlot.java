// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.donate;

import net.minecraft.client.renderer.Tessellator;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import com.krispdev.resilience.gui.objects.ResilienceSlot;

public class DonatorSlot extends ResilienceSlot
{
    private GuiScreen screen;
    private int selected;
    
    public DonatorSlot(final Minecraft theMinecraft, final GuiScreen screen) {
        super(theMinecraft, Resilience.getInstance().getInvoker().getWidth(), Resilience.getInstance().getInvoker().getHeight(), 32, Resilience.getInstance().getInvoker().getHeight() - 59, 41);
        this.screen = screen;
        this.selected = 0;
    }
    
    @Override
    protected int func_148138_e() {
        return this.getSize() * 41;
    }
    
    @Override
    protected int getSize() {
        return Donator.donatorList.size();
    }
    
    @Override
    protected void elementClicked(final int var1, final boolean var2, final int var3, final int var4) {
        this.selected = var1;
    }
    
    @Override
    protected boolean isSelected(final int var1) {
        return this.selected == var1;
    }
    
    protected int getSelected() {
        return this.selected;
    }
    
    @Override
    protected void drawBackground() {
        this.screen.drawDefaultBackground();
    }
    
    @Override
    protected void drawSlot(final int selectedIndex, final int x, final int y, final int var4, final Tessellator var5, final int var6, final int var7) {
        try {
            final Donator donator = Donator.donatorList.get(selectedIndex);
            Resilience.getInstance().getStandardFont().drawString(donator.getNickname().replaceAll("~REPLACECHAR1~", " "), (float)x, (float)(y + 3), -11141121);
            Resilience.getInstance().getStandardFont().drawString("$" + donator.getAmount(), (float)x, (float)(y + 14), -8355712);
            Resilience.getInstance().getStandardFont().drawString(donator.getMessage().replaceAll("~REPLACECHAR1~", " "), (float)x, (float)(y + 25), -11141291);
        }
        catch (Exception ex) {}
    }
}
