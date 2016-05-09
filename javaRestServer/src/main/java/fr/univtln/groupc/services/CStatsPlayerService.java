package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsPlayer;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */

@Path("/statsPlayer")
public class CStatsPlayerService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    /**
     * @param pStatsPlayer
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createField(CStatsPlayer pStatsPlayer){
        mCrudMethods.create(pStatsPlayer);
    }

    /**
     * @param pId
     * @return CStatsPlayer
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        //return (CStatsPlayer)mCrudMethods.find(CStatsPlayer.class, pId);
        String lJsonValue = null;
        CStatsPlayer lStat = mCrudMethods.find(CStatsPlayer.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lStat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CStatsPlayer>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CStatsPlayer.GET_ALL);
        String lJsonValue = null;
        List<CStatsPlayer> lStats = mCrudMethods.findWithNamedQuery(CStatsPlayer.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lStats);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @param pStatsPlayer
     * @return CStatsPlayer
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CStatsPlayer updateTeam(CStatsPlayer pStatsPlayer){
        return mCrudMethods.update(pStatsPlayer);
    }

    /**
     * @param pStatsPlayer
     */
    @DELETE
    @Consumes("application/json")
    //@Path("/")
    public void delete(CStatsPlayer pStatsPlayer){
        mCrudMethods.delete(CStatsPlayer.class, pStatsPlayer.getmID());
    }
}
