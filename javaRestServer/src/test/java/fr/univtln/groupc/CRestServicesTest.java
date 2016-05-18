package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 06/05/2016.
 */
public class CRestServicesTest extends TestCase {

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

    // Tests CRUD CPortalService

    public void testPostPortalService() throws Exception {

        CTurretEntity lTurret = new CTurretEntity
                .CTurretBuilder(78678687).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).latitude(10.5)
                .longitude(11.2).name("t1").radius(100).build();

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

    // Tests CRUD CPlayerService

    public void testPostPlayerService() throws Exception {

        CPlayerEntity lPlayerEntity = new CPlayerEntity.CPlayerBuilder(78678).email("bobz@z.fr").build();

        mJson = mMapper.writeValueAsString(lPlayerEntity);
        lResponse = mWebResource.path("/players").type("application/json").accept("application/json").post(ClientResponse.class, mJson);

        assertEquals(lResponse.getStatus(), 201);
    }


    public void testGetPlayerService() throws Exception {
        CPlayerEntity lPlayerEntity = mMapper.readValue(mWebResource.path("/players/78678").get(String.class), CPlayerEntity.class);
        assertEquals(lPlayerEntity.getEmail(), "bobz@z.fr");
    }

    public void testDeletePlayerService() throws Exception {
        lClientResponse = mWebResource.path("/players/78678").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(lClientResponse.getStatus(), 200);
    }

    // Tests CRUD CTerritoryService

    public void testPostTerritoryService() throws Exception {

        CTerritoryEntity lcTerritoryEntity = new CTerritoryEntity.CTerritoryBuilder(1).build();
        mJson = mMapper.writeValueAsString(lcTerritoryEntity);
        lResponse = mWebResource.path("/territories").type("application/json").accept("application/json").post(ClientResponse.class, mJson);

        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetTerritoryService() throws Exception {
        CTerritoryEntity lTerritoryEntity = mMapper.readValue(mWebResource.path("/territories/1").get(String.class), CTerritoryEntity.class);
        assertEquals(lTerritoryEntity.getId(), 1);
    }
/* On n'a pas besoin de supprimer des territoires

    public void testDeleteTerritoryService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/territories/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
    */
/*
    public void testGetByNamePlayerService() throws Exception {
        CPlayerEntity lPlayer = null;
        String lJsonPlayer = mWebResource.path("/players/name/nicolas").type("application/json").accept("application/json").get(String.class);
        lPlayer = mMapper.readValue(lJsonPlayer, CPlayerEntity.class);
        System.out.println(lPlayer);
        assertEquals(lPlayer.getNickName(), "nicolas");
    }
*/

    // Tests CRUD StatsBuildingAttackedService OFF
/*
    public void testPostStatsBuildingAttackedService() throws Exception {

        CStatsBuildingsAttacked lStatsBuildingsAttacked = new CStatsBuildingsAttacked.CStatsBuildingsAttackedBuilder(0).cpt(51).build();

        mJson = mMapper.writeValueAsString(lStatsBuildingsAttacked);
        lResponse = mWebResource.path("/statsBuildingAttacked").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetStatsBuildingAttackedService() throws Exception {
        CStatsBuildingsAttacked lStatsBuildingsAttacked = mMapper.readValue(mWebResource.path("/statsBuildingAttacked/1").get(String.class), CStatsBuildingsAttacked.class);
        assertEquals(lStatsBuildingsAttacked.getmId(), 0);
    }

    public void testDeleteStatsBuildingAttackedService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/statsBuildingAttacked/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
*/
    // Tests CRUD CSkillService Rouani Azedine

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

    // Tests CRUD CConsumableService OFF

    public void testPostConsumableService() throws Exception {

        CConsumableEntity lConsumableEntity = new CConsumableEntity.CConsumableBuilder(1).name("bob").rarity(5).build();
        String lJsonConsumable = mMapper.writeValueAsString(lConsumableEntity);
        lResponse = mWebResource.path("/consumables").type("application/json").accept("application/json").post(ClientResponse.class, lJsonConsumable);

        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetConsumableService() throws Exception {
        CConsumableEntity lConsumableEntity = mMapper.readValue(mWebResource.path("/consumables/1").get(String.class), CConsumableEntity.class);
        assertEquals(lConsumableEntity.getId(), 1);
    }

    public void testDeleteConsumableService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/consumables/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }


    // Tests CRUD CTurrentService

    public void testPostCTurretService() throws Exception {

        CTurretEntity lTurretPost = new CTurretEntity
                .CTurretBuilder(150).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).latitude(10.5)
                .longitude(11.2).name("c1").radius(100).build();

        mJson = mMapper.writeValueAsString(lTurretPost);
        ClientResponse lResponse = mWebResource.path("/turrets").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
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


/*
    public void testPostLinkService() throws Exception {
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(700).longitude(150).latitude(150).build();
        ArrayList <CPortalEntity> portals = new ArrayList<>();
        portals.add(lPortal1);

        CLinkEntity lLinkEntity = new CLinkEntity.CLinkBuilder(1).portals(portals).build();
        mJson = mMapper.writeValueAsString(lLinkEntity);
        ClientResponse lResponse = mWebResource.path("/links").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetLinkService() throws Exception {
        CLinkEntity lLinkEntity = mMapper.readValue(mWebResource.path("/links/1").get(String.class), CLinkEntity.class);
        assertEquals(lLinkEntity.getId(), 1);
    }

    public void testKeyLinkService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/links/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
    */
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

*/


/*
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
    }*/

    public void testPostLinkServiceWhen3rdOfAFieldToCreate() throws Exception {
        CCrudMethods lCrud = new CCrudMethods();
        String lLink1Json = null;
        String lLink2Json = null;
        String lLink3Json = null;
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(700).longitude(150).latitude(150).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(701).longitude(450).latitude(152).build();
        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(702).longitude(300).latitude(400).build();
        List<CPortalEntity> lPortals1_2 = new ArrayList<>();
        lPortals1_2.add(lPortal1);
        lPortals1_2.add(lPortal2);

        List<CPortalEntity> lPortals1_3 = new ArrayList<>();
        lPortals1_3.add(lPortal1);
        lPortals1_3.add(lPortal3);

        List<CPortalEntity> lPortals2_3 = new ArrayList<>();
        lPortals2_3.add(lPortal2);
        lPortals2_3.add(lPortal3);

        System.out.println("print 1");
        lCrud.create(lPortal1);
        System.out.println("print 2");
        lCrud.create(lPortal2);
        System.out.println("print 3");
        lCrud.create(lPortal3);


        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(800).portals(lPortals1_2).build();
        System.out.println("print 4");
        lLink1Json = mMapper.writeValueAsString(lLink1);
        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink1Json);
        //lCrud.create(lLink1);


        System.out.println("print 5");
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(801).portals(lPortals1_3).build();
        lLink2Json = mMapper.writeValueAsString(lLink2);
        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink2Json);
        //lCrud.create(lLink2);


        System.out.println("print 6");
        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(802).portals(lPortals2_3).build();
        lLink3Json = mMapper.writeValueAsString(lLink3);

        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink3Json);
        }


    public void testPostLinkServiceWhenAFieldEnglobeOtherLink() throws Exception {
        CCrudMethods lCrud = new CCrudMethods();
        String lLink1Json = null;
        String lLink2Json = null;
        String lLink3Json = null;
        String lLink4Json = null;
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(800).longitude(151).latitude(150).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(801).longitude(450).latitude(152).build();

        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(802).longitude(300).latitude(400).build();

        CPortalEntity lPortal4 = new CPortalEntity.CPortalBuilder(810).longitude(299).latitude(350).build();
        CPortalEntity lPortal5 = new CPortalEntity.CPortalBuilder(811).longitude(301).latitude(200).build();

        List<CPortalEntity> lPortals1_2 = new ArrayList<>();
        lPortals1_2.add(lPortal1);
        lPortals1_2.add(lPortal2);

        List<CPortalEntity> lPortals1_3 = new ArrayList<>();
        lPortals1_3.add(lPortal1);
        lPortals1_3.add(lPortal3);

        List<CPortalEntity> lPortals2_3 = new ArrayList<>();
        lPortals2_3.add(lPortal2);
        lPortals2_3.add(lPortal3);

        List<CPortalEntity> lPortals10_11 = new ArrayList<>();
        lPortals10_11.add(lPortal4);
        lPortals10_11.add(lPortal5);



        System.out.println("print P1");
        lCrud.create(lPortal1);
        System.out.println("print P2");
        lCrud.create(lPortal2);
        System.out.println("print P3");
        lCrud.create(lPortal3);
        System.out.println("print P4");
        lCrud.create(lPortal4);
        System.out.println("print P5");
        lCrud.create(lPortal5);



        System.out.println("print L 10 11");
        CLinkEntity lLink4 = new CLinkEntity.CLinkBuilder(910).portals(lPortals10_11).build();
        lLink4Json = mMapper.writeValueAsString(lLink4);

        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink4Json);


        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(900).portals(lPortals1_2).build();
        System.out.println("print L 1 2");
        lLink1Json = mMapper.writeValueAsString(lLink1);
        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink1Json);
        //lCrud.create(lLink1);


        System.out.println("print L 1 3");
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(901).portals(lPortals1_3).build();
        lLink2Json = mMapper.writeValueAsString(lLink2);
        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink2Json);
        //lCrud.create(lLink2);


        System.out.println("print L 2 3");
        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(902).portals(lPortals2_3).build();
        lLink3Json = mMapper.writeValueAsString(lLink3);

        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink3Json);



    }

}