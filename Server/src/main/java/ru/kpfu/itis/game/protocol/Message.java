package ru.kpfu.itis.game.protocol;

import java.io.Serializable;

public abstract class Message<T> implements Serializable {

    public abstract MessageType getType();

    public abstract T getContent();

    public abstract int getSenderId();
}
