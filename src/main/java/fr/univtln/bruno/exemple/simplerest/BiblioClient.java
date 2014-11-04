package fr.univtln.bruno.exemple.simplerest;

/**
 * Created by bruno on 04/11/14.
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.bruno.d14.simpleihm.Auteur;

public class BiblioClient {
    public static void main(String[] args) {
        // create the client
        Client c = Client.create();
        WebResource webResource = c.resource(Main.BASE_URI);

        //Send a get with a String as response
        String responseAuteursAsJson = webResource.path("biblio/auteurs").get(String.class);
        System.out.println(responseAuteursAsJson);

        //Idem but the result is deserialised to an instance of Auteur
        Auteur auteur = webResource.path("biblio/auteur/1").get(Auteur.class);
        System.out.println(auteur);

        webResource.path("biblio/auteur").queryParam("nom","X").queryParam("prenom","Y").put();
        System.out.println(webResource.path("biblio/auteurs").get(String.class));
    }
}
