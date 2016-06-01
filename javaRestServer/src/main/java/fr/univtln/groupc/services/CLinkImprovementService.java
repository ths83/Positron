package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CLinkImprovementEntity;
import fr.univtln.groupc.entities.CMultiHackEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by xdurbec066 on 01/06/16.
 */
@Path("/linkImprovement")
public class CLinkImprovementService {


    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    @POST
    @Consumes("application/json")
    public Response createLinkImprovement(String pLinkImprovementJson){
        CLinkImprovementEntity lLinkImprovement = null;
        try {
            lLinkImprovement = mMapper.readValue(pLinkImprovementJson, CLinkImprovementEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lLinkImprovement);
        return Response.status(201).entity(pLinkImprovementJson).build();

    }

    /**
     * @param pId
     * @return CLinkImprovementEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readLinkImprovement(@PathParam("id") int pId) {
        //return (CLinkImprovementEntity) mCrudMethods.find(CLinkImprovementEntity.class, pId);
        String lJsonValue = null;
        CLinkImprovementEntity lLinkImprovement = mCrudMethods.find(CLinkImprovementEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lLinkImprovement);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CLinkImprovementEntity>
     */
    @GET
    @Produces("application/json")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CLinkImprovementEntity.GET_ALL);
        String lJsonValue = null;
        List<CLinkImprovementEntity> lLinkImprovements = mCrudMethods.findWithNamedQuery(CLinkImprovementEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lLinkImprovements);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(String pLinkImprovementJson){
        CLinkImprovementEntity lLinkImprovement = null;
        try {
            lLinkImprovement = mMapper.readValue(pLinkImprovementJson, CLinkImprovementEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lLinkImprovement);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId){
        mCrudMethods.delete(CLinkImprovementEntity.class, pId);
        return Response.status(200).build();
    }



}
