package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTerritoryEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/territories")
public class CTerritoryService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    @POST
    @Consumes("application/json")
    public Response createTerritory(String pTerritoryJson){
        CTerritoryEntity lTerritory = null;
        try {
            lTerritory = mMapper.readValue(pTerritoryJson, CTerritoryEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lTerritory);
        return Response.status(201).entity(pTerritoryJson).build();

    }

    /**
     * @param pId
     * @return CTerritoryEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readTerritory(@PathParam("id") int pId){
        //System.out.println("dedans");
        //return (CTerritoryEntity)mCrudMethods.find(CTerritoryEntity.class, pId);
        String lJsonValue = null;
        CTerritoryEntity lTerritory = mCrudMethods.find(CTerritoryEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lTerritory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;

    }

    /**
     * @return List<CTerritoryEntity>
     */
    @GET
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CTerritoryEntity.GET_ALL);
        String lJsonValue = null;
        List<CTerritoryEntity> lTerritories = mCrudMethods.findWithNamedQuery(CTerritoryEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lTerritories);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pTerritoryJson){
        CTerritoryEntity lTerritory = null;
        try {
            lTerritory = mMapper.readValue(pTerritoryJson, CTerritoryEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lTerritory);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CTerritoryEntity.class, pId);
        return Response.status(200).build();
    }
}
