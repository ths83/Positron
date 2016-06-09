package fr.univtln.groupc;

import fr.univtln.groupc.entities.CResonatorEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 09/06/16.
 */
public class CRepairBuilding implements Serializable{

    int mPlayerId;
    int mBuildingId;
    int mConsomableId;

    public int getPlayerId() {
        return mPlayerId;
    }

    public void setPlayerId(int pPlayerId) {
        this.mPlayerId = pPlayerId;
    }

    public int getBuildingId() {
        return mBuildingId;
    }

    public void setBuildingId(int pBuildingId) {
        this.mBuildingId = pBuildingId;
    }

    public int getConsomableId() {
        return mConsomableId;
    }

    public void setConsomableId(int pConsomableId) {
        this.mConsomableId = pConsomableId;
    }

    public CRepairBuilding(){}

    public CRepairBuilding( CRepairBuildingBuilder pBuilder){
        mPlayerId = pBuilder.mPlayerId;
        mBuildingId = pBuilder.mBuildingId;
        mConsomableId = pBuilder.mConsomableId;
    }

    public static class CRepairBuildingBuilder{
        int mPlayerId;
        int mBuildingId;
        int mConsomableId;

        public CRepairBuildingBuilder () {}

        public CRepairBuildingBuilder playerId ( int pPlayerId ){
            mPlayerId = pPlayerId;
            return this;
        }
        public CRepairBuildingBuilder buildingId ( int pBuildingId){
            mBuildingId = pBuildingId;
            return this;
        }
        public CRepairBuildingBuilder cosomableId ( int pConsomableId){
            mConsomableId = pConsomableId;
            return this;
        }

        public CRepairBuilding build(){return new CRepairBuilding(this);}

    }
}
