package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CFieldEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */
public class CFieldService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pField
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createField(CFieldEntity pField){
        mCrudMethods.create(pField);
    }

    /**
     * @param pId
     * @return
     */
    @GET
    @Path("/{id}")
    public CFieldEntity read(@PathParam("id") int pId){
        return (CFieldEntity)mCrudMethods.find(CFieldEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Path("/all")
    public List<CFieldEntity> readAll(){
        return (List<CFieldEntity>)mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);
    }

    /**
     * @param pField
     * @return
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CFieldEntity updateTeam(CFieldEntity pField){
        return (CFieldEntity) mCrudMethods.update(pField);
    }
}
