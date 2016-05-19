package fr.univtln.groupc.entities;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.univtln.groupc.rest.CRestUpdate;

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
    private List<ABuildingEntity> mBuildings = new ArrayList<>();
    //@JsonSerialize(using = )
    private List<CResonatorEntity> mResonators = new ArrayList<CResonatorEntity>();
    //@JoinTable(schema = "positron")
    private List<CKeyEntity> mKeys = new ArrayList<>();
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

    public void attributeTeam() {

        List<CResonatorEntity> lResonators1 = new ArrayList<>();
        List<CResonatorEntity> lResonators2 = new ArrayList<>();
        int lLevel1 = 0;
        int lLevel2 = 0;
        int i=0;
        // Séparation des résonateur en team.
        for(CResonatorEntity lResonator : mResonators){
            if(lResonator.getOwner().getTeam().getId() == 1 ){
                lResonators1.add(lResonator);
            }
            else{
                lResonators2.add(lResonator);
            }
        }
        // Calcule des Dominances
        for (CResonatorEntity resonator : lResonators1) {
            lLevel1 = lLevel1 + resonator.getLevel();
            Log.d("test", "blue------>>>>>>>"+Integer.toString(lLevel1));
            i=i+1;
        }
        for (CResonatorEntity resonator : lResonators2) {
            lLevel2 = lLevel2 + resonator.getLevel();
            Log.d("test", "red------>>>>>>>"+Integer.toString(lLevel2));
        }


        //Changement de team si nécessaire.
        if (lLevel1>lLevel2){
            if (mTeam == null) {
                mTeam=lResonators1.get(0).getOwner().getTeam();
                //new CRestUpdate().updatePortalRest(this);
                //TODO  Delete Link
            }
            else {
                if (getTeam().getId() != 1) {
                    mTeam=lResonators1.get(0).getOwner().getTeam();
                }
            }
        }
        else {
            if (lLevel2 > lLevel1) {
                if (mTeam == null) {
                    mTeam=lResonators2.get(0).getOwner().getTeam();
                    //new CRestUpdate().updatePortalRest(this);
                    //TODO  Delete Link

                }
                else {
                    if (getTeam().getId() != 2) {
                        mTeam=lResonators2.get(0).getOwner().getTeam();

                    }

                }
            }
            else{

                if(getTeam() != null){
                     setTeam(null);
                    //new CRestUpdate().updatePortalRest(this);
                    //TODO  Delete Link

                }
            }
        }

    }

    @JsonIgnore
    public List<CResonatorEntity> getResonatorsTeamById(int pId){
        List<CResonatorEntity> lList = new ArrayList<>();
        for (CResonatorEntity lResonator : mResonators){
            if (lResonator.getOwner().getTeam().getId() == pId){
                lList.add(lResonator);
            }
        }
        return lList;
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
