package fr.univtln.groupc.entities;

import java.io.Serializable;



/**
 * Created by arouani277 on 02/05/16.
 */


public class CTurretEntity extends ABuildingEntity implements Serializable {
    private int mDamage;
    public final static String GET_ALL = "Turret.getAll";

    public CTurretEntity(CTurretBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mLong, pBuilder.mLat, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
        mDamage = pBuilder.mDamage;
    }

    public CTurretEntity() {
    }

    public int getDamage() {
        return mDamage;
    }

    public void setDamage(int pDamage) {
        mDamage = pDamage;
    }

    public static class CTurretBuilder{
        private int mEnergy;
        private int mId;
        private String mName;
        private int mLevel;
        private double mLong;
        private double mLat;
        private int mLifeTime;
        private int mRadius;
        private int mEnergyMax;
        private int mDamage;


        public CTurretBuilder(int pId){
            mId = pId;
        }

        public CTurretBuilder damage(int pDamage){
            mDamage = pDamage;
            return this;
        }

        public CTurretBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CTurretBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CTurretBuilder energy(int pEnergy) {
            mEnergy = pEnergy;
            return this;
        }

        public CTurretBuilder energyMax(int pEnergyMax){
            mEnergyMax = pEnergyMax;
            return this;
        }

        public CTurretBuilder longitude(double pLong){
            mLong = pLong;
            return this;
        }

        public CTurretBuilder latitude(double pLat){
            mLat = pLat;
            return this;
        }

        public CTurretBuilder radius(int pRadius){
            mRadius = pRadius;
            return this;
        }

        public CTurretBuilder lifeTime(int plifeTime){
            mLifeTime = plifeTime;
            return this;
        }
        public CTurretEntity build() {
            return new CTurretEntity(this);
        }

    }

    @Override
    public String toString() {
        return super.toString() + "CTurretEntity{" +
                "mDamage=" + mDamage +
                '}';
    }
}
