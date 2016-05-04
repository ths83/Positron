package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/consumables")
public class CConsumableService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pConsumable
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createConsumable(CConsumableEntity pConsumable){
        mCrudMethods.create(pConsumable);
    }

    /**
     * @param pId
     * @return CConsumableEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CConsumableEntity read(@PathParam("id") int pId){
        return mCrudMethods.find(CConsumableEntity.class, pId);
    }

    /**
     * @return CConsumableEntity
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CConsumableEntity> readAll(){
        return mCrudMethods.findWithNamedQuery(CConsumableEntity.GET_ALL);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/put")
    public CConsumableEntity update(CConsumableEntity pConsumable){
        return mCrudMethods.update(pConsumable);
    }

    /**
     * @param pConsumable
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void delete(CConsumableEntity pConsumable){
        mCrudMethods.delete(CConsumableEntity.class, pConsumable.getId());
    }

}
