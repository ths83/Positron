package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 24/05/16.
 */
public class CRestLinkTest extends TestCase {
    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();
    ClientResponse lClientResponse, lResponse;
    String mJson;


    public void testPostLinkService() throws Exception {
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(700).longitude(150).latitude(150).build();
        ArrayList <CPortalEntity> portals = new ArrayList<>();
        portals.add(lPortal1);

        CLinkEntity lLinkEntity = new CLinkEntity.CLinkBuilder().portals(portals).build();
        mJson = mMapper.writeValueAsString(lLinkEntity);
        ClientResponse lResponse = mWebResource.path("/links").type("application/json").accept("application/json").post(ClientResponse.class, mJson);
        assertEquals(lResponse.getStatus(), 201);
    }

    public void testGetLinkService() throws Exception {
        CLinkEntity lLinkEntity = mMapper.readValue(mWebResource.path("/links/1").get(String.class), CLinkEntity.class);
        assertEquals(lLinkEntity.getId(), 1);
    }
/*
    public void testKeyLinkService() throws Exception {
        ClientResponse clientResponse = mWebResource.path("/links/1").type("application/json").accept("application/json").delete(ClientResponse.class);
        assertEquals(clientResponse.getStatus(), 200);
    }
    */
    /*
public void testPostLinkServiceWhen3rdOfAFieldToCreate() throws Exception {
    CCrudMethods lCrud = new CCrudMethods();
    String lLink1Json = null;
    String lLink2Json = null;
    String lLink3Json = null;
    CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(700).longitude(150).latitude(150).build();
    CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(701).longitude(450).latitude(152).build();
    CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(702).longitude(300).latitude(400).build();
    List<CPortalEntity> lPortals1_2 = new ArrayList<>();
    lPortals1_2.add(lPortal1);
    lPortals1_2.add(lPortal2);

    List<CPortalEntity> lPortals1_3 = new ArrayList<>();
    lPortals1_3.add(lPortal1);
    lPortals1_3.add(lPortal3);

    List<CPortalEntity> lPortals2_3 = new ArrayList<>();
    lPortals2_3.add(lPortal2);
    lPortals2_3.add(lPortal3);

    System.out.println("print 1");
    lCrud.create(lPortal1);
    System.out.println("print 2");
    lCrud.create(lPortal2);
    System.out.println("print 3");
    lCrud.create(lPortal3);


    CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(800).portals(lPortals1_2).build();
    System.out.println("print 4");
    lLink1Json = mMapper.writeValueAsString(lLink1);
    mWebResource.path("/links").accept("application/json").type("application/json").post(lLink1Json);
    //lCrud.create(lLink1);


    System.out.println("print 5");
    CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(801).portals(lPortals1_3).build();
    lLink2Json = mMapper.writeValueAsString(lLink2);
    mWebResource.path("/links").accept("application/json").type("application/json").post(lLink2Json);
    //lCrud.create(lLink2);


    System.out.println("print 6");
    CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(802).portals(lPortals2_3).build();
    lLink3Json = mMapper.writeValueAsString(lLink3);

    mWebResource.path("/links").accept("application/json").type("application/json").post(lLink3Json);
}
    /*
    public void testPostLinkServiceWhenAFieldEnglobeOtherLink() throws Exception {
        CCrudMethods lCrud = new CCrudMethods();
        String lLink1Json = null;
        String lLink2Json = null;
        String lLink3Json = null;
        String lLink4Json = null;
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(800).longitude(151).latitude(150).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(801).longitude(450).latitude(152).build();

        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(802).longitude(300).latitude(400).build();

        CPortalEntity lPortal4 = new CPortalEntity.CPortalBuilder(810).longitude(299).latitude(350).build();
        CPortalEntity lPortal5 = new CPortalEntity.CPortalBuilder(811).longitude(301).latitude(200).build();

        List<CPortalEntity> lPortals1_2 = new ArrayList<>();
        lPortals1_2.add(lPortal1);
        lPortals1_2.add(lPortal2);

        List<CPortalEntity> lPortals1_3 = new ArrayList<>();
        lPortals1_3.add(lPortal1);
        lPortals1_3.add(lPortal3);

        List<CPortalEntity> lPortals2_3 = new ArrayList<>();
        lPortals2_3.add(lPortal2);
        lPortals2_3.add(lPortal3);

        List<CPortalEntity> lPortals10_11 = new ArrayList<>();
        lPortals10_11.add(lPortal4);
        lPortals10_11.add(lPortal5);



        System.out.println("print P1");
        lCrud.create(lPortal1);
        System.out.println("print P2");
        lCrud.create(lPortal2);
        System.out.println("print P3");
        lCrud.create(lPortal3);
        System.out.println("print P4");
        lCrud.create(lPortal4);
        System.out.println("print P5");
        lCrud.create(lPortal5);



        System.out.println("print L 10 11");
        CLinkEntity lLink4 = new CLinkEntity.CLinkBuilder(910).portals(lPortals10_11).build();
        lLink4Json = mMapper.writeValueAsString(lLink4);

        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink4Json);


        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(900).portals(lPortals1_2).build();
        System.out.println("print L 1 2");
        lLink1Json = mMapper.writeValueAsString(lLink1);
        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink1Json);
        //lCrud.create(lLink1);


        System.out.println("print L 1 3");
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(901).portals(lPortals1_3).build();
        lLink2Json = mMapper.writeValueAsString(lLink2);
        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink2Json);
        //lCrud.create(lLink2);


        System.out.println("print L 2 3");
        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(902).portals(lPortals2_3).build();
        lLink3Json = mMapper.writeValueAsString(lLink3);

        mWebResource.path("/links").accept("application/json").type("application/json").post(lLink3Json);



    }*/


}
