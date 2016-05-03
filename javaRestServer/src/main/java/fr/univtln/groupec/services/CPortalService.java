package fr.univtln.groupec.services;

import fr.univtln.groupec.dao.CCrudMethods;
import fr.univtln.groupec.entities.CPortalEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/portals")
public class CPortalService {

    private static CCrudMethods mCrudMethods = new CCrudMethods();

    @POST
    @Consumes("application/json")
    @Path("create")
    public void createBet(CPortalEntity pPortal){
        mCrudMethods.create(pPortal);
    }

    @GET
    @Path("/{id}")
    public CPortalEntity read(@PathParam("id") int pId){
        return (CPortalEntity)mCrudMethods.find(CPortalEntity.class, pId);
    }

    @GET
    @Path("/")
    public List<CPortalEntity> readAll(){
        return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
    }


}
