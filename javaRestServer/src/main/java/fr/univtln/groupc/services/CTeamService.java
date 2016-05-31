package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CTeamEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
@Path("/teams")
public class CTeamService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    @POST
    @Consumes("application/json")
    public Response createTeam(String pTeamJson){
        CTeamEntity lTeam = null;
        try {
            lTeam = mMapper.readValue(pTeamJson, CTeamEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lTeam);
        return Response.status(201).entity(pTeamJson).build();

    }


    /**
     * @param pId
     * @return lJsonValue
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

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pTeamJson){
        CTeamEntity lTeam = null;
        try {
            lTeam = mMapper.readValue(pTeamJson, CTeamEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lTeam);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CTeamEntity.class, pId);
        return Response.status(200).build();
    }
}
