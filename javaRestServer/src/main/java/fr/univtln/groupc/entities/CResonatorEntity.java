package fr.univtln.groupc.entities;

import javax.persistence.*;

/**
 * Created by arouani277 on 26/04/16.
 */

@Entity
@Table(name = "t_resonator", schema = "positron")
@NamedQueries(@NamedQuery(name = CResonatorEntity.GET_ALL, query = "select p from CResonatorEntity p"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CResonatorEntity extends ABuildingEntity {
    @ManyToOne
    @JoinColumn(name = "portal_fk")
    private CPortalEntity mPortal;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_fk")
    private CPlayerEntity mOwner;

    public final static String GET_ALL = "Resonator.getAll";

    public CResonatorEntity(CResonatorBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mLong, pBuilder.mLat, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
        mPortal = pBuilder.mPortal;
        mOwner = pBuilder.mOwner;
    }

    public CResonatorEntity() {
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        mPortal = pPortal;
    }

    public CPlayerEntity getOwner() {
        return mOwner;
    }

    public void setOwner(CPlayerEntity pOwner) {
        mOwner = pOwner;
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

        public CResonatorBuilder lifeTime(int pLifeTime){
            mLifeTime = pLifeTime;
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