package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.ws.rs.*;
import java.io.IOException;
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
    public String read(@PathParam("id") int pId){

        //return mCrudMethods.find(CConsumableEntity.class, pId);
        ObjectMapper lMapper = new ObjectMapper();
        String lJsonValue = null;
        CConsumableEntity lConsumable = mCrudMethods.find(CConsumableEntity.class, pId);
        try {
            lJsonValue = lMapper.writeValueAsString(lConsumable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return CConsumableEntity
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CConsumableEntity.GET_ALL);
        ObjectMapper lMapper = new ObjectMapper();
        String lJsonValue = null;
        List<CConsumableEntity> lConsumables = mCrudMethods.findWithNamedQuery(CConsumableEntity.GET_ALL);
        try {
            lJsonValue = lMapper.writeValueAsString(lConsumables);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
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
