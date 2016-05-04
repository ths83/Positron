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
        mCrudMethods.create(pPortal);
    }

    /**
     * @param pId
     * @return CPortalEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CPortalEntity readPortal(@PathParam("id") int pId){
        return (CPortalEntity)mCrudMethods.find(CPortalEntity.class, pId);
    }

    /**
     * @return List<CPortalEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CPortalEntity> readAll(){
        return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
    }

    /**
     * @param pPortal
     * @return CPortalEntity
     */
    @PUT
    @Consumes("application/json")
    @Path("/")
    public CPortalEntity updatePortal(CPortalEntity pPortal){
        return (CPortalEntity)mCrudMethods.update(pPortal);
    }


    /**
     * @param pPortal
     */
    @DELETE
    @Consumes("application/json")
    @Path("/")
    public void deletePortal(CPortalEntity pPortal){
        mCrudMethods.delete(CPortalEntity.class, pPortal.getId());
    }


}
