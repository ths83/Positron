package fr.univtln.groupc.stats;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by marti on 09/06/2016.
 */

@Entity
@Table(name = "t_portal_hacked", schema = "positron")
@NamedQueries(@NamedQuery(name = CStatPortalHacked.GET_BY_PLAYER_ID, query = "select s from CStatPortalHacked s where s.mPlayerId = :mPlayerId"))
public class CStatPortalHacked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mId;
    @Temporal(TemporalType.DATE)
    private Date mDate;
    private int mPlayerId;
    private int mPortalId;
    public final static String GET_BY_PLAYER_ID = "StatPortalHacked.getByPlayerId";


    public CStatPortalHacked(){}

    public CStatPortalHacked(CStatPortalHackedBuilder pBuilder){
        mDate = pBuilder.mDate;
        mPlayerId = pBuilder.mPlayerId;
        mPortalId = pBuilder.mPortalId;
    }

    public Date getDate(){
        return mDate;
    }

    public void setDate(Date pDate){
        mDate = pDate;
    }

    public int getPlayerId(){
        return mPlayerId;
    }

    public void setPlayerId(int pPlayerId){
        mPlayerId = pPlayerId;
    }

    public int getPortalId(){
        return mPortalId;
    }

    public void setPortalId(int pPortalId){
        mPortalId = pPortalId;
    }




    public static class CStatPortalHackedBuilder{
        private Date mDate;
        private int mPlayerId;
        private int mPortalId;

        public CStatPortalHackedBuilder(){}

        public CStatPortalHackedBuilder date(Date pDate){
            mDate = pDate;
            return this;
        }

        public CStatPortalHackedBuilder player(int pPlayerId){
            mPlayerId = pPlayerId;
            return this;
        }

        public CStatPortalHackedBuilder portalId(int pPortalId){
            mPortalId = pPortalId;
            return this;
        }

        public CStatPortalHacked build(){
            return new CStatPortalHacked(this);
        }
    }
}
