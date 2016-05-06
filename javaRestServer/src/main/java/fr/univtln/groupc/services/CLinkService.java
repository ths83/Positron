package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */

@Path("/links")
public class CLinkService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    /**
     * @param pLink
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createLink(CLinkEntity pLink){
        if (pLink.algoCreateLink(pLink.getPortals().get(1),pLink.getPortals().get(2))==true) {
            mCrudMethods.create(pLink);
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

        //return (CLinkEntity)mCrudMethods.find(CLinkEntity.class, pId);
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
    @Path("/all")
    public String readAll(){
        //return (List<CLinkEntity>)mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
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
    //@Path("/")
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
