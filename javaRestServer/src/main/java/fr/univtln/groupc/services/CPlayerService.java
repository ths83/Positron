package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPlayerEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */
public class CPlayerService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

    @POST
    @Consumes("application/json")
    @Path("create")
    public void createPlayer(CPlayerEntity pPlayer){
        mCrudMethods.create(pPlayer);
    }

    @GET
    @Path("/{id}")
    public CPlayerEntity read(@PathParam("id") int pId){
        return (CPlayerEntity)mCrudMethods.find(CPlayerEntity.class, pId);
    }

    @GET
    @Path("/")
    public List<CPlayerEntity> readAll(){
        return (List<CPlayerEntity>)mCrudMethods.findWithNamedQuery(CPlayerEntity.GET_ALL);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CPlayerEntity updateTeam(CPlayerEntity pPlayer){
        return (CPlayerEntity) mCrudMethods.update(pPlayer);
    }
}
