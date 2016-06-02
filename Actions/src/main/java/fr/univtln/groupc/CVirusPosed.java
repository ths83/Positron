package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by tperron710 on 02/06/16.
 */
public class CVirusPosed implements Serializable {

    private CPortalEntity mPortal;

    public CVirusPosed() {
    }

    public CVirusPosed(CPortalEntity mPortal) {
        this.mPortal = mPortal;
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity mPortal) {
        this.mPortal = mPortal;
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
}
