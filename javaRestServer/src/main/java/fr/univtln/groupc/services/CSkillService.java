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

    @POST
    @Consumes("application/json")
    @Path("create")
    public void createBet(CSkillEntity pSkill){
        mCrudMethods.create(pSkill);
    }

    @PUT
    @Consumes("application/json")
    @Path("put")
    public void ajouterSkill(@QueryParam("name") String  nom, @QueryParam("id") int id) {
        CSkillEntity csk = new CSkillEntity.CSkillBuilder(id).name(nom).build();
        mCrudMethods.create(csk);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CSkillEntity read(@PathParam("id") int pId){
        System.out.println("dedans");
        return (CSkillEntity)mCrudMethods.find(CSkillEntity.class, pId);
    }

    @GET
    @Path("/")
    public List<CPortalEntity> readAll(){
        return (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
    }
}
