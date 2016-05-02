package fr.univtln.groupeC.entities;

import javax.persistence.*;

/**
 * Created by arouani277 on 25/04/16.
 */
@Entity
@Table(name = "t_building", schema = "project")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ABuildingEntity extends AObjectEntity {
    @Column(name = "long")
    private double mLong;
    @Column(name = "lat")
    private double mLat;
    @Column(name = "lifetime")
    private int mLifeTime;
    @Column(name = "radius")
    private int mRadius;
    @Column(name = "level")
    private int mLevel;
    @Column(name = "energy_max")
    private int mEnergyMax;
    @Column(name = "energy")
    private int mEnergy;

    public ABuildingEntity(){
        super();
    }


    public ABuildingEntity(int pId, String pName, double pLong, double pLat, int pLifeTime, int pRadius, int pLevel, int pEnergy, int pEnergyMax){
        super(pId, pName);
        mLong = pLong;
        mLat = pLat;
        mLifeTime = pLifeTime;
        mRadius = pRadius;
        mLevel = pLevel;
        mEnergy = pEnergy;
        mEnergyMax = pEnergyMax;
    }

    @Override
    public String toString() {
        return super.toString() + "ABuildingEntity{" +
                "mLong=" + mLong +
                ", mLat=" + mLat +
                ", mLifeTime=" + mLifeTime +
                ", mRadius=" + mRadius +
                ", mLevel=" + mLevel +
                ", mEnergyMax=" + mEnergyMax +
                ", mEnergy=" + mEnergy +
                '}';
    }

    public double getLong() {
        return mLong;
    }

    public void setLong(float pLong) {
        mLong = pLong;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(float pLat) {
        mLat = pLat;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int pRadius) {
        mRadius = pRadius;
    }


}