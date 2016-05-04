package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;
import fr.univtln.groupc.stats.CStatsResonatorAttacked;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by arouani277 on 04/05/16.
 */
@Path("/statsBuildingAttacked")
public class CStatsResonatorAttackedService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pStatsResonatorAttacked
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createField(CStatsResonatorAttacked pStatsResonatorAttacked){
        mCrudMethods.create(pStatsResonatorAttacked);
    }

    /**
     * @param pId
     * @return pStatsResonatorAttacked
     */
    @GET
    @Path("/{id}")
    public CStatsResonatorAttacked read(@PathParam("id") int pId){
        return mCrudMethods.find(CStatsResonatorAttacked.class, pId);
    }

    /**
     * @return List<CStatsResonatorAttacked>
     */
    @GET
    @Path("/all")
    public List<CStatsResonatorAttacked> readAll(){
        return mCrudMethods.findWithNamedQuery(CStatsResonatorAttacked.GET_ALL);
    }

    /**
     * @param pStatsResonatorAttacked
     * @return CStatsResonatorAttacked
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CStatsResonatorAttacked updateTeam(CStatsResonatorAttacked pStatsResonatorAttacked){
        return mCrudMethods.update(pStatsResonatorAttacked);
    }
}
