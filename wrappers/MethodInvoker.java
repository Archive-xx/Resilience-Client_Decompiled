// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.wrappers;

import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.Vec3;
import net.minecraft.client.gui.GuiButton;
import com.mojang.authlib.GameProfile;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.network.Packet;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StringUtils;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.block.Block;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import java.lang.reflect.Field;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import com.krispdev.resilience.Resilience;
import net.minecraft.client.renderer.entity.RenderItem;

public class MethodInvoker
{
    private RenderItem renderItem;
    private Wrapper wrapper;
    private String entityLivingBaseLoc;
    
    public MethodInvoker() {
        this.renderItem = new RenderItem();
        this.wrapper = Resilience.getInstance().getWrapper();
        this.entityLivingBaseLoc = "net.minecraft.entity.EntityLivingBase";
    }
    
    public void sendChatMessage(final String msg) {
        this.wrapper.getPlayer().sendChatMessage(msg);
    }
    
    public void addChatMessage(final String str) {
        final Object chat = new ChatComponentText(str);
        if (str != null) {
            this.wrapper.getMinecraft().ingameGUI.getChatGUI().func_146227_a((IChatComponent)chat);
        }
    }
    
    public float getRotationYaw() {
        return this.wrapper.getPlayer().rotationYaw;
    }
    
    public float getRotationPitch() {
        return this.wrapper.getPlayer().rotationPitch;
    }
    
    public void setRotationYaw(final float yaw) {
        this.wrapper.getPlayer().rotationYaw = yaw;
    }
    
    public void setRotationPitch(final float pitch) {
        this.wrapper.getPlayer().rotationPitch = pitch;
    }
    
    public void setSprinting(final boolean sprinting) {
        this.wrapper.getPlayer().setSprinting(sprinting);
    }
    
    public boolean isOnLadder() {
        return this.wrapper.getPlayer().isOnLadder();
    }
    
    public float moveForward() {
        return this.wrapper.getPlayer().moveForward;
    }
    
    public boolean isCollidedHorizontally() {
        return this.wrapper.getPlayer().isCollidedHorizontally;
    }
    
    public void setMotionX(final double x) {
        this.wrapper.getPlayer().motionX = x;
    }
    
    public void setMotionY(final double y) {
        this.wrapper.getPlayer().motionY = y;
    }
    
    public void setMotionZ(final double z) {
        this.wrapper.getPlayer().motionZ = z;
    }
    
    public double getMotionX() {
        return this.wrapper.getPlayer().motionX;
    }
    
    public double getMotionY() {
        return this.wrapper.getPlayer().motionY;
    }
    
    public double getMotionZ() {
        return this.wrapper.getPlayer().motionZ;
    }
    
    public void setLandMovementFactor(final float newFactor) {
        try {
            final Class elb = Class.forName(this.entityLivingBaseLoc);
            final Field landMovement = elb.getDeclaredField("landMovementFactor");
            landMovement.setAccessible(true);
            landMovement.set(this.wrapper.getPlayer(), newFactor);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setJumpMovementFactor(final float newFactor) {
        try {
            final Class elb = Class.forName(this.entityLivingBaseLoc);
            final Field landMovement = elb.getDeclaredField("jumpMovementFactor");
            landMovement.setAccessible(true);
            landMovement.set(this.wrapper.getPlayer(), newFactor);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public float getGammaSetting() {
        return this.wrapper.getGameSettings().gammaSetting;
    }
    
    public void setGammaSetting(final float newSetting) {
        this.wrapper.getGameSettings().gammaSetting = newSetting;
    }
    
    public int getJumpCode() {
        return this.wrapper.getGameSettings().keyBindJump.getKeyCode();
    }
    
    public int getForwardCode() {
        return this.wrapper.getGameSettings().keyBindForward.getKeyCode();
    }
    
    public void setJumpKeyPressed(final boolean pressed) {
        this.wrapper.getGameSettings().keyBindJump.pressed = pressed;
    }
    
    public void setForwardKeyPressed(final boolean pressed) {
        this.wrapper.getGameSettings().keyBindForward.pressed = pressed;
    }
    
    public void setUseItemKeyPressed(final boolean pressed) {
        this.wrapper.getGameSettings().keyBindUseItem.pressed = pressed;
    }
    
    public int getSneakCode() {
        return this.wrapper.getGameSettings().keyBindSneak.getKeyCode();
    }
    
    public synchronized void displayScreen(final GuiScreen screen) {
        this.wrapper.getMinecraft().displayGuiScreen(screen);
    }
    
    public List getEntityList() {
        return this.wrapper.getWorld().loadedEntityList;
    }
    
    public float getDistanceToEntity(final Entity from, final Entity to) {
        return from.getDistanceToEntity(to);
    }
    
    public boolean isEntityDead(final Entity e) {
        return e.isDead;
    }
    
    public boolean canEntityBeSeen(final Entity e) {
        return this.wrapper.getPlayer().canEntityBeSeen(e);
    }
    
    public void attackEntity(final Entity e) {
        this.wrapper.getPlayerController().attackEntity((EntityPlayer)this.wrapper.getPlayer(), e);
    }
    
    public void swingItem() {
        this.wrapper.getPlayer().swingItem();
    }
    
    public float getEyeHeight() {
        return this.wrapper.getPlayer().getEyeHeight();
    }
    
    public float getEyeHeight(final Entity e) {
        return e.getEyeHeight();
    }
    
    public double getPosX() {
        return this.wrapper.getPlayer().posX;
    }
    
    public double getPosY() {
        return this.wrapper.getPlayer().posY;
    }
    
    public double getPosZ() {
        return this.wrapper.getPlayer().posZ;
    }
    
    public double getPosX(final Entity e) {
        return e.posX;
    }
    
    public double getPosY(final Entity e) {
        return e.posY;
    }
    
    public double getPosZ(final Entity e) {
        return e.posZ;
    }
    
    public void setInvSlot(final int slot) {
        this.wrapper.getPlayer().inventory.currentItem = slot;
    }
    
    public int getCurInvSlot() {
        return this.wrapper.getPlayer().inventory.currentItem;
    }
    
    public ItemStack getCurrentItem() {
        return this.wrapper.getPlayer().getCurrentEquippedItem();
    }
    
    public ItemStack getItemAtSlot(final int slot) {
        return this.wrapper.getPlayer().inventoryContainer.getSlot(slot).getStack();
    }
    
    public ItemStack getItemAtSlotHotbar(final int slot) {
        return this.wrapper.getPlayer().inventory.getStackInSlot(slot);
    }
    
    public int getIdFromItem(final Item item) {
        return Item.getIdFromItem(item);
    }
    
    public void clickWindow(final int slot, final int mode, final int button, final EntityPlayer player) {
        this.wrapper.getPlayerController().windowClick(player.inventoryContainer.windowId, slot, button, mode, player);
    }
    
    public void clickWindow(final int id, final int slot, final int mode, final int button, final EntityPlayer player) {
        this.wrapper.getPlayerController().windowClick(id, slot, button, mode, player);
    }
    
    public void sendUseItem(final ItemStack itemStack, final EntityPlayer player) {
        this.wrapper.getPlayerController().sendUseItem(player, (World)this.wrapper.getWorld(), itemStack);
    }
    
    public Item getItemById(final int id) {
        return Item.getItemById(id);
    }
    
    public void dropItemStack(final int slot) {
        for (int i = 0; i < this.wrapper.getPlayer().inventory.getStackInSlot(slot).stackSize; ++i) {
            this.wrapper.getPlayer().dropOneItem(false);
        }
    }
    
    public int getPacketVelocityEntityId(final S12PacketEntityVelocity p) {
        return p.func_149412_c();
    }
    
    public Entity getEntityById(final int id) {
        return this.wrapper.getWorld().getEntityByID(id);
    }
    
    public int getXMovePacketVel(final S12PacketEntityVelocity p) {
        return p.func_149411_d();
    }
    
    public int getYMovePacketVel(final S12PacketEntityVelocity p) {
        return p.func_149410_e();
    }
    
    public int getZMovePacketVel(final S12PacketEntityVelocity p) {
        return p.func_149409_f();
    }
    
    public void rightClick() {
        this.wrapper.getMinecraft().func_147121_ag();
    }
    
    public void leftClick() {
        this.wrapper.getMinecraft().func_147116_af();
    }
    
    public void setKeyBindAttackPressed(final boolean flag) {
        this.wrapper.getGameSettings().keyBindAttack.pressed = flag;
    }
    
    public MovingObjectPosition getObjectMouseOver() {
        return this.wrapper.getMinecraft().objectMouseOver;
    }
    
    public Block getBlock(final int x, final int y, final int z) {
        return this.wrapper.getWorld().getBlock(x, y, z);
    }
    
    public float getStrVsBlock(final ItemStack item, final Block block) {
        return item.func_150997_a(block);
    }
    
    public void useItemRightClick(final ItemStack item) {
        item.useItemRightClick((World)this.wrapper.getWorld(), (EntityPlayer)this.wrapper.getPlayer());
    }
    
    public ItemStack[] getArmourInventory() {
        return this.wrapper.getPlayer().inventory.armorInventory;
    }
    
    public void enableStandardItemLighting() {
        RenderHelper.enableStandardItemLighting();
    }
    
    public void disableStandardItemLighting() {
        RenderHelper.disableStandardItemLighting();
    }
    
    public void renderItemIntoGUI(final ItemStack is, final int x, final int y) {
        this.renderItem.renderItemIntoGUI(this.wrapper.getFontRenderer(), this.wrapper.getRenderEngine(), is, x, y);
    }
    
    public void renderItemOverlayIntoGUI(final ItemStack is, final int x, final int y) {
        this.renderItem.renderItemOverlayIntoGUI(this.wrapper.getFontRenderer(), this.wrapper.getRenderEngine(), is, x, y);
    }
    
    public String stripControlCodes(final String s) {
        return StringUtils.stripControlCodes(s);
    }
    
    public String getPlayerName(final EntityPlayer player) {
        return player.getCommandSenderName();
    }
    
    public String getSessionUsername() {
        return this.wrapper.getSession().getUsername();
    }
    
    public double getBoundboxMinY(final Entity e) {
        return e.boundingBox.minY;
    }
    
    public double getBoundboxMaxY(final Entity e) {
        return e.boundingBox.maxY;
    }
    
    public double getBoundboxMinX(final Entity e) {
        return e.boundingBox.minX;
    }
    
    public double getBoundboxMaxX(final Entity e) {
        return e.boundingBox.maxX;
    }
    
    public double getBoundboxMinZ(final Entity e) {
        return e.boundingBox.minZ;
    }
    
    public double getBoundboxMaxZ(final Entity e) {
        return e.boundingBox.maxZ;
    }
    
    public int getDisplayHeight() {
        return this.wrapper.getMinecraft().displayHeight;
    }
    
    public int getDisplayWidth() {
        return this.wrapper.getMinecraft().displayWidth;
    }
    
    public GuiScreen getCurrentScreen() {
        return this.wrapper.getMinecraft().currentScreen;
    }
    
    public void setupOverlayRendering() {
        this.wrapper.getEntityRenderer().setupOverlayRendering();
    }
    
    public int getScaledWidth(final ScaledResolution sr) {
        return sr.getScaledWidth();
    }
    
    public int getScaledHeight(final ScaledResolution sr) {
        return sr.getScaledHeight();
    }
    
    public void setEntityLight(final boolean state) {
        if (state) {
            this.wrapper.getEntityRenderer().enableLightmap(1.0);
        }
        else {
            this.wrapper.getEntityRenderer().disableLightmap(1.0);
        }
    }
    
    public ServerData getCurrentServerData() {
        return this.wrapper.getMinecraft().currentServerData;
    }
    
    public boolean isInCreativeMode() {
        return this.wrapper.getPlayerController().isInCreativeMode();
    }
    
    public void setStackDisplayName(final ItemStack is, final String s) {
        is.setStackDisplayName(s);
    }
    
    public String getItemDisplayName(final ItemStack is) {
        return is.getDisplayName();
    }
    
    public void sendPacket(final Packet p) {
        this.wrapper.getPlayer().sendQueue.addToSendQueue(p);
    }
    
    public Enchantment[] getEnchantList() {
        return Enchantment.enchantmentsList;
    }
    
    public void addEnchantment(final ItemStack is, final Enchantment e, final int level) {
        is.addEnchantment(e, 127);
    }
    
    public double getLastTickPosX(final Entity e) {
        return e.lastTickPosX;
    }
    
    public double getLastTickPosY(final Entity e) {
        return e.lastTickPosY;
    }
    
    public double getLastTickPosZ(final Entity e) {
        return e.lastTickPosZ;
    }
    
    public float getEntityHeight(final Entity e) {
        return e.height;
    }
    
    public double getRenderPosX() {
        return RenderManager.renderPosX;
    }
    
    public double getRenderPosY() {
        return RenderManager.renderPosY;
    }
    
    public double getRenderPosZ() {
        return RenderManager.renderPosZ;
    }
    
    public float getPlayerViewX() {
        return RenderManager.instance.playerViewX;
    }
    
    public float getPlayerViewY() {
        return RenderManager.instance.playerViewY;
    }
    
    public void loadRenderers() {
        if (this.wrapper.getMinecraft().renderGlobal != null) {
            this.wrapper.getMinecraft().renderGlobal.loadRenderers();
        }
    }
    
    public void setSmoothLighting(final int mode) {
        this.wrapper.getGameSettings().ambientOcclusion = mode;
    }
    
    public int getSmoothLighting() {
        return (this.wrapper.getGameSettings() == null) ? 2 : this.wrapper.getGameSettings().ambientOcclusion;
    }
    
    public int getIdFromBlock(final Block block) {
        return Block.getIdFromBlock(block);
    }
    
    public List getTileEntityList() {
        return this.wrapper.getWorld().field_147482_g;
    }
    
    public int getChestX(final TileEntityChest chest) {
        return chest.field_145851_c;
    }
    
    public int getChestY(final TileEntityChest chest) {
        return chest.field_145848_d;
    }
    
    public int getChestZ(final TileEntityChest chest) {
        return chest.field_145849_e;
    }
    
    public boolean isBurning() {
        return this.wrapper.getPlayer().isBurning();
    }
    
    public void setRightDelayTimer(final int delay) {
        this.wrapper.getMinecraft().rightClickDelayTimer = delay;
    }
    
    public int getItemInUseDuration() {
        return this.wrapper.getPlayer().getItemInUseDuration();
    }
    
    public boolean isOnGround() {
        return this.wrapper.getPlayer().onGround;
    }
    
    public boolean isInWater() {
        return this.wrapper.getPlayer().isInWater();
    }
    
    public void setFallDistance(final float f) {
        this.wrapper.getPlayer().fallDistance = f;
    }
    
    public void setOnGround(final boolean b) {
        this.wrapper.getPlayer().onGround = b;
    }
    
    public int getFoodLevel() {
        return this.wrapper.getPlayer().getFoodStats().getFoodLevel();
    }
    
    public float getHealth() {
        return this.wrapper.getPlayer().getHealth();
    }
    
    public void removeEntityFromWorld(final int id) {
        this.wrapper.getWorld().removeEntityFromWorld(id);
    }
    
    public void addEntityToWorld(final Entity e, final int id) {
        this.wrapper.getWorld().addEntityToWorld(id, e);
    }
    
    public void setTimerSpeed(final float speed) {
        this.wrapper.getMinecraft().timer.timerSpeed = speed;
    }
    
    public void jump() {
        this.wrapper.getPlayer().jump();
    }
    
    public GameProfile getGameProfile(final EntityPlayer ep) {
        return ep.field_146106_i;
    }
    
    public void setGameProfile(final GameProfile profile, final EntityPlayer ep) {
        ep.field_146106_i = profile;
    }
    
    public void setPositionAndRotation(final Entity e, final double x, final double y, final double z, final float yaw, final float pitch) {
        e.setPositionAndRotation(x, y, z, yaw, pitch);
    }
    
    public void copyInventory(final EntityPlayer from, final EntityPlayer to) {
        from.inventory.copyInventory(to.inventory);
    }
    
    public void setNoClip(final boolean state) {
        this.wrapper.getPlayer().noClip = state;
    }
    
    public void setSneakKeyPressed(final boolean pressed) {
        this.wrapper.getGameSettings().keyBindSneak.pressed = pressed;
    }
    
    public boolean isSneaking() {
        return this.wrapper.getPlayer().isSneaking();
    }
    
    public void setStepHeight(final float value) {
        this.wrapper.getPlayer().stepHeight = value;
    }
    
    public int getWidth() {
        return this.getDisplayWidth() / 2;
    }
    
    public int getHeight() {
        return this.getDisplayHeight() / 2;
    }
    
    public int getId(final GuiButton btn) {
        return btn.id;
    }
    
    public void addButton(final GuiScreen screen, final GuiButton btn) {
        screen.buttonList.add(btn);
    }
    
    public void clearButtons(final GuiScreen screen) {
        screen.buttonList.clear();
    }
    
    public Wrapper getWrapper() {
        return this.wrapper;
    }
    
    public MovingObjectPosition rayTraceBlocks(final Vec3 vecFromPool, final Vec3 vecFromPool2) {
        return this.wrapper.getWorld().rayTraceBlocks(vecFromPool, vecFromPool2);
    }
    
    public Block getTileEntityBlock(final EntityFallingBlock b) {
        return b.func_145805_f();
    }
    
    public float getFallDistance(final Entity e) {
        return e.fallDistance;
    }
    
    public boolean isInvisible(final Entity e) {
        return e.isInvisible();
    }
}
