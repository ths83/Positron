package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xdurbec066 on 01/06/16.
 */


    @Entity
    @Table(name = "t_multiHack", schema = "positron")
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    @NamedQueries(@NamedQuery(name = CMultiHackEntity.GET_ALL, query = "select p from CMultiHackEntity  p"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CMultiHackEntity.class)

//@JsonDeserialize(as = CTurretEntity.class)
    public class CMultiHackEntity extends ABuildingEntity implements Serializable {

        @Column(name = "hackBonus")
        private int mHackBonus;
        public final static String GET_ALL = "MultiHack.getAll";

        public CMultiHackEntity(CMultiHackBuilder pBuilder){
            super(pBuilder.mId, pBuilder.mName, pBuilder.mPortal, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
            mHackBonus = pBuilder.mHackBonus;
        }

        public CMultiHackEntity() {
        }


        public void setmHackBonus(int mHackBonus) {
            this.mHackBonus = mHackBonus;
        }

        public int getmHackBonus() {
            return mHackBonus;
        }

        public static class CMultiHackBuilder{
            private int mEnergy;
            private int mId;
            private String mName;
            private int mLevel;
            private CPortalEntity mPortal;
            private int mLifeTime;
            private int mRadius;
            private int mEnergyMax;
            private int mHackBonus;


            public CMultiHackBuilder(int pId){
                mId = pId;
            }

            public CMultiHackBuilder hackBonus(int pDefensBonus){
                mHackBonus = pDefensBonus;
                return this;
            }

            public CMultiHackBuilder name(String pName){
                mName = pName;
                return this;
            }

            public CMultiHackBuilder portal(CPortalEntity pPortal){
                mPortal = pPortal;
                return this;
            }

            public CMultiHackBuilder level(int pLevel) {
                mLevel = pLevel;
                return this;
            }

            public CMultiHackBuilder energy(int pEnergy) {
                mEnergy = pEnergy;
                return this;
            }

            public CMultiHackBuilder energyMax(int pEnergyMax){
                mEnergyMax = pEnergyMax;
                return this;
            }


            public CMultiHackBuilder radius(int pRadius){
                mRadius = pRadius;
                return this;
            }

            public CMultiHackBuilder lifeTime(int plifeTime){
                mLifeTime = plifeTime;
                return this;
            }
            public CMultiHackEntity build() {
                return new CMultiHackEntity(this);
            }

        }

        @Override
        public String toString() {
            return "CMultiHackEntity{" +
                    "mHackBonus=" +  +
                    '}'+super.toString();
        }


    }

