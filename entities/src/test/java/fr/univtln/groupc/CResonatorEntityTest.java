package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CTurretEntity;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 20/05/2016.
 */
public class CResonatorEntityTest extends TestCase {


    private ObjectMapper mMapper = new ObjectMapper();
    /*private CCrudMethods mCrud = new CCrudMethods();

    public void testSerDeserResonator() throws Exception {
        System.out.println("pas d id");/*
        CResonatorEntity lReso = new CResonatorEntity.CResonatorBuilder().energy(50).energyMax(100).name("reso").build();
        String lJson = mMapper.writeValueAsString(lReso);
        System.out.println(lJson);*/


    public void testSerDeserTurret() throws Exception {
        System.out.println("pas d id");
        /*
        CTurretEntity lTurret = new CTurretEntity.CTurretBuilder().damage(50).build();
        String lJson = mMapper.writeValueAsString(lTurret);
        System.out.println(lTurret);*/
    }

    public void testSerDeserPortalWithResonators() throws Exception {
        CResonatorEntity lReso = new CResonatorEntity.CResonatorBuilder().name("reso1").build();
        CResonatorEntity lReso2 = new CResonatorEntity.CResonatorBuilder().name("reso2").build();
        List<CResonatorEntity> lResos = new ArrayList<CResonatorEntity>();
        lResos.add(lReso);
        lResos.add(lReso2);
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(5).resonators(lResos).build();
        //System.out.println(mMapper.writeValueAsString(lPortal));
        System.out.println(mMapper.writeValueAsString(lReso));
    }

    /*public void testgetPlayer1() throws Exception {
        CPlayerEntity lPlayer = mCrud.find(CPlayerEntity.class, 1);
        System.out.println("taille step 1 -> " + lPlayer.getObjects().size());
        System.out.println("objects step 1 -> " + lPlayer.getObjects());

        lPlayer.addObjects(new CResonatorEntity.CResonatorBuilder(786).level(4).build());

        mCrud.update(lPlayer);

        CPlayerEntity lPlayerGotten = mCrud.find(CPlayerEntity.class, 1);
        System.out.println("taille step 2 -> " + lPlayerGotten.getObjects().size());
        System.out.println("objects step 2 -> " + lPlayerGotten.getObjects());
    }*/
}
