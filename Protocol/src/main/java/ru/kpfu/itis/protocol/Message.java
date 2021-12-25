package ru.kpfu.itis.protocol;

public class Message {
    private byte type;
    private byte[] data;

    public Message(byte type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public Message(byte type) {
        this.type = type;
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
}
