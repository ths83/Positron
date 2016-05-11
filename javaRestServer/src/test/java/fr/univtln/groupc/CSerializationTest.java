package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;
import org.junit.Test;
import junit.framework.TestCase;


import java.util.Arrays;
import java.util.List;

/**
 * Created by marti on 11/05/2016.
 */
public class CSerializationTest extends TestCase {

    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();

    public void testDeserializeListPortalFromJson() throws Exception{
        String lPortalsJson = mWebResource.path("/portals").accept("application/json").type("application/json").get(String.class);
        List<CPortalEntity> lPortals = mMapper.readValue(lPortalsJson, mMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
        System.out.println("portails : \n" + lPortals);
        assertFalse(lPortals == null);

    }

    public void testDeserializeListPortalFromJsonWithArraysAsList() throws Exception{
        String lPortalsJson = mWebResource.path("/portals").accept("application/json").type("application/json").get(String.class);
        List<CPortalEntity> lPortals = Arrays.asList(mMapper.readValue(lPortalsJson, CPortalEntity[].class));
        System.out.println("portails : \n" + lPortals);
        assertFalse(lPortals == null);

    }




}
