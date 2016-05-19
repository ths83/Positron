package fr.univtln.groupc.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CPlayerEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mpesnel786 on 03/05/16.
 */
@Path("/keys")
public class CKeyService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    @POST
    @Consumes("application/json")
    public Response createKey(String pKeyJson){
        CKeyEntity lKey = null;
        try {
            lKey = mMapper.readValue(pKeyJson, CKeyEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lKey);
        return Response.status(201).entity(pKeyJson).build();
    }


    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){

        //return mCrudMethods.find(CKeyEntity.class, pId);
        String lJsonValue = null;
        CKeyEntity lKey = mCrudMethods.find(CKeyEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        String lJsonValue = null;
        List<CKeyEntity> lKeys = mCrudMethods.findWithNamedQuery(CKeyEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lKeys);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    @GET
    @Produces("application/json")
    @Path("/portal/{id}")
    public String getKeyByPortal(@PathParam("id") final int ID){
        Map<String, Object> lMap = new HashMap<>();
        lMap.put("id", ID);
        //return (List<CKeyEntity>)mCrudMethods.findWithNamedQuery(CKeyEntity.GET_KEY_BY_PORTAL, lMap);

        String lJsonValue = null;
        List<CKeyEntity> lKeys = (List<CKeyEntity>)mCrudMethods.findWithNamedQuery(CKeyEntity.GET_BY_PORTAL, lMap);
        try {
            lJsonValue = mMapper.writeValueAsString(lKeys);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    @GET
    @Produces("application/json")
    @Path("/players/{id}")
    public String getKeyByPlayer(@PathParam("id") final int pId){
        String lJsonValue = null;
        CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pId);
        List<CKeyEntity> lKeys = lPlayer.getKeys();
        System.out.println("keys objects : \n" + lKeys);
        try {
            lJsonValue = mMapper.writeValueAsString(lKeys);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("-> keys json : \n" + lJsonValue);
        return lJsonValue;
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateTeam(String pKeyJson){
        CKeyEntity lKey = null;
        try {
            lKey = mMapper.readValue(pKeyJson, CKeyEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lKey);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CKeyEntity.class, pId);
        return Response.status(200).build();
    }
}
