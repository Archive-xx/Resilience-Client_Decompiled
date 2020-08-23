// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.hooks;

import java.util.Collection;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import java.util.Iterator;
import net.minecraft.client.gui.FontRenderer;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;
import com.krispdev.resilience.gui.objects.screens.DefaultPanel;
import com.krispdev.resilience.gui.objects.ClickGui;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.Minecraft;
import com.krispdev.resilience.wrappers.Wrapper;
import com.krispdev.resilience.wrappers.MethodInvoker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.GuiIngame;

public class HookGuiIngame extends GuiIngame
{
    private int arrayListCount;
    private boolean go;
    public boolean display;
    private EntityPlayer toView;
    private MethodInvoker invoker;
    private Wrapper wrapper;
    private boolean once;
    private int ticks;
    
    public HookGuiIngame(final Minecraft minecraft) {
        super(minecraft);
        this.arrayListCount = 0;
        this.go = true;
        this.display = false;
        this.invoker = Resilience.getInstance().getInvoker();
        this.wrapper = Resilience.getInstance().getWrapper();
        this.once = true;
        this.ticks = 0;
    }
    
    public void renderGameOverlay(final float par1, final boolean par2, final int par3, final int par4) {
        boolean wasExtended = false;
        ++this.ticks;
        if (this.ticks == 5) {
            wasExtended = Resilience.getInstance().getClickGui().values.isExtended();
            Resilience.getInstance().getClickGui().values.setExtended(true);
            Resilience.getInstance().getModuleManager().setModuleState("GUI", true);
        }
        if (this.ticks == 20) {
            Resilience.getInstance().getClickGui().values.setExtended(wasExtended);
        }
        if (this.once && !Resilience.getInstance().isFirstTime()) {
            this.once = false;
            Resilience.getInstance().getLogger().infoChat("Want to know how to chat in the IRC? Put the \"@\" sign before your message!");
        }
        if (this.display) {
            this.invoker.displayScreen((GuiScreen)new GuiInventory(this.toView));
            this.display = false;
        }
        if (this.go) {
            if (Resilience.getInstance().isFirstTime()) {
                Resilience.getInstance().getLogger().infoChat("Welcome to " + Resilience.getInstance().getName() + "!");
                Resilience.getInstance().getLogger().infoChat("To open the GUI hit the \"Right Shift\" key, and to open the console hit the \"Minus\" key (\"-\").");
                Resilience.getInstance().getLogger().infoChat("Remember to right click a button to get tons of great options :D");
                Resilience.getInstance().getLogger().infoChat("To chat in the IRC, type \"@\" before the message.");
                Resilience.getInstance().getLogger().infoChat("Enjoy! - Krisp");
            }
            this.go = false;
        }
        final int prevArrayListCount = this.arrayListCount;
        this.arrayListCount = 0;
        final ScaledResolution var5 = new ScaledResolution(this.wrapper.getGameSettings(), this.invoker.getDisplayWidth(), this.invoker.getDisplayHeight());
        final int var6 = this.invoker.getScaledWidth(var5);
        final int var7 = this.invoker.getScaledHeight(var5);
        final FontRenderer var8 = this.wrapper.getFontRenderer();
        this.invoker.setupOverlayRendering();
        GL11.glEnable(3042);
        GL11.glDisable(2896);
        if (Resilience.getInstance().isEnabled()) {
            for (final DefaultPanel panel : ClickGui.windows) {
                if (panel.isPinned() && !(this.invoker.getCurrentScreen() instanceof ClickGui)) {
                    GL11.glPushMatrix();
                    GL11.glDisable(2896);
                    panel.draw(-1, -1);
                    GL11.glPopMatrix();
                }
            }
            if (Resilience.getInstance().getValues().enabledModsEnabled) {
                GL11.glPushMatrix();
                GL11.glDisable(2896);
                if (prevArrayListCount > 0) {
                    drawRect(0, 0, 75, prevArrayListCount * 12 + 4, -2013265920);
                }
                GL11.glPopMatrix();
            }
            if (Resilience.getInstance().getValues().enabledModsEnabled) {
                for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
                    if (mod.isEnabled() && mod.getCategory() != ModuleCategory.GUI && mod.isVisible()) {
                        GL11.glPushMatrix();
                        GL11.glDisable(2896);
                        Resilience.getInstance().getModListFont().drawString(mod.getDisplayName(), 2.0f, (float)(this.arrayListCount * 12 + 2), -6638593);
                        GL11.glPopMatrix();
                        ++this.arrayListCount;
                    }
                }
            }
            if (Resilience.getInstance().getValues().noFireEffectEnabled && this.invoker.isBurning()) {
                Resilience.getInstance().getWrapper().getFontRenderer().drawStringWithShadow("You're on fire", (float)(this.invoker.getDisplayWidth() / 2 - Resilience.getInstance().getWrapper().getFontRenderer().getStringWidth("You're on fire") - 4), (float)(this.invoker.getDisplayHeight() / 2 - Resilience.getInstance().getWrapper().getFontRenderer().FONT_HEIGHT - 4), -65536);
            }
            if (Resilience.getInstance().getValues().potionEffectsEnabled) {
                this.renderPotions();
            }
        }
        super.renderGameOverlay(par1, par2, par3, par4);
    }
    
    private void renderPotions() {
        GL11.glPushMatrix();
        final int var1 = Resilience.getInstance().getValues().enabledModsEnabled ? 76 : 1;
        int var2 = 0;
        final boolean var3 = true;
        final Collection var4 = this.wrapper.getPlayer().getActivePotionEffects();
        if (!var4.isEmpty()) {
            final ResourceLocation rL = new ResourceLocation("textures/gui/container/inventory.png");
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(2896);
            int var5 = 33;
            if (var4.size() > 5) {
                var5 = 132 / (var4.size() - 1);
            }
            for (final PotionEffect var7 : this.wrapper.getPlayer().getActivePotionEffects()) {
                final Potion var8 = Potion.potionTypes[var7.getPotionID()];
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                this.wrapper.getMinecraft().getTextureManager().bindTexture(rL);
                if (var8.hasStatusIcon()) {
                    final int var9 = var8.getStatusIconIndex();
                    this.drawTexturedModalRect(var1 + 6, var2 + 7, 0 + var9 % 8 * 18, 198 + var9 / 8 * 18, 18, 18);
                }
                String var10 = I18n.format(var8.getName(), new Object[0]);
                if (var7.getAmplifier() == 1) {
                    var10 = String.valueOf(var10) + " II";
                }
                else if (var7.getAmplifier() == 2) {
                    var10 = String.valueOf(var10) + " III";
                }
                else if (var7.getAmplifier() == 3) {
                    var10 = String.valueOf(var10) + " IV";
                }
                this.wrapper.getFontRenderer().drawStringWithShadow(var10, (float)(var1 + 10 + 18), (float)(var2 + 6), 16777215);
                final String var11 = Potion.getDurationString(var7);
                this.wrapper.getFontRenderer().drawStringWithShadow(var11, (float)(var1 + 10 + 18), (float)(var2 + 6 + 10), 8355711);
                var2 += var5;
            }
        }
        GL11.glPopMatrix();
    }
    
    public void displayInv(final EntityPlayer e) {
        this.toView = e;
        this.display = true;
    }
}
