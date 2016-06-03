package fr.univtln.groupc;

import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by tperron710 on 02/06/16.
 */
public class CPoseVirus implements Serializable {

    private CConsumableEntity mVirus;
    private CPortalEntity mPortal;
    private CPlayerEntity mPlayer;

    public CPoseVirus() {
    }

    public CPoseVirus(CPoseVirusBuilder pPoseVirusBuilder) {
        mPlayer = pPoseVirusBuilder.mPlayer;
    }

    public CConsumableEntity getVirus() {
        return mVirus;
    }

    public void setVirus(CConsumableEntity pVirus) {
        this.mVirus = pVirus;
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

    public void setPlayer(CPlayerEntity pPlayer) {
        this.mPlayer = pPlayer;
    }

    public static class CPoseVirusBuilder implements Serializable{
        private CConsumableEntity mVirus;
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CPoseVirusBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CPoseVirusBuilder player(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CPoseVirusBuilder consumable(CConsumableEntity pVirus){
            mVirus = pVirus;
            return this;
        }

        public CPoseVirus build(){
            return new CPoseVirus(this);
        }
    }
}
