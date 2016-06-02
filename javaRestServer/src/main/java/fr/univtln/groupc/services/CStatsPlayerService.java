package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsPlayer;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */

@Path("/statsPlayer")
public class CStatsPlayerService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    
    @POST
    @Consumes("application/json")
    public Response create(String pStatsPlayerJson){
        CStatsPlayer lStatsPlayer = null;
        try {
            lStatsPlayer = mMapper.readValue(pStatsPlayerJson, CStatsPlayer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lStatsPlayer);
        return Response.status(201).entity(pStatsPlayerJson).build();

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

    
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pStatsPlayerJson){
        CStatsPlayer lStatsPlayer = null;
        try {
            lStatsPlayer = mMapper.readValue(pStatsPlayerJson, CStatsPlayer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lStatsPlayer);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CStatsPlayer.class, pId);
        return Response.status(200).build();
    }
}
