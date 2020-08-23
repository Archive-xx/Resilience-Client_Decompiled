// 
// Decompiled by Procyon v0.5.36
// 

package com.krispdev.resilience.irc.src;

import java.io.File;
import java.net.Socket;

public class DccFileTransfer
{
    public static final int BUFFER_SIZE = 1024;
    private PircBot _bot;
    private DccManager _manager;
    private String _nick;
    private String _login;
    private String _hostname;
    private String _type;
    private long _address;
    private int _port;
    private long _size;
    private boolean _received;
    private Socket _socket;
    private long _progress;
    private File _file;
    private int _timeout;
    private boolean _incoming;
    private long _packetDelay;
    private long _startTime;
    
    DccFileTransfer(final PircBot bot, final DccManager manager, final String nick, final String login, final String hostname, final String type, final String filename, final long address, final int port, final long size) {
        this._login = null;
        this._hostname = null;
        this._socket = null;
        this._progress = 0L;
        this._file = null;
        this._timeout = 0;
        this._packetDelay = 0L;
        this._startTime = 0L;
        this._bot = bot;
        this._manager = manager;
        this._nick = nick;
        this._login = login;
        this._hostname = hostname;
        this._type = type;
        this._file = new File(filename);
        this._address = address;
        this._port = port;
        this._size = size;
        this._received = false;
        this._incoming = true;
    }
    
    DccFileTransfer(final PircBot bot, final DccManager manager, final File file, final String nick, final int timeout) {
        this._login = null;
        this._hostname = null;
        this._socket = null;
        this._progress = 0L;
        this._file = null;
        this._timeout = 0;
        this._packetDelay = 0L;
        this._startTime = 0L;
        this._bot = bot;
        this._manager = manager;
        this._nick = nick;
        this._file = file;
        this._size = file.length();
        this._timeout = timeout;
        this._received = true;
        this._incoming = false;
    }
    
    public synchronized void receive(final File file, final boolean resume) {
        if (!this._received) {
            this._received = true;
            this._file = file;
            if (this._type.equals("SEND") && resume) {
                this._progress = file.length();
                if (this._progress == 0L) {
                    this.doReceive(file, false);
                }
                else {
                    this._bot.sendCTCPCommand(this._nick, "DCC RESUME file.ext " + this._port + " " + this._progress);
                    this._manager.addAwaitingResume(this);
                }
            }
            else {
                this._progress = file.length();
                this.doReceive(file, resume);
            }
        }
    }
    
    void doReceive(final File file, final boolean resume) {
        new DccFileTransfer$1(this, file, resume).start();
    }
    
    void doSend(final boolean allowResume) {
        new DccFileTransfer$2(this, allowResume).start();
    }
    
    void setProgress(final long progress) {
        this._progress = progress;
    }
    
    private void delay() {
        if (this._packetDelay > 0L) {
            try {
                Thread.sleep(this._packetDelay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public String getNick() {
        return this._nick;
    }
    
    public String getLogin() {
        return this._login;
    }
    
    public String getHostname() {
        return this._hostname;
    }
    
    public File getFile() {
        return this._file;
    }
    
    public int getPort() {
        return this._port;
    }
    
    public boolean isIncoming() {
        return this._incoming;
    }
    
    public boolean isOutgoing() {
        return !this.isIncoming();
    }
    
    public void setPacketDelay(final long millis) {
        this._packetDelay = millis;
    }
    
    public long getPacketDelay() {
        return this._packetDelay;
    }
    
    public long getSize() {
        return this._size;
    }
    
    public long getProgress() {
        return this._progress;
    }
    
    public double getProgressPercentage() {
        return 100.0 * (this.getProgress() / (double)this.getSize());
    }
    
    public void close() {
        try {
            this._socket.close();
        }
        catch (Exception ex) {}
    }
    
    public long getTransferRate() {
        final long time = (System.currentTimeMillis() - this._startTime) / 1000L;
        if (time <= 0L) {
            return 0L;
        }
        return this.getProgress() / time;
    }
    
    public long getNumericalAddress() {
        return this._address;
    }
}
