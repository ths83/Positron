package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/portals")
public class CPortalService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    /**
     * @param pPortal
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createPortal(CPortalEntity pPortal){
        System.out.println("in");
        mCrudMethods.create(pPortal);
    }

    /**
     * @param pId
     * @return CPortalEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readPortal(@PathParam("id") int pId){
        //return (CPortalEntity)mCrudMethods.find(CPortalEntity.class, pId);
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
        //return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_BY_TEAM, CQueryParameter.with("mId", pId).parameters());
        String lJsonValue = null;
        List<CPortalEntity> lPortals = (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_BY_TEAM, CQueryParameter.with("mId", pId).parameters());
        try {
            lJsonValue = mMapper.writeValueAsString(lPortals);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @return List<CPortalEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
        String lJsonValue = null;
        List<CPortalEntity> lPortals = (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lPortals);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @param pPortal
     * @return CPortalEntity
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/put")
    public CPortalEntity updatePortal(CPortalEntity pPortal){
        return (CPortalEntity)mCrudMethods.update(pPortal);
    }


    /**
     * @param pPortal
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deletePortal(CPortalEntity pPortal){
        mCrudMethods.delete(CPortalEntity.class, pPortal.getId());
    }


}
