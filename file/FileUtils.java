// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.file;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.io.IOException;
import com.krispdev.resilience.Resilience;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;

public final class FileUtils
{
    public static void print(final ArrayList<String> lines, final File dir, final boolean inform) {
        try {
            final BufferedWriter bw = new BufferedWriter(new FileWriter(dir));
            for (final String s : lines) {
                bw.write(String.valueOf(s) + "\r\n");
            }
            bw.close();
            if (inform) {
                Resilience.getInstance().getLogger().info("Printed data to " + dir.getName());
            }
        }
        catch (IOException e) {
            if (inform) {
                Resilience.getInstance().getLogger().warning("Error writing to " + dir.getName());
            }
            e.printStackTrace();
        }
    }
    
    public static ArrayList<String> read(final File dir, final boolean inform) {
        final ArrayList<String> lines = new ArrayList<String>();
        try {
            final BufferedReader br = new BufferedReader(new FileReader(dir));
            String curLine;
            while ((curLine = br.readLine()) != null && !curLine.startsWith("#")) {
                lines.add(curLine);
            }
            br.close();
            if (inform) {
                Resilience.getInstance().getLogger().info("Read data from file " + dir.getName());
            }
        }
        catch (IOException e) {
            if (inform) {
                Resilience.getInstance().getLogger().warning("Error reading file " + dir.getName());
            }
            e.printStackTrace();
        }
        return lines;
    }
}
