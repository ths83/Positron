package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 09/05/2016.
 */


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CPortalEntity.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CPortalEntity implements Serializable {

    private int mId;
    private double mLat;
    private double mLong;
    private int mRadius;
    private List<AObjectEntity> mObjects;
    private List<CResonatorEntity> mResonators = new ArrayList<CResonatorEntity>();
    private List<CLinkEntity> mLinks  = new ArrayList<CLinkEntity>();
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
                //", mLinks=" + mLinks +
                ", mTeam=" + mTeam +
                '}' + super.toString();
    }


}
