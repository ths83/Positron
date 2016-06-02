package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 04/05/16.
 */
@Path("/statsBuildingAttacked")
public class CStatsBuildingAttackedService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    
    @POST
    @Consumes("application/json")
    public Response createStatsBuildingAttacked(String pStatsBuildingsAttackedJson){
        CStatsBuildingsAttacked lStatsBuildingsAttacked = null;
        try {
            lStatsBuildingsAttacked = mMapper.readValue(pStatsBuildingsAttackedJson, CStatsBuildingsAttacked.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lStatsBuildingsAttacked);
        return Response.status(201).entity(pStatsBuildingsAttackedJson).build();

    }

    /**
     * @param pId
     * @return CStatsBuildingsAttacked
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        String lJsonValue = null;
        CStatsBuildingsAttacked lStat = mCrudMethods.find(CStatsBuildingsAttacked.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lStat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CStatsBuildingsAttacked>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CStatsBuildingsAttacked.GET_ALL);
        String lJsonValue = null;
        List<CStatsBuildingsAttacked> lStats = mCrudMethods.findWithNamedQuery(CStatsBuildingsAttacked.GET_ALL);
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
    public Response update(String pStatsBuildingsAttackedJson){
        CStatsBuildingsAttacked lStatsBuildingsAttacked = null;
        try {
            lStatsBuildingsAttacked = mMapper.readValue(pStatsBuildingsAttackedJson, CStatsBuildingsAttacked.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lStatsBuildingsAttacked);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CStatsBuildingsAttacked.class, pId);
        return Response.status(200).build();
    }
}
