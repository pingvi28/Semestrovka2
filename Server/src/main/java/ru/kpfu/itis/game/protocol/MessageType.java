package ru.kpfu.itis.game.protocol;

public enum MessageType {
    SYSTEM_MESSAGE,
    START_GAME_MESSAGE,
    GAME_STARTED,
    GAME_ACTION,
    DISCONNECT,
    FIRST_PLAYER_WINS,
    SECOND_PLAYER_WINS,
    GAME_OVER;
}
