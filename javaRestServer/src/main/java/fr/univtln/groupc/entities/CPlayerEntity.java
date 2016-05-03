package fr.univtln.groupc.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */


@Entity
@Table(name = "t_player", schema = "positron")

public class CPlayerEntity {

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
    private List<AObjectEntity> mObjects;

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
        private List<CSkillEntity> mSkills;
        private List<AObjectEntity> mObjects;

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
}