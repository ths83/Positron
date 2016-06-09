package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CShieldEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 30/05/16.
 */
public class CRestShieldTest extends TestCase{
    // Tests CRUD CPortalService
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

    public void testPostShieldService() throws Exception {

        CShieldEntity lShieldEntity = new CShieldEntity.CShieldBuilder().energy(10).defensBonus(5).level(10).name("shield1").build();

        mJson = mMapper.writeValueAsString(lShieldEntity);
        lResponse = mWebResource.path("/shields").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetShieldService() throws Exception {
        CShieldEntity lShieldEntity = mMapper.readValue(mWebResource.path("/shields/1").get(String.class), CShieldEntity.class);
        assertEquals(lShieldEntity.getId(), 1);
    }

    public void testDeleteShieldService() throws Exception {
        lClientResponse = mWebResource.path("/shields/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(lClientResponse.getStatus(), 200);
    }

}
