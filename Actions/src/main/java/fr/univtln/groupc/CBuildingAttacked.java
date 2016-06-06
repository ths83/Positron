package fr.univtln.groupc;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 02/06/16.
 */

public class CBuildingAttacked implements Serializable {

    private CPlayerEntity mPlayer;
    private CPortalEntity mPortal;

    public CBuildingAttacked() {}

    public CPlayerEntity getPlayer() {
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer) {
        mPlayer = pPlayer;
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        mPortal = pPortal;
    }

    public CBuildingAttacked(CBuildingAttackedBuilder pBuildingAttacked){
        mPortal = pBuildingAttacked.mPortal;
        mPlayer = pBuildingAttacked.mPlayer;
    }

    public static class CBuildingAttackedBuilder{
        CPlayerEntity mPlayer;
        CPortalEntity mPortal;

        public CBuildingAttackedBuilder(){}

        public CBuildingAttackedBuilder player (CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CBuildingAttackedBuilder portal (CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }
        public CBuildingAttacked build(){
            return new CBuildingAttacked(this);
        }

    }
}
