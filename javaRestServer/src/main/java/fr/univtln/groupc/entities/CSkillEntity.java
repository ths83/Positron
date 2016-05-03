package fr.univtln.groupc.entities;

import javax.persistence.*;

/**
 * Created by nmartinez016 on 25/04/16.
 */

@Entity
@Table(name = "t_skill", schema = "positron")
@NamedQueries(@NamedQuery(name = CSkillEntity.GET_ALL, query = "select p from CSkillEntity p"))
public class CSkillEntity {
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