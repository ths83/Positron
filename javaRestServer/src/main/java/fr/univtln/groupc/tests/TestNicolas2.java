package fr.univtln.groupc.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;

import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 06/05/2016.
 */
public class TestNicolas2 {

    public static void main(String[] args) {
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);
        String lJson = webResource.path("/portals/all").get(String.class);

        ObjectMapper lMapper = new ObjectMapper();
        try {
            List<CPortalEntity> lPortals = lMapper.readValue(lJson, List.class);
            System.out.println(lPortals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
