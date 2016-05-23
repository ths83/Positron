package fr.univtln.groupc.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
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

        CPortalEntity lPortalDeTest1 = new CPortalEntity.CPortalBuilder(1000).longitude(1000).latitude(2000).build();
        CPortalEntity lPortalDeTest2 = new CPortalEntity.CPortalBuilder(1001).longitude(3001).latitude(2002).build();
        CPortalEntity lPortalDeTest3 = new CPortalEntity.CPortalBuilder(1002).longitude(2001).latitude(110).build();
  /*      CPortalEntity lPortalDeTest4 = new CPortalEntity.CPortalBuilder(1003).longitude(1002).latitude(1201).build();
        CPortalEntity lPortalDeTest5 = new CPortalEntity.CPortalBuilder(1004).longitude(801).latitude(602).build();
        CPortalEntity lPortalDeTest6 = new CPortalEntity.CPortalBuilder(1005).longitude(1201).latitude(604).build();
*/
        CPortalEntity lPortalDeTest4 = new CPortalEntity.CPortalBuilder(1010).longitude(10020).latitude(12010).build();
        CPortalEntity lPortalDeTest5 = new CPortalEntity.CPortalBuilder(1011).longitude(80100).latitude(60200).build();
        CPortalEntity lPortalDeTest6 = new CPortalEntity.CPortalBuilder(1012).longitude(12010).latitude(60400).build();

        CPortalEntity lPortalDeTest7 = new CPortalEntity.CPortalBuilder(1020).longitude(100300).latitude(123100).build();
        CPortalEntity lPortalDeTest8 = new CPortalEntity.CPortalBuilder(1021).longitude(100200).latitude(120100).build();

        CPortalEntity lPortalDeTest9 = new CPortalEntity.CPortalBuilder(1030).longitude(8010000).latitude(6020000).build();
        CPortalEntity lPortalDeTest10 = new CPortalEntity.CPortalBuilder(1031).longitude(1201000).latitude(6040000).build();


        lCrud.create(lPortalDeTest1);
        lCrud.create(lPortalDeTest2);
        lCrud.create(lPortalDeTest3);
        lCrud.create(lPortalDeTest4);
        lCrud.create(lPortalDeTest5);
        lCrud.create(lPortalDeTest6);
        lCrud.create(lPortalDeTest7);
        lCrud.create(lPortalDeTest8);
        lCrud.create(lPortalDeTest9);
        lCrud.create(lPortalDeTest10);

        List<CPortalEntity> lList1_1 = new ArrayList<>();
        lList1_1.add(lPortalDeTest1);
        lList1_1.add(lPortalDeTest2);

        List<CPortalEntity> lList1_2 = new ArrayList<>();
        lList1_2.add(lPortalDeTest2);
        lList1_2.add(lPortalDeTest3);

        List<CPortalEntity> lList1_3 = new ArrayList<>();
        lList1_3.add(lPortalDeTest3);
        lList1_3.add(lPortalDeTest1);

        List<CPortalEntity> lList2_1 = new ArrayList<>();
        lList2_1.add(lPortalDeTest4);
        lList2_1.add(lPortalDeTest5);

        List<CPortalEntity> lList2_2 = new ArrayList<>();
        lList2_2.add(lPortalDeTest5);
        lList2_2.add(lPortalDeTest6);

        List<CPortalEntity> lList2_3 = new ArrayList<>();
        lList2_3.add(lPortalDeTest6);
        lList2_3.add(lPortalDeTest4);

        List<CPortalEntity> lList3 = new ArrayList<>();
        lList3.add(lPortalDeTest7);
        lList3.add(lPortalDeTest8);

        List<CPortalEntity> lList4 = new ArrayList<>();
        lList4.add(lPortalDeTest9);
        lList4.add(lPortalDeTest10);

        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(101).portals(lList1_1).build();


        String lJsonLinkToPost = null;


        System.out.println("Creation lien 1");
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ClientResponse lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);


        /////////////////////////////////////
        System.out.println("Creation lien 7");

        CLinkEntity lLink7 = new CLinkEntity.CLinkBuilder(120).portals(lList3).build();

        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink7);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);


        /////////////////////////////////////
        System.out.println("Creation lien 8");
        CLinkEntity lLink8 = new CLinkEntity.CLinkBuilder(130).portals(lList4).build();
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink8);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);


        /////////////////////////////////////
        System.out.println("Creation lien 2");

        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(102).portals(lList1_2).build();
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

   /////////////////////////////////////
        System.out.println("Creation lien 3");

        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(103).portals(lList1_3).build();

        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink3);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

        ///////////////////////////////////////
        System.out.println("Creation lien 4");

        CLinkEntity lLink4 = new CLinkEntity.CLinkBuilder(111).portals(lList2_1).build();

        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink4);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);


        ////////////////////////////////////
        System.out.println("Creation lien 5");


        CLinkEntity lLink5 = new CLinkEntity.CLinkBuilder(112).portals(lList2_2).build();
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink5);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

        ////////////////////////////////////
        System.out.println("Creation lien 6 & Field");
        CLinkEntity lLink6 = new CLinkEntity.CLinkBuilder(113).portals(lList2_3).build();
        try {
            lJsonLinkToPost = lMapper.writeValueAsString(lLink6);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);



    }


}
