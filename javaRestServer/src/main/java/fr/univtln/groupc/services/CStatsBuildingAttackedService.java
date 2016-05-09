package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;
import fr.univtln.groupc.stats.CStatsPlayer;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by arouani277 on 04/05/16.
 */
@Path("/statsBuildingAttacked")
public class CStatsBuildingAttackedService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    /**
     * @param pStatsBuildingsAttacked
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createField(CStatsBuildingsAttacked pStatsBuildingsAttacked){
        mCrudMethods.create(pStatsBuildingsAttacked);
    }

    /**
     * @param pId
     * @return CStatsBuildingsAttacked
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){
        //return mCrudMethods.find(CStatsBuildingsAttacked.class, pId);
        String lJsonValue = null;
        CStatsBuildingsAttacked lStat = mCrudMethods.find(CStatsBuildingsAttacked.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lStat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @return List<CStatsBuildingsAttacked>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return mCrudMethods.findWithNamedQuery(CStatsBuildingsAttacked.GET_ALL);
        String lJsonValue = null;
        List<CStatsBuildingsAttacked> lStats = mCrudMethods.findWithNamedQuery(CStatsBuildingsAttacked.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lStats);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lJsonValue;
    }

    /**
     * @param pStatsBuildingsAttacked
     * @return CStatsBuildingsAttacked
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CStatsBuildingsAttacked updateTeam(CStatsBuildingsAttacked pStatsBuildingsAttacked){
        return mCrudMethods.update(pStatsBuildingsAttacked);
    }

    /**
     * @param pStatsBuildingAttacked
     */
    @DELETE
    @Consumes("application/json")
    //@Path("/")
    public void delete(CStatsBuildingsAttacked pStatsBuildingAttacked){
        mCrudMethods.delete(CStatsBuildingsAttacked.class, pStatsBuildingAttacked.getmId());
    }
}
