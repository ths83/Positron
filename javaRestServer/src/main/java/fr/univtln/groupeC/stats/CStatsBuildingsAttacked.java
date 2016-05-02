package fr.univtln.groupeC.stats;

import fr.univtln.groupeC.entities.ABuildingEntity;
import fr.univtln.groupeC.entities.CPlayerEntity;

import javax.persistence.Entity;

/**
 * Created by arouani277 on 02/05/16.
 */

public class CStatsBuildingsAttacked extends AStatsAttacked {

    private ABuildingEntity mBuilding;

    public CStatsBuildingsAttacked(CStatsBuildingsAttackedBuilder pBuilder) {
        super(pBuilder.mAttacker, pBuilder.mOwner, pBuilder.mCpt, pBuilder.mId);
        mBuilding = pBuilder.mBuilding;
    }

    public static class CStatsBuildingsAttackedBuilder {
        private int mId;
        private ABuildingEntity mBuilding;
        private CPlayerEntity mOwner;
        private int mCpt;
        private CPlayerEntity mAttacker;


        public CStatsBuildingsAttackedBuilder(int pId) {
            mId = pId;
        }

        public CStatsBuildingsAttackedBuilder building(ABuildingEntity pBuilding) {
            mBuilding = pBuilding;
            return this;
        }

        public CStatsBuildingsAttackedBuilder owner(CPlayerEntity pOwner) {
            mOwner = pOwner;
            return this;
        }

        public CStatsBuildingsAttackedBuilder cpt(int pCpt) {
            mCpt = pCpt;
            return this;
        }

        public CStatsBuildingsAttackedBuilder attacker(CPlayerEntity pAttacker) {
            mAttacker = pAttacker;
            return this;
        }

        public CStatsBuildingsAttacked build() {
            return new CStatsBuildingsAttacked(this);
        }
    }


    }
