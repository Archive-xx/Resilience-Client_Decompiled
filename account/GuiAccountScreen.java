// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.account;

import net.minecraft.util.Session;
import com.krispdev.resilience.utilities.Utils;
import net.minecraft.client.gui.GuiButton;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import net.minecraft.client.Minecraft;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.gui.screens.GuiResilienceMain;
import com.krispdev.resilience.hooks.HookGuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

public class GuiAccountScreen extends GuiScreen
{
    public static final GuiAccountScreen guiScreen;
    private GuiAccountSlot accountSlot;
    private GuiScreen parentScreen;
    public static String error;
    private boolean firstTime;
    
    static {
        guiScreen = new GuiAccountScreen(new GuiResilienceMain((GuiScreen)new HookGuiMainMenu()));
        GuiAccountScreen.error = "123";
    }
    
    public GuiAccountScreen(final GuiScreen screen) {
        this.firstTime = true;
        this.parentScreen = screen;
        Resilience.getInstance().getFileManager().loadAccounts();
    }
    
    public void initGui() {
        this.firstTime = true;
        (this.accountSlot = new GuiAccountSlot(Minecraft.getMinecraft(), (GuiScreen)this)).registerScrollButtons(7, 8);
        this.buttonList.clear();
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(0, (float)(this.width / 2 - 100), (float)(this.height - 30 + 1 + 2), 200.0f, 20.0f, "Login"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(1, (float)(this.width / 2 - 204), (float)(this.height - 30 + 1 + 2), 100.0f, 20.0f, "Add"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(2, (float)(this.width / 2 + 104), (float)(this.height - 30 + 1 + 2), 100.0f, 20.0f, "Delete"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(4, (float)(this.width / 2 - 204), (float)(this.height - 53 + 2), 100.0f, 20.0f, "Edit"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(5, (float)(this.width / 2 + 104), (float)(this.height - 53 + 2), 100.0f, 20.0f, "Direct"));
        Resilience.getInstance().getInvoker().addButton((GuiScreen)this, (GuiButton)new ResilienceButton(3, 4.0f, 4.0f, 50.0f, 20.0f, "Back"));
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        Utils.drawRect(0.0f, 0.0f, (float)this.width, (float)this.height, -15724528);
        this.accountSlot.drawScreen(i, j, f);
        Resilience.getInstance().getStandardFont().drawString(this.firstTime ? "§fStatus: §8Idle" : (GuiAccountScreen.error.equals("") ? "§fStatus: §bSuccess!" : "§fStatus: §cBad Login"), 62.0f, 9.0f, GuiAccountScreen.error.equals("") ? 1716911957 : 1728009557);
        Resilience.getInstance().getStandardFont().drawCenteredString("Alt Count: §f" + Account.accountList.size(), (float)(this.width / 2), 4.0f, -1776412);
        Resilience.getInstance().getStandardFont().drawString("§fUsername: §b" + Resilience.getInstance().getInvoker().getSessionUsername(), Resilience.getInstance().getInvoker().getWidth() - Resilience.getInstance().getStandardFont().getWidth("§fUsername: §b" + Resilience.getInstance().getInvoker().getSessionUsername()) - 11.0f, 9.0f, -1);
        super.drawScreen(i, j, f);
    }
    
    public void actionPerformed(final GuiButton btn) {
        if (btn.id == 0) {
            this.firstTime = false;
            if (Account.accountList.size() <= 0) {
                return;
            }
            final Account acc = Account.accountList.get(this.accountSlot.getSelectedSlot());
            if (acc == null) {
                return;
            }
            if (acc.isPremium()) {
                final String username = acc.getUsername();
                final String password = acc.getPassword();
                try {
                    GuiAccountScreen.error = Utils.setSessionData(username, password);
                    Resilience.getInstance().getFileManager().saveAccounts(new String[0]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                this.mc.session = new Session(acc.getUsername(), "", "");
                GuiAccountScreen.error = "";
            }
        }
        else if (btn.id == 1) {
            this.mc.displayGuiScreen((GuiScreen)new GuiAdd((GuiScreen)GuiAccountScreen.guiScreen));
        }
        else if (btn.id == 4) {
            if (Account.accountList.size() <= 0) {
                return;
            }
            final Account acc = Account.accountList.get(this.accountSlot.getSelectedSlot());
            if (acc == null) {
                return;
            }
            Resilience.getInstance().getInvoker().displayScreen((GuiScreen)new GuiEdit(acc, (GuiScreen)this));
        }
        else if (btn.id == 2) {
            try {
                if (Account.accountList.size() <= 0) {
                    return;
                }
                Account.accountList.remove(Account.accountList.indexOf(Account.accountList.get(this.accountSlot.getSelectedSlot())));
                Resilience.getInstance().getFileManager().saveAccounts(new String[0]);
            }
            catch (Exception ex) {}
        }
        else if (btn.id == 5) {
            Resilience.getInstance().getInvoker().displayScreen((GuiScreen)new GuiDirect((GuiScreen)this));
        }
        else {
            this.mc.displayGuiScreen(this.parentScreen);
        }
    }
}
