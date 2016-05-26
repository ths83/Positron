package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CTeamEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestTeamTest extends TestCase{
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;
    // Tests CRUD TeamService

    public void testPostTeamService() throws Exception {
        CTeamEntity lTeamToPost = new CTeamEntity.CTeamBuilder(150).color("vert").build();
        mJson = mMapper.writeValueAsString(lTeamToPost);
        ClientResponse lResponse = mWebResource.path("/teams").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetTeamService() throws Exception {
        CTeamEntity lTeamEntity = mMapper.readValue(mWebResource.path("teams/150").get(String.class), CTeamEntity.class);
        System.out.println(lTeamEntity);
        assertEquals(lTeamEntity.getColor(), "vert");
    }


    public void testDeleteTeamService() throws Exception {
        ClientResponse lClientResponse = mWebResource.path("/teams/150").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(lClientResponse.getStatus(), 200);
    }
    /*
    public void testGetByIdTeamService() throws Exception {
        CTeamEntity lTeamGotten = mMapper.readValue(mWebResourit addce.path("/teams/150").accept("application/json").type("application/json").get(String.class), CTeamEntity.class);
        assertEquals(lTeamGotten.getId(), 150);
        assertEquals(lTeamGotten.getColor(), "vert");
    }

*/
}
