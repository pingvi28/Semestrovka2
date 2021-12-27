package ru.kpfu.itis.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

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

    public static MessageGame readMessage(InputStream in) throws IllegalArgumentException {
        byte[] buffer = new byte[Protocol.MAX_ACTION_LENGTH];
        try {
            in.read(buffer, 0, Protocol.START_BYTES.length);//Block Thread here
            if (!Arrays.equals(
                    Arrays.copyOfRange(buffer, 0, Protocol.START_BYTES.length),
                    Protocol.START_BYTES)) {
                throw new IllegalArgumentException(
                        "Message first bytes must be " + Arrays.toString(Protocol.START_BYTES)
                );
            }
            // Offset is 0 because this thread is not markable
            in.read(buffer, 0, 4);//Block Thread here
            int messageType = ByteBuffer.wrap(buffer, 0, 4).getInt();
//            if (messageType != TYPE1 && messageType != TYPE2) {
//                throw new IllegalArgumentException("Wrong message type: " + messageType + ".");
//            }
            in.read(buffer, 0, 4);//Block Thread here
            int messageLength = ByteBuffer.wrap(buffer, 0, 4).getInt();
            if (messageLength > Protocol.MAX_ACTION_LENGTH) {
                throw new IllegalArgumentException(
                        "Message can't be " + messageLength
                                + " bytes length. Maximum is " + Protocol.MAX_ACTION_LENGTH + "."
                );
            }
            in.read(buffer, 0, messageLength);//Can end before messageLength
            return new MessageGame((byte) messageType, Arrays.copyOfRange(buffer, 0, messageLength));
        } catch (IOException ex) {
            throw new IllegalArgumentException("Can't read message", ex);
        }
    }

    public MessageType getMessageType(){
        return messageType;
    }
}
