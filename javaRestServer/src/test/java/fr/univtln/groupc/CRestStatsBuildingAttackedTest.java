package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.server.CServer;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestStatsBuildingAttackedTest extends TestCase {
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

// Tests CRUD StatsBuildingAttackedService OFF

    public void testPostStatsBuildingAttackedService() throws Exception {

        CStatsBuildingsAttacked lStatsBuildingsAttacked = new CStatsBuildingsAttacked.CStatsBuildingsAttackedBuilder(0).cpt(51).build();

        mJson = mMapper.writeValueAsString(lStatsBuildingsAttacked);
        lResponse = mWebResource.path("/statsBuildingAttacked").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetStatsBuildingAttackedService() throws Exception {
        CStatsBuildingsAttacked lStatsBuildingsAttacked = mMapper.readValue(mWebResource.path("/statsBuildingAttacked/1").get(String.class), CStatsBuildingsAttacked.class);
        assertEquals(lStatsBuildingsAttacked.getId(), 0);
    }

    public void testDeleteStatsBuildingAttackedService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/statsBuildingAttacked/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }

}
