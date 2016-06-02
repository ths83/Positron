package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 02/06/16.
 */
public class CHackPortal implements Serializable {
    private CPortalEntity mPortal;
    private CPlayerEntity mPlayer;


    public CHackPortal (CHackPortalBuilder pBuilder){
        mPlayer = pBuilder.mPlayer;
        mPortal = pBuilder.mPortal;
    }


    public CPortalEntity getmPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        this.mPortal = pPortal;
    }

    public CPlayerEntity getPlayer() {
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer) {
        this.mPlayer = pPlayer;
    }



    public static class CHackPortalBuilder{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CHackPortalBuilder(){}

        public CHackPortalBuilder portal (CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CHackPortalBuilder player (CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }
        public CHackPortal builder (){
            return new CHackPortal(this);
        }
    }

}
