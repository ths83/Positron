package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by arouani277 on 02/05/16.
 */


@Entity
@Table(name = "t_turret", schema = "positron")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries(@NamedQuery(name = CTurretEntity.GET_ALL, query = "select p from CTurretEntity p"))
//@JsonDeserialize(as = CTurretEntity.class)
public class CTurretEntity extends ABuildingEntity implements Serializable, IFighter {
    @Column(name = "damage")
    private int mDamage;
    public final static String GET_ALL = "Turret.getAll";

    public CTurretEntity(CTurretBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mPortal, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
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
        private int mLevel;/*
        private double mLong;
        private double mLat;*/
        private CPortalEntity mPortal;
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

        public CTurretBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
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
        return "CTurretEntity{" +
                "mDamage=" + mDamage +
                '}'+super.toString();
    }

    public void attack(ITarget pTarget, int pDamage) {
        if(getFighterTeam().getId() != pTarget.getTargetTeam().getId() && getPortal().getTeam().getId() != 0) {
            pTarget.takeDamage(this,pDamage);
        }
        else  {
            System.out.println("Cible de la même équipe");
        }
    }

    @JsonIgnore
    public CTeamEntity getFighterTeam() {
        return getPortal().getTeam();
    }
}
