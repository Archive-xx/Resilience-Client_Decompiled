// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.command.commands;

import java.util.Iterator;
import com.krispdev.resilience.Resilience;
import java.util.ArrayList;
import com.krispdev.resilience.command.Command;

public class CmdHelp extends Command
{
    private int resultsPerPage;
    private ArrayList<Page> pages;
    
    public CmdHelp() {
        super("help", " [Page #]", "Gives command help");
        this.resultsPerPage = 4;
        this.pages = new ArrayList();
    }
    
    public boolean recieveCommand(final String cmd) throws Exception {
        final String[] args = cmd.split("help ");
        final int page = Integer.parseInt(args[1].trim());
        if (page == 0) {
            Resilience.getInstance().getLogger().warningChat("Page value cannot be zero");
            return true;
        }
        this.pages.clear();
        int iterator = 0;
        int pageNum = 0;
        int numOfPages = 0;
        if (Command.cmdList.size() % this.resultsPerPage != 0) {
            numOfPages = Command.cmdList.size() / this.resultsPerPage + 2;
        }
        else {
            numOfPages = Command.cmdList.size() / this.resultsPerPage + 1;
        }
        for (int i = 1; i <= numOfPages; ++i) {
            this.pages.add(new Page(this, i));
        }
        for (final Command command : Command.cmdList) {
            if (++iterator >= this.resultsPerPage) {
                ++pageNum;
                iterator = 0;
            }
            this.pages.get(pageNum).commands.add(command);
        }
        if (page > this.pages.size()) {
            Resilience.getInstance().getLogger().warningChat("Page value too high! Maximum: " + this.pages.size());
            return true;
        }
        Resilience.getInstance().getLogger().infoChat("§7Showing page " + page + "/" + numOfPages);
        Resilience.getInstance().getInvoker().addChatMessage("");
        for (final Command com : this.pages.get(page - 1).commands) {
            Resilience.getInstance().getInvoker().addChatMessage("§b" + Resilience.getInstance().getCmdPrefix() + com.getWords() + com.getExtras() + " §f- " + com.getDesc());
        }
        return true;
    }
    
    private class Page
    {
        private int number;
        public ArrayList<Command> commands;
        
        public Page(final CmdHelp this$0, final int number) {
            this.this$0 = this$0;
            this.commands = new ArrayList();
            this.number = number;
        }
    }
}
