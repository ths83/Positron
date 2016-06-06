package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 02/06/16.
 */
public class CHackPortal implements Serializable {
    private int mPortalId;
    private int mPlayerId;


    public CHackPortal (CHackPortalBuilder pBuilder){
        mPlayerId = pBuilder.mPlayer;
        mPortalId = pBuilder.mPortal;
    }


    public int getmPortalId() {
        return mPortalId;
    }

    public void setPortalId(int pPortal) {
        this.mPortalId = pPortal;
    }

    public int getPlayerId() {
        return mPlayerId;
    }

    public void setPlayerId(int pPlayer) {
        this.mPlayerId = pPlayer;
    }



    public static class CHackPortalBuilder{
        private int mPortal;
        private int mPlayer;

        public CHackPortalBuilder(){}

        public CHackPortalBuilder portal (int pPortal){
            mPortal = pPortal;
            return this;
        }

        public CHackPortalBuilder player (int pPlayer){
            mPlayer = pPlayer;
            return this;
        }
        public CHackPortal builder (){
            return new CHackPortal(this);
        }
    }

}
