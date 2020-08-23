// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.objects.Waypoint;
import com.krispdev.resilience.command.Command;

public class CmdWaypointsList extends Command
{
    public CmdWaypointsList() {
        super("waypoints list", "", "Lists all your waypoints");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        if (!cmd.startsWith("waypoints list")) {
            return false;
        }
        for (final Waypoint w : Waypoint.waypointsList) {
            if (w != null) {
                Resilience.getInstance().getLogger().infoChat(w.toString());
            }
        }
        return true;
    }
}
