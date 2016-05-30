package fr.univtln.groupc.stats;


import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

import javax.persistence.*;

/**
 * Created by arouani277 on 02/05/16.
 */
@Entity
@Table(name = "t_stats_building_attacked", schema = "positron")
@NamedQueries(@NamedQuery(name = CStatsResonatorAttacked.GET_ALL, query = "select p from CStatsResonatorAttacked p"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class CStatsResonatorAttacked extends AStatsAttacked{

    @ManyToOne
    private CResonatorEntity mResonator;

    public final static String GET_ALL = "StatsResonatorAttacked.getAll";

    public CStatsResonatorAttacked(CStatsResonatorAttackedBuilder pBuilder){
        super(pBuilder.mAttacker,pBuilder.mOwner, pBuilder.mCpt, pBuilder.mId);
        mResonator = pBuilder.mResonator;
    }

    public CStatsResonatorAttacked() {
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

    public CResonatorEntity getmResonator() {
        return mResonator;
    }

    public void setmResonator(CResonatorEntity mResonator) {
        this.mResonator = mResonator;
    }
}