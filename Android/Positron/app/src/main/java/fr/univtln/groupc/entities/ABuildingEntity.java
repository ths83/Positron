package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 09/05/2016.
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = CResonatorEntity.class, name = "CResonatorEntity"),
@JsonSubTypes.Type(value = CTurretEntity.class, name = "CTurretEntity")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ABuildingEntity.class)

public abstract class ABuildingEntity extends AObjectEntity implements Serializable, ITarget {

    private CPortalEntity mPortal;

    private int mLifeTime;

    private int mRadius;

    private int mLevel;

    private int mEnergyMax;

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



    @Override
    public void takeDamage(int pDamage , IFighter pAttacker) {
        int lArmor = mLevel * 2;
        List<CTurretEntity> lListTurrets = new ArrayList<>();
        List<CShieldEntity> lListShield = new ArrayList<>();

        // On fait la liste des tourelles et des boucliers du portail
        for(ABuildingEntity lBuilding : getPortal().getBuildings()){
            if (lBuilding instanceof CTurretEntity){
                lListTurrets.add((CTurretEntity) lBuilding);
            }
            else if(lBuilding instanceof CShieldEntity){
                lListShield.add((CShieldEntity)lBuilding);
            }
        }

        for(CTurretEntity lTurret : lListTurrets){
            lTurret.attack((ITarget) pAttacker,lTurret.getDamage());
        }


        for(CShieldEntity lShield : lListShield){
            lArmor = lArmor + lShield.getmDefensBonus();
        }

        pDamage = pDamage - lArmor;
        if (pDamage > 0) {
            mEnergy = mEnergy - pDamage;

            if (mEnergy <= 0){

                if(this instanceof CResonatorEntity) {
                    getPortal().attributeTeam();
                }
                //TODO DELETE this
            }
            else{
                //TODO Update this
            }
        }


    }

    @JsonIgnore
    @Override
    public CTeamEntity getTeamOfTarget() {
        if(this instanceof CResonatorEntity){
            CResonatorEntity lR=(CResonatorEntity)this;
            return lR.getOwner().getTeam();
        }
        else{
            return getPortal().getTeam();
        }
    }
}