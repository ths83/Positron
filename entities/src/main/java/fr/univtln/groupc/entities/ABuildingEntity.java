package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by arouani277 on 25/04/16.
 */
@Entity
@Table(name = "t_building", schema = "positron")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = CResonatorEntity.class, name = "CResonatorEntity"),
        @JsonSubTypes.Type(value = CTurretEntity.class, name = "CTurretEntity")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ABuildingEntity.class)

public abstract class ABuildingEntity extends AObjectEntity implements Serializable {
    /*
    @Column(name = "long")
    private double mLong;
    @Column(name = "lat")
    private double mLat;
    */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "portal_id")
    private CPortalEntity mPortal;
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


    public ABuildingEntity(int pId, String pName, CPortalEntity pPortal, int pLifeTime, int pRadius, int pLevel, int pEnergy, int pEnergyMax){
        super(pId, pName);
        /*
        mLong = pLong;
        mLat = pLat;*/
        mPortal = pPortal;
        mLifeTime = pLifeTime;
        mRadius = pRadius;
        mLevel = pLevel;
        mEnergy = pEnergy;
        mEnergyMax = pEnergyMax;
    }

    @Override
    public String toString() {
        return super.toString() + "ABuildingEntity{" +
                /*"mLong=" + mLong +
                ", mLat=" + mLat +*/
                "mPortal=" + mPortal +
                ", mLifeTime=" + mLifeTime +
                ", mRadius=" + mRadius +
                ", mLevel=" + mLevel +
                ", mEnergyMax=" + mEnergyMax +
                ", mEnergy=" + mEnergy +
                '}';
    }
    /*
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
    }*/

    public CPortalEntity getPortal(){
        return mPortal;
    }

    public void setPortal(CPortalEntity lPortal){
        mPortal = lPortal;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int pRadius) {
        mRadius = pRadius;
    }

    public int getLifeTime() {
        return mLifeTime;
    }

    public void setLifeTime(int pLifeTime) {
        mLifeTime = pLifeTime;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int pLevel) {
        mLevel = pLevel;
    }

    public int getEnergyMax() {
        return mEnergyMax;
    }

    public void setEnergyMax(int pEnergyMax) {
        mEnergyMax = pEnergyMax;
    }

    public int getEnergy() {
        return mEnergy;
    }

    public void setEnergy(int pEnergy) {
        mEnergy = pEnergy;
    }
}