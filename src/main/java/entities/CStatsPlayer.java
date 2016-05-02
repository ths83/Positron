package entities;

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

    public static class CStatsPlayerBuilder{
        private int mId;
        private List<CConsumableEntity> mConsumablesUsed = new ArrayList<CConsumableEntity>();

        private List<ABuildingEntity> mBuildingsUsed = new ArrayList<ABuildingEntity>();
        private List<ABuildingEntity> mBuildingsDestroyed = new ArrayList<ABuildingEntity>();

        private List<CResonatorEntity> mResonatorsDestroyed  = new ArrayList<CResonatorEntity>();
        private List<CResonatorEntity> mResonatorsBuilt  = new ArrayList<CResonatorEntity>();

        private List<CKeyEntity> mKeysUSed  = new ArrayList<CKeyEntity>();

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

    }


}
