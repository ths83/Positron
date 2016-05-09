package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTeamEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/teams")
public class CTeamService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

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
    public String read(@PathParam("id") int pId){
        //return mCrudMethods.find(CTeamEntity.class, pId);
        String lJsonValue = null;
        CTeamEntity lTeam = mCrudMethods.find(CTeamEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lTeam);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CTeamEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CTeamEntity.GET_ALL);
        String lJsonValue = null;
        List<CTeamEntity> lTeams = mCrudMethods.findWithNamedQuery(CTeamEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lTeams);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @param pTeam
     * @return CTeamEntity
     */
    @PUT
    @Produces("application/json")
    @Path("/put")
    public CTeamEntity updateTeam(CTeamEntity pTeam){
        return mCrudMethods.update(pTeam);
    }

    /**
     * @param pTeam
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deleteTeam(CTeamEntity pTeam){
        mCrudMethods.delete(CTeamEntity.class, pTeam.getId());
    }
}
