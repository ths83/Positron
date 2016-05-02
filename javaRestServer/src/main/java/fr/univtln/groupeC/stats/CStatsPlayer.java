package fr.univtln.groupeC.stats;

import fr.univtln.groupeC.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 02/05/16.
 */
public class CStatsPlayer {
    private CPlayerEntity mPlayer;
    private List<CConsumableEntity> mConsumablesUsed = new ArrayList<CConsumableEntity>();

    private List<ABuildingEntity> mBuildingsUsed = new ArrayList<ABuildingEntity>();
    private List<ABuildingEntity> mBuildingsDestroyed = new ArrayList<ABuildingEntity>();

    private List<CResonatorEntity> mResonatorsDestroyed  = new ArrayList<CResonatorEntity>();
    private List<CResonatorEntity> mResonatorsBuilt  = new ArrayList<CResonatorEntity>();

    private List<CKeyEntity> mKeysUSed  = new ArrayList<CKeyEntity>();

    private int mID;

    public CStatsPlayer(CStatsPlayerBuilder pBuilder){
        mID = pBuilder.mId;

        mPlayer = pBuilder.mPlayer;
        mConsumablesUsed = pBuilder.mConsumablesUsed;

        mBuildingsUsed = pBuilder.mBuildingsUsed;
        mBuildingsDestroyed = pBuilder.mBuildingsDestroyed;

        mResonatorsBuilt = pBuilder.mResonatorsBuilt;
        mResonatorsDestroyed = pBuilder.mResonatorsDestroyed;

        mKeysUSed = pBuilder.mKeysUSed;


    }

    public static class CStatsPlayerBuilder{

        private int mId;
        private List<CConsumableEntity> mConsumablesUsed = new ArrayList<CConsumableEntity>();

        private List<ABuildingEntity> mBuildingsUsed = new ArrayList<ABuildingEntity>();
        private List<ABuildingEntity> mBuildingsDestroyed = new ArrayList<ABuildingEntity>();

        private List<CResonatorEntity> mResonatorsDestroyed  = new ArrayList<CResonatorEntity>();
        private List<CResonatorEntity> mResonatorsBuilt  = new ArrayList<CResonatorEntity>();

        private List<CKeyEntity> mKeysUSed  = new ArrayList<CKeyEntity>();

        private CPlayerEntity mPlayer;


        public CStatsPlayerBuilder id(int pId){
            mId = pId;
            return this;
        }

        public CStatsPlayerBuilder consumablesUsed(List<CConsumableEntity> pConsumablesUSed){
            mConsumablesUsed = pConsumablesUSed;
            return this;
        }

        public CStatsPlayerBuilder buildingsUsed(List<ABuildingEntity> pBuildingsUsed){
            mBuildingsUsed = pBuildingsUsed;
            return this;
        }

        public CStatsPlayerBuilder buildingsDestroyed(List<ABuildingEntity> pBuildingsDestroyed){
            mBuildingsDestroyed = pBuildingsDestroyed;
            return this;
        }

        public CStatsPlayerBuilder resonatorsBuilt(List<CResonatorEntity> pResonatorsBuilt){
            mResonatorsBuilt = pResonatorsBuilt;
            return this;
        }

        public CStatsPlayerBuilder resonatorsDestroyed(List<CResonatorEntity> pResonatorsDestroyed){
            mResonatorsDestroyed = pResonatorsDestroyed;
            return this;
        }

        public CStatsPlayerBuilder keysUsed(List<CKeyEntity> pKeysUSed ){
            mKeysUSed = pKeysUSed;
            return this;
        }

        public CStatsPlayerBuilder player(CPlayerEntity pPlayer ){
            mPlayer = pPlayer;
            return this;
        }

        public CStatsPlayer build(){
            return new CStatsPlayer(this);
        }


    }

    public List<CConsumableEntity> getmConsumablesUsed() {
        return mConsumablesUsed;
    }

    public void setmConsumablesUsed(List<CConsumableEntity> mConsumablesUsed) {
        this.mConsumablesUsed = mConsumablesUsed;
    }

    public CPlayerEntity getmPlayer() {
        return mPlayer;
    }

    public void setmPlayer(CPlayerEntity mPlayer) {
        this.mPlayer = mPlayer;
    }

    public List<ABuildingEntity> getmBuildingsUsed() {
        return mBuildingsUsed;
    }

    public void setmBuildingsUsed(List<ABuildingEntity> mBuildingsUsed) {
        this.mBuildingsUsed = mBuildingsUsed;
    }

    public List<ABuildingEntity> getmBuildingsDestroyed() {
        return mBuildingsDestroyed;
    }

    public void setmBuildingsDestroyed(List<ABuildingEntity> mBuildingsDestroyed) {
        this.mBuildingsDestroyed = mBuildingsDestroyed;
    }

    public List<CResonatorEntity> getmResonatorsDestroyed() {
        return mResonatorsDestroyed;
    }

    public void setmResonatorsDestroyed(List<CResonatorEntity> mResonatorsDestroyed) {
        this.mResonatorsDestroyed = mResonatorsDestroyed;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public List<CResonatorEntity> getmResonatorsBuilt() {
        return mResonatorsBuilt;
    }

    public void setmResonatorsBuilt(List<CResonatorEntity> mResonatorsBuilt) {
        this.mResonatorsBuilt = mResonatorsBuilt;
    }

    public List<CKeyEntity> getmKeysUSed() {
        return mKeysUSed;
    }

    public void setmKeysUSed(List<CKeyEntity> mKeysUSed) {
        this.mKeysUSed = mKeysUSed;
    }
}