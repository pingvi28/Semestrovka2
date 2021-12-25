package ru.kpfu.itis.game.common;

import java.io.Serializable;

public class Information implements Serializable {
    private int clientId;

    public Information(int id){
        this.clientId = id;
    }

    public int getClientId(){
        return clientId;
    }
    public void setClientId(int Id){
        this.clientId = clientId;
    }
}
