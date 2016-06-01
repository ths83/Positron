package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import sun.rmi.runtime.Log;

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
    private List<ABuildingEntity> mBuildings = new ArrayList<ABuildingEntity>();
    @OneToMany(mappedBy = "mPortal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(schema = "positron")
    //@JsonSerialize(using = )
    private List<CResonatorEntity> mResonators = new ArrayList<CResonatorEntity>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mPortal")
    //@JoinTable(schema = "positron")
    private List<CKeyEntity> mKeys = new ArrayList<CKeyEntity>();
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "mPortals")
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

        if (mResonators != null){
            for (CResonatorEntity lResonator : mResonators){
                lResonator.setPortal(this);
            }
        }

    }


    public static class CPortalBuilder{
        private int mId;
        private double mLat;
        private double mLong;
        private int mRadius;
        private List<ABuildingEntity> mBuildings = new ArrayList<ABuildingEntity>();
        private List<CResonatorEntity> mResonators = new ArrayList<CResonatorEntity>();
        public List<CLinkEntity> mLinks = new ArrayList<CLinkEntity>();
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
            pResonator.setPortal(this);
        }
    }

    public void removeResonator(CResonatorEntity pResonator){
        if (mResonators != null){
            mResonators.remove(pResonator);
            //pResonator.setPortal(null);
        }
        else{
            System.out.println("resonators null");
        }
    }

    public void removeBuilding(ABuildingEntity pBuilding){
        if (mBuildings != null){
            mBuildings.remove(pBuilding);
            //pBuilding.setPortal(null);
        }
        else{
            System.out.println("buildings null");
        }
    }

    public void addBuilding(ABuildingEntity pBuilding){
        if (mBuildings != null){
            mBuildings.add(pBuilding);
            pBuilding.setPortal(this);

        }
    }

    public List<CLinkEntity> attributeTeam() {

        List<CResonatorEntity> lResonators1 = new ArrayList<CResonatorEntity>();
        List<CResonatorEntity> lResonators2 = new ArrayList<CResonatorEntity>();
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
            i=i+1;
        }
        for (CResonatorEntity resonator : lResonators2) {
            lLevel2 = lLevel2 + resonator.getLevel();
        }


        //Changement de team si nécessaire.
        if (lLevel1>lLevel2) {
            if (mTeam == null) {
                mTeam = lResonators1.get(0).getOwner().getTeam();
                //new CRestUpdate().updatePortalRest(this);
               return getLinks();
            } else {
                if (getTeam().getId() != 1) {
                    mTeam = lResonators1.get(0).getOwner().getTeam();

                }
            }
        }
        else {
            if (lLevel2 > lLevel1) {
                if (mTeam == null) {
                    mTeam=lResonators2.get(0).getOwner().getTeam();
                    //new CRestUpdate().updatePortalRest(this);
                   return getLinks();

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
                    return getLinks();
                }
            }
        }
        return null;
    }

    @JsonIgnore
    public List<CResonatorEntity> getResonatorsTeamById(int pId) {
        List<CResonatorEntity> lList = new ArrayList<CResonatorEntity>();
        for (CResonatorEntity lResonator : mResonators) {
            if (lResonator.getOwner().getTeam().getId() == pId) {
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
                ", mResonators=" + mResonators +*/
                //", mLinks=" + mLinks +
                ", mTeam=" + mTeam +
                '}' + super.toString();
    }

    @JsonIgnore
    public List<CShieldEntity> getShields(){
        List<CShieldEntity> lShields = new ArrayList<CShieldEntity>();

        for(ABuildingEntity lBuild : getBuildings()){
            if(lBuild instanceof CShieldEntity){
                lShields.add((CShieldEntity) lBuild);
            }
        }
    return lShields;
    }

    @JsonIgnore
    public List<CTurretEntity> getTurrets(){
        List<CTurretEntity> lTurrets = new ArrayList<CTurretEntity>();

        for(ABuildingEntity lBuild : getBuildings()){
            if(lBuild instanceof CTurretEntity){
                lTurrets.add((CTurretEntity) lBuild);
            }
        }
        return lTurrets;
    }

    public void  clearLinks(){
        mLinks.clear();
    }

    @JsonIgnore
    public int getLevel(){
    int lLevel =0;
        for(CResonatorEntity lResonator : getResonators()){
           if(lResonator.getPortal().getTeam() == lResonator.getOwner().getTeam()){
               lLevel=lLevel+lResonator.getLevel();
           }
       }
     lLevel =  lLevel/8;
    return lLevel;
    }

}