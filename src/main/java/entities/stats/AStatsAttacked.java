package entities.stats;

import entities.CPlayerEntity;
import entities.CResonatorEntity;

/**
 * Created by arouani277 on 02/05/16.
 */
public class AStatsAttacked {
    private CPlayerEntity mOwner;
    private int mCpt;
    private CPlayerEntity mAttacker;
    private int mId;

    public AStatsAttacked(CPlayerEntity pAttacker, CPlayerEntity pOwner, int pCpt, int pId) {
        mAttacker = pAttacker;
        mOwner = pOwner;
        mCpt = pCpt;
        mId = pId;
    }

    @Override
    public String toString() {
        return "AStatsAttacked{" +
                "mOwner=" + mOwner +
                ", cpt=" + mCpt +
                ", mAttacker=" + mAttacker +
                ", Id=" + mId +
                '}';
    }
}
