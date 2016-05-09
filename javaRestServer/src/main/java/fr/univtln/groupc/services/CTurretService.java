package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTurretEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/turrets")
public class CTurretService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    /**
     * @param pTurret
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createTurret(CTurretEntity pTurret){
        mCrudMethods.create(pTurret);
    }

    /**
     * @param pId
     * @return CTurretEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readTurret(@PathParam("id") int pId) {
        //return (CTurretEntity) mCrudMethods.find(CTurretEntity.class, pId);
        String lJsonValue = null;
        CTurretEntity lTurret = mCrudMethods.find(CTurretEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lTurret);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CTurretEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CTurretEntity.GET_ALL);
        String lJsonValue = null;
        List<CTurretEntity> lTurrets = mCrudMethods.findWithNamedQuery(CTurretEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lTurrets);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @param pTurret
     * @return CTurretEntity
     */
    @PUT
    @Consumes("application/json")
    @Path("/put")
    public CTurretEntity updateTurret(CTurretEntity pTurret){
        return (CTurretEntity)mCrudMethods.update(pTurret);
    }

    /**
     * @param pTurret
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deleteTurret(CTurretEntity pTurret){
        mCrudMethods.delete(CTurretEntity.class, pTurret.getId());
    }

}
