package fr.univtln.groupeC.entities;

import javax.persistence.*;

/**
 * Created by arouani277 on 26/04/16.
 */

@Entity
@Table(name = "t_resonator", schema = "project")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CResonatorEntity extends ABuildingEntity {
    @ManyToOne
    @Column(name = "portal_fk")
    private CPortalEntity mPortal;
    @Column(name = "owner_fk")
    private CPlayerEntity mOwner;

    public CResonatorEntity(CResonatorBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mLong, pBuilder.mLat, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
        mPortal = pBuilder.mPortal;
        mOwner = pBuilder.mOwner;
    }

    public CResonatorEntity() {
    }

    @Override
    public String toString() {
        return super.toString() + " CResonatorEntity{" +
                "mPortal=" + mPortal +
                ", mOwner=" + mOwner +
                '}' + super.toString();
    }

    public static class CResonatorBuilder{
        private int mEnergy;
        private int mId;
        private String mName;
        private int mLevel;
        private double mLong;
        private double mLat;
        private int mLifeTime;
        private int mRadius;
        private int mEnergyMax;

        private CPortalEntity mPortal;
        private CPlayerEntity mOwner;

        public CResonatorBuilder id(int pId){
            mId = pId;
            return this;
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

        public CResonatorBuilder energyMax(int pEnergyMax){
            mEnergyMax = pEnergyMax;
            return this;
        }

        public CResonatorBuilder longitude(double pLong){
            mLong = pLong;
            return this;
        }

        public CResonatorBuilder latitude(double pLat){
            mLat = pLat;
            return this;
        }

        public CResonatorBuilder radius(int pRadius){
            mRadius = pRadius;
            return this;
        }

        public CResonatorBuilder owner(CPlayerEntity pOwner){
            mOwner = pOwner;
            return this;
        }

        public CResonatorBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CResonatorEntity build() {
            return new CResonatorEntity(this);
        }

    }
}