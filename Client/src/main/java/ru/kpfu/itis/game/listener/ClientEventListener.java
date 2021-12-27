package ru.kpfu.itis.game.listener;

import ru.kpfu.itis.game.exception.ClientEventListenerException;
import ru.kpfu.itis.protocol.Message;

import java.util.List;

public interface ClientEventListener extends Runnable{

    void init();
    void handle(Message message, int connectionId) throws ClientEventListenerException;
    List<Integer> getTypes();

    void submit(Message message, int connectionId);
}
