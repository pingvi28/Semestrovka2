package ru.kpfu.itis;

import java.io.IOException;
import java.io.InputStream;

import static ru.kpfu.itis.Protocol.MAX_ACTION_LENGTH;
import static ru.kpfu.itis.Protocol.SEND_ERROR;

public class ProtocolInputStream {
    private InputStream inputStream;

    public ProtocolInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Action readAction() throws IOException {
        int type = -1;
        int length = 0;
        if ((type = inputStream.read()) == -1) {
            return null;
        }
        length = (inputStream.read() << 8) + inputStream.read();
        if (length > MAX_ACTION_LENGTH) {
            Action incorrectAction = new Action(SEND_ERROR, new byte[0]);
            inputStream.skip(length);
            return incorrectAction;
        }
        byte[] buffer = new byte[length];
        inputStream.read(buffer);
        Action action = new Action((byte) type, buffer);
        return action;
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
