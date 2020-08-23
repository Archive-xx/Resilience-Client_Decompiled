// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules;

import com.krispdev.resilience.event.events.player.EventOutwardPacket;
import com.krispdev.resilience.event.events.player.EventValueChange;
import com.krispdev.resilience.event.events.player.EventOnClick;
import com.krispdev.resilience.event.events.player.EventOnRender;
import com.krispdev.resilience.event.events.player.EventGameShutdown;
import com.krispdev.resilience.event.events.player.EventBlockClick;
import com.krispdev.resilience.event.events.player.EventPacketReceive;
import com.krispdev.resilience.event.events.player.EventHealthUpdate;
import com.krispdev.resilience.event.events.player.EventPostMotion;
import com.krispdev.resilience.event.events.player.EventPreMotion;
import com.krispdev.resilience.event.events.player.EventOnUpdate;
import com.krispdev.resilience.event.listeners.Listener;
import com.krispdev.resilience.Resilience;
import java.util.ArrayList;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.wrappers.MethodInvoker;
import com.krispdev.resilience.gui.objects.buttons.ModOptionBox;
import java.util.List;
import com.krispdev.resilience.interfaces.Toggleable;
import com.krispdev.resilience.interfaces.Bindable;
import com.krispdev.resilience.event.listeners.ModListener;

public abstract class DefaultModule implements ModListener, Bindable, Toggleable
{
    public List<ModOptionBox> guiExtras;
    protected MethodInvoker invoker;
    private String name;
    private String displayName;
    private boolean enabled;
    private String desc;
    private int keyCode;
    private ModuleCategory category;
    private boolean visible;
    private boolean save;
    
    public DefaultModule(final String name, final int keyCode) {
        this.guiExtras = new ArrayList();
        this.invoker = Resilience.getInstance().getInvoker();
        this.name = name;
        this.displayName = name;
        this.keyCode = keyCode;
        this.desc = "No description found";
        this.enabled = false;
        this.visible = true;
        this.category = ModuleCategory.NONE;
        this.save = true;
        Resilience.getInstance().getEventManager().registerGameListener((Listener)this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public String getDescription() {
        return this.desc;
    }
    
    public int getKeyCode() {
        return this.keyCode;
    }
    
    public ModuleCategory getCategory() {
        return this.category;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setName(final String s) {
        this.name = s;
    }
    
    public void setDisplayName(final String s) {
        this.displayName = s;
    }
    
    public void setDescription(final String s) {
        this.desc = s;
    }
    
    public void setKeyBind(final int keyCode) {
        this.keyCode = keyCode;
    }
    
    public void setCategory(final ModuleCategory c) {
        this.category = c;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public void setSave(final boolean save) {
        this.save = save;
    }
    
    public boolean shouldSave() {
        return this.save;
    }
    
    public void onUpdate(final EventOnUpdate e) {
    }
    
    public void onPreMotion(final EventPreMotion e) {
    }
    
    public void onPostMotion(final EventPostMotion e) {
    }
    
    public void onHealthUpdate(final EventHealthUpdate e) {
    }
    
    public void onPacketReceive(final EventPacketReceive e) {
    }
    
    public void onBlockClicked(final EventBlockClick e) {
    }
    
    public void onGameShutdown(final EventGameShutdown e) {
    }
    
    public void onRender(final EventOnRender e) {
    }
    
    public void onClick(final EventOnClick e) {
    }
    
    public void onValueChange(final EventValueChange event) {
    }
    
    public void onOutwardPacket(final EventOutwardPacket event) {
    }
    
    public abstract void onEnable();
    
    public abstract void onDisable();
    
    public void onToggle() {
    }
    
    public void toggle() {
        this.enabled = !this.enabled;
        this.onToggle();
        if (this.enabled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        if (Resilience.getInstance().isEnabled()) {
            Resilience.getInstance().getFileManager().saveEnabledMods(new String[0]);
        }
    }
    
    public void setEnabled(final boolean flag) {
        this.enabled = flag;
        this.onToggle();
        if (this.enabled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        if (Resilience.getInstance().isEnabled()) {
            Resilience.getInstance().getFileManager().saveEnabledMods(new String[0]);
        }
    }
    
    public void onKeyDown(final int keyCode) {
        if (!Resilience.getInstance().isEnabled() && this.getCategory() != ModuleCategory.GUI) {
            return;
        }
        if (keyCode == this.keyCode) {
            this.toggle();
        }
    }
}
