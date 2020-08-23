// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.combat;

import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.events.player.EventHealthUpdate;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.utilities.game.InventoryUtils;
import com.krispdev.resilience.wrappers.MethodInvoker;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleAutoSoup extends DefaultModule
{
    private boolean shouldSoup;
    private MethodInvoker invo;
    private InventoryUtils invUtils;
    private int prevSlot;
    private int soupId;
    private int bowlId;
    
    public ModuleAutoSoup() {
        super("AutoSoup", 24);
        this.shouldSoup = false;
        this.invo = Resilience.getInstance().getInvoker();
        this.invUtils = new InventoryUtils();
        this.prevSlot = -1;
        this.soupId = 282;
        this.bowlId = 281;
        this.setCategory(ModuleCategory.COMBAT);
        this.setDescription("Automatically eats soup when health is low. (For KitPvP)");
    }
    
    public void onHealthUpdate(final EventHealthUpdate e) {
        if (e.getHealth() < Resilience.getInstance().getValues().autoSoupHealth.getValue()) {
            this.shouldSoup = true;
        }
        else {
            this.shouldSoup = false;
        }
    }
    
    public void onUpdate(final EventOnUpdate event) {
        if (this.shouldSoup) {
            if (this.prevSlot == -1) {
                this.prevSlot = this.invo.getCurInvSlot();
            }
            final int slotHotbar = this.invUtils.getSlotOfHotbarItem(this.soupId);
            if (slotHotbar != -1) {
                this.invo.setInvSlot(slotHotbar);
                this.invUtils.sendItemUse(this.invo.getItemAtSlot(slotHotbar));
            }
            else {
                final int invSlot = this.invUtils.getSlotOfInvItem(this.soupId);
                if (invSlot != -1) {
                    final int freeSlot = this.invUtils.getFreeSlotInInv(this.bowlId);
                    final int freeHotbarSlot = this.invUtils.getFreeSlotInHotbar(0);
                    if (freeHotbarSlot != -1) {
                        this.invUtils.click(freeSlot, 1);
                        this.invUtils.click(invSlot, 1);
                    }
                    else {
                        final int hotBarSlotBad = this.invUtils.getSlotOfHotbarItem(this.bowlId);
                        if (hotBarSlotBad != -1) {
                            this.invo.dropItemStack(hotBarSlotBad);
                            this.invUtils.click(invSlot, 1);
                            this.invUtils.sendItemUse(this.invo.getItemAtSlot(invSlot));
                        }
                        this.invUtils.click(invSlot, 1);
                        this.invUtils.click(freeSlot, 1);
                    }
                }
            }
        }
        else if (this.prevSlot != -1 && this.invo.getCurInvSlot() != this.prevSlot) {
            this.invo.setInvSlot(this.prevSlot);
            this.prevSlot = -1;
        }
    }
    
    public void onEnable() {
        Resilience.getInstance().getEventManager().register((Listener)this);
    }
    
    public void onDisable() {
        Resilience.getInstance().getEventManager().unregister((Listener)this);
    }
}
