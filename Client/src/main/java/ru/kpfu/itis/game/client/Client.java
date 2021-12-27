package ru.kpfu.itis.game.client;

import ru.kpfu.itis.game.exception.ClientException;
import ru.kpfu.itis.protocol.Message;

public interface Client {
    void connect() throws ClientException;
    Message sendMessage(Message message) throws ClientException;
}
