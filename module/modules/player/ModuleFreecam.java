// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.player;

import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.events.player.EventValueChange;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import com.mojang.authlib.GameProfile;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleFreecam extends DefaultModule
{
    private EntityOtherPlayerMP entity;
    private double freecamX;
    private double freecamY;
    private double freecamZ;
    private double freecamPitch;
    private double freecamYaw;
    
    public ModuleFreecam() {
        super("Freecam", 47);
        this.entity = null;
        this.setCategory(ModuleCategory.PLAYER);
        this.setDescription("Allows you to walk outside of your body and fly through walls");
        this.setSave(false);
    }
    
    @Override
    public void onEnable() {
        Resilience.getInstance().getValues().freecamEnabled = true;
        Resilience.getInstance().getEventManager().register((Listener)this);
        this.freecamX = this.invoker.getPosX();
        this.freecamY = this.invoker.getPosY();
        this.freecamZ = this.invoker.getPosZ();
        this.freecamPitch = this.invoker.getRotationPitch();
        this.freecamYaw = this.invoker.getRotationYaw();
        this.entity = new EntityOtherPlayerMP((World)Resilience.getInstance().getWrapper().getWorld(), new GameProfile("", Resilience.getInstance().getValues().nameProtectEnabled ? Resilience.getInstance().getValues().nameProtectAlias.getValue() : this.invoker.getSessionUsername()));
        this.invoker.setPositionAndRotation((Entity)this.entity, this.invoker.getPosX(), this.invoker.getPosY() - this.invoker.getEntityHeight((Entity)Resilience.getInstance().getWrapper().getPlayer()) + 0.17, this.invoker.getPosZ(), this.invoker.getRotationYaw(), this.invoker.getRotationPitch());
        this.invoker.copyInventory((EntityPlayer)this.entity, (EntityPlayer)Resilience.getInstance().getWrapper().getPlayer());
        this.invoker.addEntityToWorld((Entity)this.entity, 69);
    }
    
    @Override
    public void onValueChange(final EventValueChange event) {
        if (event.getValue() == Resilience.getInstance().getValues().nameProtectAlias) {
            this.invoker.setGameProfile(new GameProfile("", Resilience.getInstance().getValues().nameProtectAlias.getValue()), (EntityPlayer)this.entity);
        }
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().freecamEnabled = false;
        Resilience.getInstance().getEventManager().unregister((Listener)this);
        this.invoker.removeEntityFromWorld(69);
        this.invoker.setPositionAndRotation((Entity)Resilience.getInstance().getWrapper().getPlayer(), this.freecamX, this.freecamY, this.freecamZ, (float)this.freecamYaw, (float)this.freecamPitch);
        this.invoker.setNoClip(false);
    }
    
    @Override
    public void onUpdate(final EventOnUpdate event) {
        if (this.entity != null) {
            if (Resilience.getInstance().getValues().flightEnabled) {
                this.invoker.setNoClip(true);
                this.invoker.setOnGround(false);
            }
            else {
                this.invoker.setNoClip(false);
            }
        }
    }
}
