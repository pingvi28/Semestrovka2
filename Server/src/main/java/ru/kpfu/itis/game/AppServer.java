package ru.kpfu.itis.game;

import ru.kpfu.itis.game.server.ServerImp;

public class AppServer {
    public static void main(String[] args){
        ServerImp server = new ServerImp();
        server.start();
    }
}
