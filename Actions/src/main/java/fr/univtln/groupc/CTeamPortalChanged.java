package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by marti on 30/05/2016.
 */

public class CTeamPortalChanged implements Serializable {

    private CPortalEntity mPortal;

    public CTeamPortalChanged(){}

    public CTeamPortalChanged(CPortalEntity pPortal){
        mPortal = pPortal;
    }

    public void setPortal(CPortalEntity pPortal){
        mPortal = pPortal;
    }

    public CPortalEntity getPortal(){
        return mPortal;
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
}
