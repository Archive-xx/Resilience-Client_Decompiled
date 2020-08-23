// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command;

import com.krispdev.resilience.command.commands.CmdXrayReset;
import com.krispdev.resilience.command.commands.CmdXrayClear;
import com.krispdev.resilience.command.commands.CmdXrayDel;
import com.krispdev.resilience.command.commands.CmdXrayAdd;
import com.krispdev.resilience.command.commands.CmdWaypointsList;
import com.krispdev.resilience.command.commands.CmdWaypointsDel;
import com.krispdev.resilience.command.commands.CmdWaypointsClear;
import com.krispdev.resilience.command.commands.CmdWaypointsAddColour;
import com.krispdev.resilience.command.commands.CmdWaypointsAdd;
import com.krispdev.resilience.command.commands.CmdVClip;
import com.krispdev.resilience.command.commands.CmdTrackClear;
import com.krispdev.resilience.command.commands.CmdTrackSet;
import com.krispdev.resilience.command.commands.CmdToggle;
import com.krispdev.resilience.command.commands.CmdSearchClear;
import com.krispdev.resilience.command.commands.CmdSearchDel;
import com.krispdev.resilience.command.commands.CmdSearchAdd;
import com.krispdev.resilience.command.commands.CmdSay;
import com.krispdev.resilience.command.commands.CmdRemoteView;
import com.krispdev.resilience.command.commands.CmdPrefixChange;
import com.krispdev.resilience.command.commands.CmdNameProtectSet;
import com.krispdev.resilience.command.commands.CmdReName;
import com.krispdev.resilience.command.commands.CmdMacroClear;
import com.krispdev.resilience.command.commands.CmdMacroAdd;
import com.krispdev.resilience.command.commands.CmdKillAuraMode;
import com.krispdev.resilience.command.commands.CmdIRCPrefixChange;
import com.krispdev.resilience.command.commands.CmdIRCNick;
import com.krispdev.resilience.command.commands.CmdInvSee;
import com.krispdev.resilience.command.commands.CmdHelp;
import com.krispdev.resilience.command.commands.CmdGetIP;
import com.krispdev.resilience.command.commands.CmdFriendDel;
import com.krispdev.resilience.command.commands.CmdFriendClear;
import com.krispdev.resilience.command.commands.CmdFriendAddAlias;
import com.krispdev.resilience.command.commands.CmdFriendAdd;
import com.krispdev.resilience.command.commands.CmdFakeChat;
import com.krispdev.resilience.command.commands.CmdEnemiesClear;
import com.krispdev.resilience.command.commands.CmdEnemyDel;
import com.krispdev.resilience.command.commands.CmdEnemyAdd;
import com.krispdev.resilience.command.commands.CmdEnchant;
import com.krispdev.resilience.command.commands.CmdBreadcrumbsClear;
import com.krispdev.resilience.command.commands.CmdBindRemove;
import com.krispdev.resilience.command.commands.CmdBindChange;
import com.krispdev.resilience.command.commands.CmdAntiAfkSet;
import com.krispdev.resilience.command.commands.CmdAllOff;
import com.krispdev.resilience.Resilience;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public abstract class Command
{
    private String words;
    private String extras;
    private String desc;
    protected Minecraft mc;
    public static ArrayList<Command> cmdList;
    
    static {
        Command.cmdList = new ArrayList();
    }
    
    public Command(final String words, final String extras, final String desc) {
        this.mc = Resilience.getInstance().getWrapper().getMinecraft();
        this.words = words;
        this.extras = extras;
        this.desc = desc;
    }
    
    public static void instantiateCommands() {
        add((Command)new CmdAllOff());
        add((Command)new CmdAntiAfkSet());
        add((Command)new CmdBindChange());
        add((Command)new CmdBindRemove());
        add((Command)new CmdBreadcrumbsClear());
        add((Command)new CmdEnchant());
        add((Command)new CmdEnemyAdd());
        add((Command)new CmdEnemyDel());
        add((Command)new CmdEnemiesClear());
        add((Command)new CmdFakeChat());
        add((Command)new CmdFriendAdd());
        add((Command)new CmdFriendAddAlias());
        add((Command)new CmdFriendClear());
        add((Command)new CmdFriendDel());
        add((Command)new CmdGetIP());
        add((Command)new CmdHelp());
        add((Command)new CmdInvSee());
        add((Command)new CmdIRCNick());
        add((Command)new CmdIRCPrefixChange());
        add((Command)new CmdKillAuraMode());
        add((Command)new CmdMacroAdd());
        add((Command)new CmdMacroClear());
        add((Command)new CmdReName());
        add((Command)new CmdNameProtectSet());
        add((Command)new CmdPrefixChange());
        add((Command)new CmdRemoteView());
        add((Command)new CmdSay());
        add((Command)new CmdSearchAdd());
        add((Command)new CmdSearchDel());
        add((Command)new CmdSearchClear());
        add((Command)new CmdToggle());
        add((Command)new CmdTrackSet());
        add((Command)new CmdTrackClear());
        add((Command)new CmdVClip());
        add((Command)new CmdWaypointsAdd());
        add((Command)new CmdWaypointsAddColour());
        add((Command)new CmdWaypointsClear());
        add((Command)new CmdWaypointsDel());
        add((Command)new CmdWaypointsList());
        add((Command)new CmdXrayAdd());
        add((Command)new CmdXrayDel());
        add((Command)new CmdXrayClear());
        add((Command)new CmdXrayReset());
    }
    
    private static void add(final Command cmd) {
        Command.cmdList.add(cmd);
    }
    
    public abstract boolean recieveCommand(final String p0) throws Exception;
    
    public String getWords() {
        return this.words;
    }
    
    public String getExtras() {
        return this.extras;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    protected void setWords(final String words) {
        this.words = words;
    }
    
    protected void setExtras(final String extras) {
        this.extras = extras;
    }
    
    public String getFirstWord() {
        final String[] wordArray = this.words.split(" ");
        return wordArray[0];
    }
}
