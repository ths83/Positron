package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPlayerEntity;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */
public class CPlayerService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    /**
     * @param pPlayer
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createPlayer(CPlayerEntity pPlayer){
        mCrudMethods.create(pPlayer);
    }

    /**
     * @param pId
     * @return CPlayerEntity
     */
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readPlayer(@PathParam("id") int pId){
        //return (CPlayerEntity)mCrudMethods.find(CPlayerEntity.class, pId);
        String lJsonValue = null;
        CPlayerEntity lPlayer = (CPlayerEntity)mCrudMethods.find(CPlayerEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @return List<CPlayerEntity>
     */
    @GET
    @Produces("application/json")
    @Path("/all")
    public String readAll(){
        //return (List<CPlayerEntity>)mCrudMethods.findWithNamedQuery(CPlayerEntity.GET_ALL);
        String lJsonValue = null;
        List<CPlayerEntity> lPlayers = (List<CPlayerEntity>)mCrudMethods.findWithNamedQuery(CPlayerEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lPlayers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /**
     * @param pPlayer
     * @return CPlayerEntity
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CPlayerEntity updatePlayer(CPlayerEntity pPlayer){
        return (CPlayerEntity) mCrudMethods.update(pPlayer);
    }

    /**
     * @param pPlayer
     */
    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    public void deletePlayer(CPlayerEntity pPlayer){
        mCrudMethods.delete(CPlayerEntity.class, pPlayer.getId());
    }
}
