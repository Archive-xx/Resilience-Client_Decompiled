// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.account;

import net.minecraft.util.Session;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.gui.objects.buttons.ResilienceButton;
import com.krispdev.resilience.Resilience;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiScreen;

public class GuiDirect extends GuiScreen
{
    private GuiScreen parentScreen;
    private GuiTextField usernameBox;
    private GuiPasswordBox passwordBox;
    
    public GuiDirect(final GuiScreen parent) {
        this.parentScreen = parent;
    }
    
    public void updateScreen() {
        this.usernameBox.updateCursorCounter();
        this.passwordBox.updateCursorCounter();
    }
    
    protected void keyTyped(final char c, final int i) {
        this.usernameBox.textboxKeyTyped(c, i);
        this.passwordBox.textboxKeyTyped(c, i);
        if (c == '\t') {
            if (this.usernameBox.isFocused()) {
                this.usernameBox.setFocused(false);
                this.passwordBox.setFocused(true);
            }
            else {
                this.usernameBox.setFocused(true);
                this.passwordBox.setFocused(false);
            }
        }
        if (c == '\r') {
            this.actionPerformed(this.buttonList.get(0));
        }
    }
    
    protected void mouseClicked(final int i, final int j, final int k) {
        super.mouseClicked(i, j, k);
        this.usernameBox.mouseClicked(i, j, k);
        this.passwordBox.mouseClicked(i, j, k);
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new ResilienceButton(0, (float)(Resilience.getInstance().getInvoker().getWidth() / 2 - 102), (float)(Resilience.getInstance().getInvoker().getHeight() / 2 + 25), 204.0f, 20.0f, "Login"));
        this.buttonList.add(new ResilienceButton(1, (float)(Resilience.getInstance().getInvoker().getWidth() / 2 - 102), (float)(Resilience.getInstance().getInvoker().getHeight() / 2 + 49), 204.0f, 20.0f, "Back"));
        this.usernameBox = new GuiTextField(this.mc.fontRenderer, Resilience.getInstance().getInvoker().getWidth() / 2 - 100, 76, 200, 20);
        this.passwordBox = new GuiPasswordBox(this.mc.fontRenderer, Resilience.getInstance().getInvoker().getWidth() / 2 - 100, 116, 200, 20);
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        drawRect(0, 0, Resilience.getInstance().getInvoker().getWidth(), Resilience.getInstance().getInvoker().getHeight(), -15724528);
        Resilience.getInstance().getStandardFont().drawString("Username", (float)(Resilience.getInstance().getInvoker().getWidth() / 2 - 100), 63.0f, 10526880);
        Resilience.getInstance().getStandardFont().drawString("Password", (float)(Resilience.getInstance().getInvoker().getWidth() / 2 - 100), 104.0f, 10526880);
        this.usernameBox.drawTextBox();
        this.passwordBox.drawTextBox();
        super.drawScreen(i, j, f);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
    
    protected void actionPerformed(final GuiButton par1) {
        if (par1.id == 1) {
            this.mc.displayGuiScreen((GuiScreen)GuiAccountScreen.guiScreen);
        }
        if (par1.id == 0) {
            final Account acc = new Account(this.usernameBox.getText(), this.passwordBox.getText());
            if (acc != null && acc.isPremium()) {
                final String username = acc.getUsername();
                final String password = acc.getPassword();
                try {
                    GuiAccountScreen.error = Utils.setSessionData(username, password);
                    Resilience.getInstance().getFileManager().saveAccounts(new String[0]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                this.mc.displayGuiScreen(this.parentScreen);
            }
            else {
                this.mc.session = new Session(acc.getUsername(), "", "");
                GuiAccountScreen.error = "";
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }
}
