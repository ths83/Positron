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

    /**
     * @param pTeam
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createTeam(CTeamEntity pTeam){
        mCrudMethods.create(pTeam);
    }

    /**
     * @param pId
     * @return CTeamEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CTeamEntity read(@PathParam("id") int pId){
        return mCrudMethods.find(CTeamEntity.class, pId);
    }

    /**
     * @return List<CTeamEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public List<CTeamEntity> readAll(){
        return mCrudMethods.findWithNamedQuery(CTeamEntity.GET_ALL);
    }

    /**
     * @param pTeam
     * @return CTeamEntity
     */
    @PUT
    @Produces("application/json")
    @Path("/")
    public CTeamEntity updateTeam(CTeamEntity pTeam){
        return mCrudMethods.update(pTeam);
    }

    /**
     * @param pTeam
     */
    @DELETE
    @Consumes("application/json")
    @Path("/")
    public void deleteTeam(CTeamEntity pTeam){
        mCrudMethods.delete(CTeamEntity.class, pTeam.getId());
    }
}
