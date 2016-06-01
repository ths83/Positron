package fr.univtln.groupc;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */



public class CAttackBuilding implements Serializable {
    private ABuildingEntity mBuilding;
    private CPlayerEntity mPlayer;
    private CConsumableEntity mConsumable;

    public CAttackBuilding(){
    }

    public CAttackBuilding(CAttackBuildingBuilder pBuilder){

        mPlayer = pBuilder.mPlayer;
        mBuilding = pBuilder.mBuilding;
        mConsumable = pBuilder.mConsumable;
    }

    public void setBuilding(ABuildingEntity pBuilding){
        mBuilding = pBuilding;
    }

    public ABuildingEntity getBuilding(){
        return mBuilding;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }

    public CConsumableEntity getConsumable(){
        return mConsumable;
    }

    public void setConsumable(CConsumableEntity pConsumable){
        mConsumable = pConsumable;
    }




    public static class CAttackBuildingBuilder{
        private ABuildingEntity mBuilding;
        private CPlayerEntity mPlayer;
        private CConsumableEntity mConsumable;

        public CAttackBuildingBuilder(){
        }

        public CAttackBuildingBuilder building(ABuildingEntity pBuilding){
            mBuilding = pBuilding;
            return this;
        }


        public CAttackBuildingBuilder playerId(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CAttackBuildingBuilder consumable(CConsumableEntity pConsumable){
            mConsumable = pConsumable;
            return this;
        }

        public CAttackBuilding build(){
            return new CAttackBuilding(this);
        }
    }
}
