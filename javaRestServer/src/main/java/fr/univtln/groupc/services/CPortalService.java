package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/portals")
public class CPortalService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();



    @POST
    @Consumes("application/json")
    public Response createPortal(String pPortalJson){
        CPortalEntity lPortal = null;
        try {
            lPortal = mMapper.readValue(pPortalJson, CPortalEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lPortal);
        return Response.status(201).entity(pPortalJson).build();

    }


    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readPortal(@PathParam("id") int pId){
        String lJsonValue = null;
        CPortalEntity lPortal = (CPortalEntity)mCrudMethods.find(CPortalEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lPortal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    /*
     * Liste tous les portails appartenant a une team
     */

    @GET
    @Produces("application/json")
    @Path("/teams/{id}")
    public String readPortalsByTeam(@PathParam("id") int pId){
        String lJsonValue = null;
        List<CPortalEntity> lPortals = (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_BY_TEAM, CQueryParameter.with("mId", pId).parameters());
        try {
            lJsonValue = mMapper.writeValueAsString(lPortals);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        String lJsonValue = null;
        List<CPortalEntity> lPortals = (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lPortals);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePortal(String pPortalJson){
        CPortalEntity lPortal = null;
        try {
            lPortal = mMapper.readValue(pPortalJson, CPortalEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lPortal);
        return Response.status(200).build();
    }



    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response deletePortal(@PathParam("id") int pId){
        mCrudMethods.delete(CPortalEntity.class, pId);
        return Response.status(200).build();
    }


}
