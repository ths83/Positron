package fr.univtln.groupc.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nmartinez016 on 25/04/16.
 */

@Entity
@Table(name = "t_skill", schema = "positron")
@NamedQueries(@NamedQuery(name = CSkillEntity.GET_ALL, query = "select p from CSkillEntity p"))
public class CSkillEntity implements Serializable{
    @Id
    @Column(name = "id")
    private int mId;
    @Column(name = "name")
    private String mName;
    @Column(name = "level")
    private int mLevel;
    @Column(name = "cost")
    private int mCost;

    public final static String GET_ALL = "Skill.getAll";

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

    public static String getGetAll() {
        return GET_ALL;
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
        private int mId;
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