package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CSkillEntity;

import javax.persistence.PostUpdate;
import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/resonators")
public class CResonatorService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    /**
     * @param pResonator
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createResonator(CResonatorEntity pResonator){
        mCrudMethods.create(pResonator);
    }

    /**
     * @param pId
     * @return CResonatorEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readResonator(@PathParam("id") int pId){
        System.out.println("dedans");
        //return (CResonatorEntity)mCrudMethods.find(CResonatorEntity.class, pId);
        String lJsonValue = null;
        CResonatorEntity lResonator = (CResonatorEntity)mCrudMethods.find(CResonatorEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lResonator);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @return List<CResonatorEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CResonatorEntity.GET_ALL);
        String lJsonValue = null;
        List<CResonatorEntity> lResonators = mCrudMethods.findWithNamedQuery(CResonatorEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lResonators);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @param pResonator
     * @return CResonatorEntity
     */
    @PUT
    @Path("/put")
    public CResonatorEntity updateResonator(CResonatorEntity pResonator){
        return mCrudMethods.update(pResonator);
    }

    /**
     * @param pResonator
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deleteResonator(CResonatorEntity pResonator){
        mCrudMethods.delete(CResonatorEntity.class, pResonator.getId());
    }
}
