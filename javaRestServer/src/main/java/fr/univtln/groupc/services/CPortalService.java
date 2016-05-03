package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/portals")
public class CPortalService {

    private CCrudMethods mCrudMethods = new CCrudMethods();

    @POST
    @Consumes("application/json")
    @Path("create")
    public void createBet(CPortalEntity pPortal){
        mCrudMethods.create(pPortal);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CPortalEntity read(@PathParam("id") int pId){
        return (CPortalEntity)mCrudMethods.find(CPortalEntity.class, pId);
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CPortalEntity> readAll(){
        return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
    }


}
