package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by tperron710 on 02/06/16.
 */
public class CVirusPosed implements Serializable {

    private CPortalEntity mPortal;
    private CPlayerEntity mPlayer;

    public CVirusPosed() {
    }

    public CVirusPosed(CVirusPosedBuilder pBuilder) {

        mPortal = pBuilder.mPortal;
        mPlayer = pBuilder.mPlayer;
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity mPortal) {
        this.mPortal = mPortal;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CVirusPosed that = (CVirusPosed) o;

        return !(mPortal != null ? !mPortal.equals(that.mPortal) : that.mPortal != null);

    }

    @Override
    public int hashCode() {
        return mPortal != null ? mPortal.hashCode() : 0;
    }

    public static class CVirusPosedBuilder{
        private CPlayerEntity mPlayer;
        private CPortalEntity mPortal;

        public CVirusPosedBuilder(){}

        public CVirusPosedBuilder player(CPlayerEntity  pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CVirusPosedBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CVirusPosed build(){
            return new CVirusPosed(this);
        }
    }
}
