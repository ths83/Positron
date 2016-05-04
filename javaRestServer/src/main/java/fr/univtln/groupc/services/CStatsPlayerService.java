package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsPlayer;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */

@Path("/statsPlayer")
public class CStatsPlayerService {

    private CCrudMethods mCrudMethods = new CCrudMethods();

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
    @Path("/{id}")
    public CStatsPlayer read(@PathParam("id") int pId){
        return (CStatsPlayer)mCrudMethods.find(CStatsPlayer.class, pId);
    }

    /**
     * @return List<CStatsPlayer>
     */
    @GET
    @Path("/all")
    public List<CStatsPlayer> readAll(){
        return mCrudMethods.findWithNamedQuery(CStatsPlayer.GET_ALL);
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
}
