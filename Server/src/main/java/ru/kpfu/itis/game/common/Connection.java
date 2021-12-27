package ru.kpfu.itis.game.common;

import ru.kpfu.itis.protocol.MessageGame;

import java.io.IOException;

public interface Connection {
    Information getInformation();

    void send(MessageGame message) throws IOException;
    void close();
    boolean isConnected();
    void disconnect();
}
