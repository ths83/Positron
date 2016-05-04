package fr.univtln.groupc.server;

import com.sun.jersey.api.client.Client;
import javax.ws.rs.client.ClientBuilder;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CSkillEntity;

import javax.ws.rs.client.ClientBuilder;

/**
 * Created by arouani277 on 03/05/16.
 */
public class CJavaClient {
    public static void main(String[] args) {
        // create the client
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);

       // Client client = ClientBuilder.newClient();

        CSkillEntity csk = new CSkillEntity.CSkillBuilder(5).cost(140).level(10).name("ntm").build();

        webResource.path("skills/create").post(csk);

        //Send a get with a String as response
        String responseSkillsAsJson = webResource.path("skills/5").get(String.class);
        System.out.println(responseSkillsAsJson);

        //Send a get with a String as response
        String responseSmsAsJson = webResource.path("bank/allsms").get(String.class);
        System.out.println(responseSmsAsJson);


        //Idem but the result is deserialised to an instance of Auteur
        /*
        CSmsReceived smsReceived = webResource.path("bank/sms/1").get(CSmsReceived.class);
        System.out.println(smsReceived);
        */

        webResource.path("bank/sms").queryParam("Data","X").queryParam("Data","Y").put();
        System.out.println(webResource.path("bank/allsms").get(String.class));

    }
}

