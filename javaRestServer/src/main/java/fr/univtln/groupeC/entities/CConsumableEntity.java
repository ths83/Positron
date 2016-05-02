package fr.univtln.groupeC.entities;

import javax.persistence.*;

/**
 * Created by marti on 02/05/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "t_consumbale", schema = "project")
public class CConsumableEntity extends AObjectEntity {
    @Column(name = "rarity")
    private int mRarity;

    public CConsumableEntity(CConsumableBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName);
        mRarity = pBuilder.mRarity;

    }

    public static class CConsumableBuilder{
        private int mId;
        private String mName;
        private int mRarity;

        public CConsumableBuilder(int pId){
            mId = pId;
        }

        public CConsumableBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CConsumableBuilder rarity(int pRarity){
            mRarity = pRarity;
            return this;
        }

        public CConsumableEntity build(){
            return new CConsumableEntity(this);
        }
    }
}