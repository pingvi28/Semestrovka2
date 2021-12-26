package ru.kpfu.itis.game.client;

import ru.kpfu.itis.game.common.PlayerConnection;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.protocol.Properties;

import java.io.IOException;
import java.net.Socket;

public class GameClient implements Client{
    protected Socket socket;

    @Override
    public void connect() throws ClientException {
        try{
            socket = new Socket(Properties.HOST, Properties.port);
        }
        catch(IOException ex){
            throw new ClientException("Can't connect.", ex);
        }
    }

    @Override
    public Message sendMessage(Message message) throws ClientException {
        return null;
    }

}
