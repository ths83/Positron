package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CTerritoryEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestTerritoryTest extends TestCase {
    // Tests CRUD CTerritoryService
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;
    public void testPostTerritoryService() throws Exception {

        CTerritoryEntity lcTerritoryEntity = new CTerritoryEntity.CTerritoryBuilder(1).build();
        mJson = mMapper.writeValueAsString(lcTerritoryEntity);
        lResponse = mWebResource.path("/territories").type("application/json").accept("application/json").post(ClientResponse.class, mJson);

        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetTerritoryService() throws Exception {
        CTerritoryEntity lTerritoryEntity = mMapper.readValue(mWebResource.path("/territories/1").get(String.class), CTerritoryEntity.class);
        assertEquals(lTerritoryEntity.getId(), 1);
    }
}
