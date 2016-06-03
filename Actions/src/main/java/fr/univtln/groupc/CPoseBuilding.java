package fr.univtln.groupc;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 03/06/16.
 */
public class CPoseBuilding implements Serializable{

    private CPortalEntity mPortal;
    private ABuildingEntity mBuilding;
    private CPlayerEntity mPlayer;

    public CPoseBuilding(CPoseBuildingBuilder pBuilder){
        mPortal =  pBuilder.mPortal;
        mBuilding = pBuilder.mBuilding;
        mPlayer = pBuilder.mPlayer;
    }

    public CPoseBuilding (){}

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        this.mPortal = pPortal;
    }

    public ABuildingEntity getBuilding() {
        return mBuilding;
    }

    public void setBuilding(ABuildingEntity pBuilding) {
        this.mBuilding = pBuilding;
    }

    public CPlayerEntity getmPlayer() {
        return mPlayer;
    }

    public void setmPlayer(CPlayerEntity mPlayer) {
        this.mPlayer = mPlayer;
    }

    public  static class CPoseBuildingBuilder{

        private CPortalEntity mPortal;
        private ABuildingEntity mBuilding;
        private CPlayerEntity mPlayer;

        public CPoseBuildingBuilder(){}

        public CPoseBuildingBuilder portal (CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public  CPoseBuildingBuilder building (ABuildingEntity pBuilding){
            mBuilding = pBuilding;
            return this;
        }

        public CPoseBuildingBuilder player (CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CPoseBuilding build (){
            return new CPoseBuilding(this);
        }

    }

}
