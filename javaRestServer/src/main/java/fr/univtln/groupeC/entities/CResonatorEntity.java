package fr.univtln.groupeC.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by arouani277 on 26/04/16.
 */

@Entity
@Table(name = "t_resonator", schema = "project")
public class CResonatorEntity extends AObjectEntity {
    private CPortalEntity mPortal;
    private CPlayerEntity mOwner;

    public CResonatorEntity(CResonatorBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mLevel);
        mPortal = pBuilder.mPortal;
        mOwner = pBuilder.mOwner;
    }

    @Override
    public String toString() {
        return super.toString() + " CResonatorEntity{" +
                "mPortal=" + mPortal +
                ", mOwner=" + mOwner +
                '}';
    }

    public static class CResonatorBuilder{
        private int mEnergy;
        private int mId;
        private String mName;
        private int mLevel;

        public CResonatorBuilder(int pId){
            mId = pId;
        }

        public CResonatorBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CResonatorBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CResonatorBuilder energy(int pEnergy) {
            mEnergy = pEnergy;
            return this;
        }

        public CResonatorEntity build() {
            return new CResonatorEntity(this);
        }

    }

    public int getEnergy() {
        return mEnergy;
    }

    public void setEnergy(int pEnergy) {
        mEnergy = pEnergy;
    }


}