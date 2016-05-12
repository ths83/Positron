package fr.univtln.groupc.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
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
 * Created by xdurbec066 on 10/05/16.
 */
public class CTestXavier {

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

        CPortalEntity lPortalDeTest1 = new CPortalEntity.CPortalBuilder(1000).longitude(10).latitude(10).build();
        CPortalEntity lPortalDeTest2 = new CPortalEntity.CPortalBuilder(1001).longitude(1001).latitude(2002).build();
        CPortalEntity lPortalDeTest3 = new CPortalEntity.CPortalBuilder(1002).longitude(2001).latitude(11).build();
        CPortalEntity lPortalDeTest4 = new CPortalEntity.CPortalBuilder(1003).longitude(1002).latitude(1201).build();
        CPortalEntity lPortalDeTest5 = new CPortalEntity.CPortalBuilder(1004).longitude(801).latitude(602).build();
        CPortalEntity lPortalDeTest6 = new CPortalEntity.CPortalBuilder(1005).longitude(1201).latitude(604).build();


        lCrud.create(lPortalDeTest1);
        lCrud.create(lPortalDeTest2);
        lCrud.create(lPortalDeTest3);
        lCrud.create(lPortalDeTest4);
        lCrud.create(lPortalDeTest5);
        lCrud.create(lPortalDeTest6);

        List<CPortalEntity> lList1_1 = new ArrayList<>();
        lList1_1.add(lPortalDeTest1);
        lList1_1.add(lPortalDeTest2);

        List<CPortalEntity> lList1_2 = new ArrayList<>();
        lList1_2.add(lPortalDeTest1);
        lList1_2.add(lPortalDeTest3);

        List<CPortalEntity> lList1_3 = new ArrayList<>();
        lList1_3.add(lPortalDeTest2);
        lList1_3.add(lPortalDeTest3);

        List<CPortalEntity> lList2_1 = new ArrayList<>();
        lList2_1.add(lPortalDeTest4);
        lList2_1.add(lPortalDeTest5);

        List<CPortalEntity> lList2_2 = new ArrayList<>();
        lList2_2.add(lPortalDeTest5);
        lList2_2.add(lPortalDeTest6);

        List<CPortalEntity> lList2_3 = new ArrayList<>();
        lList2_3.add(lPortalDeTest6);
        lList2_3.add(lPortalDeTest4);

        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(101).portals(lList1_1).build();
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(102).portals(lList1_2).build();
//        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(103).portals(lList1_3).build();
        CLinkEntity lLink4 = new CLinkEntity.CLinkBuilder(111).portals(lList2_1).build();
        CLinkEntity lLink5 = new CLinkEntity.CLinkBuilder(112).portals(lList2_2).build();
        CLinkEntity lLink6 = new CLinkEntity.CLinkBuilder(113).portals(lList2_3).build();



        String lJsonLinkToPost = null;

        System.out.println("Creation lien 1");
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ClientResponse lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);


        /////////////////////////////////////
        System.out.println("Creation lien 2");
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

        ///////////////////////////////////////
        System.out.println("Creation lien 4");
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink4);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);


        ////////////////////////////////////
        System.out.println("Creation lien 5");
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink5);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

        ////////////////////////////////////
        System.out.println("Creation lien 6 & Field");
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink6);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);



    }


}
