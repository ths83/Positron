package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by boblinux on 26/05/16.
 */

@Path("/tokens")
public class CTokenService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    POST
    @Consumes("application/json")
    public Response createConsumable(String pConsumableJson){
        CTokenEntity lConsumable = null;
        try {
            lConsumable = mMapper.readValue(pConsumableJson, CTokenEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lConsumable);
        return Response.status(201).entity(pConsumableJson).build();

    }


    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") String  pToken){
        String lJsonValue = null;
        CTokenEntity lConsumable = mCrudMethods.find(CTokenEntity.class, pToken);
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
        List<CTokenEntity> lConsumables = mCrudMethods.findWithNamedQuery(CTokenEntity.GET_ALL);
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
        CTokenEntity lConsumable = null;
        try {
            lConsumable = mMapper.readValue(pConsumableJson, CTokenEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lConsumable);
        return Response.status(200).build();
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") String  pToken){
        mCrudMethods.delete(CTokenEntity.class, pId);
        return Response.status(200).build();
    }

}
