package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTurretEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/turrets")
public class CTurretService {

    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pTurret
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createBet(CTurretEntity pTurret){
        mCrudMethods.create(pTurret);
    }

    /**
     * @param pId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CTurretEntity read(@PathParam("id") int pId){
        return (CTurretEntity)mCrudMethods.find(CTurretEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CTurretEntity> readAll(){
        return (List<CTurretEntity>)mCrudMethods.findWithNamedQuery(CTurretEntity.GET_ALL);
    }

}
