package ru.kpfu.itis.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public interface Message {
    byte type = 0;
    byte[] data = new byte[0];

    byte getType();
    void setType(byte type);

    byte[] getData();
    void setData(byte[] data);

    byte[] getTypeBytes(MessageType type);

    MessageGame createMessage(MessageType messageType);

    //static MessageGame readMessage(InputStream inputStream) throws IOException ;

    MessageType getMessageType();
}
