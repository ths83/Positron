package fr.univtln.groupc;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */



public class CAttackBuilding implements Serializable {
    /*private ABuildingEntity mBuilding;
    private CPlayerEntity mPlayer;
    private CConsumableEntity mConsumable;*/

    private int mBuildingId;
    private int mPlayerId;
    private int mConsumableId;


    public CAttackBuilding(){
    }

    public CAttackBuilding(CAttackBuildingBuilder pBuilder){
        mPlayerId = pBuilder.mPlayerId;
        mBuildingId = pBuilder.mBuildingId;
        mConsumableId = pBuilder.mConsumableId;
    }

    public void setBuildingId(int pBuildingId){
        mBuildingId = pBuildingId;
    }

    public int getBuildingId(){
        return mBuildingId;
    }

    public int getPlayerId(){
        return mPlayerId;
    }

    public void setPlayerId(int pPlayerId){
        mPlayerId = pPlayerId;
    }

    public int getConsumableId(){
        return mConsumableId;
    }

    public void setConsumableId(int pConsumableId){
        mConsumableId = pConsumableId;
    }




    public static class CAttackBuildingBuilder{
        /*private ABuildingEntity mBuilding;
        private CPlayerEntity mPlayer;
        private CConsumableEntity mConsumable;*/
        private int mBuildingId;
        private int mPlayerId;
        private int mConsumableId;

        public CAttackBuildingBuilder(){
        }

        public CAttackBuildingBuilder buildingId(int pBuildingId){
            mBuildingId = pBuildingId;
            return this;
        }


        public CAttackBuildingBuilder playerId(int pPlayerId){
            mPlayerId = pPlayerId;
            return this;
        }

        public CAttackBuildingBuilder consumableId(int pConsumableId){
            mConsumableId = pConsumableId;
            return this;
        }

        public CAttackBuilding build(){
            return new CAttackBuilding(this);
        }
    }
}
