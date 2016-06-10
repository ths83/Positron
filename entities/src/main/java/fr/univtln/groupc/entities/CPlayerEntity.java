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
    public static int XP_LVL_2 = 500;
    public static int XP_LVL_3 = 1200;
    public static int XP_LVL_4 = 2100;
    public static int XP_LVL_5 = 3200;
    public static int XP_LVL_6 = 4500;
    public static int XP_LVL_7 = 6000;
    public static int XP_LVL_8 = 7700;

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

    public void addObjects(AObjectEntity pObject) {
        mObjects.add(pObject);
        if (pObject instanceof CResonatorEntity){
            ((CResonatorEntity) pObject).setOwner(this);
        }

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
        if (mXp < XP_LVL_2){
            lLevel = 1;
        }
        else if(XP_LVL_2 <= mXp && mXp < XP_LVL_3){
            lLevel = 2;
        }

        else if(XP_LVL_3 <= mXp && mXp < XP_LVL_4){
            lLevel = 3;
        }
        else if(XP_LVL_4 <= mXp && mXp < XP_LVL_5){
            lLevel = 4;
        }
        else if(XP_LVL_5 <= mXp && mXp < XP_LVL_6){
            lLevel = 5;
        }
        else if(XP_LVL_6 <= mXp && mXp < XP_LVL_7){
            lLevel = 6;
        }
        else if(XP_LVL_7 <= mXp && mXp < XP_LVL_8){
            lLevel = 7;
        }
        else if(mXp >= XP_LVL_8){
            lLevel = 8;
        }
        System.out.println("level => " + lLevel);
        return lLevel;
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
        int lLuck = (int) (Math.random()*100);
        if( this.havingSkill(221) && lLuck > 70 ){
            System.out.println("Attaque esquivée grace à l'invisibilité.");
        }
        else {
            if (lDamages > 0) {
                loseEnergy(pDamage);
            }
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

        if(pFreeSkillPoint<pSkillWanted.getCost()){
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

    public boolean havingSkill(int pIdSkill){

        for(CSkillEntity lSkill : mSkills){
            if (lSkill.getId() == pIdSkill) {
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


    @JsonIgnore
    public List<CConsumableEntity> getMunitions(){
        List<CConsumableEntity> lMunitions= new ArrayList<CConsumableEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CConsumableEntity){
                if (lObject.getName().equals("Attack")){
                    lMunitions.add((CConsumableEntity)lObject);
                }
            }
        }
        return lMunitions;
    }

    @JsonIgnore
    public List<CConsumableEntity> getMunitionsByRarity(int pRarity){
        List<CConsumableEntity> lMunitions = new ArrayList<CConsumableEntity>();
        for (CConsumableEntity lMunition : getMunitions()) {
            if (lMunition.getRarity()==pRarity){
                lMunitions.add(lMunition);
            }
        }
        return lMunitions;
    }

    @JsonIgnore
    public Set<Integer> getRaritiesOfMunition(){
        Set<Integer> lRarities = new HashSet<Integer>();
        for (CConsumableEntity lMunition : getMunitions()) {
            lRarities.add(lMunition.getRarity());
        }
        return lRarities;
    }

    @JsonIgnore
    public List<CTurretEntity> getTurretsByLevel(int pLevel){
        List<CTurretEntity> lTurrets = new ArrayList<CTurretEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CTurretEntity){
                if (((CTurretEntity) lObject).getLevel() == pLevel){
                    lTurrets.add((CTurretEntity) lObject);
                }
            }
        }
        return lTurrets;
    }


    @JsonIgnore
    public List<CTurretEntity> getTurrets(){
        List<CTurretEntity> lTurrets = new ArrayList<CTurretEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CTurretEntity){
                lTurrets.add((CTurretEntity)lObject);
            }
        }
        return lTurrets;
    }


    @JsonIgnore
    public Set<Integer> getLevelsOfTurrets(){
        Set<Integer> lLevels = new HashSet<Integer>();
        for (CTurretEntity lTurret : getTurrets()) {
            lLevels.add(lTurret.getLevel());
        }
        return lLevels;
    }

    @JsonIgnore
    public List<CMultiHackEntity> getMultiHacksByLevel(int pLevel){
        List<CMultiHackEntity> lMultiHacks = new ArrayList<CMultiHackEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CMultiHackEntity){
                if (((CMultiHackEntity) lObject).getLevel() == pLevel){
                    lMultiHacks.add((CMultiHackEntity) lObject);
                }
            }
        }
        return lMultiHacks;
    }


    @JsonIgnore
    public List<CMultiHackEntity> getMultiHacks(){
        List<CMultiHackEntity> lMultyHacks = new ArrayList<CMultiHackEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CMultiHackEntity){
                lMultyHacks.add((CMultiHackEntity)lObject);
            }
        }
        return lMultyHacks;
    }


    @JsonIgnore
    public Set<Integer> getLevelsOfMultiHacks(){
        Set<Integer> lLevels = new HashSet<Integer>();
        for (CMultiHackEntity lMultiHack : getMultiHacks()) {
            lLevels.add(lMultiHack.getLevel());
        }
        return lLevels;
    }


    @JsonIgnore
    public List<CShieldEntity> getShieldsByLevel(int pLevel){
        List<CShieldEntity> lShields = new ArrayList<CShieldEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CShieldEntity){
                if (((CShieldEntity) lObject).getLevel() == pLevel){
                    lShields.add((CShieldEntity) lObject);
                }
            }
        }
        return lShields;
    }


    @JsonIgnore
    public List<CShieldEntity> getShields(){
        List<CShieldEntity> lShields = new ArrayList<CShieldEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CShieldEntity){
                lShields.add((CShieldEntity)lObject);
            }
        }
        return lShields;
    }


    @JsonIgnore
    public Set<Integer> getLevelsOfShields(){
        Set<Integer> lLevels = new HashSet<Integer>();
        for (CShieldEntity lShield : getShields()) {
            lLevels.add(lShield.getLevel());
        }
        return lLevels;
    }


    @JsonIgnore
    public List<CLinkImprovementEntity> getLinkImprovementsByLevel(int pLevel){
        List<CLinkImprovementEntity> lLinkImprovements = new ArrayList<CLinkImprovementEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CLinkImprovementEntity){
                if (((CLinkImprovementEntity) lObject).getLevel() == pLevel){
                    lLinkImprovements.add((CLinkImprovementEntity) lObject);
                }
            }
        }
        return lLinkImprovements;
    }


    @JsonIgnore
    public List<CLinkImprovementEntity> getLinkImprovements(){
        List<CLinkImprovementEntity> lLinkImprovements = new ArrayList<CLinkImprovementEntity>();
        for (AObjectEntity lObject : mObjects){
            if (lObject instanceof CLinkImprovementEntity){
                lLinkImprovements.add((CLinkImprovementEntity)lObject);
            }
        }
        return lLinkImprovements;
    }


    @JsonIgnore
    public Set<Integer> getLevelsOfLinkImprovements(){
        Set<Integer> lLevels = new HashSet<Integer>();
        for (CLinkImprovementEntity lLinkImprovement : getLinkImprovements()) {
            lLevels.add(lLinkImprovement.getLevel());
        }
        return lLevels;
    }


    @JsonIgnore
    public int getProgressOfLevel (){
        int lProgress = 0;
        int lXp = getXp();

        switch (getLevel()){
            case 1 :
                lProgress =  (XP_LVL_2/lXp) *100;
                break;

            case 2 :
                lProgress =  (XP_LVL_3/lXp) *100;
                break;

            case 3 :
                lProgress =  (XP_LVL_4/lXp) *100;

                break;

            case 4 :
                lProgress =  (XP_LVL_5/lXp) *100;

                break;

            case 5 :
                lProgress =  (XP_LVL_6/lXp) *100;

                break;

            case 6 :
                lProgress =  (XP_LVL_7/lXp) *100;

                break;

            case 7 :
                lProgress =  (XP_LVL_8/lXp) *100;

                break;

            case 8 :
                lProgress =100;
                break;
        }

        return lProgress;

    }
}