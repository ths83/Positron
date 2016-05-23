package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */


@Entity
@Table(name = "t_player", schema = "positron")
@NamedQueries({@NamedQuery(name = CPlayerEntity.GET_ALL, query = "select p from CPlayerEntity p"),
@NamedQuery(name = CPlayerEntity.GET_BY_NAME, query = "select p from CPlayerEntity p where p.mNickName = :mNickName")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class CPlayerEntity implements Serializable {
    @Id
    @Column(name = "id")
    private int mId;
    @Column(name = "nickname")
    private String mNickName;
    @Column(name = "email")
    private String mEmail;
    @ManyToOne
    @JoinColumn(name = "team")
    private CTeamEntity mTeam;
    @Column(name = "xp")
    private int mXp;
    @Column(name = "bag_size")
    private int mBagSize;
    @Column(name = "longitude")
    private double mLong;
    @Column(name = "mLatitude")
    private double mLat;
    @Column(name = "energy")
    private int mEnergy;
    @Column(name = "energy_max")
    private int mEnergyMax;
    @OneToMany
    @JoinTable(schema = "positron")
    private List<CSkillEntity> mSkills;
    @OneToMany
    @JoinTable(schema = "positron")
    private List<AObjectEntity> mObjects =new ArrayList<AObjectEntity>();

    public final static String GET_ALL = "Player.getAll";
    public final static String GET_BY_NAME = "Player.getByName";

    public CPlayerEntity(){}

    public CPlayerEntity(CPlayerBuilder pBuilder){
        mId = pBuilder.mId;
        mNickName = pBuilder.mNickName;
        mEmail = pBuilder.mEmail;
        mTeam = pBuilder.mTeam;
        mXp = pBuilder.mXp;
        mBagSize = pBuilder.mBagSize;
        mObjects = pBuilder.mObjects;
        mSkills = pBuilder.mSkills;
        mLat = pBuilder.mLat;
        mLong = pBuilder.mLong;
        mEnergy = pBuilder.mEnergy;
        mEnergyMax = pBuilder.mEnergyMax;
        mBagSize = pBuilder.mBagSize;
    }

    public void print(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "CPlayerEntity{" +
                "mId=" + mId +
                ", mNickName='" + mNickName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mTeam=" + mTeam +
                ", mXp=" + mXp +
                ", mBagSize=" + mBagSize +
                ", mLong=" + mLong +
                ", mLat=" + mLat +
                ", mEnergy=" + mEnergy +
                ", mEnergyMax=" + mEnergyMax +
                ", mTeam=" + mTeam +
                ", mSkills=" + mSkills +
                ", mObjects=" + mObjects +
                '}';
    }

    public void setSkills(List<CSkillEntity> pSkills){
        mSkills = pSkills;
    }

    public void addSkill(CSkillEntity pSkill){
        mSkills.add(pSkill);
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String pNickName) {
        mNickName = pNickName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String pEmail) {
        mEmail = pEmail;
    }

    public CTeamEntity getTeam() {
        return mTeam;
    }

    public void setTeam(CTeamEntity pTeam) {
        mTeam = pTeam;
    }

    public int getXp() {
        return mXp;
    }

    public void setXp(int pXp) {
        mXp = pXp;
    }

    public int getBagSize() {
        return mBagSize;
    }

    public void setBagSize(int pBagSize) {
        mBagSize = pBagSize;
    }

    public double getLong() {
        return mLong;
    }

    public void setLong(double pLong) {
        mLong = pLong;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double pLat) {
        mLat = pLat;
    }

    public int getEnergy() {
        return mEnergy;
    }

    public void setEnergy(int pEnergy) {
        mEnergy = pEnergy;
    }

    public int getEnergyMax() {
        return mEnergyMax;
    }

    public void setEnergyMax(int pEnergyMax) {
        mEnergyMax = pEnergyMax;
    }


    public List<AObjectEntity> getObjects() {
        return mObjects;
    }


    public void setObjects(List<AObjectEntity> pObjects) {
        mObjects = pObjects;
    }

    public void addObjects(AObjectEntity o) {
        mObjects.add(o);
    }
/*
    @JsonIgnore
    public List<AObjectEntity> getKeys(){
        List<AObjectEntity> lKeys = new ArrayList<>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CKeyEntity){
                lKeys.add(lObject);
            }
        }
        return lKeys;
    }*/

    @JsonIgnore
    public List<CKeyEntity> getKeys(){
        List<CKeyEntity> lKeys = new ArrayList<>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CKeyEntity){
                lKeys.add((CKeyEntity)lObject);
            }
        }
        return lKeys;
    }




    public static class CPlayerBuilder{
        private int mId;
        private String mNickName;
        private String mEmail;
        private CTeamEntity mTeam;
        private int mEnergy;
        private int mEnergyMax;
        private double mLat;
        private double mLong;
        private int mBagSize;
        private int mXp;
        private List<CSkillEntity> mSkills = new ArrayList<>();
        private List<AObjectEntity> mObjects = new ArrayList<>();

        public CPlayerBuilder(int pId){
            mId = pId;
        }

        public CPlayerBuilder nickname(String pNickName){
            mNickName = pNickName;
            return this;
        }

        public CPlayerBuilder email(String pEmail){
            mEmail = pEmail;
            return this;
        }

        public CPlayerBuilder team(CTeamEntity pTeam){
            mTeam = pTeam;
            return this;
        }

        public CPlayerBuilder longitude(double pLong){
            mLong = pLong;
            return this;
        }

        public CPlayerBuilder latitude(double pLat){
            mLat = pLat;
            return this;
        }

        public CPlayerBuilder energy(int pEnergy){
            mEnergy = pEnergy;
            return this;
        }

        public CPlayerBuilder energyMax(int pEnergyMax){
            mEnergyMax = pEnergyMax;
            return this;
        }

        public CPlayerBuilder xp(int pXp){
            mXp = pXp;
            return this;
        }

        public CPlayerBuilder bagSize(int pBagSize){
            mBagSize = pBagSize;
            return this;
        }

        public CPlayerBuilder objects(List<AObjectEntity> pObjects){
            mObjects = pObjects;
            return this;
        }

        public CPlayerBuilder skills(List<CSkillEntity> pSkills){
            mSkills = pSkills;
            return this;
        }

        public CPlayerEntity build(){
            return new CPlayerEntity(this);
        }
    }

    @JsonIgnore
    public int getLevel(){
        int lLevel = 0;
        int lXp = getXp();

        if (lXp < 500){
            lLevel = 1;
        }

        else if(500 <= lXp && lXp < 1200){
            lLevel = 2;
        }

        else if(120 <= lXp && lXp < 2100){

            lLevel = 3;
            }
        else if(2100 <= lXp && lXp < 3200){

            lLevel = 4;

        }
        else if(3200 <= lXp && lXp < 4500){
            lLevel = 5;
            }
        else if(45000 <= lXp && lXp < 6000){
            lLevel = 6;
        }
        else if(6000 <= lXp && lXp < 7700){
            lLevel = 7;
        }
        else if(lXp >= 7700){
            lLevel = 8;
        }
        return lLevel;
    }
}