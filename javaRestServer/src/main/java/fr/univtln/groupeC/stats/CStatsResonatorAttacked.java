package fr.univtln.groupeC.stats;

import fr.univtln.groupeC.entities.CPlayerEntity;
import fr.univtln.groupeC.entities.CResonatorEntity;

/**
 * Created by arouani277 on 02/05/16.
 */
public class CStatsResonatorAttacked extends AStatsAttacked{
    private CResonatorEntity mResonator;

    public CStatsResonatorAttacked(CStatsResonatorAttackedBuilder pBuilder){
        super(pBuilder.mAttacker,pBuilder.mOwner, pBuilder.mCpt, pBuilder.mId);
        mResonator = pBuilder.mResonator;
    }

    public static class CStatsResonatorAttackedBuilder {
        private int mId;
        private CResonatorEntity mResonator;
        private CPlayerEntity mOwner;
        private int mCpt;
        private CPlayerEntity mAttacker;


        public CStatsResonatorAttackedBuilder(int pId) {
            mId = pId;
        }

        public CStatsResonatorAttackedBuilder resonator(CResonatorEntity pResonator) {
            mResonator = pResonator;
            return this;
        }

        public CStatsResonatorAttackedBuilder owner(CPlayerEntity pOwner) {
            mOwner = pOwner;
            return this;
        }

        public CStatsResonatorAttackedBuilder cpt(int pCpt) {
            mCpt = pCpt;
            return this;
        }

        public CStatsResonatorAttackedBuilder attacker(CPlayerEntity pAttacker) {
            mAttacker = pAttacker;
            return this;
        }

        public CStatsResonatorAttacked build(){
            return new CStatsResonatorAttacked(this);
        }


    }
}