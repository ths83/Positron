package fr.univtln.groupc;


import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;


/**
 * Created by marti on 01/06/2016.
 */
public class CLinkCreated implements Serializable{

    private CLinkEntity mLink;
    private CPlayerEntity mPlayer;
    private CPortalEntity mPortal1;
    private CPortalEntity mPortal2;

    public CLinkCreated(){}

    public CLinkCreated(CLinkCreatedBuilder pBuilder){
        mLink = pBuilder.mLink;
        mPlayer = pBuilder.mPlayer;
    }

    public void setLink(CLinkEntity pLink){
        mLink = pLink;
    }

    public CLinkEntity getLink(){
        return mLink;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }

    public CPortalEntity getPortal1(){
        return mPortal1;
    }

    public void setPortal1(CPortalEntity pPortal1){
        mPortal1 = pPortal1;
    }

    public CPortalEntity getPortal2(){
        return mPortal2;
    }

    public void setPortal2(CPortalEntity pPortal2){
        mPortal2 = pPortal2;
    }

    public static class CLinkCreatedBuilder{
        private CLinkEntity mLink;
        private CPlayerEntity mPlayer;
        private CPortalEntity mPortal1;
        private CPortalEntity mPortal2;

        public CLinkCreatedBuilder(){}

        public CLinkCreatedBuilder link(CLinkEntity pLink){
            mLink = pLink;
            return this;
        }

        public CLinkCreatedBuilder player(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CLinkCreated build(){
            return new CLinkCreated(this);
        }
    }
}
