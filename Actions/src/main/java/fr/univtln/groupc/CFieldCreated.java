package fr.univtln.groupc;

import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;

/**
 * Created by marti on 01/06/2016.
 */
public class CFieldCreated implements Serializable {

    private CLinkEntity mLink;
    private CPlayerEntity mPlayer;

    public CFieldCreated(){}

    public CFieldCreated(CFieldCreatedBuilder pBuilder){
        mLink = pBuilder.mLink;
        mPlayer = pBuilder.mPlayer;
    }

    public CLinkEntity getLink(){
        return mLink;
    }

    public void setLink(CLinkEntity pLink){
        mLink = pLink;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }

    public static class CFieldCreatedBuilder {
        private CLinkEntity mLink;
        private CPlayerEntity mPlayer;

        public CFieldCreatedBuilder(){}

        public CFieldCreatedBuilder link(CLinkEntity pLink){
            mLink = pLink;
            return this;
        }

        public CFieldCreatedBuilder player(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CFieldCreated build(){
            return new CFieldCreated(this);
        }

    }

}
