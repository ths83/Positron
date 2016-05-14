package fr.univtln.groupc.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.CPlayerEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */

@Path("/players")
public class CPlayerService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    @POST
    @Consumes("application/json")
    public Response createPlayer(String pPlayerJson){
        CPlayerEntity lPlayer = null;
        try {
            lPlayer = mMapper.readValue(pPlayerJson, CPlayerEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lPlayer);
        return Response.status(201).entity(pPlayerJson).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readPlayer(@PathParam("id") int pId){
        String lJsonValue = null;
        CPlayerEntity lPlayer = (CPlayerEntity)mCrudMethods.find(CPlayerEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        String lJsonValue = null;
        List<CPlayerEntity> lPlayers = (List<CPlayerEntity>)mCrudMethods.findWithNamedQuery(CPlayerEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lPlayers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    @GET
    @Produces
    @Path("/name/{nickname}")
    public String readByName(@PathParam("nickname") String pName){
        String lJsonValue = null;
        List<CPlayerEntity> lPlayers = (List<CPlayerEntity>)mCrudMethods.findWithNamedQuery(CPlayerEntity.GET_BY_NAME, CQueryParameter.with("mNickName", pName).parameters());
        CPlayerEntity lPlayer = lPlayers.get(0);
        try {
            lJsonValue = mMapper.writeValueAsString(lPlayer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlayer(String pPlayerJson){
        CPlayerEntity lPlayer = null;
        try {
            lPlayer = mMapper.readValue(pPlayerJson, CPlayerEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lPlayer);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CPlayerEntity.class, pId);
        return Response.status(200).build();
    }
}
