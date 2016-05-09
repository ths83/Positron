package fr.univtln.groupc.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */

public class CTeamEntity implements Serializable {

    private int mId;

    private List<CPlayerEntity> mPlayers = new ArrayList<>();

    private List<CPortalEntity> mPortals = new ArrayList<>();

    private String mColor;

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public List<CPlayerEntity> getPlayers() {
        return mPlayers;
    }

    public void setPlayers(List<CPlayerEntity> pPlayers) {
        mPlayers = pPlayers;
    }

    public List<CPortalEntity> getPortals() {
        return mPortals;
    }

    public void setPortals(List<CPortalEntity> pPortals) {
        mPortals = pPortals;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String pColor) {
        mColor = pColor;
    }





    public final static String GET_ALL = "Team.getAll";

    public CTeamEntity(){}

    public CTeamEntity(CTeamBuilder pBuilder){
        mId = pBuilder.mId;
        mPlayers = pBuilder.mPlayers;
        mPortals = pBuilder.mPortals;
        mColor = pBuilder.mColor;

    }

    public void addPortal(CPortalEntity pPortal){
        mPortals.add(pPortal);
    }

    public void addPlayer(CPlayerEntity pPlayer){
        mPlayers.add(pPlayer);
    }

    public static class CTeamBuilder{
        private int mId;
        private List<CPlayerEntity> mPlayers = new ArrayList<>();
        private List<CPortalEntity> mPortals = new ArrayList<>();
        private String mColor;

        public CTeamBuilder(int pId){
            mId = pId;
        }

        public CTeamBuilder players(List<CPlayerEntity> pPlayers){
            mPlayers = pPlayers;
            return this;
        }

        public CTeamBuilder portals(List<CPortalEntity> pPortals){
            mPortals = pPortals;
            return this;
        }

        public CTeamBuilder color(String pColor){
            mColor = pColor;
            return this;
        }

        public CTeamEntity build(){
            return new CTeamEntity(this);
        }


    }

    @Override
    public String toString() {
        return "CTeamEntity{" +
                "mId=" + mId +
                ", mPlayers=" + mPlayers +
                ", mColor='" + mColor + '\'' +
                '}';
    }
}