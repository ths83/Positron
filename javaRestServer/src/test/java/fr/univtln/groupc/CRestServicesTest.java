package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 06/05/2016.
 */
public class CRestServicesTest extends TestCase {

    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();

    // Tests CRUD TeamService

    public void testPostTeamService() throws Exception {
        CTeamEntity lTeamToPost = new CTeamEntity.CTeamBuilder(150).color("vert").build();
        String lJsonTeam = mMapper.writeValueAsString(lTeamToPost);
        ClientResponse lResponse = mWebResource.path("/teams").type("application/json").accept("application/json").post(ClientResponse.class, lJsonTeam);

        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetTeamService() throws Exception {
        CTeamEntity cTeamEntity = mMapper.readValue(mWebResource.path("teams/150").get(String.class), CTeamEntity.class);
        assertEquals(cTeamEntity.getColor(), "vert");
    }

    public void testDeleteTeamService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/teams/150").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }

    // Tests CRUD CTurrentService

    public void testPostCTurretService() throws Exception {

        CTurretEntity lTurretPost = new CTurretEntity
                .CTurretBuilder(150).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).latitude(10.5)
                .longitude(11.2).name("c1").radius(100).build();

        String lJsonTeam = mMapper.writeValueAsString(lTurretPost);
        ClientResponse lResponse = mWebResource.path("/turrets").type("application/json").accept("application/json").post(ClientResponse.class, lJsonTeam);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetTurretService() throws Exception {
        CTurretEntity cTurretEntity = mMapper.readValue(mWebResource.path("turrets/150").get(String.class), CTurretEntity.class);
        assertEquals(cTurretEntity.getName(), "c1");
    }

    public void testDeleteTurrentService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/turrets/150").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }

    // Tests CRUD CPortalService

    public void testPostPortalService() throws Exception {

        CTurretEntity c1 = new CTurretEntity
                .CTurretBuilder(78678687).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).latitude(10.5)
                .longitude(11.2).name("c1").radius(100).build();

        CResonatorEntity cr = new CResonatorEntity.CResonatorBuilder(78687678).energy(100)
                .latitude(10.5).energyMax(200)
                .level(9).longitude(5.2).name("cr")
                .radius(54).build();

        List<AObjectEntity> objects = new ArrayList();
        objects.add(c1);
        objects.add(cr);

        List<CResonatorEntity> resonators = new ArrayList<CResonatorEntity>();
        resonators.add(cr);

        CPortalEntity lPortalPost = new CPortalEntity.CPortalBuilder(150).latitude(10).longitude(5.2).objects(objects).resonators(resonators).build();

        String lJsonTeam = mMapper.writeValueAsString(lPortalPost);
        ClientResponse lResponse = mWebResource.path("/portals").type("application/json").accept("application/json").post(ClientResponse.class, lJsonTeam);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetPortalService() throws Exception {
        CPortalEntity cPortalEntity = mMapper.readValue(mWebResource.path("/portals/150").get(String.class), CPortalEntity.class);
        assertEquals(cPortalEntity.getId(), 150);
    }

    public void testDeletePortalService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/portals/150").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }

    // Tests CRUD CPlayerService

    public void testPostPlayerService() throws Exception {

        CTeamEntity ctm = new CTeamEntity.CTeamBuilder(78678).color("red").build();

        CPlayerEntity lPLayerPost = new CPlayerEntity.CPlayerBuilder(5858).email("bob@z.fr").nickname("mahmoud").team(ctm).build();

        String lJsonTeam = mMapper.writeValueAsString(lPLayerPost);
        ClientResponse lResponse = mWebResource.path("/players").type("application/json").accept("application/json").post(ClientResponse.class, lJsonTeam);

        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetPlayerService() throws Exception {
        CPlayerEntity cPlayerEntity= mMapper.readValue(mWebResource.path("/players/150").get(String.class), CPlayerEntity.class);
        assertEquals(cPlayerEntity.getEmail(), "bobz@z.fr");
    }

    public void testDeletePlayerService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/players/150").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
/*
    public void testGetByIdTeamService() throws Exception {
        CTeamEntity lTeamGotten = mMapper.readValue(mWebResource.path("/teams/150").accept("application/json").type("application/json").get(String.class), CTeamEntity.class);
        assertEquals(lTeamGotten.getId(), 150);
        assertEquals(lTeamGotten.getColor(), "vert");
    }

    public void testPostPlayerService() throws Exception {
        CPlayerEntity lPlayerToPost = new CPlayerEntity.CPlayerBuilder(50).email("email_de_test").energy(50).latitude(125.3).longitude(142.9).build();
        String lJsonPlayer = mMapper.writeValueAsString(lPlayerToPost);
        ClientResponse lResponse = mWebResource.path("players").type("application/json").accept("application/json").post(ClientResponse.class, lJsonPlayer);
        assertEquals(lResponse.getStatus(), 201);
    }
<<<<<<< HEAD


=======
*/


/*
>>>>>>> f783e1318a2e52804e4678bff64c58d436826606
    public void testGetByIdPlayerService() throws Exception {
        CPlayerEntity lPlayerGotten = mMapper.readValue(mWebResource.path("players/50").get(String.class), CPlayerEntity.class);
        // need post method first
        assertEquals(lPlayerGotten.getId(), 50);
        assertEquals(lPlayerGotten.getEmail(), "email_de_test");
        assertEquals(lPlayerGotten.getEnergy(), 50);
        // etc
    }

    public void testUpdatePlayerService() throws Exception {
        String lPlayerJson = null;
        String lUpdatedPlayer = null;
        lPlayerJson = mWebResource.path("/players/50").accept("application/json").type("application/json").get(String.class);
        CPlayerEntity lPlayerToUpdate = mMapper.readValue(lPlayerJson, CPlayerEntity.class);
        lPlayerToUpdate.setNickName("update");
        lUpdatedPlayer = mMapper.writeValueAsString(lPlayerToUpdate);
        ClientResponse lResponse = mWebResource.path("/players").accept("application/json").type("application/json").put(ClientResponse.class, lPlayerJson);

        assertEquals(lResponse.getStatus(), 200);
    }
*/
}
