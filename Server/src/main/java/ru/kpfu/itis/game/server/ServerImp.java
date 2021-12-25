package ru.kpfu.itis.game.server;

import ru.kpfu.itis.game.common.*;
import ru.kpfu.itis.game.server.exeption.ServerException;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.protocol.Properties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerImp implements Server{
    private final ServerSocket serverSocket;
    protected List<ServerEventListener> listeners;
    protected boolean started;
    protected List<Socket> sockets;

    public ServerImp() throws IllegalStateException{
        try {
            serverSocket = new ServerSocket(Properties.port);
            this.listeners = new ArrayList<>();
            this.started = false;
        }
        catch (IOException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void start(){
        System.out.println("Server started");
        started = true;
        while (true){
            try {
                System.out.println("Ожидаю");
                Socket socket = serverSocket.accept();
                System.out.println("Соединил");
            } catch (IOException e) {
                throw new IllegalStateException(e.getMessage());
            }
        }
    }

    @Override
    public void registerListener(ServerEventListener listener) throws ServerException {
        if(started){
            throw new ServerException("Server has been started already.");
        }
        listener.init(this);
        this.listeners.add(listener);
    }

    @Override
    public void sendMessage(int connectionId, Message message) throws ServerException{
        if(!started){
            throw new ServerException("Server hasn't been started yet.");
        }
        try{
            Socket socket = sockets.get(connectionId);
            socket.getOutputStream().write(message.getData());
            socket.getOutputStream().flush();
        } catch (IOException ex) {
            throw new ServerException("Can't send message.", ex);
        }
    }

    public void removeConnection(int id, Connection connection){
        if(connection == null)return;
        System.out.println("Пользователь ID:" + connection.getInformation().getClientId() +  "отсоединился");
    }
}
