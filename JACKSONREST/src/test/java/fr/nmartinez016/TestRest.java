package fr.nmartinez016;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import junit.framework.TestCase;

/**
 * Created by marti on 05/05/2016.
 */
public class TestRest extends TestCase {

    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();


    public void testServiceAGet() throws Exception {
        CClassA lGottenA = mMapper.readValue(mWebResource.path("classa/get").get(String.class), CClassA.class);
        System.out.println(lGottenA);
        assertEquals(lGottenA.getmName(), "jaja");
    }
}
