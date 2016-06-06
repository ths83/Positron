package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_player", schema = "positron")
@NamedQueries({@NamedQuery(name = CPlayerEntity.GET_ALL, query = "select p from CPlayerEntity p"),
        @NamedQuery(name = CPlayerEntity.GET_BY_NAME, query = "select p from CPlayerEntity p where p.mNickName = :mNickName"),
        @NamedQuery(name = CPlayerEntity.GET_BY_MAIL, query = "select p from CPlayerEntity p where p.mEmail = :mEmail")
})

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CPlayerEntity.class)

public class CPlayerEntity implements Serializable, ITarget, IFighter {
    @Id
    @Column(name = "id")
    private int mId;
    @Column(name = "nickname")
    private String mNickName= null;
    @Column(name = "email")
    private String mEmail= null;
    @ManyToOne
    @JoinColumn(name = "team")
    private CTeamEntity mTeam = null;
    @Column(name = "xp")
    private int mXp = 0;
    @Column(name = "bag_size")
    private int mBagSize = 0;
    @Column(name = "longitude")
    private double mLong;
    @Column(name = "mLatitude")
    private double mLat;
    @Column(name = "energy")
    private int mEnergy = 0;
    @Column(name = "energy_max")
    private int mEnergyMax = 0;
    @OneToMany
    @JoinTable(schema = "positron")
    private List<CSkillEntity> mSkills;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(schema = "positron")
    private List<AObjectEntity> mObjects =new ArrayList<AObjectEntity>();

    public final static String GET_ALL = "Player.getAll";
    public final static String GET_BY_NAME = "Player.getByName";
    public final static String GET_BY_MAIL = "Player.getByMail";

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

        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CResonatorEntity){
                ((CResonatorEntity) lObject).setOwner(this);
            }
        }
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
                //", mTeam=" + mTeam +
                ", mXp=" + mXp +
                ", mBagSize=" + mBagSize +
                ", mLong=" + mLong +
                ", mLat=" + mLat +
                ", mEnergy=" + mEnergy +
                ", mEnergyMax=" + mEnergyMax +
                //", mTeam=" + mTeam +
                ", mSkills=" + mSkills +
                //", mObjects=" + mObjects +
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

    public List<CSkillEntity> getSkills() {
        return mSkills;
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

    public void attack(ITarget pTarget,CConsumableEntity pAmmunition) {
        if(getFighterTeam().getId() != pTarget.getTargetTeam().getId()) {
            int lDammage=0;
            switch (pAmmunition.getRarity()){
                case(0):
                    lDammage = getLevel() * 10 + 20;
                    break;

                case(1):
                    lDammage = getLevel() * 15 + 30;
                    break;

                case(2):
                    lDammage = getLevel() * 20 + 40;
                    break;
            }
            pTarget.takeDamage(this,lDammage);
            removeObject(((AObjectEntity) pAmmunition));

        }
        else  {
            System.out.println("Cible de la même équipe");
        }
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
    public int getLevel(){
        int lLevel = 0;
        if (mXp < 500){
            lLevel = 1;
        }
        else if(500 <= mXp && mXp < 1200){
            lLevel = 2;
        }

        else if(1200 <= mXp && mXp < 2100){
            lLevel = 3;
        }
        else if(2100 <= mXp && mXp < 3200){
            lLevel = 4;
        }
        else if(3200 <= mXp && mXp < 4500){
            lLevel = 5;
        }
        else if(4500 <= mXp && mXp < 6000){
            lLevel = 6;
        }
        else if(6000 <= mXp && mXp < 7700){
            lLevel = 7;
        }
        else if(mXp >= 7700){
            lLevel = 8;
        }
        System.out.println("level => " + lLevel);
        return lLevel;
    }


    @JsonIgnore
    public List<CTurretEntity> getTurretsByLevel(int pId){
        List<CTurretEntity> lTurrets = new ArrayList<CTurretEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CTurretEntity){
                if (((CTurretEntity) lObject).getLevel() == pId){
                    lTurrets.add((CTurretEntity) lObject);
                }
            }
        }
        return lTurrets;
    }


    @JsonIgnore
    public List<CConsumableEntity> getBombs(){
        List<CConsumableEntity> lBombs = new ArrayList<CConsumableEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CConsumableEntity){
                if (lObject.getName().equals("Bombe")){
                    lBombs.add((CConsumableEntity)lObject);
                }
            }
        }
        return lBombs;
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
        private List<CSkillEntity> mSkills = new ArrayList<CSkillEntity>();
        private List<AObjectEntity> mObjects = new ArrayList<AObjectEntity>();

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


    public void takeDamage(IFighter pFighter, int pDamage) {
        int lDamages = pDamage - getLevel()*2;
        if(lDamages > 0) {
            loseEnergy(pDamage);
        }
    }

    @JsonIgnore
    public CTeamEntity getTargetTeam() {
        return getTeam();
    }

    @JsonIgnore
    public CTeamEntity getFighterTeam() {
        return getTeam();
    }



    public void removeObject (AObjectEntity pObject){
        mObjects.remove(pObject);
        //TODO DELETE OBJECT
    }

    public void loseEnergy(int pEnergyLose){
        if(pEnergyLose < mEnergy) {
            mEnergy = mEnergy - pEnergyLose;
        }
        else {
            mEnergy =0;
        }
    }
    public void gainEnergy(int pEnergyGain){
        if(pEnergyGain + mEnergy < mEnergyMax){
            mEnergy = mEnergy + pEnergyGain;
        }
        else{
            mEnergy = mEnergyMax;
        }
    }

    public void addXP(int pExperienceAdded){
        if(getLevel() != 8){
            mXp = mXp + pExperienceAdded;
        }
    }



    @JsonIgnore
    public List<CResonatorEntity> getResonators(){
        List<CResonatorEntity> lResonators = new ArrayList<CResonatorEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CResonatorEntity){
                lResonators.add((CResonatorEntity)lObject);
                }
            }
        return lResonators;
    }

    @JsonIgnore
    public List<CResonatorEntity> getResonatorsByLevel(int pLevel){
        List<CResonatorEntity> lResonators = new ArrayList<CResonatorEntity>();
        for (CResonatorEntity lResonator : getResonators()) {
            if (lResonator.getLevel()==pLevel){
                lResonators.add(lResonator);
            }
        }
        return lResonators;
    }

    @JsonIgnore
    public Set<Integer> getLevelsOfResonators(){
        Set<Integer> lLevels = new HashSet<Integer>();
        for (CResonatorEntity lResonator : getResonators()) {
            lLevels.add(lResonator.getLevel());
            }
    return lLevels;
    }

    @JsonIgnore
    public List<CKeyEntity> getKeys(){
        List<CKeyEntity> lKeys = new ArrayList<CKeyEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CKeyEntity){
                lKeys.add((CKeyEntity)lObject);
            }
        }
        return lKeys;
    }

    @JsonIgnore
    public List<CKeyEntity> getKeysByPortal(int pId){
        List<CKeyEntity> lKeys = new ArrayList<CKeyEntity>();
        for (CKeyEntity lKey : getKeys()) {
            if (lKey.getPortal().getId()==pId){
                lKeys.add(lKey);
            }
        }
        return lKeys;
    }

    @JsonIgnore
    public Set<Integer> getIdPortalsOfKeys(){
        Set<Integer> lIdPortals = new HashSet<Integer>();
        for (CKeyEntity lKey : getKeys()) {
            lIdPortals.add(lKey.getPortal().getId());
        }
        return lIdPortals;
    }

    @JsonIgnore
    public int getLevelOnSkillBranch(String pType){
        int lCount = 0;
        for (CSkillEntity lSkill : mSkills){
            if (lSkill.getType().equals(pType)){
                lCount++;
            }
        }
        return lCount;
    }

    public boolean skillAvailable(CSkillEntity pSkillWanted ,int pFreeSkillPoint){

        if(pFreeSkillPoint < pSkillWanted.getLevel()){
            return false;
        }
        else if(pSkillWanted.getLevel() == 1) {
            return true;
        }
        else{

            for(CSkillEntity lSkill : mSkills){
                if(lSkill.equals(pSkillWanted)){
                    return false;
                }
            }
            if (getLevelOnSkillBranch(pSkillWanted.getType())+1 == pSkillWanted.getLevel() ){
                return true;
            }
        }
        return false;
    }

    public boolean havingSkill(int pIdSkillVerified){

        for(CSkillEntity lSkill : mSkills){

            if (pIdSkillVerified == lSkill.getId()) {
                return true;

            }
        }
        return false;
    }

    @JsonIgnore
    public List<CConsumableEntity> getViruses(){
        List<CConsumableEntity> lConsumables = new ArrayList<CConsumableEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CConsumableEntity){
                lConsumables.add((CConsumableEntity)lObject);
            }
        }
        return lConsumables;
    }

    @JsonIgnore
    public List<CConsumableEntity> getVirusesByRarity(int pId){
        List<CConsumableEntity> lConsumables = new ArrayList<CConsumableEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CConsumableEntity){
                if (((CConsumableEntity) lObject).getRarity() == pId){
                    lConsumables.add((CConsumableEntity)lObject);
                }
            }
        }
        return lConsumables;
    }


}