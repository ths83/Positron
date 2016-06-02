package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CMultiHackEntity;
import fr.univtln.groupc.entities.CShieldEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by xdurbec066 on 01/06/16.
 */

@Path("/multiHacks")
public class CMultiHackService {


    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    @POST
    @Consumes("application/json")
    public Response createMultiHack(String pMultiHackJson){
        CMultiHackEntity lMultiHack = null;
        try {
            lMultiHack = mMapper.readValue(pMultiHackJson, CMultiHackEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lMultiHack);
        return Response.status(201).entity(pMultiHackJson).build();

    }

    /**
     * @param pId
     * @return CMultiHackEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readMultiHack(@PathParam("id") int pId) {
        //return (CMultiHackEntity) mCrudMethods.find(CMultiHackEntity.class, pId);
        String lJsonValue = null;
        CMultiHackEntity lMultiHack = mCrudMethods.find(CMultiHackEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lMultiHack);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CMultiHackEntity>
     */
    @GET
    @Produces("application/json")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CMultiHackEntity.GET_ALL);
        String lJsonValue = null;
        List<CMultiHackEntity> lMultiHacks = mCrudMethods.findWithNamedQuery(CMultiHackEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lMultiHacks);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pMultiHackJson){
        CMultiHackEntity lMultiHack = null;
        try {
            lMultiHack = mMapper.readValue(pMultiHackJson, CMultiHackEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lMultiHack);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CMultiHackEntity.class, pId);
        return Response.status(200).build();
    }



}
