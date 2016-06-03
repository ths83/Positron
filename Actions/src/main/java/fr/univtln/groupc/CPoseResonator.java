package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */


public class CPoseResonator implements Serializable {

    /*private CPortalEntity mPortal;
    private CResonatorEntity mResonator;
    private CPlayerEntity mPlayer;*/
    private int mResonatorId;
    private int mPortalId;

    public CPoseResonator(){}

    public CPoseResonator(CPoseResonatorBuilder pBuilder){
        /*mPortal = pBuilder.mPortal;
        mResonator = pBuilder.mResonator;
        mPlayer = pBuilder.mPlayer;*/
        mResonatorId = pBuilder.mResonatorId;
        mPortalId = pBuilder.mPortalId;
    }

    /*public void setPortal(CPortalEntity pPortal){
        mPortal = pPortal;
    }

    public void setResonator(CResonatorEntity pResonator){
        mResonator = pResonator;
    }

    public CPortalEntity getPortal(){
        return mPortal;
    }

    public CResonatorEntity getResonator(){
        return mResonator;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }*/

    /*public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }*/

    public int getResonatorId() {
        return mResonatorId;
    }

    public int getPortalId(){
        return  mPortalId;
    }

    public void setResonatorId(int pResonatorId) {
        mResonatorId = pResonatorId;
    }

    public void setPortalId(int pPortalId){
        mPortalId = pPortalId;
    }


    @Override
    public String toString() {
        return "CPoseResonator{" +
                /*"mPortal=" + mPortal +*/
                ", mResonatorId=" + mResonatorId +
                /*", mPlayer=" + mPlayer +*/
                '}';
    }

    public static class CPoseResonatorBuilder{
        /*private CPortalEntity mPortal;
        private CResonatorEntity mResonator;
        private CPlayerEntity mPlayer;*/
        private int mResonatorId;
        private int mPortalId;

        public CPoseResonatorBuilder(){
        }

        public CPoseResonatorBuilder portalId(int pPortalId){
            mPortalId = pPortalId;
            return this;
        }

        public CPoseResonatorBuilder resonatorId(int pResonatorId){
            mResonatorId = pResonatorId;
            return this;
        }

        /*public CPoseResonatorBuilder playerId(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }*/

        public CPoseResonator build(){
            return new CPoseResonator(this);
        }
    }
}