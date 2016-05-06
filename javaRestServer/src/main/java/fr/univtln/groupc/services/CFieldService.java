package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CFieldEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */
public class CFieldService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

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
     * @return CFieldEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        //return (CFieldEntity)mCrudMethods.find(CFieldEntity.class, pId);
        String lJsonValue = null;
        CFieldEntity lField = (CFieldEntity)mCrudMethods.find(CFieldEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lField);
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
        //return (List<CFieldEntity>)mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);
        String lJsonValue = null;
        List<CFieldEntity> lFields = (List<CFieldEntity>)mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lFields);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @param pField
     * @return
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CFieldEntity updateField(CFieldEntity pField){
        return (CFieldEntity) mCrudMethods.update(pField);
    }

    /**
     * @param pField
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void delete(CFieldEntity pField){
        mCrudMethods.delete(CFieldEntity.class, pField.getId());
    }
}
