package ru.kpfu.itis.streams;

import ru.kpfu.itis.protocol.MessageGame;

import java.io.IOException;
import java.io.InputStream;

import static ru.kpfu.itis.protocol.Protocol.MAX_ACTION_LENGTH;
import static ru.kpfu.itis.protocol.MessageType.SEND_ERROR;

public class ProtocolInputStream {
    private InputStream inputStream;

    public ProtocolInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public MessageGame readAction() throws IOException {
        int type = -1;
        int length = 0;
        if ((type = inputStream.read()) == -1) {
            return null;
        }
        length = (inputStream.read() << 8) + inputStream.read();
        if (length > MAX_ACTION_LENGTH) {
            MessageGame incorrectMessage = new MessageGame(SEND_ERROR.getByte(), new byte[0]);
            inputStream.skip(length);
            return incorrectMessage;
        }
        byte[] buffer = new byte[length];
        inputStream.read(buffer);
        MessageGame message = new MessageGame((byte) type, buffer);
        return message;
    }

    public int read() throws IOException {
        return inputStream.read();
    }

    public int read(byte[] b) throws IOException {
        return inputStream.read(b);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    public long skip(long n) throws IOException {
        return inputStream.skip(n);
    }

    public int available() throws IOException {
        return inputStream.available();
    }

    public void close() throws IOException {
        inputStream.close();
    }

    public void mark(int readlimit) {
        inputStream.mark(readlimit);
    }

    public void reset() throws IOException {
        inputStream.reset();
    }

    public boolean markSupported() {
        return inputStream.markSupported();
    }
}
