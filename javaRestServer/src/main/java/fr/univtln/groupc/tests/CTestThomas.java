package fr.univtln.groupc.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toms on 5/12/16.
 */
public class CTestThomas {

    public static void main(String[] args) {
        CCrudMethods lCrud = new CCrudMethods();


        List<CResonatorEntity> lResonatorEntityList = new ArrayList<>();
        List<ABuildingEntity> lPortalBuilding = new ArrayList<>();

        List<CPortalEntity> lPortalLinked = new ArrayList<>();
        List<CPortalEntity> lPortalLinked2 = new ArrayList<>();
        List<CPortalEntity> lPortalLinked3 = new ArrayList<>();
        List<CPortalEntity> lPortalLinked4 = new ArrayList<>();

        List<CKeyEntity> lKeyPortals1 = new ArrayList<>();
        CKeyEntity lKeyEntity = new CKeyEntity.CKeyBuilder(2000).build();
        lKeyPortals1.add(lKeyEntity);

        List<CKeyEntity> lKeyPortals2 = new ArrayList<>();
        CKeyEntity lKeyEntity2 = new CKeyEntity.CKeyBuilder(2001).build();
        lKeyPortals2.add(lKeyEntity2);

        List<CKeyEntity> lKeyPortals3 = new ArrayList<>();
        CKeyEntity lKeyEntity3 = new CKeyEntity.CKeyBuilder(2002).build();
        lKeyPortals3.add(lKeyEntity3);

/*
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(2003).latitude(45.1948).longitude(5.6045).keys(lKeyPortals1).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(2004).latitude(43.1948).longitude(5.6045).keys(lKeyPortals2).build();
        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(2005).latitude(42.1948).longitude(6.6045).keys(lKeyPortals3).build();
*/


        CTeamEntity lAtom = lCrud.find(CTeamEntity.class,1);
        CTeamEntity lXenom = lCrud.find(CTeamEntity.class, 2);

        CPlayerEntity lPlayer = lCrud.find(CPlayerEntity.class,1);

        CPortalEntity lPortalGotten = lCrud.find(CPortalEntity.class, 1);
        CPortalEntity lPortalGotten2 = lCrud.find(CPortalEntity.class, 2);
        CPortalEntity lPortalGotten3 = lCrud.find(CPortalEntity.class, 3);
        CPortalEntity lPortalGotten4 = lCrud.find(CPortalEntity.class, 4);
        CPortalEntity lPortalGotten5 = lCrud.find(CPortalEntity.class, 5);


        lPortalLinked.add(lPortalGotten);
        lPortalLinked.add(lPortalGotten2);

        lPortalLinked2.add(lPortalGotten2);
        lPortalLinked2.add(lPortalGotten3);

        lPortalLinked3.add(lPortalGotten);
        lPortalLinked3.add(lPortalGotten3);

        lPortalLinked4.add(lPortalGotten4);
        lPortalLinked4.add(lPortalGotten5);

        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(10).owner(lPlayer).build();
        CResonatorEntity lResonator2 = new CResonatorEntity.CResonatorBuilder(11).owner(lPlayer).build();
        CResonatorEntity lResonator3 = new CResonatorEntity.CResonatorBuilder(12).owner(lPlayer).build();

        CKeyEntity lKey = new CKeyEntity.CKeyBuilder(500).build();
        CTurretEntity lTurret = new CTurretEntity.CTurretBuilder(600).build();
        CConsumableEntity lConsumable = new CConsumableEntity.CConsumableBuilder(200).build();



        lPortalGotten.addResonator(lResonator);
        lPortalGotten2.addResonator(lResonator2);
        lPortalGotten3.addResonator(lResonator3);

        lPortalGotten.attributeTeam();
        lPortalGotten2.attributeTeam();
        lPortalGotten3.attributeTeam();

        lCrud.update(lPortalGotten);
        lCrud.update(lPortalGotten2);
        lCrud.update(lPortalGotten3);

        lCrud.create(lLink);
        lCrud.create(lLink2);
        lCrud.create(lLink3);
        lCrud.create(lLink4);


        CLinkEntity lLink = new CLinkEntity.CLinkBuilder(1).portals(lPortalLinked).build();
        String lJsonLinkToPost = null ;
        ObjectMapper lMapper = new ObjectMapper();
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);
        try {

            lJsonLinkToPost = lMapper.writeValueAsString(lLink);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ClientResponse lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(2).portals(lPortalLinked2).build();
        try {

            lJsonLinkToPost = lMapper.writeValueAsString(lLink2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(3).portals(lPortalLinked3).build();

        try {

            lJsonLinkToPost = lMapper.writeValueAsString(lLink3);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);

        CLinkEntity lLink4 = new CLinkEntity.CLinkBuilder(4).portals(lPortalLinked4).build();

        try {

            lJsonLinkToPost = lMapper.writeValueAsString(lLink4);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        lResponsePostLink = webResource.path("/links").accept("application/json").type("application/json").post(ClientResponse.class, lJsonLinkToPost);


    }
}
