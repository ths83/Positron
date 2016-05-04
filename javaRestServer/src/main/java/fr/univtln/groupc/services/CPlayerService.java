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
     * @return CPlayerEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CPlayerEntity readPlayer(@PathParam("id") int pId){
        return (CPlayerEntity)mCrudMethods.find(CPlayerEntity.class, pId);
    }

    /**
     * @return List<CPlayerEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CPlayerEntity> readAll(){
        return (List<CPlayerEntity>)mCrudMethods.findWithNamedQuery(CPlayerEntity.GET_ALL);
    }

    /**
     * @param pPlayer
     * @return CPlayerEntity
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CPlayerEntity updatePlayer(CPlayerEntity pPlayer){
        return (CPlayerEntity) mCrudMethods.update(pPlayer);
    }

    /**
     * @param pPlayer
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deletePlayer(CPlayerEntity pPlayer){
        mCrudMethods.delete(CPlayerEntity.class, pPlayer.getId());
    }
}
