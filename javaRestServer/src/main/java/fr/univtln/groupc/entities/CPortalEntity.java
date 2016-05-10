package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "portalId", scope = CPortalEntity.class)

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
    @OneToMany
    @JoinTable(schema = "positron")
    private List<AObjectEntity> mObjects;
    @OneToMany
    @JoinTable(schema = "positron")
    private List<CResonatorEntity> mResonators = new ArrayList<CResonatorEntity>();
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
        mObjects = pBuilder.mObjects;
        mResonators = pBuilder.mResonators;
        mTeam = pBuilder.mTeam;
        mLinks = pBuilder.mLinks;
    }


    public static class CPortalBuilder{
        private int mId;
        private double mLat;
        private double mLong;
        private int mRadius;
        private List<AObjectEntity> mObjects = new ArrayList<>();
        private List<CResonatorEntity> mResonators = new ArrayList<>();
        public List<CLinkEntity> mLinks = new ArrayList<>();
        private CTeamEntity mTeam;

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

        public CPortalBuilder objects(List<AObjectEntity> pObjects){
            mObjects = pObjects;
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

        public CPortalEntity build(){
            return new CPortalEntity(this);
        }
    }

    public int getPortalId() {
        return mId;
    }

    public void setPortalId(int pId) {
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

    public List<AObjectEntity> getObjects(){
        return mObjects;
    }

    public void setObjects(List<AObjectEntity> pObjects){
        mObjects = pObjects;
    }


    @Override
    public String toString() {
        return "CPortalEntity{" +
                "mId=" + mId +
                ", mLat=" + mLat +
                ", mLong=" + mLong +
                ", mRadius=" + mRadius +
                ", mObjects=" + mObjects +
                ", mResonators=" + mResonators +
                ", mTeam=" + mTeam +
                '}' + super.toString();
    }



}