package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

/**
 * Created by xdurbec066 on 03/06/16.
 */
public class CAOEAttacked {

    private CPortalEntity mPortal;
    private CPlayerEntity mPlayer;

    public CAOEAttacked(){}


    public CPortalEntity getPortal() {
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

    public CAOEAttacked(CAOEAttackedBuilder pBuilder){
        mPlayer = pBuilder.mPlayer;
        mPortal = pBuilder.mPortal;
    }

    public static class CAOEAttackedBuilder{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CAOEAttackedBuilder(){}

        public CAOEAttackedBuilder portal (CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CAOEAttackedBuilder player (CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }
        public CAOEAttacked build (){ return new CAOEAttacked(this);}

    }
}
