package ru.kpfu.itis.protocol;

public class MessageType {
    public static final byte SEND_ERROR = 0;
    public static final byte ADD_PLAYER = 1;
    public static final byte GAME_STARTED = 2;
    public static final byte OPENED_FIRST_CARD = 3;
    public static final byte OPENED_SECOND_CARD = 4;
    public static final byte SUCCESS = 5;
    public static final byte FAIL = 6;
}
