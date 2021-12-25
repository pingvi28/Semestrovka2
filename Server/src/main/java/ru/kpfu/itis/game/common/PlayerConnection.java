package ru.kpfu.itis.game.common;

import ru.kpfu.itis.game.protocol.Message;

import java.io.*;
import java.net.Socket;

public class PlayerConnection implements Connection{
    private final Socket socket;
    private final InputStream in;
    private final OutputStream out;
    private Information userInformation;

    public PlayerConnection(Socket socket, int id){
        this.socket = socket;
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            receiveUserInformation();
            userInformation.setClientId(id);
            sendId(id);
        }
        catch (IOException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    private void receiveUserInformation() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(in);
            userInformation = (Information)inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new IllegalStateException("Server connection : receiveInformation throw exception"+e.getMessage());
        }
    }

    private void sendId(int id) throws IOException {
        DataOutputStream dataOutputStream= new DataOutputStream(out);
        dataOutputStream.writeInt(id);
    }

    @Override
    public Information getInformation() {
        return userInformation;
    }

    @Override
    public void send(Message message) throws IOException {

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
}
