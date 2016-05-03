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

    @POST
    @Consumes("application/json")
    @Path("create")
    public void createLink(CLinkEntity pLink){
        if (pLink.algoCreateLink(pLink.getmPortals().get(1),pLink.getmPortals().get(2))==true) {
            mCrudMethods.create(pLink);
        }
    }

    @GET
    @Path("/{id}")
    public CLinkEntity read(@PathParam("id") int pId){
        return (CLinkEntity)mCrudMethods.find(CLinkEntity.class, pId);
    }

    @GET
    @Path("/")
    public List<CLinkEntity> readAll(){
        return (List<CLinkEntity>)mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CLinkEntity updateTeam(CLinkEntity pLink){
        return (CLinkEntity) mCrudMethods.update(pLink);
    }
}
