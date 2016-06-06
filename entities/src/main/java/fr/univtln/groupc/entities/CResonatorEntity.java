package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 26/04/16.
 */

@Entity
@Table(name = "t_resonator", schema = "positron")
@NamedQueries({@NamedQuery(name = CResonatorEntity.GET_ALL, query = "select r from CResonatorEntity r"),@NamedQuery(name = CResonatorEntity.GET_RESONATOR_BY_PORTAL, query = "select r from CResonatorEntity r  where r.mPortal = (select p from CPortalEntity p where p.mId = :mId)"),
        @NamedQuery(name = CResonatorEntity.GET_RESONATOR_BY_PORTAL_AND_TEAM, query = "select r from CResonatorEntity r  where r.mPortal = (select p from CPortalEntity p where p.mId = :mId) and r.mOwner = (select o from CPlayerEntity o where o.mTeam = ( select t from CTeamEntity t where t.mId=:mId2))")})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CResonatorEntity.class)
@JsonDeserialize(as = CResonatorEntity.class)
public class CResonatorEntity extends ABuildingEntity implements Serializable {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_fk")
    private CPlayerEntity mOwner;

    public final static String GET_ALL = "Resonator.getAll";
    public final static String GET_RESONATOR_BY_PORTAL = "Resonator.getResonatorsByPortal";
    public final static String GET_RESONATOR_BY_PORTAL_AND_TEAM = "Resonator.getResonatorsByPortalAndTeam";


    public CResonatorEntity() {
    }


    public CResonatorEntity(CResonatorBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mPortal, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
        setPortal(pBuilder.mPortal);
        mOwner = pBuilder.mOwner;
        if (getPortal() != null){
            getPortal().addResonator(this);
        }
        if (mOwner != null){
            mOwner.addObjects((AObjectEntity) this);
        }
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
                "mPortal=" +
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