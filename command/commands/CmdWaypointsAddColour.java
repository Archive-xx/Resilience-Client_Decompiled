// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import com.krispdev.resilience.command.objects.Waypoint;
import com.krispdev.resilience.command.Command;

public class CmdWaypointsAddColour extends Command
{
    public CmdWaypointsAddColour() {
        super("waypoints add ", "[name] [%r] [%g] [%b]", "Adds a waypoint at your current coords (with color)");
    }
    
    @Override
    public boolean recieveCommand(final String cmd) throws Exception {
        if (!cmd.startsWith("waypoints add")) {
            return false;
        }
        final String[] check = cmd.split(" ");
        if (check.length < 6) {
            return false;
        }
        for (final Waypoint w : Waypoint.waypointsList) {
            if (w.getName().equalsIgnoreCase(check[2])) {
                Resilience.getInstance().getLogger().warningChat("Already waypoint with name §b" + w.getName());
                return true;
            }
        }
        Waypoint.waypointsList.add(new Waypoint(check[2], (int)Math.round(Resilience.getInstance().getInvoker().getPosX()), (int)Math.round(Resilience.getInstance().getInvoker().getPosY()), (int)Math.round(Resilience.getInstance().getInvoker().getPosZ()), Float.parseFloat(check[3]) / 100.0f, Float.parseFloat(check[5]) / 100.0f, Float.parseFloat(check[4]) / 100.0f));
        Resilience.getInstance().getFileManager().saveWaypoints(new String[0]);
        Resilience.getInstance().getLogger().infoChat("Successfully added waypoint §b" + check[2]);
        return true;
    }
}
