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

    /**
     * @param pPlayer
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createPlayer(CPlayerEntity pPlayer){
        mCrudMethods.create(pPlayer);
    }

    /**
     * @param pId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CPlayerEntity read(@PathParam("id") int pId){
        return (CPlayerEntity)mCrudMethods.find(CPlayerEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CPlayerEntity> readAll(){
        return (List<CPlayerEntity>)mCrudMethods.findWithNamedQuery(CPlayerEntity.GET_ALL);
    }

    /**
     * @param pPlayer
     * @return
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CPlayerEntity updateTeam(CPlayerEntity pPlayer){
        return (CPlayerEntity) mCrudMethods.update(pPlayer);
    }
}
