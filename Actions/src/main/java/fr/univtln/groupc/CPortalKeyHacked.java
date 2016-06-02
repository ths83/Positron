package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 02/06/16.
 */
public class CPortalKeyHacked implements Serializable{

    private CPlayerEntity mPlayer;

    public CPortalKeyHacked() {}

    public CPlayerEntity getPlayer() {
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer) {
        this.mPlayer = pPlayer;
    }

    public CPortalKeyHacked(CPlayerEntity pPlayer) {
        this.mPlayer = pPlayer;
    }
}

