package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CKeyEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */
public class CKeyService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

    @POST
    @Consumes("application/json")
    @Path("create")
    public void createKey(CKeyEntity pKey){
            mCrudMethods.create(pKey);
    }

    @GET
    @Path("/{id}")
    public CKeyEntity read(@PathParam("id") int pId){
        return (CKeyEntity)mCrudMethods.find(CKeyEntity.class, pId);
    }

    @GET
    @Path("/")
    public List<CKeyEntity> readAll(){
        return (List<CKeyEntity>)mCrudMethods.findWithNamedQuery(CKeyEntity.GET_ALL);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CKeyEntity updateTeam(CKeyEntity pKey){
        return (CKeyEntity) mCrudMethods.update(pKey);
    }
}
