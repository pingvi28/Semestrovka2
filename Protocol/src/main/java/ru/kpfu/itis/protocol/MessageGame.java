package ru.kpfu.itis.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MessageGame implements Message{
    private byte type;
    private byte[] data;
    protected MessageType messageType;

    public MessageGame(byte type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public MessageGame(byte type) {
        this.type = type;
    }

    public MessageGame(MessageType userAction) {
        this.messageType = userAction;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getBytes(MessageGame message){
        int rawMessageLength = 4; // 4 байта на тип сообщения, 4 байта на boolean(храним ли userId), 4 байта userId

        byte[] rawMessage = new byte[rawMessageLength];

        int j = 0;
        byte[] type = getTypeBytes(message.getMessageType());
        for(int i = 0;i < type.length;i++){
            rawMessage[j++] = type[i];
        }

        return rawMessage;
    }

    public byte[] getTypeBytes(MessageType type){
        return ByteBuffer.allocate(4).put(type.getByte()).array();
    }

    public MessageGame createMessage(MessageType userAction){
        return new MessageGame(userAction);
    }

    public MessageGame readMessage(InputStream inputStream) throws IOException {
        byte[] type = new byte[4];

        inputStream.read(type, 0, 4);
        byte typeByte = ByteBuffer.wrap(type, 0, 4).get();

        return new MessageGame(MessageType.getMessageType(typeByte));
    }

    public MessageType getMessageType(){
        return messageType;
    }
}
