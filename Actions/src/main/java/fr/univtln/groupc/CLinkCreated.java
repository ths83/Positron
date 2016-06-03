package fr.univtln.groupc;


import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;


/**
 * Created by marti on 01/06/2016.
 */
public class CLinkCreated implements Serializable{

    private CLinkEntity mLink;
    private CPlayerEntity mPlayer;

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

    public static class CLinkCreatedBuilder{
        private CLinkEntity mLink;
        private CPlayerEntity mPlayer;

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
