package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestConsumableTest extends TestCase {
    // Tests CRUD CConsumableService OFF
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

    public void testPostConsumableService() throws Exception {

        CConsumableEntity lConsumableEntity = new CConsumableEntity.CConsumableBuilder(1).name("bob").rarity(5).build();
        String lJsonConsumable = mMapper.writeValueAsString(lConsumableEntity);
        lResponse = mWebResource.path("/consumables").type("application/json").accept("application/json").post(ClientResponse.class, lJsonConsumable);

        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetConsumableService() throws Exception {
        CConsumableEntity lConsumableEntity = mMapper.readValue(mWebResource.path("/consumables/1").get(String.class), CConsumableEntity.class);
        assertEquals(lConsumableEntity.getId(), 1);
    }
/*
    public void testDeleteConsumableService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/consumables/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
*/

}
