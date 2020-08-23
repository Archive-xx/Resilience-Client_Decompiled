// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.utilities.game;

import com.krispdev.resilience.utilities.Utils;
import com.krispdev.resilience.relations.EnemyManager;
import com.krispdev.resilience.relations.FriendManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import com.krispdev.resilience.event.events.player.EventOnClick;
import java.util.Iterator;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.wrappers.MethodInvoker;

public class EntityUtils
{
    private MethodInvoker invoker;
    
    public EntityUtils() {
        this.invoker = Resilience.getInstance().getInvoker();
    }
    
    public Entity getClosestEntity(final Entity from, final boolean players, final boolean mobs, final boolean animals, final boolean invisibles, final boolean propBlocks) {
        Entity prevEntity = null;
        for (final Object o : this.invoker.getEntityList()) {
            if (o instanceof EntityLivingBase) {
                final EntityLivingBase entity = (EntityLivingBase)o;
                if (entity == null) {
                    continue;
                }
                if (this.isThePlayer((Entity)entity)) {
                    continue;
                }
                if (prevEntity == null) {
                    if (this.isEntityFriend((Entity)entity)) {
                        continue;
                    }
                    prevEntity = (Entity)entity;
                }
                else {
                    if (this.isEntityFriend((Entity)entity)) {
                        continue;
                    }
                    if (entity instanceof EntityOtherPlayerMP && players) {
                        if (!invisibles && this.invoker.isInvisible((Entity)entity)) {
                            continue;
                        }
                        if (this.isCloser((Entity)entity, prevEntity, 2.0f)) {
                            prevEntity = (Entity)entity;
                        }
                    }
                    if (entity instanceof EntityMob && !(entity instanceof EntityHorse) && !(entity instanceof EntityAnimal) && mobs && this.isCloser((Entity)entity, prevEntity, 1.0f)) {
                        prevEntity = (Entity)entity;
                    }
                    if ((entity instanceof EntityAnimal || entity instanceof EntityHorse) && animals && this.isCloser((Entity)entity, prevEntity, 0.0f)) {
                        prevEntity = (Entity)entity;
                    }
                }
            }
            if (o instanceof EntityFallingBlock && propBlocks) {
                final EntityFallingBlock entity2 = (EntityFallingBlock)o;
                if (entity2 == null || !this.isCloser((Entity)entity2, prevEntity, 0.0f)) {
                    continue;
                }
                prevEntity = (Entity)entity2;
            }
        }
        return prevEntity;
    }
    
    public boolean isCloser(final Entity now, final Entity first, final float error) {
        if (first.getClass().isAssignableFrom(now.getClass())) {
            return this.invoker.getDistanceToEntity((Entity)Resilience.getInstance().getWrapper().getPlayer(), now) < this.invoker.getDistanceToEntity((Entity)Resilience.getInstance().getWrapper().getPlayer(), first);
        }
        return this.invoker.getDistanceToEntity((Entity)Resilience.getInstance().getWrapper().getPlayer(), now) < this.invoker.getDistanceToEntity((Entity)Resilience.getInstance().getWrapper().getPlayer(), first) + error;
    }
    
    public boolean canHit(final Entity e) {
        return e != null && !this.invoker.isEntityDead(e) && this.invoker.canEntityBeSeen(e) && this.isWithinRange(6.0f, e);
    }
    
    public boolean canHit(final Entity e, final float range) {
        return e != null && !this.invoker.isEntityDead(e) && this.invoker.canEntityBeSeen(e) && this.isWithinRange(range, e);
    }
    
    public boolean isWithinRange(final float range, final Entity e) {
        return this.invoker.getDistanceToEntity(e, (Entity)Resilience.getInstance().getWrapper().getPlayer()) <= range;
    }
    
    public void hitEntity(final Entity e) {
        this.invoker.attackEntity(e);
        this.invoker.swingItem();
    }
    
    public void hitEntity(final Entity e, final boolean block) {
        this.invoker.attackEntity(e);
        final EventOnClick eventClick = new EventOnClick(0);
        eventClick.onEvent();
        this.invoker.swingItem();
        if (block && this.invoker.getCurrentItem().getItem() instanceof ItemSword) {
            this.invoker.useItemRightClick(this.invoker.getCurrentItem());
        }
    }
    
    public boolean isThePlayer(final Entity e) {
        return e != null && Resilience.getInstance().getWrapper().getPlayer() != null && e == Resilience.getInstance().getWrapper().getPlayer();
    }
    
    public boolean isEntityFriend(final Entity e) {
        if (e instanceof EntityOtherPlayerMP) {
            final EntityOtherPlayerMP player = (EntityOtherPlayerMP)e;
            return FriendManager.isFriend(this.invoker.getPlayerName((EntityPlayer)player));
        }
        return false;
    }
    
    public boolean isEntityEnemy(final Entity e) {
        if (e instanceof EntityOtherPlayerMP) {
            final EntityOtherPlayerMP player = (EntityOtherPlayerMP)e;
            return EnemyManager.isEnemy(this.invoker.getPlayerName((EntityPlayer)player));
        }
        return false;
    }
    
    public void faceEntity(final Entity e) {
        final double var4 = this.invoker.getPosX(e) - this.invoker.getPosX();
        final double var5 = this.invoker.getPosZ(e) - this.invoker.getPosZ();
        double var6;
        if (e instanceof EntityLivingBase) {
            final EntityLivingBase entity = (EntityLivingBase)e;
            var6 = this.invoker.getPosY((Entity)entity) + this.invoker.getEyeHeight((Entity)entity) - (this.invoker.getPosY() + this.invoker.getEyeHeight());
        }
        else {
            var6 = (this.invoker.getBoundboxMinY(e) + this.invoker.getBoundboxMaxY(e)) / 2.0 - (this.invoker.getPosY() + this.invoker.getEyeHeight());
        }
        final double var7 = Utils.sqrt_double(var4 * var4 + var5 * var5);
        final float var8 = (float)(Math.atan2(var5, var4) * 180.0 / 3.141592653589793) - 90.0f;
        final float var9 = (float)(-(Math.atan2(var6, var7) * 180.0 / 3.141592653589793));
        this.invoker.setRotationPitch(var9);
        this.invoker.setRotationYaw(var8);
    }
    
    public boolean isEntityDead(final Entity entity) {
        return entity.isDead;
    }
}
