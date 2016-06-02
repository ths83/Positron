package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 02/06/16.
 */
public class CHackPortalKey implements Serializable{
    public CPortalEntity mPortal;
    public CPlayerEntity mPlayer;


    public CHackPortalKey(CHackPortalKeyBuilder pBuilder){
        mPlayer = pBuilder.mPlayer;
        mPortal = pBuilder.mPortal;
    }

    public CPortalEntity getmPortal() {
        return mPortal;
    }

    public void setmPortal(CPortalEntity mPortal) {
        this.mPortal = mPortal;
    }

    public CPlayerEntity getmPlayer() {
        return mPlayer;
    }

    public void setmPlayer(CPlayerEntity mPlayer) {
        this.mPlayer = mPlayer;
    }

    public static class CHackPortalKeyBuilder{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CHackPortalKeyBuilder(){}

        public CHackPortalKeyBuilder portal (CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }
        public CHackPortalKeyBuilder player (CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CHackPortalKey builder (){
            return  new CHackPortalKey(this);
        }
    }
}
