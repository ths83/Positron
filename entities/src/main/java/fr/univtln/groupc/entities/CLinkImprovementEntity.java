package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xdurbec066 on 01/06/16.
 */

@Entity
@Table(name = "t_linkImprovement", schema = "positron")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries(@NamedQuery(name = CLinkImprovementEntity.GET_ALL, query = "select p from CLinkImprovementEntity  p"))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CLinkImprovementEntity.class)

//@JsonDeserialize(as = CTurretEntity.class)
public class CLinkImprovementEntity extends ABuildingEntity implements Serializable {

    @Column(name = "rangeBonus")
    private double mRangeBonus;
    public final static String GET_ALL = "LinkImprovement.getAll";

    public CLinkImprovementEntity(CLinkImprovementBuilder pBuilder) {
        super(pBuilder.mId, pBuilder.mName, pBuilder.mPortal, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
        mRangeBonus = pBuilder.mRangeBonus;
    }

    public CLinkImprovementEntity() {
    }


    public void setmRangeBonus(int mRangeBonus) {
        this.mRangeBonus = mRangeBonus;
    }

    public double getmRangeBonus() {
        return mRangeBonus;
    }

    public static class CLinkImprovementBuilder {
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
        private double mRangeBonus;


        public CLinkImprovementBuilder() {

        }

        public CLinkImprovementBuilder rangeBonus(double pRangeBonus) {
            mRangeBonus = pRangeBonus;
            return this;
        }

        public CLinkImprovementBuilder name(String pName) {
            mName = pName;
            return this;
        }

        public CLinkImprovementBuilder portal(CPortalEntity pPortal) {
            mPortal = pPortal;
            return this;
        }

        public CLinkImprovementBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CLinkImprovementBuilder energy(int pEnergy) {
            mEnergy = pEnergy;
            return this;
        }

        public CLinkImprovementBuilder energyMax(int pEnergyMax) {
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
        public CLinkImprovementBuilder radius(int pRadius) {
            mRadius = pRadius;
            return this;
        }

        public CLinkImprovementBuilder lifeTime(int plifeTime) {
            mLifeTime = plifeTime;
            return this;
        }

        public CLinkImprovementEntity build() {
            return new CLinkImprovementEntity(this);
        }

    }

    @Override
    public String toString() {
        return "CLinkImprovementEntity{" +
                "mRangeBonus=" + +
                '}' + super.toString();
    }


}



