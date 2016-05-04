package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CKeyEntity;

import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mpesnel786 on 03/05/16.
 */
@Path("/keys")
public class CKeyService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

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
    public CKeyEntity read(@PathParam("id") int pId){
        return mCrudMethods.find(CKeyEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Produces("application/json")
    public List<CKeyEntity> readAll(){
        return mCrudMethods.findWithNamedQuery(CKeyEntity.GET_ALL);
    }

    @GET
    @Produces("application/json")
    @Path("/portal/{id}")
    public List<CKeyEntity> getKeybyportal(@PathParam("id") final int ID){
        Map<String, Object> lMap = new HashMap<>();
        lMap.put("id", ID);
        return (List<CKeyEntity>)mCrudMethods.findWithNamedQuery(CKeyEntity.GET_KEY_BY_PORTAL, lMap);
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
