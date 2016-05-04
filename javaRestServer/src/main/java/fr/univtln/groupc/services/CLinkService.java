package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */

@Path("/links")
public class CLinkService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pLink
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createLink(CLinkEntity pLink){
        if (pLink.algoCreateLink(pLink.getPortals().get(1),pLink.getPortals().get(2))==true) {
            mCrudMethods.create(pLink);
        }
    }

    /**
     * @param pId
     * @return CLinkEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CLinkEntity read(@PathParam("id") int pId){
        return (CLinkEntity)mCrudMethods.find(CLinkEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CLinkEntity> readAll(){
        return (List<CLinkEntity>)mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
    }

    /**
     * @param pLink
     * @return CLinkEntity
     */
    @DELETE
    @Consumes("application/json")
    @Path("/")
    public void deleteLink(CLinkEntity pLink){
        mCrudMethods.delete(CLinkEntity.class, pLink.getId());
    }
}
