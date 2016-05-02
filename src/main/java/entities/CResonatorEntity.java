package entities;

import javax.persistence.*;

/**
 * Created by arouani277 on 26/04/16.
 */

@Entity
@Table(name = "t_resonator", schema = "project")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CResonatorEntity extends ABuildingEntity {
    @Column(name = "portal_fk")
    private CPortalEntity mPortal;
    @Column(name = "player_fk")
    private CPlayerEntity mOwner;


    public CResonatorEntity(CResonatorBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName, pBuilder.mLong, pBuilder.mLat, pBuilder.mLifeTime, pBuilder.mRadius, pBuilder.mLevel, pBuilder.mEnergy, pBuilder.mEnergyMax);
        mPortal = pBuilder.mPortal;
        mOwner = pBuilder.mOwner;
    }

    @Override
    public String toString() {
        return super.toString() + " CResonatorEntity{" +
                "mPortal=" + mPortal +
                ", mOwner=" + mOwner +
                '}';
    }

    public static class CResonatorBuilder{
        private int mId;
        private String mName;
        private float mLong;
        private float mLat;
        private int mLifeTime;
        private int mRadius;
        private int mLevel;
        private int mEnergyMax;
        private int mEnergy;
        private CPortalEntity mPortal;
        private CPlayerEntity mOwner;

        public CResonatorBuilder(int pId){
            mId = pId;
        }

        public CResonatorBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CResonatorBuilder longitude(int pLongitude){
            mLong = pLongitude;
            return this;
        }

        public CResonatorBuilder latitude(int pLatitude){
            mLat = pLatitude;
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


}
