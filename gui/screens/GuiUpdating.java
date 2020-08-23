// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.gui.screens;

import com.krispdev.resilience.utilities.Utils;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.file.ThreadUpdateGame;
import net.minecraft.client.gui.GuiScreen;

public class GuiUpdating extends GuiScreen
{
    private int count;
    private ThreadUpdateGame downloadFile;
    public static boolean isDone;
    public static boolean isDownloading;
    public static boolean isExtracting;
    public static boolean isDeleting;
    
    static {
        GuiUpdating.isDone = false;
        GuiUpdating.isDownloading = false;
        GuiUpdating.isExtracting = false;
        GuiUpdating.isDeleting = false;
    }
    
    public GuiUpdating() {
        this.count = 0;
    }
    
    public void initGui() {
        try {
            final StringBuilder append = new StringBuilder("http://krispdev.com/updateDownloadIncrement.php?username=").append(this.mc.session.getUsername()).append("&update=");
            Resilience.getInstance().getValues().getClass();
            final URL obj = new URL(append.append(24).toString());
            final HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode();
            con.disconnect();
            Resilience.getInstance().getFileManager().downloadFile(new File("Resilience.zip"), new URL("http://resilience.krispdev.com/Resilience.zip"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void drawScreen(final int i, final int j, final float f) {
        drawRect(0, 0, Resilience.getInstance().getInvoker().getWidth(), Resilience.getInstance().getInvoker().getHeight(), -14671840);
        Utils.drawRect(50.0f, (float)(Resilience.getInstance().getInvoker().getHeight() - 40), (float)(Resilience.getInstance().getInvoker().getWidth() - 50), (float)(Resilience.getInstance().getInvoker().getHeight() - 15), -12369085);
        final float onePixel = (Resilience.getInstance().getInvoker().getWidth() - 100) / 100.0f;
        Utils.drawRect(50.0f, (float)(Resilience.getInstance().getInvoker().getHeight() - 40), onePixel * ThreadUpdateGame.getPercentDone() + 50.0f, (float)(Resilience.getInstance().getInvoker().getHeight() - 15), -13421569);
        super.drawScreen(i, j, f);
        Resilience.getInstance().getPanelTitleFont().drawCenteredString(GuiUpdating.isDone ? "Done. Please restart your game for changes to take effect." : (GuiUpdating.isDownloading ? ("Downloading - " + Math.round(ThreadUpdateGame.getPercentDone()) + "%") : (GuiUpdating.isExtracting ? ("Extracting - " + Math.round(ThreadUpdateGame.getPercentDone()) + "%") : (GuiUpdating.isDeleting ? "Deleting residue zip files..." : "Starting..."))), (float)(Resilience.getInstance().getInvoker().getWidth() / 2), 4.0f, -1);
    }
}
