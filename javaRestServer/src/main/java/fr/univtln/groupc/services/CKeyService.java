package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CKeyEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mpesnel786 on 03/05/16.
 */
@Path("/keys")
public class CKeyService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    /**
     * @param pKey
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createKey(CKeyEntity pKey){
            mCrudMethods.create(pKey);
    }

    /**
     * @param pId
     * @return CKeyEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){

        //return mCrudMethods.find(CKeyEntity.class, pId);
        String lJsonValue = null;
        CKeyEntity lKey = mCrudMethods.find(CKeyEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lKey);
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
        //return mCrudMethods.findWithNamedQuery(CKeyEntity.GET_ALL);
        String lJsonValue = null;
        List<CKeyEntity> lKeys = mCrudMethods.findWithNamedQuery(CKeyEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lKeys);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    @GET
    @Produces("application/json")
    @Path("/portal/{id}")
    public String getKeybyportal(@PathParam("id") final int ID){
        Map<String, Object> lMap = new HashMap<>();
        lMap.put("id", ID);
        //return (List<CKeyEntity>)mCrudMethods.findWithNamedQuery(CKeyEntity.GET_KEY_BY_PORTAL, lMap);

        String lJsonValue = null;
        List<CKeyEntity> lKeys = (List<CKeyEntity>)mCrudMethods.findWithNamedQuery(CKeyEntity.GET_KEY_BY_PORTAL, lMap);
        try {
            lJsonValue = mMapper.writeValueAsString(lKeys);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @param pKey
     * @return CKeyEntity
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CKeyEntity updateTeam(CKeyEntity pKey){
        return mCrudMethods.update(pKey);
    }

    /**
     * @param pKey
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void delete(CKeyEntity pKey){
        mCrudMethods.delete(CKeyEntity.class, pKey.getId());
    }
}
