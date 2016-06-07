package fr.univtln.groupc;

import fr.univtln.groupc.entities.CLinkEntity;

import java.io.Serializable;

/**
 * Created by marti on 31/05/2016.
 */
public class CCreateLink implements Serializable {

    //private CLinkEntity mLink;
    private int mKeyId;
    private int mPlayerId;
    private int mPortalId;

    public CCreateLink(){}

    public CCreateLink(CCreateLinkBuilder pBuilder){
        //mLink = pBuilder.mLink;
        mKeyId = pBuilder.mKeyId;
        mPlayerId = pBuilder.mPlayerId;
        mPortalId = pBuilder.mPortalId;
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
/*
    public void setLink(CLinkEntity pLink){
        mLink = pLink;
    }

    public CLinkEntity getLink(){
        return mLink;
    }
*/

    public int getPortalId(){
        return mPortalId;
    }

    public void setPortalId(int pPortalId){
        mPortalId = pPortalId;
    }


/*
    @Override
    public String toString() {
        return "CCreateLink{" +
                "mLink=" + mLink +
                '}';
    }*/

    public static class CCreateLinkBuilder{
        //private CLinkEntity mLink;
        private int mKeyId;
        private int mPlayerId;
        private int mPortalId;

        public CCreateLinkBuilder(){}

        /*
        public CCreateLinkBuilder link(CLinkEntity pLink){
            mLink = pLink;
            return this;
        }*/

        public CCreateLinkBuilder portalId(int pPortalId){
            mPortalId = pPortalId;
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
