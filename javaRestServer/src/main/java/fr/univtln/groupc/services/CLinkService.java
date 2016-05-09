package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.algorithm.CAlgorithm;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */

@Path("/links")
public class CLinkService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    @POST
    @Consumes("application/json")
    public Response createLink(String pLinkJson){
        /*if (pLink.algoCreateLink(pLink.getPortals().get(1),pLink.getPortals().get(2))==true) {
            mCrudMethods.create(pLink);
        }*/
        CLinkEntity lLink = null;
        List<CLinkEntity> lLinks = mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
        List<CFieldEntity> lFields = mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);
        try {
            lLink = mMapper.readValue(pLinkJson, CLinkEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (CAlgorithm.detectColision(lLink, lLinks, lFields)){
            mCrudMethods.create(lLink);
            return Response.status(201).entity(pLinkJson).build();
        }
        else{
            return Response.status(500).build();
            // erreur a costumiser
        }

    }

    /**
     * @param pId
     * @return CLinkEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){

        String lJsonValue = null;
        CLinkEntity lLink = (CLinkEntity)mCrudMethods.find(CLinkEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lLink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @return
     */
    @GET
    @Produces("application/json")
    public String readAll(){
        String lJsonValue = null;
        List<CLinkEntity> lLinks = (List<CLinkEntity>)mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lLinks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    @PUT
    public CLinkEntity updateLink(CLinkEntity pLink){
        return mCrudMethods.update(pLink);
    }

    /**
     * @param pLink
     * @return CLinkEntity
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deleteLink(CLinkEntity pLink){
        mCrudMethods.delete(CLinkEntity.class, pLink.getId());
    }
}
