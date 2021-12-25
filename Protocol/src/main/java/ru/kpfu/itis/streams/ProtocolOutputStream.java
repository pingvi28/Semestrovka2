package ru.kpfu.itis.streams;

import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.protocol.Protocol;

import java.io.IOException;
import java.io.OutputStream;

public class ProtocolOutputStream {
    private OutputStream outputStream;

    public ProtocolOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeAction(Message message) throws IOException {
        byte type = message.getType();
        byte[] data = message.getData();
        int length = data.length;
        if (length > Protocol.MAX_ACTION_LENGTH) {
            return;
        }
        outputStream.write(type);
        outputStream.write(length >> 8);
        outputStream.write(length);
        outputStream.write(data);
        outputStream.flush();
    }

    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    public void write(byte[] b) throws IOException {
        outputStream.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    public void flush() throws IOException {
        outputStream.flush();
    }

    public void close() throws IOException {
        outputStream.close();
    }
}
