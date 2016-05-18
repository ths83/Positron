package fr.univtln.groupc.entities;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 18/05/16.
 */
public class CShieldEntity extends ABuildingEntity implements Serializable {


    private int mDefensBonus;
    public final static String GET_ALL = "Shield.getAll";

    public CShieldEntity(CShieldBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mPortal, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
        mDefensBonus = pBuilder.mDefensBonus;
    }

    public CShieldEntity() {
    }


    public void setmDefensBonus(int mDefensBonus) {
        this.mDefensBonus = mDefensBonus;
    }

    public int getmDefensBonus() {
        return mDefensBonus;
    }

    public static class CShieldBuilder{
        private int mEnergy;
        private int mId;
        private String mName;
        private int mLevel;/*
        private double mLong;
        private double mLat;*/
        private CPortalEntity mPortal;
        private int mLifeTime;
        private int mRadius;
        private int mEnergyMax;
        private int mDefensBonus;


        public CShieldBuilder(int pId){
            mId = pId;
        }

        public CShieldBuilder damage(int pDefensBonus){
            mDefensBonus = pDefensBonus;
            return this;
        }

        public CShieldBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CShieldBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CShieldBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CShieldBuilder energy(int pEnergy) {
            mEnergy = pEnergy;
            return this;
        }

        public CShieldBuilder energyMax(int pEnergyMax){
            mEnergyMax = pEnergyMax;
            return this;
        }
        /*
                public CTurretBuilder longitude(double pLong){
                    mLong = pLong;
                    return this;
                }

                public CTurretBuilder latitude(double pLat){
                    mLat = pLat;
                    return this;
                }
        */
        public CShieldBuilder radius(int pRadius){
            mRadius = pRadius;
            return this;
        }

        public CShieldBuilder lifeTime(int plifeTime){
            mLifeTime = plifeTime;
            return this;
        }
        public CShieldEntity build() {
            return new CShieldEntity(this);
        }

    }

    @Override
    public String toString() {
        return "CShieldEntity{" +
                "mDefensBonus=" +  +
                '}'+super.toString();
    }


}

