package ru.kpfu.itis.game.server;

import ru.kpfu.itis.game.server.exeption.ServerException;
import ru.kpfu.itis.protocol.Message;

public interface Server {
    public void registerListener(ServerEventListener listener) throws ServerException;
    public void sendMessage(int connectionId, Message message) throws ServerException;
    public void start() throws ServerException;
}