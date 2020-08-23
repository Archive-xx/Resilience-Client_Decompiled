// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.irc.src;

import java.util.StringTokenizer;
import java.util.Vector;

public class DccManager
{
    private PircBot _bot;
    private Vector _awaitingResume;
    
    DccManager(final PircBot bot) {
        this._awaitingResume = new Vector();
        this._bot = bot;
    }
    
    boolean processRequest(final String nick, final String login, final String hostname, final String request) {
        final StringTokenizer tokenizer = new StringTokenizer(request);
        tokenizer.nextToken();
        final String type = tokenizer.nextToken();
        final String filename = tokenizer.nextToken();
        if (type.equals("SEND")) {
            final long address = Long.parseLong(tokenizer.nextToken());
            final int port = Integer.parseInt(tokenizer.nextToken());
            long size = -1L;
            try {
                size = Long.parseLong(tokenizer.nextToken());
            }
            catch (Exception ex) {}
            final DccFileTransfer transfer = new DccFileTransfer(this._bot, this, nick, login, hostname, type, filename, address, port, size);
            this._bot.onIncomingFileTransfer(transfer);
        }
        else if (type.equals("RESUME")) {
            final int port2 = Integer.parseInt(tokenizer.nextToken());
            final long progress = Long.parseLong(tokenizer.nextToken());
            DccFileTransfer transfer2 = null;
            synchronized (this._awaitingResume) {
                for (int i = 0; i < this._awaitingResume.size(); ++i) {
                    transfer2 = this._awaitingResume.elementAt(i);
                    if (transfer2.getNick().equals(nick) && transfer2.getPort() == port2) {
                        this._awaitingResume.removeElementAt(i);
                        break;
                    }
                }
            }
            // monitorexit(this._awaitingResume)
            if (transfer2 != null) {
                transfer2.setProgress(progress);
                this._bot.sendCTCPCommand(nick, "DCC ACCEPT file.ext " + port2 + " " + progress);
            }
        }
        else if (type.equals("ACCEPT")) {
            final int port2 = Integer.parseInt(tokenizer.nextToken());
            final long progress = Long.parseLong(tokenizer.nextToken());
            DccFileTransfer transfer2 = null;
            synchronized (this._awaitingResume) {
                for (int i = 0; i < this._awaitingResume.size(); ++i) {
                    transfer2 = this._awaitingResume.elementAt(i);
                    if (transfer2.getNick().equals(nick) && transfer2.getPort() == port2) {
                        this._awaitingResume.removeElementAt(i);
                        break;
                    }
                }
            }
            // monitorexit(this._awaitingResume)
            if (transfer2 != null) {
                transfer2.doReceive(transfer2.getFile(), true);
            }
        }
        else {
            if (!type.equals("CHAT")) {
                return false;
            }
            final long address = Long.parseLong(tokenizer.nextToken());
            final int port = Integer.parseInt(tokenizer.nextToken());
            final DccChat chat = new DccChat(this._bot, nick, login, hostname, address, port);
            new DccManager$1(this, chat).start();
        }
        return true;
    }
    
    void addAwaitingResume(final DccFileTransfer transfer) {
        synchronized (this._awaitingResume) {
            this._awaitingResume.addElement(transfer);
        }
        // monitorexit(this._awaitingResume)
    }
    
    void removeAwaitingResume(final DccFileTransfer transfer) {
        this._awaitingResume.removeElement(transfer);
    }
}
