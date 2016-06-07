package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CSkillEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/skills")
public class CSkillService{
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    
    @POST
    @Consumes("application/json")
    public Response createSkill(String pSkillJson){
        CSkillEntity lSkill = null;
        try {
            lSkill = mMapper.readValue(pSkillJson, CSkillEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lSkill);
        return Response.status(201).entity(pSkillJson).build();

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

    
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pSkillJson){
        CSkillEntity lSkill = null;
        try {
            lSkill = mMapper.readValue(pSkillJson, CSkillEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lSkill);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CSkillEntity.class, pId);
        return Response.status(200).build();
    }
}
