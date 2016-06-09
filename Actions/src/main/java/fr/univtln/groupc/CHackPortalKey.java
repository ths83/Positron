package fr.univtln.groupc;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.io.Serializable;

/**
 * Created by xdurbec066 on 02/06/16.
 */
public class CHackPortalKey implements Serializable{
    public int mPortalId;
    public int mPlayerId;

    CHackPortalKey(){}

    public CHackPortalKey(CHackPortalKeyBuilder pBuilder){
        mPlayerId = pBuilder.mPlayerId;
        mPortalId = pBuilder.mPortalId;
    }

    public int getPortalId() {
        return mPortalId;
    }

    public void setPortalId(int mPortalId) {
        this.mPortalId = mPortalId;
    }

    public int getPlayerId() {
        return mPlayerId;
    }

    public void setPlayerId(int mPlayerId) {
        this.mPlayerId = mPlayerId;
    }

    public static class CHackPortalKeyBuilder{
        private int mPortalId;
        private int mPlayerId;

        public CHackPortalKeyBuilder(){}

        public CHackPortalKeyBuilder portal (int pPortal){
            mPortalId = pPortal;
            return this;
        }
        public CHackPortalKeyBuilder player (int pPlayer){
            mPlayerId = pPlayer;
            return this;
        }

        public CHackPortalKey builder (){
            return  new CHackPortalKey(this);
        }
    }
}
