package fr.univtln.groupc.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 06/05/2016.
 */
public class TestNicolas2 {


    public static void main(String[] args) {
        CCrudMethods lCrud = new CCrudMethods();

        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);
        String lJson = webResource.path("/portals").get(String.class);
        List<CPortalEntity> lPortals = null;

        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            lPortals = lMapper.readValue(lJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //lPortals = lMapper.readValue(lJson, List.class);
        System.out.println(lPortals);

        for (CPortalEntity lPort : lPortals){
            System.out.println(lPort.getId());
        }

        CPortalEntity lPortalDeTest1 = new CPortalEntity.CPortalBuilder(77).longitude(200).latitude(200).build();
        CPortalEntity lPortalDeTest2 = new CPortalEntity.CPortalBuilder(78).longitude(300).latitude(300).build();
        CPortalEntity lPortalDeTest3 = new CPortalEntity.CPortalBuilder(79).longitude(200).latitude(300).build();

        List<CPortalEntity> lList1_2 = new ArrayList<>();
        lList1_2.add(lPortalDeTest1);
        lList1_2.add(lPortalDeTest2);

        List<CPortalEntity> lList1_3 = new ArrayList<>();
        lList1_3.add(lPortalDeTest1);
        lList1_3.add(lPortalDeTest3);

        List<CPortalEntity> lList2_3 = new ArrayList<>();
        lList2_3.add(lPortalDeTest2);
        lList2_3.add(lPortalDeTest3);

        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(77).portals(lList1_2).build();
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(78).portals(lList1_3).build();

        lCrud.create(lPortalDeTest1);
        lCrud.create(lPortalDeTest2);
        lCrud.create(lPortalDeTest3);
        lCrud.create(lLink1);
        lCrud.create(lLink2);

        String lJsonPortal1 = null;

        try {
            lJsonPortal1 = lMapper.writeValueAsString(lPortalDeTest1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(lJsonPortal1);

        CPortalEntity lPortalGotten = null;
        try {
            lPortalGotten = lMapper.readValue(lJsonPortal1, CPortalEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lPortalGotten);
        System.out.println(lPortalGotten.getLinks());

        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(80).portals(lList2_3).build();

        String lJsonLinkToPost = null;
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink3);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ClientResponse lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);



    }
}
