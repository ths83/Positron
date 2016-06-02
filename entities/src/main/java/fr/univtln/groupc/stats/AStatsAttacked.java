package fr.univtln.groupc.stats;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.univtln.groupc.entities.CPlayerEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by arouani277 on 02/05/16.
 */
@Entity
@Table(name = "t_stats_attacked", schema = "positron")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")

@JsonSubTypes({@JsonSubTypes.Type(value = CStatsBuildingsAttacked.class, name = "CStatsBuildingsAttacked"),
        @JsonSubTypes.Type(value = CStatsResonatorAttacked.class, name = "CStatsResonatorAttacked")})

public class AStatsAttacked implements Serializable {

    @ManyToOne
    @JoinColumn(name = "owner")
    private CPlayerEntity mOwner;

    @ManyToOne
    @JoinColumn(name = "attacker")
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

    public CPlayerEntity getmOwner() {
        return mOwner;
    }

    public void setmOwner(CPlayerEntity mOwner) {
        this.mOwner = mOwner;
    }

    public CPlayerEntity getAttacker() {
        return mAttacker;
    }

    public void setAttacker(CPlayerEntity pAttacker) {
        mAttacker = pAttacker;
    }

    public int getCpt() {
        return mCpt;
    }

    public void setCpt(int pCpt) {
        mCpt = pCpt;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }
}