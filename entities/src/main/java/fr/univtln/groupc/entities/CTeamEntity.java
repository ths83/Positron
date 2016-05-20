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
@Entity
@Table(name = "t_team", schema = "positron")
@NamedQueries(@NamedQuery(name = CTeamEntity.GET_ALL, query = "select t from CTeamEntity t"))
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CTeamEntity.class)

public class CTeamEntity implements Serializable {

    @Id
    @Column(name = "id")
    private int mId;

    @OneToMany(mappedBy="mTeam")
    @JoinTable(schema = "positron")
    private List<CPlayerEntity> mPlayers = new ArrayList<CPlayerEntity>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mTeam")
    private List<CPortalEntity> mPortals = new ArrayList<CPortalEntity>();

    @Column(name = "color")
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
        private List<CPlayerEntity> mPlayers = new ArrayList<CPlayerEntity>();
        private List<CPortalEntity> mPortals = new ArrayList<CPortalEntity>();
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