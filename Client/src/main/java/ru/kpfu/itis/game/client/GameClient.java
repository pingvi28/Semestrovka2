package ru.kpfu.itis.game.client;

import ru.kpfu.itis.game.exception.ClientException;
import ru.kpfu.itis.game.listener.ClientEventListener;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.protocol.Properties;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameClient implements Client{
    protected List<ClientEventListener> listeners;
    protected final String address;
    protected final int port;
    protected Socket socket;
    public static int ID;
    protected boolean connected;
    protected MessageHandler messageHandler;

    public GameClient() {
        this.address = Properties.HOST;
        this.port = Properties.port;
        listeners = new ArrayList<>();
        connected = false;
    }

    @Override
    public void connect() throws ClientException {
        try{
            socket = new Socket(address, port);
            messageHandler = new MessageHandler(this);
            Thread messageHandlerThread = new Thread(messageHandler);
            messageHandlerThread.start();
            connected = true;
        }
        catch(IOException ex){
            throw new ClientException("Can't connect.", ex);
        }
    }

    @Override
    public Message sendMessage(Message message) throws ClientException {
        return null;
    }

    public static int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
