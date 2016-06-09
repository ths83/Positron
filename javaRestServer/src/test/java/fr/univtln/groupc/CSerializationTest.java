package fr.univtln.groupc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;
import org.junit.Test;
import junit.framework.TestCase;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marti on 11/05/2016.
 */
public class CSerializationTest extends TestCase {

    //Client c = Client.create();
    //WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();

    /*
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

    }*/

    public void testDeserializeAObjectEntityIntoCConsumableEntity() throws Exception {
        String lSerializedJsonObject = null;
        AObjectEntity lDeserializedObject = null;
        AObjectEntity lObject = new CConsumableEntity.CConsumableBuilder().rarity(3).name("le consommable").build();
        lSerializedJsonObject = mMapper.writeValueAsString(lObject);
        System.out.println("object serialized :\n" + lSerializedJsonObject);
        lDeserializedObject = mMapper.readValue(lSerializedJsonObject, AObjectEntity.class);
        System.out.println("object deserialized :\n" + lDeserializedObject);
        assertTrue("deserialisation object into consumable : OK", lDeserializedObject != null);
    }

    public void testDeserializeAObjectEntityIntoCKeyEntity() throws Exception {
        String lSerializedJsonObject = null;
        AObjectEntity lDeserializedObject = null;
        AObjectEntity lObject = new CKeyEntity.CKeyBuilder().name("le consommable").build();
        lSerializedJsonObject = mMapper.writeValueAsString(lObject);
        System.out.println("object serialized :\n" + lSerializedJsonObject);
        lDeserializedObject = mMapper.readValue(lSerializedJsonObject, AObjectEntity.class);
        System.out.println("object deserialized :\n" + lDeserializedObject);
        assertTrue("deserialisation object into consumable : OK", lDeserializedObject != null);
    }

    public void testDeserializeListOfAObjectEntitiesContainingOnlyConsumables() throws Exception {
        String lSerializedList = null;
        AObjectEntity lConsumable1 = new CConsumableEntity.CConsumableBuilder().rarity(2).name("le consommable 1").build();
        AObjectEntity lConsumable2 = new CConsumableEntity.CConsumableBuilder().rarity(3).name("le consommable 2").build();

        List<AObjectEntity> lDeserializedList = new ArrayList<>();
        List<AObjectEntity> lObjects = new ArrayList<>();
        //CTurretEntity lTurret = new CTurretEntity.CTurretBuilder(1).damage(15).level(12).name("la tourelle").build();
        lObjects.add(lConsumable1);
        lObjects.add(lConsumable2);
        //lObjects.add(lTurret);
        System.out.println("objects : \n" + lObjects);
        //lSerializedList = mMapper.writeValueAsString(lObjects);
        lSerializedList = mMapper.writerWithType(new TypeReference<List<AObjectEntity>>() {}).writeValueAsString(lObjects);
        System.out.println("objects serialized\n" + lSerializedList);
        lDeserializedList = mMapper.readValue(lSerializedList, mMapper.getTypeFactory().constructCollectionType(List.class, AObjectEntity.class));
        System.out.println("objects deserialized\n" + lDeserializedList);
        assertFalse(lDeserializedList == null);

    }


    public void testDeserializeListOfAObjectEntitiesIntoTheSubclassMatching() throws Exception {
        String lSerializedList = null;
        List<AObjectEntity> lDeserializedList = new ArrayList<>();
        List<AObjectEntity> lObjects = new ArrayList<>();
        AObjectEntity lKey = new CKeyEntity.CKeyBuilder().name("la clef").build();
        AObjectEntity lConsumable = new CConsumableEntity.CConsumableBuilder().rarity(3).name("le consommable").build();
        //CTurretEntity lTurret = new CTurretEntity.CTurretBuilder(1).damage(15).level(12).name("la tourelle").build();
        lObjects.add(lKey);
        lObjects.add(lConsumable);
        //lObjects.add(lTurret);
        System.out.println("objects : \n" + lObjects);
        //lSerializedList = mMapper.writeValueAsString(lObjects);
        lSerializedList = mMapper.writerWithType(new TypeReference<List<AObjectEntity>>() {}).writeValueAsString(lObjects);

        System.out.println("objects serialized\n" + lSerializedList);
        lDeserializedList = mMapper.readValue(lSerializedList, mMapper.getTypeFactory().constructCollectionType(List.class, AObjectEntity.class));
        System.out.println("objects deserialized\n" + lDeserializedList);
        assertFalse(lDeserializedList == null);

    }

    public void testSerializationOfPortal() throws Exception {
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        List<CResonatorEntity> lResonators = new ArrayList<>();
        CResonatorEntity lResonator1 = new CResonatorEntity.CResonatorBuilder().build();
        CResonatorEntity lResonator2 = new CResonatorEntity.CResonatorBuilder().build();
        lResonators.add(lResonator1);
        lResonators.add(lResonator2);
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(1).longitude(120.0).latitude(110).resonators(lResonators).build();
        String lSerializedPortal = mMapper.writeValueAsString(lPortal);
        System.out.println(lSerializedPortal);
        CPortalEntity lPortalGotten = mMapper.readValue(lSerializedPortal, CPortalEntity.class);
        System.out.println(lPortalGotten);
    }

    public void testSerializationOfListOfPortals() throws Exception {
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        List<CResonatorEntity> lResonators = new ArrayList<>();
        List<CPortalEntity> lPortals = new ArrayList<>();
        CResonatorEntity lResonator1 = new CResonatorEntity.CResonatorBuilder().build();
        CResonatorEntity lResonator2 = new CResonatorEntity.CResonatorBuilder().build();
        lResonators.add(lResonator1);
        lResonators.add(lResonator2);
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(1).longitude(120.0).latitude(110).resonators(lResonators).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(2).longitude(50.1).latitude(78.2).build();
        lPortals.add(lPortal);
        lPortals.add(lPortal2);
        String lSerializedPortal = mMapper.writeValueAsString(lPortals);
        System.out.println(lSerializedPortal);
        List<CPortalEntity> lPortalsGotten = mMapper.readValue(lSerializedPortal, mMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
        System.out.println(lPortalsGotten);

    }

    public void testDeserializeListOfPortalsFromGetAllRest() throws Exception {
        Client c = Client.create();
        WebResource lWebResource = c.resource(CServer.BASE_URI);
        String lJson = lWebResource.path("/portals").accept("application/json").type("application/json").get(String.class);
        System.out.println(lJson + "\n");
        List<CPortalEntity> lPortals = mMapper.readValue(lJson, mMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
        System.out.println(lPortals);
    }

    public void testSerializationOfPlayerWithAListOfObjects() throws Exception {
        List<AObjectEntity> lObjects = new ArrayList<>();
        AObjectEntity lObject = new CConsumableEntity.CConsumableBuilder().name("le bon consommable").build();
        AObjectEntity lObject2 = new CKeyEntity.CKeyBuilder().build();
        lObjects.add(lObject);
        lObjects.add(lObject2);
        CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(1).nickname("nicolas").objects(lObjects).build();
        String lSerializedList = null;
        lSerializedList = mMapper.writeValueAsString(lPlayer);
        CPlayerEntity lPlayerDeserialized = mMapper.readValue(lSerializedList, CPlayerEntity.class);
        System.out.println(lPlayerDeserialized);
    }

    public void testDeserializePlayer() throws Exception {
        CCrudMethods lCrud = new CCrudMethods();
        CPlayerEntity lPlayer = lCrud.find(CPlayerEntity.class, 1);
        String lJson = null;
        lJson = mMapper.writeValueAsString(lPlayer);
        System.out.println("ok 0 ?");

        CPlayerEntity lNewPlayer = mMapper.readValue(lJson, CPlayerEntity.class);
        System.out.println("ok ! " + lNewPlayer);
    }
}