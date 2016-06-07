package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.AObjectEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mpesnel786 on 04/05/16.
 */
@Path("/objects")
public class CObjectService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    @GET
    @Produces("application/json")
    @Path("/player/{id}")
    public String getObjectByPlayer(@PathParam("id") final int ID){
        Map<String, Object> lMap = new HashMap<>();
        lMap.put("id", ID);
        //return (List<AObjectEntity>)mCrudMethods.findWithNamedQuery(AObjectEntity.GET_OBJECT_BY_PLAYER, lMap);
        String lJsonValue = null;
        List<AObjectEntity> lObjects = (List<AObjectEntity>)mCrudMethods.findWithNamedQuery(AObjectEntity.GET_OBJECT_BY_PLAYER, lMap);
        try {
            lJsonValue = mMapper.writeValueAsString(lObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }
}
