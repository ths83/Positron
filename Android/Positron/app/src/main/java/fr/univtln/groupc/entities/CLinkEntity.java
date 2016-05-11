package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpesnel786 on 09/05/16.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CLinkEntity implements Serializable{
    private int mId;
    private CFieldEntity mField;
    private List<CPortalEntity> mPortals  = new ArrayList<>();

    public CLinkEntity() {}

    public CLinkEntity(CLinkBuilder pBuilder) {
        mId = pBuilder.mId;
        mPortals = pBuilder.mPortals;
        for (CPortalEntity cPortalEntity : mPortals) {
            cPortalEntity.addLink(this);
        }
    }

    @Override
    public String toString() {
        return "CLinkEntity{" +
                "mId=" + mId +
                ", mField=" + mField +
                //", mPortals=" + mPortals +
                '}';
    }

    public static class CLinkBuilder{
        private int mId;
        private List<CPortalEntity> mPortals = new ArrayList<>();

        public CLinkBuilder(int pId) {
            mId = pId;
        }

        public CLinkBuilder portals(List<CPortalEntity> pPortals){
            mPortals = pPortals;
            return this;
        }

        public CLinkEntity build(){
            return new CLinkEntity((this));
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
    public List<CPortalEntity> getPortals() {
        return mPortals;
    }

    public void setPortals(List<CPortalEntity> pPortals) {
        mPortals = pPortals;
    }

    public CFieldEntity getField() {
        return mField;
    }

    public void setField(CFieldEntity pField) {
        mField = pField;
    }

    public boolean algoCreateLink(CPortalEntity p1, CPortalEntity p2){
        boolean b=false;
        return(b);
    }

    public List<CPortalEntity> getmPortals() {
        return mPortals;
    }
}
