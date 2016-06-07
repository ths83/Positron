package fr.univtln.groupc;

import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */
public class CAttackAOE implements Serializable {

    private int mPortalId;
    private int mPlayerId;
    private int mConsumableId;

    public CAttackAOE(){}

    public CAttackAOE(CAttackAOEBuilder pBuilder){
        mPlayerId = pBuilder.mPlayer;
    }

    public int getPortalId(){
        return mPortalId;
    }

    public void setPortalId(int pPortal){
        mPortalId = pPortal;
    }

    public int getPlayerId(){
        return mPlayerId;
    }

    public void setPlayerId(int pPlayer){
        mPlayerId = pPlayer;
    }

    public int getConsumableId(){
        return mConsumableId;
    }

    public void setConsumableId(int pConsumable){
        mConsumableId = pConsumable;
    }

    public static class CAttackAOEBuilder implements Serializable{
        private int mPortal;
        private int mPlayer;
        private int mConsumable;

        CAttackAOEBuilder(){}

        public CAttackAOEBuilder portal(int pPortal){
            mPortal = pPortal;
            return this;
        }

        public CAttackAOEBuilder player(int pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CAttackAOEBuilder consumable(int pConsumable){
            mConsumable = pConsumable;
            return this;
        }

        public CAttackAOE build(){
            return new CAttackAOE(this);
        }
    }



}
