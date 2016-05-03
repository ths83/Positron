package fr.univtln.groupec.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by nmartinez016 on 25/04/16.
 */

@Entity
@Table(name = "t_skill", schema = "positron")
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

    public CSkillEntity(){}

    public CSkillEntity(CSkillBuilder pBuilder){
        mId = pBuilder.mId;
        mName = pBuilder.mName;
        mLevel = pBuilder.mLevel;
        mCost = pBuilder.mCost;
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