package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestPlayerTest extends TestCase {
    // Tests CRUD CPlayerService
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

    public void testPostPlayerService() throws Exception {

        CPlayerEntity lPlayerEntity = new CPlayerEntity.CPlayerBuilder(78678).email("bobz@z.fr").build();
        mJson = mMapper.writeValueAsString(lPlayerEntity);
        lResponse = mWebResource.path("/players").type("application/json").accept("application/json").post(ClientResponse.class, mJson);

        assertEquals(lResponse.getStatus(), 201);
    }


    public void testGetPlayerService() throws Exception {
        CPlayerEntity lPlayerEntity = mMapper.readValue(mWebResource.path("/players/78678").get(String.class), CPlayerEntity.class);
        assertEquals(lPlayerEntity.getEmail(), "bobz@z.fr");
    }

    public void testGetPlayerByMailService() throws Exception {
        CPlayerEntity lPlayerGotten = mMapper.readValue(mWebResource.path("players/mail/bobz@z.fr").get(String.class), CPlayerEntity.class);
        assertEquals(lPlayerGotten.getEmail(), "bobz@z.fr");
    }
    public void testDeletePlayerService() throws Exception {
        lClientResponse = mWebResource.path("/players/78678").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(lClientResponse.getStatus(), 200);
    }



    /*



    public void testUpdatePlayerService() throws Exception {
        String lPlayerJson = null;
        String lUpdatedPlayer = null;
        lPlayerJson = mWebResource.path("/players/50").accept("application/json").type("application/json").get(String.class);
        CPlayerEntity lPlayerToUpdate = mMapper.readValue(lPlayerJson, CPlayerEntity.class);
        lPlayerToUpdate.setNickName("update");
        lUpdatedPlayer = mMapper.writeValueAsString(lPlayerToUpdate);
        ClientResponse lResponse = mWebResource.path("/players").accept("application/json").type("application/json").put(ClientResponse.class, lPlayerJson);

        assertEquals(lResponse.getStatus(), 200);
    }*/
}
