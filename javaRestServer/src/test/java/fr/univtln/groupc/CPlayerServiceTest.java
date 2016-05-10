package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by marti on 10/05/2016.
 */
public class CPlayerServiceTest extends TestCase{
    Client mClient = Client.create();
    WebResource mWebResource = mClient.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();

    public void testPostPlayer() throws Exception {
        CPlayerEntity lPlayerToPost = new CPlayerEntity.CPlayerBuilder(150).
        mWebResource.path("/players").post();
    }
}
