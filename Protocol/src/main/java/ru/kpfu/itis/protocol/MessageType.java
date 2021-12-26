package ru.kpfu.itis.protocol;

import java.util.HashMap;
import java.util.Map;

public enum  MessageType {
    SEND_ERROR(0),
    ADD_PLAYER( 1),
    GAME_STARTED (2),
    OPENED_FIRST_CARD (3),
    OPENED_SECOND_CARD(4),
    SUCCESS(5),
    FAIL(6);

    private int b;
    private static final Map<Byte, MessageType> TYPE_MAP;

    MessageType(int b) { this.b = b; }

    public byte getByte() {
        return (byte) b;
    }

    static {
        TYPE_MAP = new HashMap<>(MessageType.values().length);
        for (MessageType mt : MessageType.values()) {
            TYPE_MAP.put(mt.getByte(), mt);
        }
    }

    public static MessageType getMessageType(byte value) {
        return TYPE_MAP.get(value);
    }
}
