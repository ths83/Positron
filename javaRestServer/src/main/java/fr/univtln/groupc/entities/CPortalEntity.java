package fr.univtln.groupc.entities;

import com.owlike.genson.annotation.JsonIgnore;
import com.owlike.genson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */

@Entity
@Table(name = "t_portal" , schema = "positron")
@NamedQueries(@NamedQuery(name = CPortalEntity.GET_ALL, query = "select p from CPortalEntity p"))
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
    private List<CResonatorEntity> mResonators;
    @ManyToMany(mappedBy = "mPortals")
    @JsonIgnore
    private List<CLinkEntity> mLinks  = new ArrayList<CLinkEntity>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_fk")
    private CTeamEntity mTeam;

    public final static String GET_ALL = "Portal.getAll";

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
        private List<AObjectEntity> mObjects;
        private List<CResonatorEntity> mResonators;
        public List<CLinkEntity> mLinks;
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

    @JsonIgnore
    public List<CLinkEntity> getLinks() {
        return mLinks;
    }

    @JsonProperty
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
                ", mLinks=" + mLinks +
                ", mTeam=" + mTeam +
                '}' + super.toString();
    }
}