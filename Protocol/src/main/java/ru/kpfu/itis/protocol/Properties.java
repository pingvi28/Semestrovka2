package ru.kpfu.itis.protocol;

public class Properties {
    public final static String HOST = "127.0.0.1";
    public static int port = 6666;

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Properties.port = port;
    }
}
