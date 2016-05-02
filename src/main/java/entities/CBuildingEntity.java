package entities;

import javax.persistence.*;

/**
 * Created by arouani277 on 25/04/16.
 */
@Entity
@Table(name = "t_building")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CBuildingEntity extends AObjectEntity {
    @Column(name = "long")
    private float mLong;
    @Column(name = "lat")
    private float mLat;
    @Column(name = "lifetime")
    private int mLifeTime;
    @Column(name = "radius")
    private int mRadius;
    @Column(name = "level")
    private int mLevel;
    @Column(name = "energy_max")
    private int mEnergyMax;
    @Column(name = "energy")
    private int mEnergy;

    public CBuildingEntity(CBuildingBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName);
        mLong = pBuilder.mLong;
        mLat = pBuilder.mLat;
        mLifeTime = pBuilder.mLifeTime;
        mRadius = pBuilder.mRadius;
        mLevel = pBuilder.mLevel;
        mEnergy = pBuilder.mEnergy;
        mEnergyMax = pBuilder.mEnergyMax;
    }

    public static class CBuildingBuilder{
        private int mId;
        private String mName;
        private float mLong;
        private float mLat;
        private int mLifeTime;
        private int mRadius;
        private int mLevel;
        private int mEnergyMax;
        private int mEnergy;


        public CBuildingBuilder (int pID) {
            mId = pID;
        }

        public CBuildingBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CBuildingBuilder longitude(float pLong) {
            mLong = pLong;
            return this;
        }

        public CBuildingBuilder lat(float pLat) {
            mLat = pLat;
            return this;
        }

        public CBuildingBuilder lifetime(int pLifeTime) {
            mLifeTime = pLifeTime;
            return this;
        }

        public CBuildingBuilder radius(int pRadius) {
            mRadius = pRadius;
            return this;
        }

        public CBuildingBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CBuildingBuilder energy(int pEnergy){
            mEnergy = pEnergy;
            return this;
        }

        public CBuildingBuilder energyMax(int pEnergyMax){
            mEnergyMax = pEnergyMax;
            return this;
        }


        public CBuildingEntity build() {
            return new CBuildingEntity(this);
        }

    }

    public float getLong() {
        return mLong;
    }

    public void setLong(float pLong) {
        mLong = pLong;
    }

    public float getLat() {
        return mLat;
    }

    public void setLat(float pLat) {
        mLat = pLat;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int pRadius) {
        mRadius = pRadius;
    }

    @Override
    public String toString() {
        return "CBuildingEntity{" +
                "mLong=" + mLong +
                ", mLat=" + mLat +
                ", mLifeTime=" + mLifeTime +
                ", mRadius=" + mRadius +
                ", mLevel=" + mLevel +
                ", mEnergyMax=" + mEnergyMax +
                ", mEnergy=" + mEnergy +
                '}';
    }
}
