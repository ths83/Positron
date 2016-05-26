package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by boblinux on 26/05/16.
 */
public class CRestTokenTest extends TestCase {
    // Tests CRUD CTerritoryService
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;
    public void testPostTerritoryService() throws Exception {

        CTokenEntity lTokenEntity = new CTokenEntity.CTerritoryBuilder("1");
        mJson = mMapper.writeValueAsString(lTokenEntity);
        lResponse = mWebResource.path("/tokens").type("application/json").accept("application/json").post(ClientResponse.class, mJson);

        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetTerritoryService() throws Exception {
        CTokenEntity lTokenEntity = mMapper.readValue(mWebResource.path("/tokens/1").get(String.class), CTokenEntity.class);
        assertEquals(lTerritoryEntity.getToken(), 1);
    }
}
