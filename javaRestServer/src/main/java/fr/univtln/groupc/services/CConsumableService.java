package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/consumables")
public class CConsumableService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    @POST
    @Consumes("application/json")
    @Path("create")
    public Response createConsumable(String pConsumableJson){
        CConsumableEntity lConsumable = null;
        try {
            lConsumable = mMapper.readValue(pConsumableJson, CConsumableEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lConsumable);
        return Response.status(201).entity(pConsumableJson).build();

    }


    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        String lJsonValue = null;
        CConsumableEntity lConsumable = mCrudMethods.find(CConsumableEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lConsumable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        String lJsonValue = null;
        List<CConsumableEntity> lConsumables = mCrudMethods.findWithNamedQuery(CConsumableEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lConsumables);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pConsumableJson){
        CConsumableEntity lConsumable = null;
        try {
            lConsumable = mMapper.readValue(pConsumableJson, CConsumableEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lConsumable);
        return Response.status(200).build();

    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public void delete(@PathParam("id") int pId){
        mCrudMethods.delete(CConsumableEntity.class, pId);
    }

}
