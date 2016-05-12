package fr.univtln.groupc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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
        AObjectEntity lObject = new CConsumableEntity.CConsumableBuilder(1).rarity(3).name("le consommable").build();
        lSerializedJsonObject = mMapper.writeValueAsString(lObject);
        System.out.println("object serialized :\n" + lSerializedJsonObject);
        lDeserializedObject = mMapper.readValue(lSerializedJsonObject, AObjectEntity.class);
        System.out.println("object deserialized :\n" + lDeserializedObject);
        assertTrue("deserialisation object into consumable : OK", lDeserializedObject != null);
    }

    public void testDeserializeAObjectEntityIntoCKeyEntity() throws Exception {
        String lSerializedJsonObject = null;
        AObjectEntity lDeserializedObject = null;
        AObjectEntity lObject = new CKeyEntity.CKeyBuilder(1).name("le consommable").build();
        lSerializedJsonObject = mMapper.writeValueAsString(lObject);
        System.out.println("object serialized :\n" + lSerializedJsonObject);
        lDeserializedObject = mMapper.readValue(lSerializedJsonObject, AObjectEntity.class);
        System.out.println("object deserialized :\n" + lDeserializedObject);
        assertTrue("deserialisation object into consumable : OK", lDeserializedObject != null);
    }

    public void testDeserializeListOfAObjectEntitiesContainingOnlyConsumables() throws Exception {
        String lSerializedList = null;
        AObjectEntity lConsumable1 = new CConsumableEntity.CConsumableBuilder(1).rarity(2).name("le consommable 1").build();
        AObjectEntity lConsumable2 = new CConsumableEntity.CConsumableBuilder(2).rarity(3).name("le consommable 2").build();

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
        AObjectEntity lKey = new CKeyEntity.CKeyBuilder(1).name("la clef").build();
        AObjectEntity lConsumable = new CConsumableEntity.CConsumableBuilder(1).rarity(3).name("le consommable").build();
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
}
