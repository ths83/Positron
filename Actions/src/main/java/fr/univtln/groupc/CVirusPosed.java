package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tperron710 on 02/06/16.
 */
public class CVirusPosed implements Serializable {

    private List<Integer> mIdsToRemove;
    private CPlayerEntity mPlayer;

    public CVirusPosed() {
    }

    public CVirusPosed(CVirusPosedBuilder pBuilder) {

        mIdsToRemove = pBuilder.mIdsToRemove;
        mPlayer = pBuilder.mPlayer;
    }

    public List<Integer> getIdsToRemove() {
        return mIdsToRemove;
    }

    public void setIdsToRemove(List<Integer> pIdsToRemove) {
        mIdsToRemove = pIdsToRemove;
    }

    public CPlayerEntity getPlayer(){
        return mPlayer;
    }

    public void setPlayer(CPlayerEntity pPlayer){
        mPlayer = pPlayer;
    }


    public static class CVirusPosedBuilder{
        private CPlayerEntity mPlayer;
        private List<Integer> mIdsToRemove;

        public CVirusPosedBuilder(){}

        public CVirusPosedBuilder player(CPlayerEntity  pPlayer){
            mPlayer = pPlayer;
            return this;
        }

        public CVirusPosedBuilder idsToRemove(List<Integer> pIdsToRemove){
            mIdsToRemove = pIdsToRemove;
            return this;
        }

        public CVirusPosed build(){
            return new CVirusPosed(this);
        }
    }
}
