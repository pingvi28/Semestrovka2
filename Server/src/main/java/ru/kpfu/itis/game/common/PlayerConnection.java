package ru.kpfu.itis.game.common;

import ru.kpfu.itis.game.server.ServerEventListener;
import ru.kpfu.itis.protocol.MessageGame;

import java.io.*;
import java.net.Socket;

public class PlayerConnection implements Connection{
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;
    public Information userInformation;
    private final ServerEventListener eventListener;

    public PlayerConnection(Socket socket, final ServerEventListener eventListener){
        this.socket = socket;
        this.eventListener = eventListener;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void sendId(int id) throws IOException {
        DataOutputStream dataOutputStream= new DataOutputStream(out);
        dataOutputStream.writeInt(id);
    }

    @Override
    public Information getInformation() {
        return userInformation;
    }

    @Override
    public void send(MessageGame message) throws IOException {

    }

    @Override
    public void close() {
        try {
            socket.close();
        }
        catch (IOException e) {
            System.out.println("(PC#close socket) " + e.getMessage() + " : " + e.getCause());
        }
    }

    @Override
    public int getId() {
        return userInformation.getClientId();
    }

    @Override
    public boolean isConnected() {
        return !socket.isClosed();
    }

    public synchronized void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("(FP#disconnect) " + e.getMessage() + " : " + e.getCause());
        }
    }
}
