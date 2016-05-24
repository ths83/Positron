package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.AObjectEntity;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestKeyTest extends TestCase {
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

    // Tests CRUD CKeyService

    public void testPostKeyService() throws Exception {
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(700).longitude(150).latitude(150).build();

        CKeyEntity lKeyEntity = new CKeyEntity.CKeyBuilder(1).name("key1").portal(lPortal1).build();
        mJson = mMapper.writeValueAsString(lKeyEntity);
        ClientResponse lResponse = mWebResource.path("/keys").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetKeyService() throws Exception {
        CKeyEntity lKeyEntity = mMapper.readValue(mWebResource.path("/keys/1").get(String.class), CKeyEntity.class);
        assertEquals(lKeyEntity.getId(), 1);
    }

    public void testKeyService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/keys/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
    public void testGetKeyByPlayer() throws Exception{
        String lJsonGotten = null;
        List<CKeyEntity> lKeys = new ArrayList<>();
        lJsonGotten = mWebResource.path("/keys/players/41").get(String.class);
        System.out.println("json : \n" + lJsonGotten);
        lKeys = mMapper.readValue(lJsonGotten,mMapper.getTypeFactory().constructCollectionType(List.class, AObjectEntity.class));
        System.out.println("keys -> " + lKeys);
        System.out.println(lKeys.size());
    }
}
