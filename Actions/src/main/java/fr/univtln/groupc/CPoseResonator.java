package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */


public class CPoseResonator implements Serializable {

    private CPortalEntity mPortal;
    private CResonatorEntity mResonator;
    private CPlayerEntity mPlayer;

    public CPoseResonator(){}

    public CPoseResonator(CPoseResonatorBuilder pBuilder){
        mPortal = pBuilder.mPortal;
        mResonator = pBuilder.mResonator;
        mPlayer = pBuilder.mPlayer;
    }

    public void setPortal(CPortalEntity pPortal){
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
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }

    @Override
    public String toString() {
        return "CPoseResonator{" +
                "mPortal=" + mPortal +
                ", mResonator=" + mResonator +
                ", mPlayer=" + mPlayer +
                '}';
    }

    public static class CPoseResonatorBuilder{
        private CPortalEntity mPortal;
        private CResonatorEntity mResonator;
        private CPlayerEntity mPlayer;

        public CPoseResonatorBuilder(){
        }

        public CPoseResonatorBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CPoseResonatorBuilder resonator(CResonatorEntity pResonator){
            mResonator = pResonator;
            return this;
        }

        public CPoseResonatorBuilder playerId(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CPoseResonator build(){
            return new CPoseResonator(this);
        }
    }
}