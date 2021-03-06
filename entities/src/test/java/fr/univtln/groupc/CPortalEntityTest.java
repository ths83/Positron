package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 20/05/2016.
 */
public class CPortalEntityTest extends TestCase {

    private ObjectMapper mObjectMapper = new ObjectMapper();

    public void testSerDeserListPortalsWithLinks() throws Exception {
        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder().build();
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder().build();
        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder().build();
        CLinkEntity lLink4 = new CLinkEntity.CLinkBuilder().build();
        /*
        List<CLinkEntity> lLinks = new ArrayList<CLinkEntity>();
        List<CPortalEntity> lPortals = new ArrayList<CPortalEntity>();
        lLinks.add(lLink1);
        lLinks.add(lLink2);

        List<CLinkEntity> lLinks2 = new ArrayList<CLinkEntity>();
        lLinks2.add(lLink3);
        lLinks2.add(lLink4);

        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(1).longitude(214.2).latitude(58.1).links(lLinks).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(2).longitude(145.2).latitude(210.9).links(lLinks2).build();
        lPortals.add(lPortal1);
        lPortals.add(lPortal2);

        String lJson = mObjectMapper.writeValueAsString(lPortals);
        CCrudMethods lCrud = new CCrudMethods();
        lCrud.*/

        //List<CPortalEntity> lPortalsGotten = mObjectMapper.readValue(lJson, mObjectMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
        //System.out.println("portails : \n" + mObjectMapper.readValue(lJson, mObjectMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class)));
        //System.out.println(lPortalsGotten);

    }

    public void testSerDeserPortalWithResonators() throws Exception {
        CResonatorEntity lReso = new CResonatorEntity.CResonatorBuilder().level(4).build();
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(1).longitude(214.2).latitude(58.1).build();
        lPortal1.addResonator(lReso);
        String lJson = mObjectMapper.writeValueAsString(lPortal1);

        CPortalEntity lPortalGotten = mObjectMapper.readValue(lJson, CPortalEntity.class);
        //System.out.println("portails : \n" + mObjectMapper.readValue(lJson, mObjectMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class)));
        System.out.println(lPortalGotten);

    }
}
