package fr.univtln.groupc.server;

import com.sun.jersey.api.client.Client;
import javax.ws.rs.client.ClientBuilder;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CConsumableEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CSkillEntity;
import fr.univtln.groupc.entities.CTeamEntity;

import javax.ws.rs.client.ClientBuilder;
import java.util.List;

/**
 * Created by arouani277 on 03/05/16.
 */
public class CJavaClient {
    public static void main(String[] args) {
        // create the client
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);

       // Client client = ClientBuilder.newClient();

        CSkillEntity csk = new CSkillEntity.CSkillBuilder(5).cost(140).level(10).name("skillTest1").build();

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
        // !!!!!!!!!!! TEST FAILED !!!!!!!!!!!


        // TEST PERSIST CONSUMABLE + GET BY ID

        CConsumableEntity lConsumable = new CConsumableEntity.CConsumableBuilder(10).name("conso1").rarity(2).build();

        webResource.path("consumables/create").post(lConsumable);
        CConsumableEntity lConsumableGotten = webResource.path("consumables/10").get(CConsumableEntity.class);
        System.out.println(lConsumableGotten);

        // TEST SUCCESFUL
    }
}

