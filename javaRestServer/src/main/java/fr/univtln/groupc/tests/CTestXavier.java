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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdurbec066 on 10/05/16.
 */
public class CTestXavier {

    public static void main(String[] args) {
        Client c = Client.create();
        WebResource mWebResource = c.resource(CServer.BASE_URI);
        ObjectMapper mMapper = new ObjectMapper();
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        CCrudMethods lCrud = new CCrudMethods();

        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(123).latitude(1.0).longitude(1.0).build();
        CPortalEntity lP2 = new CPortalEntity.CPortalBuilder(234).latitude(2.0).longitude(2.0).build();
        CPortalEntity lP3 = new CPortalEntity.CPortalBuilder(345).latitude(1.0).longitude(2.0).build();

        List<CPortalEntity> lPortals1 = new ArrayList<>();
        lPortals1.add(lP1);
        lPortals1.add(lP2);

        List<CPortalEntity> lPortals2 = new ArrayList<>();
        lPortals2.add(lP2);
        lPortals2.add(lP3);


        CLinkEntity lL1= new CLinkEntity.CLinkBuilder(123).portals(lPortals1).build();
        CLinkEntity lL2= new CLinkEntity.CLinkBuilder(124).portals(lPortals2).build();


        lCrud.create(lP1);
        lCrud.create(lP2);
        lCrud.create(lP3);
        lCrud.create(lL1);
        lCrud.create(lL2);


        List<CPortalEntity> lPortals3 = new ArrayList<>();
        lPortals3.add(lP1);
        lPortals3.add(lP3);
        CLinkEntity lL3= new CLinkEntity.CLinkBuilder(123).portals(lPortals3).build();
        System.out.println(lL3);

        //lCrud.create(lL3);

        String lLinkJson = null;
        try {
            lLinkJson = mMapper.writeValueAsString(lL3);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(lLinkJson);
        ClientResponse lResponse = mWebResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lLinkJson);



    }

}
