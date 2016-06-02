package fr.univtln.groupc.server;

import com.sun.jersey.api.client.Client;
import javax.ws.rs.client.ClientBuilder;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.*;

import javax.ws.rs.client.ClientBuilder;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
public class CJavaClient {
    public static void main(String[] args) {
        // create the client
        CCrudMethods lCrud = new CCrudMethods();
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);

       // Client client = ClientBuilder.newClient();

        CSkillEntity lSkill = new CSkillEntity.CSkillBuilder(5).cost(140).level(10).name("skillTest1").build();

        //webResource.path("skills/create").post(csk);

        //Send a get with a String as response
        /*
        String responseSkillsAsJson = webResource.path("skills/5").get(String.class);
        System.out.println(responseSkillsAsJson);
*/
        //Send a get with a String as response
         /*
        String responseSmsAsJson = webResource.path("bank/allsms").get(String.class);
        System.out.println(responseSmsAsJson);*/

        //Idem but the result is deserialised to an instance of Auteur
        /*
        CSmsReceived smsReceived = webResource.path("bank/sms/1").get(CSmsReceived.class);
        System.out.println(smsReceived);
        */
/*
        webResource.path("bank/sms").queryParam("Data","X").queryParam("Data","Y").put();
        System.out.println(webResource.path("bank/allsms").get(String.class));*/

        /*
         * TESTS REST PORTAL
         */

        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(80).longitude(80).latitude(80).build();
        //webResource.path("portals/create").post(lPortal);
        // TEST GET PORTAL BY ID
        CPortalEntity lPortalGotten = webResource.path("portals/80").get(CPortalEntity.class);
        System.out.println(lPortalGotten);
        // TEST SUCCESFULL

        // TEST GET ALL PORTALS
        System.out.println(webResource.path("portals/all").get(List.class));
        // TEST SUCCESFULL

        // TEST GET TEAM BY ID
        CTeamEntity lTeamGotten = webResource.path("teams/1").get(CTeamEntity.class);
        System.out.println(lTeamGotten);
        // TEST SUCCESFUL

        lPortalGotten.setTeam(lTeamGotten);
        // TEST UPDATE PORTAL WITH A TEAM
        webResource.path("portals/put").put(lPortalGotten);

        lPortalGotten = webResource.path("portals/80").get(CPortalEntity.class);
        System.out.println("apres update -> ");
        System.out.println(lPortalGotten);
        // TEST SUCCESFUL

        // TEST TEAM LIST PORTALS AFTER UPDATING PORTAL
        System.out.println(webResource.path("teams/1").get(CTeamEntity.class));
        // TEST SUCCESFUL



        // TEST PERSIST CONSUMABLE + GET BY ID

        CConsumableEntity lConsumable = new CConsumableEntity.CConsumableBuilder(10).name("conso1").rarity(2).build();

        //webResource.path("consumables/create").post(lConsumable);
        CConsumableEntity lConsumableGotten = webResource.path("consumables/10").get(CConsumableEntity.class);
        System.out.println(lConsumableGotten);
        // TEST SUCCESFUL

        // TEST NAMED QUERY GET PORTAL BY TEAM

        System.out.println(lCrud.findWithNamedQuery(CPortalEntity.GET_BY_TEAM, CQueryParameter.with("mId", 1).parameters()));
        System.out.println(webResource.path("portals/teams/1").get(List.class));
        // TEST SUCCESFUL

        // TEST UPDATE CONSUMABLE

        lConsumable.setRarity(3);
        webResource.path("consumables/put").put(lConsumable);
        lConsumable = webResource.path("consumables/10").get(CConsumableEntity.class);
        System.out.println(lConsumable);

        // TEST SUCCESFUL

        // TEST SKILLS
        CSkillEntity lSkill10 = new CSkillEntity.CSkillBuilder(10).name("skill10").cost(15).level(2).build();
        webResource.path("skills/create").post(lSkill10);
        // TEST SUCCESFUL

        // TEST SKILLS BY LEVEL
        System.out.println(webResource.path("skills/level/2").get(List.class));
        // TEST SUCCESFUL

        // TEST CREATE PLAYER

        CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(7).nickname("raul").email("gonzalesblancoraul@gmail.com").xp(150).energy(800).energyMax(800).build();
        lCrud.create(lPlayer);

        // TEST SUCCESFUL

        // TEST GET PLAYER BY ID
        CPlayerEntity lPlayerGotten = lCrud.find(CPlayerEntity.class, 7);
        System.out.println(lPlayerGotten);
        lPlayerGotten.addSkill(lSkill10);
        lCrud.update(lPlayerGotten);
        System.out.println(lCrud.find(CPlayerEntity.class, 7));
        // TEST SUCCESFUL

        // TEST CREATE RESONATOR
        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(50).name("reso_de_test").level(3).energyMax(40).energy(30).radius(45).build();
        lCrud.create(lResonator);
        // TEST SUCCESFUL

        // TEST GET RESONATOR BY ID
        CResonatorEntity lResonatorGotten = lCrud.find(CResonatorEntity.class, 50);
        // TEST SUCCESFUL

        // TEST UPDATE RESONATOR OWNER
        lResonatorGotten.setOwner(lPlayerGotten);
        lCrud.update(lResonatorGotten);
        // TEST SUCCESFUL



    }
}

