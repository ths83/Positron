package fr.univtln.groupc.entities;

import java.io.Serializable;

/**
 * Created by mpesnel786 on 09/05/16.
 */
public class CSkillEntity implements Serializable{
    private int mId;
    private String mName;
    private int mLevel;
    private int mCost;

    public CSkillEntity(){}

    public CSkillEntity(CSkillBuilder pBuilder){
        mId = pBuilder.mId;
        mName = pBuilder.mName;
        mLevel = pBuilder.mLevel;
        mCost = pBuilder.mCost;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int pLevel) {
        mLevel = pLevel;
    }

    public int getCost() {
        return mCost;
    }

    public void setCost(int pCost) {
        mCost = pCost;
    }

    @Override
    public String toString() {
        return "CSkillEntity{" +
                "mId=" + mId +
                ", mName ='" + mName + '\'' +
                ", mLevel=" + mLevel +
                ", mCost=" + mCost +
                '}';
    }

    public static class CSkillBuilder{
        private final int mId;
        private String mName;
        private int mLevel;
        private int mCost;

        public CSkillBuilder(int pId){
            mId = pId;
        }

        public CSkillBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CSkillBuilder level(int pLevel){
            mLevel = pLevel;
            return this;
        }

        public CSkillBuilder cost(int pCost){
            mCost = pCost;
            return this;
        }

        public CSkillEntity build(){
            return new CSkillEntity(this);
        }
    }
}
