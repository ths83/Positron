package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 02/06/16.
 */
public class CPortalHacked implements Serializable{
    private CPlayerEntity mPlayer;


    public CPortalHacked() {}

    public CPlayerEntity getPlayer() {
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer) {
        mPlayer = pPlayer;
    }

    public CPortalHacked(CPlayerEntity pPlayer) {
        mPlayer = pPlayer;
    }
}
