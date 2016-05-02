package entities;

/**
 * Created by arouani277 on 26/04/16.
 */
public class CResonatorEntity extends AObjectEntity {
    private int mEnergy;

    public CResonatorEntity(CResonatorBuilder pBuilder){
        super(pBuilder.mID, pBuilder.mName, pBuilder.mLevel);
        mEnergy = pBuilder.mEnergy;
    }

    @Override
    public String toString() {
        return super.toString() + " CResonatorEntity{" +
                "mPortal=" + mPortal +
                ", mOwner=" + mOwner +
                '}';
    }

    public static class CResonatorBuilder{
        private int mEnergy;
        private int mID;
        private String mName;
        private int mLevel;

        public CResonatorBuilder id(int pId){
            mID = pId;
            return this;
        }

        public CResonatorBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CResonatorBuilder level(int pLevel) {
            mLevel = pLevel;
            return this;
        }

        public CResonatorBuilder energy(int pEnergy) {
            mEnergy = pEnergy;
            return this;
        }

        public CResonatorEntity build() {
            return new CResonatorEntity(this);
        }

    }

    public int getmEnergy() {
        return mEnergy;
    }

    public void setmEnergy(int mEnergy) {
        this.mEnergy = mEnergy;
    }

    @Override
    public String toString() {
        return "CResonatorEntity{" +
                "mEnergy=" + mEnergy +
                '}' + super.toString();
    }
}
