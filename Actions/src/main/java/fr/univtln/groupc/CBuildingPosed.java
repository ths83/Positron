package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 03/06/16.
 */
public class CBuildingPosed implements Serializable{

    private CPortalEntity mPortal;
    private CPlayerEntity mPlayer;

    CBuildingPosed(){}

    CBuildingPosed(CBuildingPosedBuilder pBuilder){
        mPortal = pBuilder.mPortal;
        mPlayer = pBuilder.mPlayer;
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        this.mPortal = pPortal;
    }

    public CPlayerEntity getPlayer() {
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity mPlayer) {
        this.mPlayer = mPlayer;
    }

    public static class CBuildingPosedBuilder{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CBuildingPosedBuilder(){}

        public CBuildingPosedBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CBuildingPosedBuilder player (CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CBuildingPosed build (){
            return new CBuildingPosed(this);
        }

    }
}
