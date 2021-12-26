package ru.kpfu.itis.game.server;

import ru.kpfu.itis.game.server.exeption.ServerException;
import ru.kpfu.itis.protocol.MessageGame;

public interface Server {
    void registerListener(ServerEventListener listener) throws ServerException;
    void sendMessage(int connectionId, MessageGame message) throws ServerException;
    void start() throws ServerException;
}