package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CSkillEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestSkillTest extends TestCase{
    // Tests CRUD CSkillService Rouani Azedine
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;
    public void testPostSkillService() throws Exception {

        CSkillEntity lSkillEntity = new CSkillEntity.CSkillBuilder(66).cost(5).level(10).name("rogue").build();
        mJson = mMapper.writeValueAsString(lSkillEntity);
        lResponse = mWebResource.path("/skills").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetSkillService() throws Exception {
        CSkillEntity lSkillEntity = mMapper.readValue(mWebResource.path("/skills/66").get(String.class), CSkillEntity.class);
        assertEquals(lSkillEntity.getId(), 66);
    }

    public void testDeleteSkillService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/skills/66").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
}
