package fr.univtln.groupc.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nmartinez016 on 25/04/16.
 */
@Entity
@Table(name = "t_team", schema = "positron")
@NamedQueries(@NamedQuery(name = CTeamEntity.GET_ALL, query = "select t from CTeamEntity t"))

public class CTeamEntity {

    @Id
    @Column(name = "id")
    private int mId;

    @OneToMany
    @JoinTable(schema = "positron")
    private List<CPlayerEntity> mPlayers;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(schema = "positron")
    private List<CPortalEntity> mPortals;

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

    public static class CTeamBuilder{
        private int mId;
        private List<CPlayerEntity> mPlayers;
        private List<CPortalEntity> mPortals;
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
                ", mPortals=" + mPortals +
                ", mColor='" + mColor + '\'' +
                '}';
    }
}