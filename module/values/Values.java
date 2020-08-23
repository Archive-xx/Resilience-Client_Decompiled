// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.values;

import com.krispdev.resilience.Resilience;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import com.krispdev.resilience.irc.ResilienceIRCBot;
import net.minecraft.util.ResourceLocation;
import java.util.List;
import com.krispdev.resilience.command.objects.Waypoint;
import com.krispdev.resilience.utilities.value.values.StringValue;
import com.krispdev.resilience.utilities.value.values.BoolValue;
import com.krispdev.resilience.utilities.value.values.NumberValue;
import com.krispdev.resilience.utilities.value.Value;
import java.util.ArrayList;

public class Values
{
    public ArrayList<Value> values;
    public ArrayList<NumberValue> numValues;
    public ArrayList<BoolValue> boolValues;
    public ArrayList<StringValue> strValues;
    public Waypoint deathWaypoint;
    public NumberValue antiAFKSeconds;
    public NumberValue autoSoupHealth;
    public NumberValue flySpeed;
    public NumberValue speed;
    public NumberValue range;
    public NumberValue nukerRadius;
    public NumberValue fastBreakSpeed;
    public NumberValue timerSpeed;
    public NumberValue highJumpMultiplier;
    public NumberValue stepHeight;
    public NumberValue searchRange;
    public NumberValue buttonSize;
    public BoolValue players;
    public BoolValue mobs;
    public BoolValue animals;
    public BoolValue invisibles;
    public BoolValue propBlocks;
    public BoolValue chestESPTracers;
    public StringValue nameProtectAlias;
    public StringValue cmdPrefix;
    public StringValue ircPrefix;
    public boolean enabledModsEnabled;
    public boolean flightEnabled;
    public boolean niceChatEnabled;
    public boolean nameProtectEnabled;
    public boolean caveFinderEnabled;
    public boolean namesEnabled;
    public boolean noFireEffectEnabled;
    public boolean potionEffectsEnabled;
    public boolean fastBreakEnabled;
    public boolean ircEnabled;
    public boolean killAuraEnabled;
    public boolean autoBlockEnabled;
    public boolean noHurtcamEnabled;
    public boolean freecamEnabled;
    public boolean highJumpEnabled;
    public boolean jesusEnabled;
    public boolean noSlowdownEnabled;
    public boolean autoRespawnEnabled;
    public boolean antiBlindessEnabled;
    public boolean antiNauseaEnabled;
    public boolean safeWalkEnabled;
    public List<Double[]> breadcrumbPosList;
    public List<Double[]> trackPosList;
    public String trackName;
    public String banReason;
    public String banTime;
    public final ResourceLocation altBackground;
    public final int UPDATE_VERSION = 24;
    public ArrayList<Float[]> searchIds;
    public int ticksForSearch;
    public int version;
    public ResilienceIRCBot bot;
    
    public Values() {
        this.values = new ArrayList();
        this.numValues = new ArrayList();
        this.boolValues = new ArrayList();
        this.strValues = new ArrayList();
        this.antiAFKSeconds = new NumberValue(60.0f, 1.0f, 120.0f, "AntiAFK Delay (Sec.)", true);
        this.autoSoupHealth = new NumberValue(14.0f, 1.0f, 19.0f, "AutoSoup Threshold", true);
        this.flySpeed = new NumberValue(2.0f, 0.0f, 10.0f, "Flight Speed", false);
        this.speed = new NumberValue(11.0f, 3.0f, 15.0f, "KillAura Speed", false);
        this.range = new NumberValue(3.9f, 2.5f, 6.0f, "KillAura Range", false);
        this.nukerRadius = new NumberValue(4.0f, 2.0f, 6.0f, "Nuker Radius", true);
        this.fastBreakSpeed = new NumberValue(0.8f, 0.1f, 0.9f, "FastBreak Speed", false);
        this.timerSpeed = new NumberValue(2.0f, 0.1f, 10.0f, "Speed Multiplier", false);
        this.highJumpMultiplier = new NumberValue(3.0f, 2.0f, 30.0f, "HighJump Multiplier", true);
        this.stepHeight = new NumberValue(2.0f, 1.0f, 30.0f, "Step Height", true);
        this.searchRange = new NumberValue(100.0f, 40.0f, 300.0f, "Search Range", true);
        this.buttonSize = new NumberValue(1.0f, 1.0f, 2.0f, "GUI Button Size", true);
        this.players = new BoolValue("Attack Players", true);
        this.mobs = new BoolValue("Attack Mobs", true);
        this.animals = new BoolValue("Attack Animals", true);
        this.invisibles = new BoolValue("Attack Invisibles", true);
        this.propBlocks = new BoolValue("Attack PropBlocks", true);
        this.chestESPTracers = new BoolValue("Draw ChestESP Tracers", false);
        this.nameProtectAlias = new StringValue("NameProtect Alias", "SetNameProtectAlias");
        this.cmdPrefix = new StringValue("Command Prefix", ".");
        this.ircPrefix = new StringValue("IRC Prefix", "@");
        this.enabledModsEnabled = false;
        this.flightEnabled = false;
        this.niceChatEnabled = false;
        this.nameProtectEnabled = false;
        this.caveFinderEnabled = false;
        this.namesEnabled = false;
        this.noFireEffectEnabled = false;
        this.potionEffectsEnabled = false;
        this.fastBreakEnabled = false;
        this.ircEnabled = false;
        this.killAuraEnabled = false;
        this.autoBlockEnabled = false;
        this.noHurtcamEnabled = false;
        this.freecamEnabled = false;
        this.highJumpEnabled = false;
        this.jesusEnabled = false;
        this.noSlowdownEnabled = false;
        this.autoRespawnEnabled = false;
        this.antiBlindessEnabled = false;
        this.antiNauseaEnabled = false;
        this.safeWalkEnabled = false;
        this.breadcrumbPosList = new ArrayList();
        this.trackPosList = new ArrayList();
        this.trackName = "No one to track";
        this.banReason = "No reason given. Contact krisphf@gmail.com for details.";
        this.banTime = "Infinite";
        this.altBackground = new ResourceLocation("textures/blocks/stone.png");
        this.UPDATE_VERSION = 24;
        this.searchIds = new ArrayList();
        this.ticksForSearch = 70;
        this.version = 5;
    }
    
    public void initValues() {
        this.add(new Value("=-=-=-=Number Settings=-=-=-="));
        this.add((Value)this.autoSoupHealth);
        this.add((Value)this.antiAFKSeconds);
        this.add((Value)this.fastBreakSpeed);
        this.add((Value)this.flySpeed);
        this.add((Value)this.highJumpMultiplier);
        this.add((Value)this.nukerRadius);
        this.add((Value)this.timerSpeed);
        this.add((Value)this.searchRange);
        this.add((Value)this.speed);
        this.add((Value)this.stepHeight);
        this.add((Value)this.range);
        this.add(new Value("=-=-=-=True/False Settings=-=-=-="));
        this.add((Value)this.chestESPTracers);
        this.add((Value)this.players);
        this.add((Value)this.mobs);
        this.add((Value)this.animals);
        this.add(new Value("=-=-=-=Other Settings=-=-=-="));
        this.add((Value)this.nameProtectAlias);
        this.add((Value)this.cmdPrefix);
        this.add((Value)this.ircPrefix);
    }
    
    public boolean isDonator(final String username) {
        try {
            final URL url = new URL("http://resilience.krispdev.com/Rerererencedonatorsx789");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String temp;
            while ((temp = in.readLine()) != null) {
                final String[] args = temp.split("BITCHEZBECRAYCRAY123WAYOVER30CHAR");
                if (username.equalsIgnoreCase(args[3])) {
                    return true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isAccountBanned() {
        try {
            final URL url = new URL("http://resilience.krispdev.com/BannedMembers");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String temp;
            while ((temp = in.readLine()) != null) {
                final String[] args = temp.split("~SPLITCHAR~");
                if (args[0].trim().equalsIgnoreCase(Resilience.getInstance().getInvoker().getSessionUsername())) {
                    this.banReason = args[1];
                    this.banTime = args[2];
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean needsUpdate() {
        try {
            final URL url = new URL("http://krispdev.com/Resilience-Version");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            final String text = in.readLine();
            if (Integer.parseInt(text) > 24) {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private void add(final Value v) {
        if (v instanceof NumberValue) {
            this.numValues.add(v);
        }
        else if (v instanceof BoolValue) {
            this.boolValues.add(v);
        }
        else if (v instanceof StringValue) {
            this.strValues.add(v);
        }
        this.values.add(v);
    }
}
