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
    public CTerritoryEntity readTerritory(@PathParam("id") int pId){
        System.out.println("dedans");
        return (CTerritoryEntity)mCrudMethods.find(CTerritoryEntity.class, pId);

    }

    /**
     * @return List<CTerritoryEntity>
     */
    @GET
    @Path("/all")
    public List<CTerritoryEntity> readAll(){
        return mCrudMethods.findWithNamedQuery(CTerritoryEntity.GET_ALL);
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
