package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTeamEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/teams")
public class CTeamService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createBet(CTeamEntity pTeam){
        mCrudMethods.create(pTeam);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CTeamEntity read(@PathParam("id") int pId){
        return (CTeamEntity)mCrudMethods.find(CTeamEntity.class, pId);
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CTeamEntity> readAll(){
        return (List<CTeamEntity>)mCrudMethods.findWithNamedQuery(CTeamEntity.GET_ALL);
    }
}
