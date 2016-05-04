package fr.univtln.groupc.stats;

import fr.univtln.groupc.entities.CPlayerEntity;

import javax.persistence.*;

/**
 * Created by arouani277 on 02/05/16.
 */
@Entity
@Table(name = "t_stats_attacked", schema = "positron")
public class AStatsAttacked {

    @ManyToOne
    @Column(name = "owner")
    private CPlayerEntity mOwner;

    @ManyToOne
    @Column(name = "attacker")
    private CPlayerEntity mAttacker;

    @Id
    private int mId;

    private int mCpt;

    public AStatsAttacked(CPlayerEntity pAttacker, CPlayerEntity pOwner, int pCpt, int pId) {
        mAttacker = pAttacker;
        mOwner = pOwner;
        mCpt = pCpt;
        mId = pId;
    }

    public AStatsAttacked() {
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