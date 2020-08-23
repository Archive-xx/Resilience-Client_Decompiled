// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.player;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import java.util.Iterator;
import com.krispdev.resilience.relations.FriendManager;
import com.krispdev.resilience.relations.Friend;
import net.minecraft.network.play.server.S02PacketChat;
import com.krispdev.resilience.event.events.player.EventPacketReceive;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoTPAccept extends DefaultModule
{
    public ModuleAutoTPAccept() {
        super("Auto TP Accept", 0);
        this.setCategory(ModuleCategory.PLAYER);
        this.setDescription("Automatically accepts TP requests from friends");
    }
    
    @Override
    public void onPacketReceive(final EventPacketReceive event) {
        if (event.getPacket() instanceof S02PacketChat) {
            final S02PacketChat p = (S02PacketChat)event.getPacket();
            System.out.println(String.valueOf(this.invoker.getSessionUsername()) + " =-=-=-=-=-= " + p.func_148915_c().getUnformattedText());
            try {
                final String line = p.func_148915_c().getUnformattedText();
                final String[] spaceArray = line.split(" ");
                for (final Friend friend : Friend.friendList) {
                    if (spaceArray[0].toLowerCase().contains(friend.getName().toLowerCase())) {
                        final String lineAfterSpace = line.replace(spaceArray[0], "");
                        if (!lineAfterSpace.trim().equals("has requested to teleport to you.")) {
                            continue;
                        }
                        this.invoker.sendChatMessage("/tpaccept");
                    }
                }
                FriendManager.isFriend(spaceArray[0]);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
