package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestPortalTest extends TestCase {
    // Tests CRUD CPortalService
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;

    public void testPostPortalService() throws Exception {

        CTurretEntity lTurret = new CTurretEntity
                .CTurretBuilder(78678687).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).name("t1").radius(100).build();

        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(78687678).energy(100)
                .latitude(10.5).energyMax(200)
                .level(9).longitude(5.2).name("cr")
                .radius(54).build();

        List<AObjectEntity> objects = new ArrayList();
        objects.add(lTurret);
        objects.add(lResonator);

        List<CResonatorEntity> lResonators = new ArrayList<CResonatorEntity>();
        lResonators.add(lResonator);


        CPortalEntity lPortalPost = new CPortalEntity.CPortalBuilder(150).latitude(10).longitude(5.2).resonators(lResonators).build();
        mJson = mMapper.writeValueAsString(lPortalPost);
        lResponse = mWebResource.path("/portals").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetPortalService() throws Exception {
        CPortalEntity lPortalEntity = mMapper.readValue(mWebResource.path("/portals/150").get(String.class), CPortalEntity.class);
        assertEquals(lPortalEntity.getId(), 150);
    }

    public void testDeletePortalService() throws Exception {
        lClientResponse = mWebResource.path("/portals/150").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(lClientResponse.getStatus(), 200);
    }

    public void testPortalWithKeyAndBuildingsGet() throws Exception {
        String lJsonGotten = null;
        lJsonGotten = mWebResource.path("/portals/778").get(String.class);
        System.out.println(lJsonGotten);

    }
    public void testPortalWithKeyAndBuildingsPost() throws Exception {
        CKeyEntity lKey1 = new CKeyEntity.CKeyBuilder(23).name("clef1").build();
        CKeyEntity lKey2 = new CKeyEntity.CKeyBuilder(24).name("clef2").build();
        ABuildingEntity lTurret1 = new CTurretEntity.CTurretBuilder(74).name("turret1").build();
        List<CKeyEntity> lKeys = new ArrayList<>();
        List<ABuildingEntity> lBuildings = new ArrayList<>();
        lKeys.add(lKey1);
        lKeys.add(lKey2);
        lBuildings.add(lTurret1);
        //String lTurretJson = mMapper.writeValueAsString(lTurret1);
        CCrudMethods lCrud = new CCrudMethods();
        //lCrud.create(lTurret1);
        //mWebResource.path("/turrets").accept("application/json").type("application/json").post(lTurretJson);
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(778).buildings(lBuildings).keys(lKeys).latitude(445.2).longitude(112.3).build();
        mJson = mMapper.writeValueAsString(lPortal);
        //lCrud.create(lPortal);
        lResponse = mWebResource.path("/portals").accept("application/json").type("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }
}
