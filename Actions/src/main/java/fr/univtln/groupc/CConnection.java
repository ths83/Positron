package fr.univtln.groupc;

import java.io.Serializable;

/**
 * Created by marti on 29/05/2016.
 */
public class CConnection implements Serializable {
    private int mPlayerId;

    public CConnection(){}

    public CConnection(int pId){
        mPlayerId = pId;
    }

    public void setPlayerId(int pId){
        mPlayerId = pId;
    }

    public int getPlayerId(){
        return mPlayerId;
    }
}
