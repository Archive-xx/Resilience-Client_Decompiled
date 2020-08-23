// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.file;

import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.Sys;
import java.net.HttpURLConnection;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import com.krispdev.resilience.gui.screens.GuiUpdating;
import java.io.File;
import java.net.URL;

public class ThreadUpdateGame extends Thread
{
    private URL url;
    private File location;
    private static float percent;
    private float sizeDone;
    
    static {
        ThreadUpdateGame.percent = 0.0f;
    }
    
    public ThreadUpdateGame(final URL url, final File location) {
        this.url = url;
        this.location = location;
    }
    
    @Override
    public void run() {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        Label_0260: {
            try {
                GuiUpdating.isDownloading = true;
                in = new BufferedInputStream(this.url.openStream());
                fout = new FileOutputStream(this.location);
                final HttpURLConnection con = (HttpURLConnection)this.url.openConnection();
                final byte[] data = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    Thread.sleep(1L);
                    fout.write(data, 0, count);
                    this.sizeDone = (float)this.location.length();
                    ThreadUpdateGame.percent = this.sizeDone / con.getContentLength() * 100.0f;
                }
                con.disconnect();
            }
            catch (Exception e) {
                Sys.openURL("http://resilience.krispdev.com/downloads");
                e.printStackTrace();
                break Label_0260;
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                if (fout != null) {
                    try {
                        fout.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                ThreadUpdateGame.percent = 0.0f;
            }
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            ThreadUpdateGame.percent = 0.0f;
        }
        GuiUpdating.isExtracting = true;
        GuiUpdating.isDownloading = false;
        final String version = "Resilience";
        try {
            final File dir = new File("versions" + File.separator + version);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            final File jar = new File("versions" + File.separator + "Resilience" + File.separator + version + ".jar");
            final File zip = new File("Resilience.zip");
            final OutputStream out1 = new FileOutputStream("versions" + File.separator + "Resilience" + File.separator + version + ".jar");
            final OutputStream out2 = new FileOutputStream("versions" + File.separator + "Resilience" + File.separator + version + ".json");
            final ZipInputStream in2 = new ZipInputStream(new BufferedInputStream(new FileInputStream("Resilience.zip")));
            ZipEntry entry = null;
            while ((entry = in2.getNextEntry()) != null) {
                if (entry.getName().equals("Resilience.jar")) {
                    final byte[] buffer = new byte[8192];
                    int len;
                    while ((len = in2.read(buffer)) != -1) {
                        out1.write(buffer, 0, len);
                        if (ThreadUpdateGame.percent < 100.0f) {
                            ThreadUpdateGame.percent = jar.length() / (float)zip.length() * 100.0f;
                        }
                    }
                    out1.close();
                }
                else {
                    if (!entry.getName().equals("Resilience.json")) {
                        continue;
                    }
                    final byte[] buffer = new byte[8192];
                    int len;
                    while ((len = in2.read(buffer)) != -1) {
                        out2.write(buffer, 0, len);
                    }
                    out2.close();
                }
            }
            in2.close();
            GuiUpdating.isExtracting = false;
            GuiUpdating.isDeleting = true;
            this.location.delete();
            GuiUpdating.isDone = true;
        }
        catch (Exception e3) {
            Sys.openURL("http://resilience.krispdev.com/downloads");
            e3.printStackTrace();
        }
    }
    
    public static float getPercentDone() {
        return ThreadUpdateGame.percent;
    }
}
