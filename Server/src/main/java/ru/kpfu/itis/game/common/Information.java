package ru.kpfu.itis.game.common;

import java.io.Serializable;

public class Information implements Serializable {
    private static int clientId = 0;

    public Information(int id){
        this.clientId = id;
    }

    public static int getClientId(){
        return clientId;
    }
    public void setClientId(int id){
        this.clientId = clientId;
    }
}
