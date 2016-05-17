package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CFieldEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */
@Path("/fields")

public class CFieldService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    @POST
    @Consumes("application/json")
    public Response createField(String pFieldJson){
        CFieldEntity lField = null;
        try {
            lField = mMapper.readValue(pFieldJson, CFieldEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lField);
        return Response.status(201).entity(pFieldJson).build();
    }


    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        String lJsonValue = null;
        CFieldEntity lField = mCrudMethods.find(CFieldEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lField);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        String lJsonValue = null;
        List<CFieldEntity> lFields = (List<CFieldEntity>)mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lFields);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateField(String pFieldJson){
        CFieldEntity lField = null;
        try {
            lField = mMapper.readValue(pFieldJson, CFieldEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lField);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response delete(@PathParam("id") int pId)
    {
        mCrudMethods.delete(CFieldEntity.class, pId);
        return Response.status(200).build();
    }
}
