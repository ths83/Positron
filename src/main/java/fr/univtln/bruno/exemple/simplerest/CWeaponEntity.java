package fr.univtln.bruno.exemple.simplerest;

/**
 * Created by arouani277 on 25/04/16.
 */
public class CWeaponEntity extends CObjectEntity{

    private int mDamage;

    public CWeaponEntity(CWeaponBuilder pBuilder){
        super(pBuilder.mID, pBuilder.mName, pBuilder.mLevel);
        mDamage = pBuilder.mDamage;
    }

    public static class CWeaponBuilder{
        private int mDamage;
        private int mID;
        private String mName;
        private int mLevel;

        public CWeaponBuilder id(int pID) {
            mID = pID;
            return this;
        }

        public CWeaponBuilder damage(int pDamage) {
            mDamage = pDamage;
            return this;
        }

        public CWeaponBuilder name(String pName) {
            mName = pName;
            return this;
        }

        public CWeaponBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CWeaponEntity build() {
            return new CWeaponEntity(this);
        }

    }

    public int getmDamage() {
        return mDamage;
    }

    public void setmDamage(int mDamage) {
        this.mDamage = mDamage;
    }

    @Override
    public String toString() {

        return "CWeaponEntity{" +
                "mDamage=" + mDamage +
                '}' + super.toString() ;
    }
}
