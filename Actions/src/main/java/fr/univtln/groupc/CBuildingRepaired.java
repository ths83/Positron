package fr.univtln.groupc;


import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 09/06/16.
 */
public class CBuildingRepaired implements Serializable {



        CPlayerEntity mPlayer;
        ABuildingEntity mBuilding;


    public CPlayerEntity getPlayer() {
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer) {
        mPlayer = pPlayer;
    }

    public ABuildingEntity getBuilding() {
        return mBuilding;
    }

    public void setBuilding(ABuildingEntity pBuilding) {
        mBuilding = pBuilding;
    }

    public CBuildingRepaired(){}

        public CBuildingRepaired( CBuildingRepairedBuidler pBuilder){
            mPlayer  = pBuilder.mPlayer;
            mBuilding = pBuilder.mBuilding;
        }


        public static class CBuildingRepairedBuidler{
            CPlayerEntity mPlayer;
            ABuildingEntity mBuilding;

            public CBuildingRepairedBuidler () {}

            public CBuildingRepairedBuidler player ( CPlayerEntity pPlayerId ){
                mPlayer = pPlayerId;
                return this;
            }
            public CBuildingRepairedBuidler building ( ABuildingEntity pBuildingId){
                mBuilding = pBuildingId;
                return this;
            }

            public CBuildingRepaired build(){return new CBuildingRepaired(this);}

        }
    }


