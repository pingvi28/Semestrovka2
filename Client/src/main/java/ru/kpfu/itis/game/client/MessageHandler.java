package ru.kpfu.itis.game.client;

import ru.kpfu.itis.game.listener.ClientEventListener;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.protocol.MessageGame;

import java.io.IOException;

public class MessageHandler implements Runnable{

    protected GameClient client;

    public MessageHandler(GameClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true){
            try {
                MessageGame message = MessageGame.readMessage(client.socket.getInputStream());
                for (ClientEventListener listener : client.listeners){
                    if (listener.getTypes().contains(message.getType())){
                        listener.submit(message, client.getID());
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
