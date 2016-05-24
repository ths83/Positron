package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CTerritoryEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestFieldTest extends TestCase {
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

    // Tests CRUD CFieldService

    public void testPostFieldService() throws Exception {

        CPortalEntity lPortalEntity = new CPortalEntity.CPortalBuilder(1).latitude(12.2).longitude(45.2).radius(10).build();
        ArrayList<CPortalEntity> lPortals = new ArrayList<>();
        lPortals.add(lPortalEntity);

        CTerritoryEntity lTerritoryEntity = new CTerritoryEntity.CTerritoryBuilder(1).build();

        CLinkEntity lLinkEntity = new CLinkEntity.CLinkBuilder(1).portals(lPortals).build();
        ArrayList<CLinkEntity> lLinks = new ArrayList<>();
        lLinks.add(lLinkEntity);
        CFieldEntity lFieldEntity = new CFieldEntity.CFieldBuilder(2).links(lLinks).territory(lTerritoryEntity).build();
        // TODO : FIX WITH territory(lTerritoryEntity) class

        mJson = mMapper.writeValueAsString(lFieldEntity);
        ClientResponse lResponse = mWebResource.path("/fields").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }
/*
    public void testGetFieldService() throws Exception {
        CFieldEntity lFieldEntity = mMapper.readValue(mWebResource.path("fields/2").get(String.class), CFieldEntity.class);
        assertEquals(lFieldEntity.getId(), 2);
    }

/*
    public void testDeleteFieldService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/fields/2").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
*/
}
