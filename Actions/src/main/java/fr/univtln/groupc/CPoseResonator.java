package fr.univtln.groupc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

import java.io.Serializable;

/**
 * Created by marti on 26/05/2016.
 */


public class CPoseResonator implements Serializable {

    private CPortalEntity mPortal;
    private CResonatorEntity mResonator;
    private int mPlayerId;

    public CPoseResonator(){
    }

    public CPoseResonator(CPoseResonatorBuilder pBuilder){
        mPortal = pBuilder.mPortal;
        mResonator = pBuilder.mResonator;
        mPlayerId = pBuilder.mPlayerId;
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

    public int getPlayerId(){
        return mPlayerId;
    }

    public void setPlayerId(int pId){
        mPlayerId = pId;
    }

    @Override
    public String toString() {
        return "CPoseResonator{" +
                "mPortal=" + mPortal +
                ", mResonator=" + mResonator +
                ", mPlayerId=" + mPlayerId +
                '}';
    }

    public static class CPoseResonatorBuilder{
        private CPortalEntity mPortal;
        private CResonatorEntity mResonator;
        private int mPlayerId;

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

        public CPoseResonatorBuilder playerId(int pPlayerId){
            mPlayerId = pPlayerId;
            return this;
        }

        public CPoseResonator build(){
            return new CPoseResonator(this);
        }
    }
}