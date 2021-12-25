package ru.kpfu.itis.game.common;

public class Properties {
    public static String host = "127.0.0.1";
    public static int port = 2808;

    public static void setHost(String host) {
        Properties.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Properties.port = port;
    }
}
