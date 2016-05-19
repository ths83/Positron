package fr.univtln.groupc.stats;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import javax.persistence.*;

/**
 * Created by arouani277 on 02/05/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "t_stats_building_attacked", schema = "positron")
@NamedQueries(@NamedQuery(name = CStatsBuildingsAttacked.GET_ALL, query = "select c from CStatsBuildingsAttacked c"))

public class CStatsBuildingsAttacked extends AStatsAttacked {

    @ManyToOne
    @JoinColumn(name = "building_fk")
    private ABuildingEntity mBuilding;

    public final static String GET_ALL = "StatsBuildingAttacked.getAll";

    public CStatsBuildingsAttacked(CStatsBuildingsAttackedBuilder pBuilder) {
        super(pBuilder.mAttacker, pBuilder.mOwner, pBuilder.mCpt, pBuilder.mId);
        mBuilding = pBuilder.mBuilding;
    }

    public CStatsBuildingsAttacked() {
        super();
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

    public ABuildingEntity getmBuilding() {
        return mBuilding;
    }

    public void setmBuilding(ABuildingEntity mBuilding) {
        this.mBuilding = mBuilding;
    }
}
