package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by marti on 30/05/2016.
 */

public class CResonatorPosed implements Serializable {

    private CPortalEntity mPortal;

    private CPlayerEntity mPlayer;

    public CResonatorPosed(){}

    public CResonatorPosed(CResonatorPosedBuilder pBuild){
        mPortal = pBuild.mPortal;
        mPlayer = pBuild.mPlayer;
    }

    public CPortalEntity getPortal(){
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal){
        mPortal = pPortal;
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

        CResonatorPosed that = (CResonatorPosed) o;

        return !(mPortal != null ? !mPortal.equals(that.mPortal) : that.mPortal != null);

    }

    @Override
    public int hashCode() {
        return mPortal != null ? mPortal.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CResonatorPosed{" +
                "mPortal=" + mPortal +
                '}';
    }

    public static class CResonatorPosedBuilder{
        private CPortalEntity mPortal;
        private CPlayerEntity mPlayer;

        public CResonatorPosedBuilder(){

        }

        public CResonatorPosedBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CResonatorPosedBuilder player(CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CResonatorPosed build(){
            return new CResonatorPosed(this);
        }
    }
}
