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
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public CTerritoryEntity readTerritory(@PathParam("id") int pId){
        System.out.println("dedans");
        return (CTerritoryEntity)mCrudMethods.find(CTerritoryEntity.class, pId);
    }

    /**
     * @return
     */
    @GET
    @Path("/all")
    public List<CTerritoryEntity> readAll(){
        return (List<CTerritoryEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
    }

    @PUT
    @Consumes("application/json")
    @Path("/")
    public CTerritoryEntity updateTerritory(CTerritoryEntity pTerritory){
        return (CTerritoryEntity)mCrudMethods.update(pTerritory);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/")
    public void deleteTerritory(CTerritoryEntity pTerritory){
        mCrudMethods.delete(CTerritoryEntity.class, pTerritory.getId());
    }
}
