package ru.kpfu.itis.game.client;

import java.net.InetAddress;

public class Connection {
    private static Connection instanse;

    public Connection(InetAddress address, int port) {
        this.instanse = this;
    }
}
