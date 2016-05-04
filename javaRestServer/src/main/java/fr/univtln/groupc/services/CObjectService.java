package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CKeyEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mpesnel786 on 04/05/16.
 */
@Path("/objects")
public class CObjectService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    @GET
    @Produces("application/json")
    @Path("/player/{id}")
    public List<AObjectEntity> getObjectbyplayer(@PathParam("id") final int ID){
        Map<String, Object> lMap = new HashMap<>();
        lMap.put("id", ID);
        return (List<AObjectEntity>)mCrudMethods.findWithNamedQuery(AObjectEntity.GET_OBJECT_BY_PLAYER, lMap);
    }
}
