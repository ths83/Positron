package fr.univtln.groupc;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

/**
 * Created by xdurbec066 on 02/06/16.
 */
public class CBuildingAttacked {

    private CPlayerEntity mPlayer;
    private ABuildingEntity mBuilding;

    public CPlayerEntity getPlayer() {
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer) {
        this.mPlayer = pPlayer;
    }

    public ABuildingEntity getBuilding() {
        return mBuilding;
    }

    public void setBuilding(ABuildingEntity pBuilding) {
        this.mBuilding = pBuilding;
    }
    public CBuildingAttacked(CBuildingAttackedBuilder pBuildingAttacked){
        mBuilding = pBuildingAttacked.mBuilding;
        mPlayer = pBuildingAttacked.mPlayer;
    }

    public static class CBuildingAttackedBuilder{
        CPlayerEntity mPlayer;
        ABuildingEntity mBuilding;

        public CBuildingAttackedBuilder(){}

        public CBuildingAttackedBuilder player (CPlayerEntity pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CBuildingAttackedBuilder building (ABuildingEntity pBuilding){
            mBuilding = pBuilding;
            return this;
        }
        public CBuildingAttacked build(){
            return new CBuildingAttacked(this);
        }

    }
}
