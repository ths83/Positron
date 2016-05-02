package entities;

import javax.persistence.Entity;

/**
 * Created by arouani277 on 25/04/16.
 */
@Entity
public class CBuildingEntity extends CObjectEntity {
    private float mLong;
    private float mLat;
    private int mTimeLife;
    private int mRadius;
    private int mDamage;
    private int mHeal;

    public CBuildingEntity(CBuildingBuilder pBuilder){
        super(pBuilder.mID, pBuilder.mName, pBuilder.mLevel);
        mLong = pBuilder.mLong;
        mLat = pBuilder.mLat;

        mDamage = pBuilder.mDamage;
    }

    public static class CBuildingBuilder{
        private float mLong;
        private float mLat;
        private int mTimeLife;
        private int mRadius;
        private int mDamage;
        private int mHeal;
        private int mID;
        private String mName;
        private int mLevel;

        public CBuildingBuilder id(int pID) {
            mID = pID;
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

        public CBuildingBuilder timelife(int pTimeLife) {
            mTimeLife = pTimeLife;
            return this;
        }

        public CBuildingBuilder radius(int pRadius) {
            mRadius = pRadius;
            return this;
        }

        public CBuildingBuilder damage(int pDamage) {
            mDamage = pDamage;
            return this;
        }

        public CBuildingBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CBuildingBuilder name(String pName) {
            mName = pName;
            return this;
        }

        public CBuildingBuilder heal(int pHeal) {
            mHeal = pHeal;
            return this;
        }

        public CBuildingEntity build() {
            return new CBuildingEntity(this);
        }



    }

    public float getmLong() {
        return mLong;
    }

    public void setmLong(float mLong) {
        this.mLong = mLong;
    }

    public float getmLat() {
        return mLat;
    }

    public void setmLat(float mLat) {
        this.mLat = mLat;
    }

    public int getmRadius() {
        return mRadius;
    }

    public void setmRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public int getmTimeLife() {
        return mTimeLife;
    }

    public void setmTimeLife(int mTimeLife) {
        this.mTimeLife = mTimeLife;
    }

    public int getmDamage() {
        return mDamage;
    }

    public void setmDamage(int mDamage) {
        this.mDamage = mDamage;
    }

    public int getmHeal() {
        return mHeal;
    }

    public void setmHeal(int mHeal) {
        this.mHeal = mHeal;
    }

    @Override
    public String toString() {
        return "CBuildingEntity{" +
                "mLong=" + mLong +
                ", mLat=" + mLat +
                ", mTimeLife=" + mTimeLife +
                ", mRadius=" + mRadius +
                ", mDamage=" + mDamage +
                ", mHeal=" + mHeal +
                '}' + super.toString();
    }
}
