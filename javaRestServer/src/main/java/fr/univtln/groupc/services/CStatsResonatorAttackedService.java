package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;
import fr.univtln.groupc.stats.CStatsResonatorAttacked;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 04/05/16.
 */
@Path("/statsResonatorsAttacked")
public class CStatsResonatorAttackedService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    
    @POST
    @Consumes("application/json")
    public Response create(String pStatsResonatorAttackedJson){
        CStatsResonatorAttacked lStatsResonatorAttacked = null;
        try {
            lStatsResonatorAttacked = mMapper.readValue(pStatsResonatorAttackedJson, CStatsResonatorAttacked.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lStatsResonatorAttacked);
        return Response.status(201).entity(pStatsResonatorAttackedJson).build();

    }

    /**
     * @param pId
     * @return pStatsResonatorAttacked
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        //return mCrudMethods.find(CStatsResonatorAttacked.class, pId);
        String lJsonValue = null;
        CStatsResonatorAttacked lStat = mCrudMethods.find(CStatsResonatorAttacked.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lStat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CStatsResonatorAttacked>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CStatsResonatorAttacked.GET_ALL);
        String lJsonValue = null;
        List<CStatsResonatorAttacked> lStats = mCrudMethods.findWithNamedQuery(CStatsResonatorAttacked.GET_ALL);
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
    public Response update(String pStatsResonatorAttackedJson){
        CStatsResonatorAttacked lStatsResonatorAttacked = null;
        try {
            lStatsResonatorAttacked = mMapper.readValue(pStatsResonatorAttackedJson, CStatsResonatorAttacked.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lStatsResonatorAttacked);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CStatsResonatorAttacked.class, pId);
        return Response.status(200).build();
    }
}
