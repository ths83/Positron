package fr.univtln.groupc.entities;

/**
 * Created by marti on 09/05/2016.
 */

public class CKeyEntity extends AObjectEntity {
    private CPortalEntity mPortal;

    public final static String GET_ALL = "Key.getAll";
    public final static String GET_KEY_BY_PORTAL = "Key.getbyportal";

    public CKeyEntity(){}

    public CKeyEntity(CKeyBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName);
        mPortal = pBuilder.mPortal;
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        mPortal = pPortal;
    }

    public static class CKeyBuilder{
        int mId;
        String mName;
        CPortalEntity mPortal;

        public CKeyBuilder(int pId){
            mId = pId;
        }

        public CKeyBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CKeyBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CKeyEntity build(){
            return new CKeyEntity(this);
        }
    }

}
