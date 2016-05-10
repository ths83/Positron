package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by marti on 10/05/2016.
 */
public class CFieldServiceTest extends TestCase {
    Client mClient = Client.create();
    WebResource mWebResource = mClient.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();

    public void testPostField() throws Exception {
        CFieldEntity lFieldToPost = new CFieldEntity.CFieldBuilder(150).build();
    }

    public void testGetFieldById() throws Exception {
        CFieldEntity lGottenField = mWebResource.path("/fields/150").accept("application/json").get(CFieldEntity.class);
        assertEquals(lGottenField.getId(), 150);
    }
}
