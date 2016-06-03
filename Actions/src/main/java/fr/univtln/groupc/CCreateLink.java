package fr.univtln.groupc;

import fr.univtln.groupc.entities.CLinkEntity;

import java.io.Serializable;

/**
 * Created by marti on 31/05/2016.
 */
public class CCreateLink implements Serializable {

    private CLinkEntity mLink;
    private int mKeyId;
    private int mPlayerId;

    public CCreateLink(){}

    public CCreateLink(CCreateLinkBuilder pBuilder){
        mLink = pBuilder.mLink;
        mKeyId = pBuilder.mKeyId;
        mPlayerId = pBuilder.mPlayerId;
    }

    public int getKeyId(){
        return mKeyId;
    }

    public void setKeyId(int pKeyId){
        mKeyId = pKeyId;
    }

    public int getPlayerId(){
        return mPlayerId;
    }

    public void setPlayerId(int pPlayerId){
        mPlayerId = pPlayerId;
    }

    public void setLink(CLinkEntity pLink){
        mLink = pLink;
    }

    public CLinkEntity getLink(){
        return mLink;
    }
/*
    @Override
    public String toString() {
        return "CCreateLink{" +
                "mLink=" + mLink +
                '}';
    }*/

    public static class CCreateLinkBuilder{
        private CLinkEntity mLink;
        private int mKeyId;
        private int mPlayerId;

        public CCreateLinkBuilder(){}

        public CCreateLinkBuilder link(CLinkEntity pLink){
            mLink = pLink;
            return this;
        }

        public CCreateLinkBuilder keyId(int pKeyId){
            mKeyId = pKeyId;
            return this;
        }

        public CCreateLinkBuilder playerId(int pPlayerId){
            mPlayerId = pPlayerId;
            return this;
        }

        public CCreateLink build(){
            return new CCreateLink(this);
        }
    }
}
