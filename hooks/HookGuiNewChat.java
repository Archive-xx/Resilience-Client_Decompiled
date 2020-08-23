// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.hooks;

import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import com.krispdev.resilience.relations.Friend;
import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.gui.ChatLine;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;

public class HookGuiNewChat extends GuiNewChat
{
    public HookGuiNewChat(final Minecraft par1Minecraft) {
        super(par1Minecraft);
    }
    
    public void func_146230_a(final int p_146230_1_) {
        if (this.field_146247_f.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
            final int var2 = this.func_146232_i();
            boolean var3 = false;
            int var4 = 0;
            final int var5 = this.field_146253_i.size();
            final float var6 = this.field_146247_f.gameSettings.chatOpacity * 0.9f + 0.1f;
            if (var5 > 0) {
                if (this.func_146241_e()) {
                    var3 = true;
                }
                final float var7 = this.func_146244_h();
                final int var8 = MathHelper.ceiling_float_int(this.func_146228_f() / var7);
                GL11.glPushMatrix();
                GL11.glTranslatef(2.0f, 20.0f, 0.0f);
                GL11.glScalef(var7, var7, 1.0f);
                for (int var9 = 0; var9 + this.field_146250_j < this.field_146253_i.size() && var9 < var2; ++var9) {
                    final ChatLine var10 = this.field_146253_i.get(var9 + this.field_146250_j);
                    if (var10 != null) {
                        final int var11 = p_146230_1_ - var10.getUpdatedCounter();
                        if (var11 < 200 || var3) {
                            double var12 = var11 / 200.0;
                            var12 = 1.0 - var12;
                            var12 *= 10.0;
                            if (var12 < 0.0) {
                                var12 = 0.0;
                            }
                            if (var12 > 1.0) {
                                var12 = 1.0;
                            }
                            var12 *= var12;
                            int var13 = (int)(255.0 * var12);
                            if (var3) {
                                var13 = 255;
                            }
                            var13 *= (int)var6;
                            ++var4;
                            if (var13 > 3) {
                                final byte var14 = 0;
                                final int var15 = -var9 * (Resilience.getInstance().getValues().niceChatEnabled ? 12 : 9);
                                if (Resilience.getInstance().getValues().niceChatEnabled) {
                                    Utils.drawRect((float)var14, (float)(var15 - 12), (float)(var14 + var8 + 4), (float)var15, var13 / 2 << 24);
                                }
                                else {
                                    drawRect((int)var14, var15 - 9, var14 + var8 + 4, var15, var13 / 2 << 24);
                                }
                                String var16 = var10.func_151461_a().getFormattedText();
                                var16 = var16.replaceAll("§r", "");
                                for (final Friend friend : Friend.friendList) {
                                    if (StringUtils.containsIgnoreCase((CharSequence)var16, (CharSequence)friend.getName())) {
                                        var16 = var16.replaceAll("(?i)" + friend.getName(), friend.getAlias());
                                    }
                                }
                                var16 = var16.replaceAll("§k", "");
                                var16 = var16.replaceAll("§l", "");
                                var16 = var16.replaceAll("§m", "");
                                var16 = var16.replaceAll("§n", "");
                                var16 = var16.replaceAll("§o", "");
                                if (Resilience.getInstance().getValues().nameProtectEnabled && StringUtils.containsIgnoreCase((CharSequence)var16, (CharSequence)Resilience.getInstance().getInvoker().getSessionUsername())) {
                                    var16 = var16.replaceAll("(?i)" + Resilience.getInstance().getInvoker().getSessionUsername(), Resilience.getInstance().getValues().nameProtectAlias.getValue());
                                }
                                if (Resilience.getInstance().getValues().niceChatEnabled) {
                                    Resilience.getInstance().getChatFont().drawStringWithShadow(var16.replaceAll("§", "§"), (float)(var14 + 2), (float)(var15 - 12), 16777215 + (var13 << 24));
                                }
                                else {
                                    this.field_146247_f.fontRenderer.drawStringWithShadow(var16, (float)var14, (float)(var15 - 8), 16777215 + (var13 << 24));
                                }
                                GL11.glDisable(3008);
                            }
                        }
                    }
                }
                if (var3) {
                    final int var9 = this.field_146247_f.fontRenderer.FONT_HEIGHT;
                    GL11.glTranslatef(-3.0f, 0.0f, 0.0f);
                    final int var17 = var5 * var9 + var5;
                    final int var11 = var4 * var9 + var4;
                    final int var18 = this.field_146250_j * var11 / var5;
                    final int var19 = var11 * var11 / var17;
                    if (var17 != var11) {
                        final int var13 = (var18 > 0) ? 170 : 96;
                        final int var20 = this.field_146251_k ? 13382451 : 3355562;
                        drawRect(0, -var18, 2, -var18 - var19, var20 + (var13 << 24));
                        drawRect(2, -var18, 1, -var18 - var19, 13421772 + (var13 << 24));
                    }
                }
                GL11.glPopMatrix();
            }
        }
    }
}
