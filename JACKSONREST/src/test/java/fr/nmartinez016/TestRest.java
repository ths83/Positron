package fr.nmartinez016;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import junit.framework.TestCase;

import java.io.IOException;

/**
 * Created by marti on 05/05/2016.
 */
public class TestRest extends TestCase {

    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();

    public void testServiceAPost() throws Exception {
        String lAJson = null;
        CClassA lA = new CClassA(29, "jaaatt29");
        try {
            lAJson = mMapper.writeValueAsString(lA);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClientResponse lResponse = mWebResource.path("/classa/salut").accept("application/json").type("application/json").post(ClientResponse.class, lAJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testServiceAGet() throws Exception {
        CClassA lGottenA = mMapper.readValue(mWebResource.path("classa/get").get(String.class), CClassA.class);
        assertEquals(lGottenA.getmName(), "jaja");
    }

    public void testServiceADelete() throws Exception {

        ClientResponse lResponse = mWebResource.path("/classa/delete/29").accept("application/json").type("application/json").delete(ClientResponse.class);
        assertEquals(lResponse.getStatus(), 200);
    }
}
