package ru.kpfu.itis.protocol;

public class Properties {
    public final static String HOST = "127.0.0.1";
    public static int port = 6661;
    public static int sizeBoard = 0;

    public static int getSizeBoard() {
        return sizeBoard;
    }

    public static void setSizeBoard(int size) {
        System.out.println(size);
        if(sizeBoard == 0){
            if (size > 0 && size < 8){
                Properties.sizeBoard = size;
            }else System.out.println("Некорректное значение");
        }else System.out.println("Другой игрок уже ввел значение");
    }

    public static void setPort(int port) {
        Properties.port = port;
    }
}
