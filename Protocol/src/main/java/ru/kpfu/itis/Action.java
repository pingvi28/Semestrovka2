package ru.kpfu.itis;

public class Action {
    private byte type;
    private byte[] data;

    public Action(byte type, byte[] data) {
        this.type = type;
        this.data = data;
    }

    public Action(byte type) {
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
