package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CSkillEntity;

import javax.persistence.PostUpdate;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/resonators")
public class CResonatorService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

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
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CResonatorEntity readResonator(@PathParam("id") int pId){
        System.out.println("dedans");
        return (CResonatorEntity)mCrudMethods.find(CResonatorEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CResonatorEntity> readAll(){
        return (List<CResonatorEntity>)mCrudMethods.findWithNamedQuery(CResonatorEntity.GET_ALL);
    }

    @PUT
    @Path("/put")
    public CResonatorEntity updateResonator(CResonatorEntity pResonator){
        return (CResonatorEntity)mCrudMethods.update(pResonator);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deleteResonator(CResonatorEntity pResonator){
        mCrudMethods.delete(CResonatorEntity.class, pResonator.getId());
    }
}
