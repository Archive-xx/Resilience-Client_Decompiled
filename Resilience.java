// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience;

import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.command.Command;
import com.krispdev.resilience.utilities.font.TTFRenderer;
import com.krispdev.resilience.utilities.XrayUtils;
import com.krispdev.resilience.module.values.Values;
import com.krispdev.resilience.file.FileManager;
import com.krispdev.resilience.gui.objects.ClickGui;
import com.krispdev.resilience.event.EventManager;
import com.krispdev.resilience.module.ModuleManager;
import com.krispdev.resilience.wrappers.MethodInvoker;
import com.krispdev.resilience.wrappers.Wrapper;
import com.krispdev.resilience.logger.ResilienceLogger;

public class Resilience
{
    private static Resilience instance;
    private ResilienceLogger logger;
    private Wrapper wrapper;
    private MethodInvoker invoker;
    private ModuleManager moduleManager;
    private EventManager eventManager;
    private ClickGui clickGui;
    private FileManager fileManager;
    private Values values;
    private XrayUtils xrayUtils;
    private TTFRenderer panelTitleFont;
    private TTFRenderer buttonExtraFont;
    private TTFRenderer standardFont;
    private TTFRenderer modListFont;
    private TTFRenderer radarFont;
    private TTFRenderer chatFont;
    private TTFRenderer waypointFont;
    private TTFRenderer largeFont;
    private TTFRenderer xLargeFont;
    private String name;
    private String version;
    private String author;
    private boolean isFirstTime;
    private boolean enabled;
    
    static {
        Resilience.instance = null;
    }
    
    public Resilience() {
        this.logger = null;
        this.wrapper = null;
        this.invoker = null;
        this.moduleManager = null;
        this.eventManager = null;
        this.clickGui = null;
        this.fileManager = null;
        this.values = null;
        this.xrayUtils = null;
        this.panelTitleFont = null;
        this.buttonExtraFont = null;
        this.standardFont = null;
        this.modListFont = null;
        this.radarFont = null;
        this.chatFont = null;
        this.waypointFont = null;
        this.largeFont = null;
        this.xLargeFont = null;
        this.name = "Resilience";
        this.version = "1.4 Release";
        this.author = "Krisp";
        this.isFirstTime = false;
        this.enabled = true;
    }
    
    public void start() {
        this.getModuleManager().instantiateModules();
        this.getValues().initValues();
        try {
            this.getFileManager().init();
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
        Command.instantiateCommands();
        Utils.initDonators();
        this.getModuleManager().setModuleState("Target Players", true);
        this.getModuleManager().setModuleState("Target Mobs", true);
        this.getModuleManager().setModuleState("Target Animals", true);
    }
    
    public static Resilience getInstance() {
        if (Resilience.instance == null) {
            Resilience.instance = new Resilience();
        }
        return Resilience.instance;
    }
    
    public ResilienceLogger getLogger() {
        if (this.logger == null) {
            this.logger = new ResilienceLogger();
        }
        return this.logger;
    }
    
    public Wrapper getWrapper() {
        if (this.wrapper == null) {
            this.wrapper = new Wrapper();
        }
        return this.wrapper;
    }
    
    public MethodInvoker getInvoker() {
        if (this.invoker == null) {
            this.invoker = new MethodInvoker();
        }
        return this.invoker;
    }
    
    public ModuleManager getModuleManager() {
        if (this.moduleManager == null) {
            this.moduleManager = new ModuleManager();
        }
        return this.moduleManager;
    }
    
    public EventManager getEventManager() {
        if (this.eventManager == null) {
            this.eventManager = new EventManager();
        }
        return this.eventManager;
    }
    
    public ClickGui getClickGui() {
        if (this.clickGui == null) {
            this.clickGui = new ClickGui();
        }
        return this.clickGui;
    }
    
    public FileManager getFileManager() {
        if (this.fileManager == null) {
            this.fileManager = new FileManager();
        }
        return this.fileManager;
    }
    
    public Values getValues() {
        if (this.values == null) {
            this.values = new Values();
        }
        return this.values;
    }
    
    public XrayUtils getXrayUtils() {
        if (this.xrayUtils == null) {
            this.xrayUtils = new XrayUtils();
        }
        return this.xrayUtils;
    }
    
    public TTFRenderer getStandardFont() {
        if (this.standardFont == null) {
            this.standardFont = new TTFRenderer("Arial", 0, 18);
        }
        return this.standardFont;
    }
    
    public TTFRenderer getPanelTitleFont() {
        if (this.panelTitleFont == null) {
            this.panelTitleFont = new TTFRenderer("Arial", 0, 23);
        }
        return this.panelTitleFont;
    }
    
    public TTFRenderer getButtonExtraFont() {
        if (this.buttonExtraFont == null) {
            this.buttonExtraFont = new TTFRenderer("Arial", 0, 16);
        }
        return this.buttonExtraFont;
    }
    
    public TTFRenderer getModListFont() {
        if (this.modListFont == null) {
            this.modListFont = new TTFRenderer("Arial", 0, 20);
        }
        return this.modListFont;
    }
    
    public TTFRenderer getRadarFont() {
        if (this.radarFont == null) {
            this.radarFont = new TTFRenderer("Arial", 0, 10);
        }
        return this.radarFont;
    }
    
    public TTFRenderer getChatFont() {
        if (this.chatFont == null) {
            this.chatFont = new TTFRenderer("Arial", 0, 19);
        }
        return this.chatFont;
    }
    
    public TTFRenderer getWaypointFont() {
        if (this.waypointFont == null) {
            this.waypointFont = new TTFRenderer("Arial", 0, 40);
        }
        return this.waypointFont;
    }
    
    public TTFRenderer getLargeFont() {
        if (this.largeFont == null) {
            this.largeFont = new TTFRenderer("Arial", 0, 30);
        }
        return this.largeFont;
    }
    
    public TTFRenderer getXLargeFont() {
        if (this.xLargeFont == null) {
            this.xLargeFont = new TTFRenderer("Arial", 0, 45);
        }
        return this.xLargeFont;
    }
    
    public String getCmdPrefix() {
        return this.getValues().cmdPrefix.getValue();
    }
    
    public String getIRCPrefix() {
        return this.getValues().ircPrefix.getValue();
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isFirstTime() {
        return this.isFirstTime;
    }
    
    public void setFirstTime() {
        this.isFirstTime = true;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean flag) {
        this.enabled = flag;
    }
    
    public void setCmdPrefix(final String prefix) {
        this.getValues().cmdPrefix.setValue(prefix);
    }
    
    public void setIRCPrefix(final String prefix) {
        this.getValues().ircPrefix.setValue(prefix);
    }
}
