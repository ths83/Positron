package fr.univtln.groupc;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 03/06/16.
 */
public class CPoseBuilding implements Serializable{

    private int mPortalId;
    private int mBuildingId;
    private int mPlayerId;

    public CPoseBuilding(CPoseBuildingBuilder pBuilder){
        mPortalId =  pBuilder.mPortal;
        mBuildingId = pBuilder.mBuilding;
        mPlayerId = pBuilder.mPlayer;
    }

    public CPoseBuilding (){}

    public int getPortalId() {
        return mPortalId;
    }

    public void setPortalId(int pPortal) {
        this.mPortalId = pPortal;
    }

    public int getBuildingId() { return mBuildingId; }

    public void setBuildingId(int pBuilding) {
        this.mBuildingId = pBuilding;
    }

    public int getPlayerId() {
        return mPlayerId;
    }

    public void setPlayerId(int pPlayer) {
        this.mPlayerId = pPlayer;
    }

    public  static class CPoseBuildingBuilder{

        private int mPortal;
        private int mBuilding;
        private int mPlayer;

        public CPoseBuildingBuilder(){}

        public CPoseBuildingBuilder portal (int pPortal){
            mPortal = pPortal;
            return this;
        }

        public  CPoseBuildingBuilder building (int pBuilding){
            mBuilding = pBuilding;
            return this;
        }

        public CPoseBuildingBuilder player (int pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CPoseBuilding build (){
            return new CPoseBuilding(this);
        }

    }

}
