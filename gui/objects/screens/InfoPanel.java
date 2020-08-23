// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.objects.screens;

import com.krispdev.resilience.utilities.font.TTFRenderer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import java.text.DecimalFormat;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.Utils;

public class InfoPanel extends DefaultPanel
{
    public InfoPanel(final String name, final int x, final int y, final int x1, final int y1, final boolean visible) {
        super(name, x, y, x1, y1, visible);
    }
    
    public void draw(final int x, final int y) {
        super.draw(x, y);
        if (this.isExtended()) {
            Utils.drawRect((float)this.getX(), (float)(this.getY() + 17), (float)this.getX1(), (float)(this.getY() + 94), -1727790076);
            Resilience.getInstance().getStandardFont().drawString("X: §b" + new DecimalFormat("#.##").format(Resilience.getInstance().getInvoker().getPosX()), this.getX() + 4.5f, (float)(this.getY() + 17 + 3), -1);
            Resilience.getInstance().getStandardFont().drawString("Y: §b" + new DecimalFormat("#.##").format(Resilience.getInstance().getInvoker().getPosY()), this.getX() + 4.5f, (float)(this.getY() + 17 + 3 + 12), -1);
            Resilience.getInstance().getStandardFont().drawString("Z: §b" + new DecimalFormat("#.##").format(Resilience.getInstance().getInvoker().getPosZ()), this.getX() + 4.5f, (float)(this.getY() + 17 + 3 + 24), -1);
            final int yaw = MathHelper.floor_double(Resilience.getInstance().getInvoker().getRotationYaw() * 4.0f / 360.0f + 0.5) & 0x3;
            final char firstC = Direction.directions[yaw].charAt(0);
            final String firstLetter = String.valueOf(firstC).toUpperCase();
            final String direction = String.valueOf(firstLetter) + Direction.directions[yaw].toLowerCase().replaceFirst(String.valueOf(firstC).toLowerCase(), "");
            Resilience.getInstance().getStandardFont().drawString("Direction: §b" + direction, this.getX() + 4.5f, (float)(this.getY() + 17 + 3 + 36), -1);
            final int floorX = MathHelper.floor_double(Resilience.getInstance().getInvoker().getPosX());
            final int floorY = MathHelper.floor_double(Resilience.getInstance().getInvoker().getPosY());
            final int floorZ = MathHelper.floor_double(Resilience.getInstance().getInvoker().getPosZ());
            final Chunk currentChunck = Resilience.getInstance().getWrapper().getWorld().getChunkFromBlockCoords(floorX, floorZ);
            final BiomeGenBase biome = currentChunck.getBiomeGenForWorldCoords(floorX & 0xF, floorZ & 0xF, Resilience.getInstance().getWrapper().getWorld().getWorldChunkManager());
            final String biomeName = biome.biomeName;
            Resilience.getInstance().getStandardFont().drawString("Biome: §b" + biomeName, this.getX() + 4.5f, (float)(this.getY() + 17 + 3 + 48), -1);
            final TTFRenderer standardFont = Resilience.getInstance().getStandardFont();
            final StringBuilder sb = new StringBuilder("FPS: §b");
            Resilience.getInstance().getWrapper().getMinecraft();
            standardFont.drawString(sb.append(Minecraft.debugFPS).toString(), this.getX() + 4.5f, (float)(this.getY() + 17 + 3 + 60), -1);
        }
    }
}
