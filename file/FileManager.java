// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.file;

import com.krispdev.resilience.account.Account;
import java.net.URL;
import com.krispdev.resilience.utilities.XrayBlock;
import com.krispdev.resilience.command.objects.Waypoint;
import com.krispdev.resilience.command.objects.Macro;
import com.krispdev.resilience.relations.Enemy;
import com.krispdev.resilience.relations.Friend;
import com.krispdev.resilience.module.categories.ModuleCategory;
import org.lwjgl.input.Keyboard;
import com.krispdev.resilience.module.modules.DefaultModule;
import com.krispdev.resilience.utilities.value.values.StringValue;
import com.krispdev.resilience.utilities.value.values.BoolValue;
import com.krispdev.resilience.utilities.value.values.NumberValue;
import com.krispdev.resilience.utilities.value.Value;
import java.util.Iterator;
import com.krispdev.resilience.gui.objects.screens.DefaultPanel;
import com.krispdev.resilience.gui.objects.ClickGui;
import java.util.ArrayList;
import java.io.IOException;
import com.krispdev.resilience.Resilience;
import java.io.File;

public class FileManager
{
    public ThreadUpdateGame dF;
    public boolean loadGui;
    public boolean loadKeybinds;
    public boolean loadEnabledMods;
    public boolean loadXrayBlocks;
    public boolean loadEnemies;
    public boolean loadFriends;
    public boolean loadConfigs;
    public boolean loadWaypoints;
    public boolean loadMacros;
    public boolean shouldAsk;
    private File mainDir;
    private File accountsDir;
    private File gui;
    private File configs;
    private File keybinds;
    private File enabledMods;
    private File firstTime;
    private File friends;
    private File enemies;
    private File macros;
    private File waypoints;
    private File xray;
    private File ask;
    private File accounts;
    File[] oldFiles;
    
    public FileManager() {
        this.loadGui = true;
        this.loadKeybinds = true;
        this.loadEnabledMods = true;
        this.loadXrayBlocks = true;
        this.loadEnemies = true;
        this.loadFriends = true;
        this.loadConfigs = true;
        this.loadWaypoints = true;
        this.loadMacros = true;
        this.shouldAsk = false;
        this.mainDir = new File(Resilience.getInstance().getName());
        this.accountsDir = new File(this.mainDir + File.separator + "Accounts");
        this.gui = new File(this.mainDir + File.separator + "GuiSettings.res");
        this.configs = new File(this.mainDir + File.separator + "Configs.res");
        this.keybinds = new File(this.mainDir + File.separator + "Keybinds.res");
        this.enabledMods = new File(this.mainDir + File.separator + "EnabledMods.res");
        this.firstTime = new File(this.mainDir + File.separator + "FirstTime.res");
        this.friends = new File(this.mainDir + File.separator + "Friends.res");
        this.enemies = new File(this.mainDir + File.separator + "Enemies.res");
        this.macros = new File(this.mainDir + File.separator + "Macros.res");
        this.waypoints = new File(this.mainDir + File.separator + "Waypoints.res");
        this.xray = new File(this.mainDir + File.separator + "XrayBlocks.res");
        this.ask = new File(this.mainDir + File.separator + "DonationAsk.res");
        this.accounts = new File(this.accountsDir + File.separator + "Accounts.res");
        this.oldFiles = new File[] { new File(this.mainDir + File.separator + "Keybinds.txt"), new File(this.mainDir + File.separator + "GuiSettings.txt"), new File(this.mainDir + File.separator + "EnabledMods.txt"), new File(this.mainDir + File.separator + "XrayBlocks.txt"), new File(this.mainDir + File.separator + "Enemies.txt"), new File(this.mainDir + File.separator + "Friends.txt"), new File(this.mainDir + File.separator + "Configs.txt"), new File(this.mainDir + File.separator + "Waypoints.txt"), new File(this.mainDir + File.separator + "FIRST_TIME_FILE_CHECKER"), new File(this.mainDir + File.separator + "Macros.txt"), new File(this.mainDir + File.separator + "FIRST_TIME_FILE_CHECK") };
    }
    
    public void init() throws Exception {
        if (!this.mainDir.exists()) {
            this.mainDir.mkdir();
            Resilience.getInstance().getLogger().info("Created the main client directory");
        }
        if (!this.accountsDir.exists()) {
            this.accountsDir.mkdir();
        }
        if (!this.ask.exists()) {
            this.saveOptions("3");
        }
        if (!this.firstTime.exists()) {
            this.firstTime.createNewFile();
            Resilience.getInstance().setFirstTime();
            File[] oldFiles;
            for (int length = (oldFiles = this.oldFiles).length, i = 0; i < length; ++i) {
                final File file = oldFiles[i];
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        if (!this.keybinds.exists()) {
            this.saveBinds(new String[0]);
            Resilience.getInstance().getLogger().info("Created Keybinds.res");
        }
        if (!this.gui.exists()) {
            try {
                this.gui.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            Resilience.getInstance().getLogger().info("Created GuiSettings.res");
        }
        if (!this.configs.exists()) {
            Resilience.getInstance().getValues().speed.setValue(11.0f);
            Resilience.getInstance().getValues().range.setValue(4.0f);
            this.saveConfigs(new String[0]);
            Resilience.getInstance().getLogger().info("Created Configs.res");
        }
        if (!this.enabledMods.exists()) {
            this.saveEnabledMods("Enabled Mods", "NiceChat", "Target Players", "Target Mobs", "Target Animals", "IRC");
            Resilience.getInstance().getLogger().info("Created EnabledMods.res");
        }
        if (!this.friends.exists()) {
            this.saveFriends(new String[0]);
            Resilience.getInstance().getLogger().info("Created Friends.res");
        }
        if (!this.enemies.exists()) {
            this.saveEnemies(new String[0]);
            Resilience.getInstance().getLogger().info("Created Enemies.res");
        }
        if (!this.macros.exists()) {
            this.saveMacros(new String[0]);
            Resilience.getInstance().getLogger().info("Created Macros.res");
        }
        if (!this.waypoints.exists()) {
            this.saveWaypoints(new String[0]);
            Resilience.getInstance().getLogger().info("Created Waypoints.res");
        }
        if (!this.xray.exists()) {
            this.saveXrayBlocks("56", "57", "14", "15", "16", "41", "42", "73", "74", "152", "173", "129", "133", "10", "11", "8", "9");
            Resilience.getInstance().getLogger().info("Saved XrayBlocks.res");
        }
        if (!this.accounts.exists()) {
            this.saveAccounts(new String[0]);
            Resilience.getInstance().getLogger().info("Saved Accounts.res");
        }
        this.loadConfigs();
        if (this.loadKeybinds) {
            this.loadBinds();
        }
        if (this.loadEnabledMods) {
            this.loadEnabledMods();
        }
        if (this.loadFriends) {
            this.loadFriends();
        }
        if (this.loadEnemies) {
            this.loadEnemies();
        }
        if (this.loadMacros) {
            this.loadMacros();
        }
        if (this.loadWaypoints) {
            this.loadWaypoints();
        }
        if (this.loadXrayBlocks) {
            this.loadXrayBlocks();
        }
        final int num = this.loadOptions();
        if (num != -1) {
            this.saveOptions(String.valueOf(num + 1));
            this.shouldAsk = (num > 2 && !Resilience.getInstance().getValues().isDonator(Resilience.getInstance().getInvoker().getSessionUsername()));
            if (this.shouldAsk) {
                this.saveOptions("0");
            }
        }
    }
    
    public void saveGui(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        Resilience.getInstance().getClickGui();
        for (final DefaultPanel panel : ClickGui.windows) {
            toPrint.add(String.valueOf(panel.getTitle()) + ":" + panel.getDragX() + ":" + panel.getDragY() + ":" + panel.isExtended() + ":" + panel.isPinned() + ":" + panel.isVisible());
        }
        FileUtils.print((ArrayList)toPrint, this.gui, true);
    }
    
    public void loadGui() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.gui, true);
        for (final DefaultPanel panel : ClickGui.windows) {
            for (final String s : lines) {
                final String[] args = s.split(":");
                if (panel.getTitle().equalsIgnoreCase(args[0])) {
                    panel.setDragX(Integer.parseInt(args[1]));
                    panel.setDragY(Integer.parseInt(args[2]));
                    panel.setExtended(Boolean.parseBoolean(args[3]));
                    panel.setPinned(Boolean.parseBoolean(args[4]));
                    panel.setVisible(Boolean.parseBoolean(args[5]));
                }
            }
        }
    }
    
    public void saveConfigs(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final Value value : Resilience.getInstance().getValues().values) {
            if (value instanceof NumberValue) {
                final NumberValue numValue = (NumberValue)value;
                toPrint.add(String.valueOf(value.getName()) + ":" + (numValue.shouldRound() ? ((float)(int)numValue.getValue()) : numValue.getValue()));
            }
            else if (value instanceof BoolValue) {
                final BoolValue tfValue = (BoolValue)value;
                toPrint.add(String.valueOf(value.getName()) + ":" + tfValue.getState());
            }
            else if (value instanceof StringValue) {
                final StringValue strValue = (StringValue)value;
                toPrint.add(String.valueOf(strValue.getName()) + ":" + strValue.getValue());
            }
            else {
                toPrint.add(value.getName());
            }
        }
        FileUtils.print((ArrayList)toPrint, this.configs, true);
    }
    
    public void loadConfigs() throws Exception {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.configs, true);
        for (final String s : lines) {
            final String[] args = s.split(":");
            for (final Value value : Resilience.getInstance().getValues().values) {
                if (value.getName().equalsIgnoreCase(args[0])) {
                    if (value instanceof NumberValue) {
                        for (final NumberValue numValue : Resilience.getInstance().getValues().numValues) {
                            if (numValue.getName().equalsIgnoreCase(args[0].trim())) {
                                numValue.setValue(Float.parseFloat(args[1]));
                            }
                        }
                    }
                    else if (value instanceof BoolValue) {
                        for (final BoolValue boolValue : Resilience.getInstance().getValues().boolValues) {
                            if (boolValue.getName().equalsIgnoreCase(args[0].trim())) {
                                boolValue.setState(Boolean.parseBoolean(args[1]));
                            }
                        }
                    }
                    else {
                        if (!(value instanceof StringValue)) {
                            continue;
                        }
                        for (final StringValue strValue : Resilience.getInstance().getValues().strValues) {
                            if (strValue.getName().equalsIgnoreCase(args[0].trim())) {
                                strValue.setValue(args[1]);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void saveBinds(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
            toPrint.add(String.valueOf(mod.getName()) + ":" + Keyboard.getKeyName(mod.getKeyCode()));
        }
        FileUtils.print((ArrayList)toPrint, this.keybinds, true);
    }
    
    public void loadBinds() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.keybinds, true);
        for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
            for (final String s : lines) {
                final String[] args = s.split(":");
                if (mod.getName().equalsIgnoreCase(args[0].trim())) {
                    mod.setKeyBind(Keyboard.getKeyIndex(args[1]));
                }
            }
        }
    }
    
    public void saveEnabledMods(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
            if (mod.isEnabled() && mod.getCategory() != ModuleCategory.GUI && mod.getCategory() != ModuleCategory.NONE && mod.shouldSave()) {
                toPrint.add(mod.getName());
            }
        }
        FileUtils.print((ArrayList)toPrint, this.enabledMods, false);
    }
    
    public void loadEnabledMods() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.enabledMods, true);
        for (final String s : lines) {
            for (final DefaultModule mod : Resilience.getInstance().getModuleManager().moduleList) {
                if (mod.getName().equalsIgnoreCase(s.trim())) {
                    mod.setEnabled(true);
                }
            }
        }
    }
    
    public void saveFriends(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final Friend friend : Friend.friendList) {
            toPrint.add(String.valueOf(friend.getName()) + ":" + friend.getAlias());
        }
        FileUtils.print((ArrayList)toPrint, this.friends, true);
    }
    
    public void loadFriends() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.friends, true);
        for (final String line : lines) {
            final String[] args = line.split(":");
            if (args.length < 1) {
                Friend.friendList.add(new Friend(args[0], args[0]));
            }
            else {
                Friend.friendList.add(new Friend(args[0], args[1]));
            }
        }
    }
    
    public void saveEnemies(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final Enemy enemy : Enemy.enemyList) {
            toPrint.add(enemy.getName());
        }
        FileUtils.print((ArrayList)toPrint, this.enemies, true);
    }
    
    public void loadEnemies() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.enemies, true);
        for (final String line : lines) {
            Enemy.enemyList.add(new Enemy(line));
        }
    }
    
    public void saveMacros(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final Macro macro : Macro.macroList) {
            toPrint.add(String.valueOf(macro.getCommand()) + ":" + macro.getKey());
        }
        FileUtils.print((ArrayList)toPrint, this.macros, true);
    }
    
    public void loadMacros() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.macros, true);
        for (final String line : lines) {
            final String[] args = line.split(":");
            Macro.macroList.add(new Macro(Integer.parseInt(args[1]), args[0]));
        }
    }
    
    public void saveWaypoints(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final Waypoint w : Waypoint.waypointsList) {
            toPrint.add(String.valueOf(w.getName()) + ":" + w.getX() + ":" + w.getY() + ":" + w.getZ() + ":" + w.getR() + ":" + w.getG() + ":" + w.getB());
        }
        FileUtils.print((ArrayList)toPrint, this.waypoints, true);
    }
    
    public void loadWaypoints() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.waypoints, true);
        for (final String s : lines) {
            final String[] args = s.split(":");
            Waypoint.waypointsList.add(new Waypoint(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[6]), Float.parseFloat(args[5])));
        }
    }
    
    public void loadXrayBlocks() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.xray, true);
        for (final String s : lines) {
            Resilience.getInstance().getXrayUtils().xrayBlocks.add(new XrayBlock(Integer.parseInt(s)));
        }
    }
    
    public void saveXrayBlocks(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final XrayBlock xrayBlock : Resilience.getInstance().getXrayUtils().xrayBlocks) {
            toPrint.add(String.valueOf(xrayBlock.getId()));
        }
        FileUtils.print((ArrayList)toPrint, this.xray, true);
    }
    
    public void saveOptions(final String num) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        toPrint.add(num);
        FileUtils.print((ArrayList)toPrint, this.ask, false);
    }
    
    public int loadOptions() {
        final ArrayList<String> line = (ArrayList<String>)FileUtils.read(this.ask, false);
        return Integer.parseInt(line.get(0));
    }
    
    public void downloadFile(final File dir, final URL fileLocation) {
        (this.dF = new ThreadUpdateGame(fileLocation, dir)).start();
    }
    
    public void saveAccounts(final String... presets) {
        final ArrayList<String> toPrint = new ArrayList<String>();
        for (final String line : presets) {
            toPrint.add(line);
        }
        for (final Account acc : Account.accountList) {
            toPrint.add(String.valueOf(acc.getUsername()) + ":" + acc.getPassword());
        }
        FileUtils.print((ArrayList)toPrint, this.accounts, true);
    }
    
    public void loadAccounts() {
        final ArrayList<String> lines = (ArrayList<String>)FileUtils.read(this.accounts, true);
        if (lines == null) {
            return;
        }
        for (final String s : lines) {
            final String[] args = s.split(":");
            if (args.length > 1 && !args[0].equals("") && args[0] != null && args[1] != null && !args[1].equals("")) {
                Account.accountList.add(new Account(args[0], args[1]));
            }
            else {
                if (args.length != 1 || args[0].equals("") || args[0] == null) {
                    continue;
                }
                Account.accountList.add(new Account(args[0], ""));
            }
        }
    }
}
