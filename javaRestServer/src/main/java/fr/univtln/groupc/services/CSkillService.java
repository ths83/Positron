package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CSkillEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/skills")
public class CSkillService{
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

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
    public String readSkill(@PathParam("id") int pId){
        //System.out.println("dedans");
        //return (CSkillEntity)mCrudMethods.find(CSkillEntity.class, pId);
        String lJsonValue = null;
        CSkillEntity lSkill = mCrudMethods.find(CSkillEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lSkill);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    @GET
    @Produces("application/json")
    @Path("/level/{id}")
    public String readSkillByLevel(@PathParam("id") int pLevel){
        //return (List<CSkillEntity>)mCrudMethods.findWithNamedQuery(CSkillEntity.GET_BY_LEVEL, CQueryParameter.with("mLevel", pLevel).parameters());
        String lJsonValue = null;
        List<CSkillEntity> lSkills = mCrudMethods.findWithNamedQuery(CSkillEntity.GET_BY_LEVEL);
        try {
            lJsonValue = mMapper.writeValueAsString(lSkills);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CPortalEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
        String lJsonValue = null;
        List<CSkillEntity> lSkills = mCrudMethods.findWithNamedQuery(CSkillEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lSkills);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
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
