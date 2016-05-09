package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTerritoryEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/territories")
public class CTerritoryService {

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    /**
     * @param pTerritory
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createTerritory(CTerritoryEntity pTerritory){
        mCrudMethods.create(pTerritory);
    }

    /**
     * @param pId
     * @return CTerritoryEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readTerritory(@PathParam("id") int pId){
        //System.out.println("dedans");
        //return (CTerritoryEntity)mCrudMethods.find(CTerritoryEntity.class, pId);
        String lJsonValue = null;
        CTerritoryEntity lTerritory = mCrudMethods.find(CTerritoryEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lTerritory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;

    }

    /**
     * @return List<CTerritoryEntity>
     */
    @GET
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CTerritoryEntity.GET_ALL);
        String lJsonValue = null;
        List<CTerritoryEntity> lTerritories = mCrudMethods.findWithNamedQuery(CTerritoryEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lTerritories);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @param pTerritory
     * @return CTerritoryEntity
     */
    @PUT
    @Consumes("application/json")
    @Path("/put")
    public CTerritoryEntity updateTerritory(CTerritoryEntity pTerritory){
        return (CTerritoryEntity)mCrudMethods.update(pTerritory);
    }

    /**
     * @param pTerritory
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deleteTerritory(CTerritoryEntity pTerritory){
        mCrudMethods.delete(CTerritoryEntity.class, pTerritory.getId());
    }
}
