package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CSkillEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/skills")
public class CSkillService{
    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pSkill
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createSkill(CSkillEntity pSkill){
        mCrudMethods.create(pSkill);
    }

    /**
     * @param pId
     * @return CSkillEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CSkillEntity readSkill(@PathParam("id") int pId){
        System.out.println("dedans");
        return (CSkillEntity)mCrudMethods.find(CSkillEntity.class, pId);
    }

    /**
     * @return List<CPortalEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CPortalEntity> readAll(){
        return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
    }

    /**
     * @param pSkill
     * @return CSkillEntity
     */
    @PUT
    @Consumes("application/json")
    @Path("/put")
    public CSkillEntity updateSkill(CSkillEntity pSkill){
        return (CSkillEntity)mCrudMethods.update(pSkill);
    }

    /**
     * @param pSkill
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deleteSkill(CSkillEntity pSkill){
        mCrudMethods.delete(CSkillEntity.class, pSkill);
    }
}
