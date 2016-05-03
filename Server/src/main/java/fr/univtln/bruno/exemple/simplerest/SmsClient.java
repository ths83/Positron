package fr.univtln.bruno.exemple.simplerest;

/**
 * Created by bruno on 04/11/14.
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class SmsClient {
    public static void main(String[] args) {
        // create the client
        Client c = Client.create();
        WebResource webResource = c.resource(Main.BASE_URI);

        //Send a get with a String as response
        String responseSmsAsJson = webResource.path("bank/allsms").get(String.class);
        System.out.println(responseSmsAsJson);


        //Idem but the result is deserialised to an instance of Auteur
        CSmsReceived smsReceived = webResource.path("bank/sms/1").get(CSmsReceived.class);
        System.out.println(smsReceived);


        webResource.path("bank/sms").queryParam("Data","X").queryParam("Data","Y").put();
        System.out.println(webResource.path("bank/allsms").get(String.class));

    }
}
