// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import com.krispdev.resilience.utilities.Utils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Mouse;
import java.util.Iterator;
import com.krispdev.resilience.command.Command;
import com.krispdev.resilience.Resilience;
import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.gui.objects.other.GuiCustomFontField;
import java.net.URI;
import net.minecraft.client.gui.GuiScreen;

public class ResilienceConsole extends GuiScreen
{
    private boolean field_73897_d;
    private boolean field_73905_m;
    private int field_73903_n;
    private URI clickedURI;
    protected GuiCustomFontField inputField;
    private String defaultInputFieldText;
    
    public ResilienceConsole() {
        this.defaultInputFieldText = "";
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        (this.inputField = new GuiCustomFontField(Resilience.getInstance().getStandardFont(), 79, 18, this.width - 75, 12)).setMaxStringLength(100);
        this.inputField.setEnableBackgroundDrawing(false);
        this.inputField.setFocused(true);
        this.inputField.setText(this.defaultInputFieldText);
        this.inputField.setCanLoseFocus(false);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
    
    public void updateScreen() {
        this.inputField.updateCursorCounter();
    }
    
    protected void keyTyped(final char par1, final int par2) {
        this.field_73905_m = false;
        if (par2 != 15) {
            this.field_73897_d = false;
        }
        if (par2 == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
        else if (par2 != 28 && par2 != 156) {
            this.inputField.textboxKeyTyped(par1, par2);
        }
        else {
            final String var3 = this.inputField.getText().trim();
            if (var3.length() > 0) {
                for (final Command cmd : Command.cmdList) {
                    try {
                        if (!var3.startsWith(cmd.getWords())) {
                            continue;
                        }
                        final boolean result = cmd.recieveCommand(var3);
                        if (result) {
                            this.mc.displayGuiScreen((GuiScreen)null);
                            return;
                        }
                        continue;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Resilience.getInstance().getLogger().warningChat("§fUnkown command \"§b".concat(var3).concat("§f\". Type \"§bhelp§f\" for help"));
            }
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    public void func_146274_d() {
        super.handleMouseInput();
        int var1 = Mouse.getEventDWheel();
        if (var1 != 0) {
            if (var1 > 1) {
                var1 = 1;
            }
            if (var1 < -1) {
                var1 = -1;
            }
            if (!isShiftKeyDown()) {
                var1 *= 7;
            }
        }
    }
    
    protected void mouseClicked(final int par1, final int par2, final int par3) {
        this.inputField.mouseClicked(par1, par2, par3);
        super.mouseClicked(par1, par2, par3);
    }
    
    public void confirmClicked(final boolean par1, final int par2) {
        if (par2 == 0) {
            if (par1) {
                this.func_73896_a(this.clickedURI);
            }
            this.clickedURI = null;
            this.mc.displayGuiScreen((GuiScreen)this);
        }
    }
    
    private void func_73896_a(final URI par1URI) {
        try {
            final Class var2 = Class.forName("java.awt.Desktop");
            final Object var3 = var2.getMethod("getDesktop", (Class[])new Class[0]).invoke(null, new Object[0]);
            var2.getMethod("browse", URI.class).invoke(var3, par1URI);
        }
        catch (Throwable var4) {
            var4.printStackTrace();
        }
    }
    
    public void completePlayerName() {
    }
    
    private void func_73893_a(final String par1Str, final String par2Str) {
        if (par1Str.length() >= 1) {
            this.field_73905_m = true;
        }
    }
    
    public void drawScreen(final int par1, final int par2, final float par3) {
        GL11.glPushMatrix();
        GL11.glDisable(2896);
        Utils.drawBetterRect(75.0, 14.0, (double)(this.width - 75), 30.0, -1442840576, -1437708722);
        this.inputField.drawTextBox();
        final String msg = this.inputField.getText();
        int theHeight = 35;
        for (final Command cmd : Command.cmdList) {
            if (cmd.getWords().startsWith(msg) && msg.length() > 0) {
                final String toDisplay = String.valueOf(cmd.getWords()) + cmd.getExtras() + " | " + cmd.getDesc();
                Utils.drawBetterRect(75.0, (double)(theHeight - 3), (double)(this.width - 75), (double)(theHeight + 12), -285212673, -299489754);
                Resilience.getInstance().getStandardFont().drawString("§f" + toDisplay, 79.0f, (float)(theHeight - 2), -15384811);
                Resilience.getInstance().getStandardFont().drawString("§b" + msg, 79.0f, (float)(theHeight - 2), -15584170);
                theHeight += 16;
            }
            else {
                if (!msg.startsWith(cmd.getWords())) {
                    continue;
                }
                final String toDisplay = "§b" + cmd.getWords() + cmd.getExtras() + " §f| " + cmd.getDesc();
                Utils.drawBetterRect(75.0, (double)(theHeight - 3), (double)(this.width - 75), (double)(theHeight + 12), -285212673, -299489754);
                Resilience.getInstance().getStandardFont().drawString(toDisplay, 79.0f, (float)(theHeight - 2), -15581419);
                theHeight += 16;
            }
        }
        if (msg.length() == 0) {
            Resilience.getInstance().getStandardFont().drawString("§fType \"§bhelp§f\" to get help.", 79.0f, 35.0f, -15584170);
        }
        GL11.glPopMatrix();
        super.drawScreen(par1, par2, par3);
    }
    
    public boolean doesGuiPauseGame() {
        return true;
    }
}
