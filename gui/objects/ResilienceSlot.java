// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects;

import net.minecraft.client.renderer.OpenGlHelper;
import com.krispdev.resilience.Resilience;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Mouse;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.Minecraft;

public abstract class ResilienceSlot
{
    private final Minecraft field_148161_k;
    protected int field_148155_a;
    private int field_148158_l;
    protected int field_148153_b;
    protected int field_148154_c;
    protected int field_148151_d;
    protected int field_148152_e;
    protected final int field_148149_f;
    private int field_148159_m;
    private int field_148156_n;
    protected int field_148150_g;
    protected int field_148162_h;
    protected boolean field_148163_i;
    private float field_148157_o;
    private float field_148170_p;
    private float field_148169_q;
    private int field_148168_r;
    private long field_148167_s;
    private boolean field_148166_t;
    private boolean field_148165_u;
    protected int field_148160_j;
    private boolean field_148164_v;
    private static final String __OBFID = "CL_00000679";
    
    public ResilienceSlot(final Minecraft par1Minecraft, final int par2, final int par3, final int par4, final int par5, final int par6) {
        this.field_148163_i = true;
        this.field_148157_o = -2.0f;
        this.field_148168_r = -1;
        this.field_148166_t = true;
        this.field_148164_v = true;
        this.field_148161_k = par1Minecraft;
        this.field_148155_a = par2;
        this.field_148158_l = par3;
        this.field_148153_b = par4;
        this.field_148154_c = par5;
        this.field_148149_f = par6;
        this.field_148152_e = 0;
        this.field_148151_d = par2;
    }
    
    public void func_148122_a(final int p_148122_1_, final int p_148122_2_, final int p_148122_3_, final int p_148122_4_) {
        this.field_148155_a = p_148122_1_;
        this.field_148158_l = p_148122_2_;
        this.field_148153_b = p_148122_3_;
        this.field_148154_c = p_148122_4_;
        this.field_148152_e = 0;
        this.field_148151_d = p_148122_1_;
    }
    
    public void func_148130_a(final boolean p_148130_1_) {
        this.field_148166_t = p_148130_1_;
    }
    
    protected void func_148133_a(final boolean p_148133_1_, final int p_148133_2_) {
        this.field_148165_u = p_148133_1_;
        this.field_148160_j = p_148133_2_;
        if (!p_148133_1_) {
            this.field_148160_j = 0;
        }
    }
    
    protected abstract int getSize();
    
    protected abstract void elementClicked(final int p0, final boolean p1, final int p2, final int p3);
    
    protected abstract boolean isSelected(final int p0);
    
    protected int func_148138_e() {
        return this.getSize() * this.field_148149_f + this.field_148160_j;
    }
    
    protected abstract void drawBackground();
    
    protected abstract void drawSlot(final int p0, final int p1, final int p2, final int p3, final Tessellator p4, final int p5, final int p6);
    
    protected void func_148129_a(final int p_148129_1_, final int p_148129_2_, final Tessellator p_148129_3_) {
    }
    
    protected void func_148132_a(final int p_148132_1_, final int p_148132_2_) {
    }
    
    protected void func_148142_b(final int p_148142_1_, final int p_148142_2_) {
    }
    
    public int func_148124_c(final int p_148124_1_, final int p_148124_2_) {
        final int var3 = this.field_148152_e + this.field_148155_a / 2 - this.func_148139_c() / 2;
        final int var4 = this.field_148152_e + this.field_148155_a / 2 + this.func_148139_c() / 2;
        final int var5 = p_148124_2_ - this.field_148153_b - this.field_148160_j + (int)this.field_148169_q - 4;
        final int var6 = var5 / this.field_148149_f;
        return (p_148124_1_ < this.func_148137_d() && p_148124_1_ >= var3 && p_148124_1_ <= var4 && var6 >= 0 && var5 >= 0 && var6 < this.getSize()) ? var6 : -1;
    }
    
    public void func_148134_d(final int p_148134_1_, final int p_148134_2_) {
        this.field_148159_m = p_148134_1_;
        this.field_148156_n = p_148134_2_;
    }
    
    private void func_148121_k() {
        int var1 = this.func_148135_f();
        if (var1 < 0) {
            var1 /= 2;
        }
        if (!this.field_148163_i && var1 < 0) {
            var1 = 0;
        }
        if (this.field_148169_q < 0.0f) {
            this.field_148169_q = 0.0f;
        }
        if (this.field_148169_q > var1) {
            this.field_148169_q = (float)var1;
        }
    }
    
    public int func_148135_f() {
        return this.func_148138_e() - (this.field_148154_c - this.field_148153_b - 4);
    }
    
    public int func_148148_g() {
        return (int)this.field_148169_q;
    }
    
    public boolean func_148141_e(final int p_148141_1_) {
        return p_148141_1_ >= this.field_148153_b && p_148141_1_ <= this.field_148154_c;
    }
    
    public void func_148145_f(final int p_148145_1_) {
        this.field_148169_q += p_148145_1_;
        this.func_148121_k();
        this.field_148157_o = -2.0f;
    }
    
    public void func_148147_a(final GuiButton p_148147_1_) {
        if (p_148147_1_.enabled) {
            if (p_148147_1_.id == this.field_148159_m) {
                this.field_148169_q -= this.field_148149_f * 2 / 3;
                this.field_148157_o = -2.0f;
                this.func_148121_k();
            }
            else if (p_148147_1_.id == this.field_148156_n) {
                this.field_148169_q += this.field_148149_f * 2 / 3;
                this.field_148157_o = -2.0f;
                this.func_148121_k();
            }
        }
    }
    
    public void func_148128_a(final int p_148128_1_, final int p_148128_2_, final float p_148128_3_) {
        this.field_148150_g = p_148128_1_;
        this.field_148162_h = p_148128_2_;
        this.drawBackground();
        final int var4 = this.getSize();
        final int var5 = this.func_148137_d();
        final int var6 = var5 + 6;
        if (p_148128_1_ > this.field_148152_e && p_148128_1_ < this.field_148151_d && p_148128_2_ > this.field_148153_b && p_148128_2_ < this.field_148154_c) {
            if (Mouse.isButtonDown(0) && this.func_148125_i()) {
                if (this.field_148157_o == -1.0f) {
                    boolean var7 = true;
                    if (p_148128_2_ >= this.field_148153_b && p_148128_2_ <= this.field_148154_c) {
                        final int var8 = this.field_148155_a / 2 - this.func_148139_c() / 2;
                        final int var9 = this.field_148155_a / 2 + this.func_148139_c() / 2;
                        final int var10 = p_148128_2_ - this.field_148153_b - this.field_148160_j + (int)this.field_148169_q - 4;
                        final int var11 = var10 / this.field_148149_f;
                        if (p_148128_1_ >= var8 && p_148128_1_ <= var9 && var11 >= 0 && var10 >= 0 && var11 < var4) {
                            final boolean var12 = var11 == this.field_148168_r && Minecraft.getSystemTime() - this.field_148167_s < 250L;
                            this.elementClicked(var11, var12, p_148128_1_, p_148128_2_);
                            this.field_148168_r = var11;
                            this.field_148167_s = Minecraft.getSystemTime();
                        }
                        else if (p_148128_1_ >= var8 && p_148128_1_ <= var9 && var10 < 0) {
                            this.func_148132_a(p_148128_1_ - var8, p_148128_2_ - this.field_148153_b + (int)this.field_148169_q - 4);
                            var7 = false;
                        }
                        if (p_148128_1_ >= var5 && p_148128_1_ <= var6) {
                            this.field_148170_p = -1.0f;
                            int var13 = this.func_148135_f();
                            if (var13 < 1) {
                                var13 = 1;
                            }
                            int var14 = (int)((this.field_148154_c - this.field_148153_b) * (this.field_148154_c - this.field_148153_b) / (float)this.func_148138_e());
                            if (var14 < 32) {
                                var14 = 32;
                            }
                            if (var14 > this.field_148154_c - this.field_148153_b - 8) {
                                var14 = this.field_148154_c - this.field_148153_b - 8;
                            }
                            this.field_148170_p /= (this.field_148154_c - this.field_148153_b - var14) / (float)var13;
                        }
                        else {
                            this.field_148170_p = 1.0f;
                        }
                        if (var7) {
                            this.field_148157_o = (float)p_148128_2_;
                        }
                        else {
                            this.field_148157_o = -2.0f;
                        }
                    }
                    else {
                        this.field_148157_o = -2.0f;
                    }
                }
                else if (this.field_148157_o >= 0.0f) {
                    this.field_148169_q -= (p_148128_2_ - this.field_148157_o) * this.field_148170_p;
                    this.field_148157_o = (float)p_148128_2_;
                }
            }
            else {
                while (!this.field_148161_k.gameSettings.touchscreen && Mouse.next()) {
                    int var15 = Mouse.getEventDWheel();
                    if (var15 != 0) {
                        if (var15 > 0) {
                            var15 = -1;
                        }
                        else if (var15 < 0) {
                            var15 = 1;
                        }
                        this.field_148169_q += var15 * this.field_148149_f / 2;
                    }
                    this.field_148161_k.currentScreen.handleMouseInput();
                }
                this.field_148157_o = -1.0f;
            }
        }
        this.func_148121_k();
        GL11.glDisable(2896);
        GL11.glDisable(2912);
        final Tessellator var16 = Tessellator.instance;
        this.field_148161_k.getTextureManager().bindTexture(Resilience.getInstance().getValues().altBackground);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final float var17 = 32.0f;
        var16.startDrawingQuads();
        var16.setColorOpaque_I(2105376);
        var16.addVertexWithUV((double)this.field_148152_e, (double)this.field_148154_c, 0.0, (double)(this.field_148152_e / var17), (double)((this.field_148154_c + (int)this.field_148169_q) / var17));
        var16.addVertexWithUV((double)this.field_148151_d, (double)this.field_148154_c, 0.0, (double)(this.field_148151_d / var17), (double)((this.field_148154_c + (int)this.field_148169_q) / var17));
        var16.addVertexWithUV((double)this.field_148151_d, (double)this.field_148153_b, 0.0, (double)(this.field_148151_d / var17), (double)((this.field_148153_b + (int)this.field_148169_q) / var17));
        var16.addVertexWithUV((double)this.field_148152_e, (double)this.field_148153_b, 0.0, (double)(this.field_148152_e / var17), (double)((this.field_148153_b + (int)this.field_148169_q) / var17));
        var16.draw();
        final int var9 = this.field_148152_e + this.field_148155_a / 2 - this.func_148139_c() / 2 + 2;
        final int var10 = this.field_148153_b + 4 - (int)this.field_148169_q;
        if (this.field_148165_u) {
            this.func_148129_a(var9, var10, var16);
        }
        this.func_148120_b(var9, var10, p_148128_1_, p_148128_2_);
        GL11.glDisable(2929);
        final byte var18 = 4;
        this.func_148136_c(0, this.field_148153_b, 255, 255);
        this.func_148136_c(this.field_148154_c, this.field_148158_l, 255, 255);
        GL11.glEnable(3042);
        OpenGlHelper.glBlendFunc(770, 771, 0, 1);
        GL11.glDisable(3008);
        GL11.glShadeModel(7425);
        GL11.glDisable(3553);
        var16.startDrawingQuads();
        var16.setColorRGBA_I(0, 0);
        var16.addVertexWithUV((double)this.field_148152_e, (double)(this.field_148153_b + var18), 0.0, 0.0, 1.0);
        var16.addVertexWithUV((double)this.field_148151_d, (double)(this.field_148153_b + var18), 0.0, 1.0, 1.0);
        var16.setColorRGBA_I(0, 255);
        var16.addVertexWithUV((double)this.field_148151_d, (double)this.field_148153_b, 0.0, 1.0, 0.0);
        var16.addVertexWithUV((double)this.field_148152_e, (double)this.field_148153_b, 0.0, 0.0, 0.0);
        var16.draw();
        var16.startDrawingQuads();
        var16.setColorRGBA_I(0, 255);
        var16.addVertexWithUV((double)this.field_148152_e, (double)this.field_148154_c, 0.0, 0.0, 1.0);
        var16.addVertexWithUV((double)this.field_148151_d, (double)this.field_148154_c, 0.0, 1.0, 1.0);
        var16.setColorRGBA_I(0, 0);
        var16.addVertexWithUV((double)this.field_148151_d, (double)(this.field_148154_c - var18), 0.0, 1.0, 0.0);
        var16.addVertexWithUV((double)this.field_148152_e, (double)(this.field_148154_c - var18), 0.0, 0.0, 0.0);
        var16.draw();
        int var13 = this.func_148135_f();
        if (var13 > 0) {
            int var14 = (this.field_148154_c - this.field_148153_b) * (this.field_148154_c - this.field_148153_b) / this.func_148138_e();
            if (var14 < 32) {
                var14 = 32;
            }
            if (var14 > this.field_148154_c - this.field_148153_b - 8) {
                var14 = this.field_148154_c - this.field_148153_b - 8;
            }
            int var19 = (int)this.field_148169_q * (this.field_148154_c - this.field_148153_b - var14) / var13 + this.field_148153_b;
            if (var19 < this.field_148153_b) {
                var19 = this.field_148153_b;
            }
            var16.startDrawingQuads();
            var16.setColorRGBA_I(0, 255);
            var16.addVertexWithUV((double)var5, (double)this.field_148154_c, 0.0, 0.0, 1.0);
            var16.addVertexWithUV((double)var6, (double)this.field_148154_c, 0.0, 1.0, 1.0);
            var16.addVertexWithUV((double)var6, (double)this.field_148153_b, 0.0, 1.0, 0.0);
            var16.addVertexWithUV((double)var5, (double)this.field_148153_b, 0.0, 0.0, 0.0);
            var16.draw();
            var16.startDrawingQuads();
            var16.setColorRGBA_I(8421504, 255);
            var16.addVertexWithUV((double)var5, (double)(var19 + var14), 0.0, 0.0, 1.0);
            var16.addVertexWithUV((double)var6, (double)(var19 + var14), 0.0, 1.0, 1.0);
            var16.addVertexWithUV((double)var6, (double)var19, 0.0, 1.0, 0.0);
            var16.addVertexWithUV((double)var5, (double)var19, 0.0, 0.0, 0.0);
            var16.draw();
            var16.startDrawingQuads();
            var16.setColorRGBA_I(12632256, 255);
            var16.addVertexWithUV((double)var5, (double)(var19 + var14 - 1), 0.0, 0.0, 1.0);
            var16.addVertexWithUV((double)(var6 - 1), (double)(var19 + var14 - 1), 0.0, 1.0, 1.0);
            var16.addVertexWithUV((double)(var6 - 1), (double)var19, 0.0, 1.0, 0.0);
            var16.addVertexWithUV((double)var5, (double)var19, 0.0, 0.0, 0.0);
            var16.draw();
        }
        this.func_148142_b(p_148128_1_, p_148128_2_);
        GL11.glEnable(3553);
        GL11.glShadeModel(7424);
        GL11.glEnable(3008);
        GL11.glDisable(3042);
    }
    
    public void func_148143_b(final boolean p_148143_1_) {
        this.field_148164_v = p_148143_1_;
    }
    
    public boolean func_148125_i() {
        return this.field_148164_v;
    }
    
    public int func_148139_c() {
        return 220;
    }
    
    protected void func_148120_b(final int p_148120_1_, final int p_148120_2_, final int p_148120_3_, final int p_148120_4_) {
        final int var5 = this.getSize();
        final Tessellator var6 = Tessellator.instance;
        for (int var7 = 0; var7 < var5; ++var7) {
            final int var8 = p_148120_2_ + var7 * this.field_148149_f + this.field_148160_j;
            final int var9 = this.field_148149_f - 4;
            if (var8 <= this.field_148154_c && var8 + var9 >= this.field_148153_b) {
                if (this.field_148166_t && this.isSelected(var7)) {
                    final int var10 = this.field_148152_e + (this.field_148155_a / 2 - this.func_148139_c() / 2);
                    final int var11 = this.field_148152_e + this.field_148155_a / 2 + this.func_148139_c() / 2;
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glDisable(3553);
                    var6.startDrawingQuads();
                    var6.setColorOpaque_I(8421504);
                    var6.addVertexWithUV((double)var10, (double)(var8 + var9 + 2), 0.0, 0.0, 1.0);
                    var6.addVertexWithUV((double)var11, (double)(var8 + var9 + 2), 0.0, 1.0, 1.0);
                    var6.addVertexWithUV((double)var11, (double)(var8 - 2), 0.0, 1.0, 0.0);
                    var6.addVertexWithUV((double)var10, (double)(var8 - 2), 0.0, 0.0, 0.0);
                    var6.setColorOpaque_I(0);
                    var6.addVertexWithUV((double)(var10 + 1), (double)(var8 + var9 + 1), 0.0, 0.0, 1.0);
                    var6.addVertexWithUV((double)(var11 - 1), (double)(var8 + var9 + 1), 0.0, 1.0, 1.0);
                    var6.addVertexWithUV((double)(var11 - 1), (double)(var8 - 1), 0.0, 1.0, 0.0);
                    var6.addVertexWithUV((double)(var10 + 1), (double)(var8 - 1), 0.0, 0.0, 0.0);
                    var6.draw();
                    GL11.glEnable(3553);
                }
                this.drawSlot(var7, p_148120_1_, var8, var9, var6, p_148120_3_, p_148120_4_);
            }
        }
    }
    
    protected int func_148137_d() {
        return this.field_148155_a / 2 + 124;
    }
    
    private void func_148136_c(final int p_148136_1_, final int p_148136_2_, final int p_148136_3_, final int p_148136_4_) {
        final Tessellator var5 = Tessellator.instance;
        this.field_148161_k.getTextureManager().bindTexture(Resilience.getInstance().getValues().altBackground);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final float var6 = 32.0f;
        var5.startDrawingQuads();
        var5.setColorRGBA_I(4210752, p_148136_4_);
        var5.addVertexWithUV((double)this.field_148152_e, (double)p_148136_2_, 0.0, 0.0, (double)(p_148136_2_ / var6));
        var5.addVertexWithUV((double)(this.field_148152_e + this.field_148155_a), (double)p_148136_2_, 0.0, (double)(this.field_148155_a / var6), (double)(p_148136_2_ / var6));
        var5.setColorRGBA_I(4210752, p_148136_3_);
        var5.addVertexWithUV((double)(this.field_148152_e + this.field_148155_a), (double)p_148136_1_, 0.0, (double)(this.field_148155_a / var6), (double)(p_148136_1_ / var6));
        var5.addVertexWithUV((double)this.field_148152_e, (double)p_148136_1_, 0.0, 0.0, (double)(p_148136_1_ / var6));
        var5.draw();
    }
    
    public void func_148140_g(final int p_148140_1_) {
        this.field_148152_e = p_148140_1_;
        this.field_148151_d = p_148140_1_ + this.field_148155_a;
    }
    
    public int func_148146_j() {
        return this.field_148149_f;
    }
    
    public void registerScrollButtons(final int par1, final int par2) {
        this.func_148134_d(par1, par2);
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        this.func_148128_a(i, j, f);
    }
}
