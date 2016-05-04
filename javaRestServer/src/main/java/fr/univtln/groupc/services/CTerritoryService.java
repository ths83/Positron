package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTerritoryEntity;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/territories")
public class CTerritoryService {

    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pTerritory
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createSkill(CTerritoryEntity pTerritory){
        mCrudMethods.create(pTerritory);
    }

    /**
     * @param pId
     * @return CTerritoryEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CTerritoryEntity read(@PathParam("id") int pId){
        return mCrudMethods.find(CTerritoryEntity.class, pId);
    }

    /**
     * @return List<CTerritoryEntity>
     */
    @GET
    @Path("/all")
    public List<CTerritoryEntity> readAll(){
        return mCrudMethods.findWithNamedQuery(CTerritoryEntity.GET_ALL);
    }
}
