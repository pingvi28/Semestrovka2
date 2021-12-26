package ru.kpfu.itis.game.server;

import ru.kpfu.itis.game.common.PlayerConnection;

public interface ServerEventListener {

    void onConnectionReady(PlayerConnection connection);

    void onDisconnect(PlayerConnection connection);
}
