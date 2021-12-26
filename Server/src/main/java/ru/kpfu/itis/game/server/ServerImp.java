package ru.kpfu.itis.game.server;

import ru.kpfu.itis.game.common.*;
import ru.kpfu.itis.game.server.exeption.ServerException;
import ru.kpfu.itis.protocol.MessageGame;
import ru.kpfu.itis.protocol.Properties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerImp implements Server, ServerEventListener{
    private final ServerSocket serverSocket;
    protected List<ServerEventListener> listeners;
    protected boolean started;
    protected List<Socket> sockets;
    protected PlayerConnection[] connection;
    private Socket[] clientSockets;
    private int playerNumber = 1;
    protected int capacity = 2;

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
        clientSockets = new Socket[2];
        while (playerNumber != 3){
            try {
                System.out.println("Waite" + playerNumber);

                for (int i = 0; i < clientSockets.length ; i++) {
                    Socket socket = serverSocket.accept();
                    clientSockets[i] = socket;
                    //connection[i] = new PlayerConnection(socket, this);
                    //connection[i].userInformation.setClientId(i);
                    //connection[i].sendId(i);
                    System.out.println("Connected player " + i);
                }

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
        this.listeners.add(listener);
    }

    @Override
    public void sendMessage(int connectionId, MessageGame message) throws ServerException{
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

    @Override
    public void onConnectionReady(PlayerConnection connection) {

    }

    @Override
    public void onDisconnect(PlayerConnection connection) {

    }
}
