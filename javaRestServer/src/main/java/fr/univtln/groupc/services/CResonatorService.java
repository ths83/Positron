package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.CResonatorEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/resonators")
public class CResonatorService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    @POST
    @Consumes("application/json")
    public Response createResonator(String pResonatorJson){
        CResonatorEntity lResonator = null;
        try {
            lResonator = mMapper.readValue(pResonatorJson, CResonatorEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lResonator);
        return Response.status(201).entity(pResonatorJson).build();
    }


    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readResonator(@PathParam("id") int pId){
        System.out.println("dedans");
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        //return (CResonatorEntity)mCrudMethods.find(CResonatorEntity.class, pId);
        String lJsonValue = null;
        CResonatorEntity lResonator = (CResonatorEntity)mCrudMethods.find(CResonatorEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lResonator);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    @GET
    @Produces("application/json")
    @Path("/portals/{id}")
    public String readResonatorsByPortal(@PathParam("id") int pId){
        String lJsonValue = null;
        List<CResonatorEntity> lResonators = (List<CResonatorEntity>)mCrudMethods.findWithNamedQuery(CResonatorEntity.GET_RESONATOR_BY_PORTAL, CQueryParameter.with("mId", pId).parameters());
        try {
            lJsonValue = mMapper.writeValueAsString(lResonators);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    @GET
    @Produces("application/json")
    @Path("/portals/teams/{id}/{id2}")
    public String readResonatorsByPortalAndByTeam(@PathParam("id") int pId,@PathParam("id2") int pId2){
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lJsonValue = null;
        List<CResonatorEntity> lResonators = (List<CResonatorEntity>)mCrudMethods.findWithNamedQuery(CResonatorEntity.GET_RESONATOR_BY_PORTAL_AND_TEAM, CQueryParameter.with("mId", pId).and("mId2",pId2).parameters());
        try {
            lJsonValue = mMapper.writeValueAsString(lResonators);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lJsonValue = null;
        List<CResonatorEntity> lResonators = mCrudMethods.findWithNamedQuery(CResonatorEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lResonators);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateResonator(String pResonatorJson){
        //mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        CResonatorEntity lResonator = null;
        System.out.println("dans update reso");
        try {
            lResonator = mMapper.readValue(pResonatorJson, CResonatorEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lResonator);
        System.out.println("reso update ok");
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response deleteResonator(@PathParam("id") int pId){
        mCrudMethods.delete(CResonatorEntity.class, pId);
        return Response.status(200).build();
    }
}
