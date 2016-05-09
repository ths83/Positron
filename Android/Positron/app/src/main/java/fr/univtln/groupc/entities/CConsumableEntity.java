package fr.univtln.groupc.entities;

/**
 * Created by marti on 09/05/2016.
 */


public class CConsumableEntity extends AObjectEntity {
    private int mRarity;
    public final static String GET_ALL = "consumable.getAll";

    public CConsumableEntity(){
        super();
    }

    public CConsumableEntity(CConsumableBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName);
        mRarity = pBuilder.mRarity;

    }

    public int getRarity() {
        return mRarity;
    }

    public void setRarity(int pRarity) {
        mRarity = pRarity;
    }

    @Override
    public String toString() {
        return super.toString() + "CConsumableEntity{" +
                "mRarity=" + mRarity +
                '}';
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