package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by marti on 30/05/2016.
 */

public class CTeamPortalChanged implements Serializable {

    private CPortalEntity mPortal;

    private CPlayerEntity mPlayer;

    public CTeamPortalChanged(){}

    public CTeamPortalChanged(CTeamPortalChangedBuilder pBuilder){

        mPortal = pBuilder.mPortal;
        mPlayer = pBuilder.mPlayer;

    }

    public void setPortal(CPortalEntity pPortal){
        mPortal = pPortal;
    }

    public CPortalEntity getPortal(){
        return mPortal;
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CTeamPortalChanged that = (CTeamPortalChanged) o;

        return !(mPortal != null ? !mPortal.equals(that.mPortal) : that.mPortal != null);

    }

    @Override
    public int hashCode() {
        return mPortal != null ? mPortal.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CTeamPortalChanged{" +
                "mPortal=" + mPortal +
                '}';
    }

    public static class CTeamPortalChangedBuilder{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CTeamPortalChangedBuilder(){}

        public CTeamPortalChangedBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CTeamPortalChangedBuilder player(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CTeamPortalChanged build(){
            return new CTeamPortalChanged(this);
        }
    }
}
