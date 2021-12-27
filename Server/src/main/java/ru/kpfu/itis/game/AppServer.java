package ru.kpfu.itis.game;

import ru.kpfu.itis.game.server.ServerImp;

public class AppServer {
    public static ServerImp server;

    public static void main(String[] args){
        server = new ServerImp();
        server.start();
    }
}
