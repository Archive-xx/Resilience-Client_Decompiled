// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.hooks;

import com.krispdev.resilience.event.events.player.EventHealthUpdate;
import com.krispdev.resilience.event.events.player.EventPostMotion;
import com.krispdev.resilience.event.events.player.EventPreMotion;
import java.util.Iterator;
import com.krispdev.resilience.donate.Donator;
import com.krispdev.resilience.irc.ResilienceIRCBot;
import com.krispdev.resilience.command.Command;
import com.krispdev.resilience.Resilience;
import net.minecraft.entity.player.EntityPlayer;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.util.Session;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class HookEntityClientPlayerMP extends EntityClientPlayerMP
{
    private int cooldownTimer;
    private String prevMessage;
    
    public HookEntityClientPlayerMP(final Minecraft p_i45064_1_, final World p_i45064_2_, final Session p_i45064_3_, final NetHandlerPlayClient netClientHandler, final StatFileWriter p_i45064_5_) {
        super(p_i45064_1_, p_i45064_2_, p_i45064_3_, netClientHandler, p_i45064_5_);
        this.cooldownTimer = 0;
        this.prevMessage = "";
    }
    
    public void onUpdate() {
        if (this.cooldownTimer > 0) {
            --this.cooldownTimer;
        }
        final EventOnUpdate updateEvent = new EventOnUpdate((EntityPlayer)this);
        updateEvent.onEvent();
        if (!updateEvent.isCancelled()) {
            super.onUpdate();
            return;
        }
        updateEvent.setCancelled(false);
    }
    
    public void sendChatMessage(final String s) {
        if (s.startsWith(Resilience.getInstance().getCmdPrefix()) && Resilience.getInstance().isEnabled()) {
            for (final Command cmd : Command.cmdList) {
                try {
                    final String replaced = s.replaceFirst(Resilience.getInstance().getCmdPrefix(), "");
                    final String[] inputWords = replaced.split(" ");
                    if (replaced.startsWith(cmd.getWords())) {
                        try {
                            if (cmd.recieveCommand(s.replaceFirst(Resilience.getInstance().getCmdPrefix(), ""))) {
                                break;
                            }
                            continue;
                        }
                        catch (Exception e2) {
                            Resilience.getInstance().getLogger().warningChat("§cInternal error! §fSyntax: §b" + cmd.getWords().concat(cmd.getExtras()));
                            continue;
                        }
                    }
                    s.replace(Resilience.getInstance().getCmdPrefix(), "").toLowerCase().startsWith(cmd.getFirstWord());
                }
                catch (Exception ex) {
                    Resilience.getInstance().getLogger().warningChat("Reset the command prefix to \".\" due to strange internal exception!");
                    Resilience.getInstance().setCmdPrefix(".");
                }
            }
        }
        else if (s.startsWith(Resilience.getInstance().getIRCPrefix()) && Resilience.getInstance().isEnabled()) {
            try {
                if (!Resilience.getInstance().getValues().ircEnabled) {
                    Resilience.getInstance().getLogger().warningChat("Please enable \"IRC\" to chat in the IRC!");
                    return;
                }
                final String msg = s.replaceFirst(Resilience.getInstance().getIRCPrefix(), "");
                if (this.cooldownTimer < 2) {
                    this.cooldownTimer = 30;
                    if (!msg.trim().equalsIgnoreCase(this.prevMessage)) {
                        this.prevMessage = msg;
                        ResilienceIRCBot.bot.changeNick(ResilienceIRCBot.username);
                        ResilienceIRCBot.bot.sendMessage("#ResilienceClient", s.replaceFirst(Resilience.getInstance().getIRCPrefix(), ""));
                        String msgToPlace = ResilienceIRCBot.username;
                        final boolean nick = msgToPlace.startsWith("XxXN");
                        if (nick) {
                            msgToPlace = msgToPlace.replaceFirst("XxXN", "");
                        }
                        final boolean krisp = msgToPlace.equals("Krisp");
                        final boolean vip = Donator.isDonator(msgToPlace, 5.0f);
                        Resilience.getInstance().getLogger().irc(String.valueOf(msgToPlace) + ": " + msg);
                        Resilience.getInstance().getLogger().ircChat(String.valueOf(nick ? "§f[§3NickName§f]§b " : "") + (krisp ? "§f[§cOwner§f] §b" : (vip ? "§f[§6VIP§f]§b " : "§b")) + msgToPlace + "§8:" + (krisp ? "§c " : (vip ? "§6 " : "§f ")) + msg);
                    }
                    else {
                        Resilience.getInstance().getLogger().warningChat("Please don't send the same message twice in a row!");
                    }
                }
                else {
                    Resilience.getInstance().getLogger().warningChat("Please wait a bit between IRC chats!");
                }
            }
            catch (Exception e) {
                Resilience.getInstance().getLogger().warningChat("Error in IRC. Have you enabled \"IRC\"? To be safe, we have reset the IRC prefix to \"@\"");
                Resilience.getInstance().setIRCPrefix("@");
                e.printStackTrace();
            }
        }
        else {
            super.sendChatMessage(s);
        }
    }
    
    public void sendMotionUpdates() {
        if (Resilience.getInstance().getValues().freecamEnabled) {
            return;
        }
        final float prevPitch = Resilience.getInstance().getInvoker().getRotationPitch();
        final float prevYaw = Resilience.getInstance().getInvoker().getRotationYaw();
        final EventPreMotion eventPre = new EventPreMotion((EntityPlayer)this);
        eventPre.onEvent();
        if (!eventPre.isCancelled()) {
            super.sendMotionUpdates();
            Resilience.getInstance().getInvoker().setRotationPitch(prevPitch);
            Resilience.getInstance().getInvoker().setRotationYaw(prevYaw);
            final EventPostMotion eventPost = new EventPostMotion((EntityPlayer)this);
            eventPost.onEvent();
            return;
        }
        eventPre.setCancelled(false);
    }
    
    public void moveEntity(final double par1, final double par3, final double par5) {
        super.moveEntity(par1, par3, par5);
        if (Resilience.getInstance().getValues().flightEnabled) {
            this.inWater = false;
        }
    }
    
    public boolean handleWaterMovement() {
        return !Resilience.getInstance().getValues().flightEnabled && super.handleWaterMovement();
    }
    
    public void setHealth(final float health) {
        final EventHealthUpdate eventHealth = new EventHealthUpdate(health);
        eventHealth.onEvent();
        super.setHealth(health);
    }
    
    public boolean isEntityInsideOpaqueBlock() {
        return !Resilience.getInstance().getValues().freecamEnabled && super.isEntityInsideOpaqueBlock();
    }
    
    protected boolean func_145771_j(final double par1, final double par3, final double par5) {
        return !Resilience.getInstance().getValues().freecamEnabled && super.func_145771_j(par1, par3, par5);
    }
}
