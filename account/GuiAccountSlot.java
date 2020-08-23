// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.account;

import net.minecraft.client.renderer.Tessellator;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import com.krispdev.resilience.gui.objects.ResilienceSlot;

public class GuiAccountSlot extends ResilienceSlot
{
    private int selectedSlot;
    private Minecraft mc;
    private GuiScreen screen;
    
    public GuiAccountSlot(final Minecraft mc, final GuiScreen screen) {
        super(mc, Resilience.getInstance().getInvoker().getWidth(), Resilience.getInstance().getInvoker().getHeight(), 32, Resilience.getInstance().getInvoker().getHeight() - 59, 30);
        this.selectedSlot = 0;
        this.screen = screen;
    }
    
    @Override
    protected int func_148138_e() {
        return this.getSize() * 30;
    }
    
    @Override
    protected int getSize() {
        return Account.accountList.size();
    }
    
    @Override
    protected void elementClicked(final int var1, final boolean var2, final int var3, final int var4) {
        this.selectedSlot = var1;
    }
    
    @Override
    protected boolean isSelected(final int var1) {
        return this.selectedSlot == var1;
    }
    
    protected int getSelectedSlot() {
        return this.selectedSlot;
    }
    
    @Override
    protected void drawBackground() {
        Utils.drawRect(0.0f, 0.0f, (float)this.screen.width, (float)this.screen.height, -15724528);
    }
    
    @Override
    protected void drawSlot(final int selected, final int x, final int y, final int var4, final Tessellator var5, final int var6, final int var7) {
        try {
            final Account account = Account.accountList.get(selected);
            Resilience.getInstance().getPanelTitleFont().drawString(account.getUsername(), (float)x, (float)(y + 1), 16777215);
            if (account.isPremium()) {
                Resilience.getInstance().getStandardFont().drawString("§bPremium", (float)x, (float)(y + 14), 1728053247);
            }
            else {
                Resilience.getInstance().getStandardFont().drawString("§cNon-Premium (No Password)", (float)x, (float)(y + 14), 1728053247);
            }
        }
        catch (Exception ex) {}
    }
}
