package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CTurretEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestTurretTest extends TestCase {
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;


    // Tests CRUD CTurrentService

    public void testPostCTurretService() throws Exception {

        CTurretEntity lTurretPost = new CTurretEntity
                .CTurretBuilder(150).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).name("c1").radius(100).build();

        mJson = mMapper.writeValueAsString(lTurretPost);
        ClientResponse lResponse = mWebResource.path("/turrets").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetTurretService() throws Exception {
        CTurretEntity cTurretEntity = mMapper.readValue(mWebResource.path("turrets/150").get(String.class), CTurretEntity.class);
        assertEquals(cTurretEntity.getName(), "c1");
    }

    public void testDeleteTurrentService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/turrets/150").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }

}
