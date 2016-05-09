package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;
import fr.univtln.groupc.stats.CStatsResonatorAttacked;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 04/05/16.
 */
@Path("/statsResonatorsAttacked")
public class CStatsResonatorAttackedService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

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
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        //return mCrudMethods.find(CStatsResonatorAttacked.class, pId);
        String lJsonValue = null;
        CStatsResonatorAttacked lStat = mCrudMethods.find(CStatsResonatorAttacked.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lStat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CStatsResonatorAttacked>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CStatsResonatorAttacked.GET_ALL);
        String lJsonValue = null;
        List<CStatsResonatorAttacked> lStats = mCrudMethods.findWithNamedQuery(CStatsResonatorAttacked.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lStats);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
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

    /**
     * @param pStatsResonatorAttacked
     */
    @DELETE
    @Consumes("application/json")
    //@Path("/")
    public void delete(CStatsResonatorAttacked pStatsResonatorAttacked){
        mCrudMethods.delete(CStatsResonatorAttacked.class, pStatsResonatorAttacked.getmId());
    }
}
