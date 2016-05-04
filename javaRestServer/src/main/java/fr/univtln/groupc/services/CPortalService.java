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
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CPortalEntity readPortal(@PathParam("id") int pId){
        return (CPortalEntity)mCrudMethods.find(CPortalEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CPortalEntity> readAll(){
        return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
    }

    @PUT
    @Consumes("application/json")
    @Path("/put")
    public CPortalEntity updatePortal(CPortalEntity pPortal){
        return (CPortalEntity)mCrudMethods.update(pPortal);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deletePortal(CPortalEntity pPortal){
        mCrudMethods.delete(CPortalEntity.class, pPortal.getId());
    }


}
