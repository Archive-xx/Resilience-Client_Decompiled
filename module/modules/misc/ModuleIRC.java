// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.module.modules.misc;

import com.krispdev.resilience.irc.src.IrcException;
import java.io.IOException;
import com.krispdev.resilience.irc.src.NickAlreadyInUseException;
import com.krispdev.resilience.irc.ResilienceIRCBot;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.module.categories.ModuleCategory;
import com.krispdev.resilience.module.modules.DefaultModule;

public class ModuleIRC extends DefaultModule
{
    public ModuleIRC() {
        super("IRC", 0);
        this.setCategory(ModuleCategory.MISC);
        this.setDescription("Enables/Disables the Internet Relay Chat ingame");
    }
    
    @Override
    public void onEnable() {
        if (Resilience.getInstance().getValues().bot == null) {
            Resilience.getInstance().getValues().bot = new ResilienceIRCBot(this.invoker.getSessionUsername());
        }
        else {
            try {
                Resilience.getInstance().getValues().bot.connect("#ResilienceClient");
            }
            catch (NickAlreadyInUseException e) {
                Resilience.getInstance().getValues().bot.setUsername(String.valueOf(Resilience.getInstance().getValues().bot.getUsername()) + "_");
                this.setEnabled(true);
                e.printStackTrace();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            catch (IrcException e3) {
                e3.printStackTrace();
            }
        }
        new Thread(Resilience.getInstance().getValues().bot).start();
        Resilience.getInstance().getValues().ircEnabled = true;
    }
    
    @Override
    public void onDisable() {
        Resilience.getInstance().getValues().ircEnabled = false;
    }
}
