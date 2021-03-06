package ru.kpfu.itis.game.server;

import ru.kpfu.itis.game.common.*;
import ru.kpfu.itis.game.server.exeption.ServerException;
import ru.kpfu.itis.protocol.MessageGame;
import ru.kpfu.itis.protocol.Properties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerImp implements Server, ServerEventListener{
    private final ServerSocket serverSocket;
    public static Properties properties;
    protected List<ServerEventListener> listeners;
    protected boolean started;
    protected PlayerConnection[] connection;
    private Map<Socket, PlayerConnection> connectionMap = new HashMap<Socket, PlayerConnection>() {
    };
    protected int capacity = 2;
    boolean hasPlace;

    public ServerImp() throws IllegalStateException{
        try {
            serverSocket = new ServerSocket(Properties.port);
            properties = new Properties();
            this.listeners = new ArrayList<>();
            this.started = false;
            this.hasPlace = true;
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
                if(hasPlace){
                    for (int i = 1; i <= capacity ; i++) {
                        Socket socket = serverSocket.accept();
                        connectionMap.put(socket, new PlayerConnection(socket,i,this));
                        //connection[i] = new PlayerConnection(socket, this);
                        //connection[i].userInformation.setClientId(i);
                        //connection[i].sendId(i);
                        System.out.println("Connected player " + i);
                        if ( i == (capacity - 1)){
                            hasPlace = false;
                        }
                    }
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
            //Socket socket = sockets[connectionId];
            Socket socket = new Socket();
            socket.getOutputStream().write(message.getData());
            socket.getOutputStream().flush();
        } catch (IOException ex) {
            throw new ServerException("Can't send message.", ex);
        }
    }

    public void removeConnection(int id, Connection connection){
        if(connection == null)return;
        System.out.println("???????????????????????? ID:" + connection.getInformation().getClientId() +  "????????????????????????");
    }

    @Override
    public void onConnectionReady(PlayerConnection connection) {

    }

    @Override
    public void onDisconnect(PlayerConnection connection) {

    }
}
