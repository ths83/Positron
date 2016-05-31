package fr.univtln.groupc;

import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */
public class CAttackAOE implements Serializable {

    private CPortalEntity mPortal;
    private CPlayerEntity mPlayer;
    private CConsumableEntity mConsumable;

    public CAttackAOE(){}

    public CAttackAOE(CAttackAOEBuilder pBuilder){
        mPlayer = pBuilder.mPlayer;
    }

    public CPortalEntity getPortal(){
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal){
        mPortal = pPortal;
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

    public static class CAttackAOEBuilder implements Serializable{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;
        private CConsumableEntity mConsumable;

        CAttackAOEBuilder(){}

        public CAttackAOEBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CAttackAOEBuilder player(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CAttackAOEBuilder consumable(CConsumableEntity pConsumable){
            mConsumable = pConsumable;
            return this;
        }

        public CAttackAOE build(){
            return new CAttackAOE(this);
        }
    }



}
