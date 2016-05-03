package fr.univtln.groupc.entities;

import com.owlike.genson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */

@Entity
@Table(name = "portal" , schema = "positron")
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
    private List<AObjectEntity> mObjects;
    @OneToMany
    private List<CResonatorEntity> mResonators;
    @ManyToMany(mappedBy = "mPortals")
    @JsonIgnore
    private List<CLinkEntity> mLinks  = new ArrayList<CLinkEntity>();
    @ManyToOne
    @JoinColumn(name = "portal")
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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public double getmLat() {
        return mLat;
    }

    public void setmLat(float mLat) {
        this.mLat = mLat;
    }

    public double getmLong() {
        return mLong;
    }

    public void setmLong(float mLong) {
        this.mLong = mLong;
    }

    public int getmRadius() {
        return mRadius;
    }

    public void setmRadius(int mRadius) {
        this.mRadius = mRadius;
    }
    @JsonIgnore
    public List<CLinkEntity> getmLinks() {
        return mLinks;
    }

    public void setmLinks(List<CLinkEntity> mLinks) {
        this.mLinks = mLinks;
    }

    public void addmLink(CLinkEntity plink){
        mLinks.add(plink);
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