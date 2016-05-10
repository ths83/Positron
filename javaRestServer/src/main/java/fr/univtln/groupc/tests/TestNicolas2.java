package fr.univtln.groupc.tests;

import com.fasterxml.jackson.core.type.TypeReference;
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

    public static <T> T fromJSON(final TypeReference<T> type, final String jsonPacket) {
        T data = null;

        try {
            data = new ObjectMapper().readValue(jsonPacket, type);
        } catch (Exception e) {
            // Handle the problem
        }
        return data;
    }

    public static void main(String[] args) {
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);
        String lJson = webResource.path("/portals").get(String.class);
        List<CPortalEntity> lPortals = null;

        ObjectMapper lMapper = new ObjectMapper();
        lPortals = fromJSON(new TypeReference<List<CPortalEntity>>() {}, lJson);
        //lPortals = lMapper.readValue(lJson, List.class);
        System.out.println(lPortals);

        for (CPortalEntity lPort : lPortals){
            System.out.println(lPort.getId());
        }
    }
}
