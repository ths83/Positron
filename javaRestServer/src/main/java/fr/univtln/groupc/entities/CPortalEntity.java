package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */

@Entity
@Table(name = "t_portal" , schema = "positron")
@NamedQueries({@NamedQuery(name = CPortalEntity.GET_ALL, query = "select p from CPortalEntity p"),
@NamedQuery(name = CPortalEntity.GET_BY_TEAM, query = "select p from CPortalEntity p where p.mTeam = (select t from CTeamEntity t where t.mId = :mId)")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CPortalEntity.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CPortalEntity implements Serializable {
    @Id
    @Column(name = "portal_id")
    private int mId;
    @Column(name = "latitude")
    private double mLat;
    @Column(name = "longitude")
    private double mLong;
    @Column(name = "radius")
    private int mRadius;
    @OneToMany(mappedBy = "mPortal")
    @JoinTable(schema = "positron")
    private List<ABuildingEntity> mBuildings = new ArrayList<>();
    @OneToMany(mappedBy = "mPortal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(schema = "positron")
    //@JsonSerialize(using = )
    private List<CResonatorEntity> mResonators = new ArrayList<CResonatorEntity>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mPortal")
    //@JoinTable(schema = "positron")
    private List<CKeyEntity> mKeys = new ArrayList<>();
    @ManyToMany(mappedBy = "mPortals")
    private List<CLinkEntity> mLinks  = new ArrayList<CLinkEntity>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_fk")
    private CTeamEntity mTeam;

    public final static String GET_ALL = "Portal.getAll";
    public final static String GET_BY_TEAM = "Portal.getByTeam";

    public CPortalEntity(){}

    public CPortalEntity(CPortalBuilder pBuilder){
        mId = pBuilder.mId;
        mLat = pBuilder.mLat;
        mLong = pBuilder.mLong;
        mRadius = pBuilder.mRadius;
        mBuildings = pBuilder.mBuildings;
        mResonators = pBuilder.mResonators;
        mTeam = pBuilder.mTeam;
        mLinks = pBuilder.mLinks;
        mKeys = pBuilder.mKeys;
        if (mKeys != null){
            for (CKeyEntity lKey : mKeys){
                lKey.setPortal(this);
            }
        }
        if (mBuildings != null){
            for (ABuildingEntity lBuilding : mBuildings){
                lBuilding.setPortal(this);
            }
        }

    }


    public static class CPortalBuilder{
        private int mId;
        private double mLat;
        private double mLong;
        private int mRadius;
        private List<ABuildingEntity> mBuildings = new ArrayList<>();
        private List<CResonatorEntity> mResonators = new ArrayList<>();
        public List<CLinkEntity> mLinks = new ArrayList<>();
        private CTeamEntity mTeam;
        private List<CKeyEntity> mKeys;

        public CPortalBuilder(int pId){
            mId = pId;
        }

        public CPortalBuilder latitude(double pLat){
            mLat = pLat;
            return this;
        }

        public CPortalBuilder longitude(double pLong){
            mLong = pLong;
            return this;
        }

        public CPortalBuilder radius(int pRadius){
            mRadius = pRadius;
            return this;
        }

        public CPortalBuilder buildings(List<ABuildingEntity> pBuildings){
            mBuildings = pBuildings;
            return this;
        }

        public CPortalBuilder links(List<CLinkEntity> pLinks){
            mLinks = pLinks;
            return this;
        }

        public CPortalBuilder resonators(List<CResonatorEntity> pResonators){
            mResonators = pResonators;
            return this;
        }

        public CPortalBuilder team(CTeamEntity pTeam){
            mTeam = pTeam;
            return this;
        }

        public CPortalBuilder keys(List<CKeyEntity> pKeys){
            mKeys = pKeys;
            return this;
        }

        public CPortalEntity build(){
            return new CPortalEntity(this);
        }
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double pLat) {
        mLat = pLat;
    }

    public double getLong() {
        return mLong;
    }

    public void setLong(double pLong) {
        mLong = pLong;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int pRadius) {
        mRadius = pRadius;
    }

    public List<CLinkEntity> getLinks() {
        return mLinks;
    }

    public void setLinks(List<CLinkEntity> pLinks) {
        mLinks = pLinks;
    }

    public void addLink(CLinkEntity pLink){
        mLinks.add(pLink);
    }

    public CTeamEntity getTeam(){
        return mTeam;
    }

    public void setTeam(CTeamEntity pTeam){
        mTeam = pTeam;
        if (mTeam != null){
            mTeam.addPortal(this);
        }
    }

    public List<CResonatorEntity> getResonators(){
        return mResonators;
    }

    public void setResonators(List<CResonatorEntity> pResonators){
        mResonators = pResonators;
    }

    public List<ABuildingEntity> getBuildings(){
        return mBuildings;
    }

    public void setBuildings(List<ABuildingEntity> pBuildings){
        mBuildings = pBuildings;
    }

    public List<CKeyEntity> getKeys(){
        return mKeys;
    }

    public void setKeys(List<CKeyEntity> pKeys){
        mKeys = pKeys;
    }

    public void addResonator(CResonatorEntity pResonator){
        if (mResonators != null){
            mResonators.add(pResonator);
        }
    }

    @Override
    public String toString() {
        return "CPortalEntity{" +
                "mId=" + mId +
                /*", mLat=" + mLat +
                ", mLong=" + mLong +
                ", mRadius=" + mRadius +
                ", mObjects=" + mObjects +
                ", mResonators=" + mResonators +
                //", mLinks=" + mLinks +
                ", mTeam=" + mTeam +
                */'}' + super.toString();
    }


}