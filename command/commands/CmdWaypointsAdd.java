// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.objects.Waypoint;
import com.krispdev.resilience.command.Command;

public class CmdWaypointsAdd extends Command
{
    public CmdWaypointsAdd() {
        super("waypoints add ", "[name]", "Adds a waypoint at your coordinates");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        if (!cmd.startsWith("waypoints add")) {
            return false;
        }
        final String[] check = cmd.split(" ");
        if (check.length > 3) {
            return false;
        }
        final String[] args = cmd.split("add ");
        for (final Waypoint w : Waypoint.waypointsList) {
            if (w.getName().equalsIgnoreCase(check[2])) {
                Resilience.getInstance().getLogger().warningChat("Already waypoint with name §b" + w.getName());
                return true;
            }
        }
        Waypoint.waypointsList.add(new Waypoint(args[1], (int)Resilience.getInstance().getInvoker().getPosX(), (int)Resilience.getInstance().getInvoker().getPosY(), (int)Resilience.getInstance().getInvoker().getPosZ(), 1.0f, 1.0f, 1.0f));
        Resilience.getInstance().getFileManager().saveWaypoints(new String[0]);
        Resilience.getInstance().getLogger().infoChat("Successfully added waypoint §b" + args[1]);
        return true;
    }
}
