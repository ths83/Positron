package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTurretEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/turrets")
public class CTurretService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    @POST
    @Consumes("application/json")
    public Response createTurret(String pTurretJson){
        CTurretEntity lTurret = null;
        try {
            lTurret = mMapper.readValue(pTurretJson, CTurretEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lTurret);
        return Response.status(201).entity(pTurretJson).build();

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

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pTurretJson){
        CTurretEntity lTurret = null;
        try {
            lTurret = mMapper.readValue(pTurretJson, CTurretEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lTurret);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CTurretEntity.class, pId);
        return Response.status(200).build();
    }

}
