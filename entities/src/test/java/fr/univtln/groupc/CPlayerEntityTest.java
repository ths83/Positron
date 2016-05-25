package fr.univtln.groupc;

import fr.univtln.groupc.entities.*;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 20/05/2016.
 */
public class CPlayerEntityTest extends TestCase {

    public void testGetResonatorById() throws Exception {
        List<AObjectEntity> lObjects = new ArrayList<AObjectEntity>();
        AObjectEntity lReso1 = new CResonatorEntity.CResonatorBuilder(1).name("myPortal1").level(3).build();
        AObjectEntity lReso2 = new CResonatorEntity.CResonatorBuilder(2).name("myPortal2").level(3).build();
        AObjectEntity lReso3 = new CResonatorEntity.CResonatorBuilder(3).name("myPortal3").level(3).build();
        AObjectEntity lReso4 = new CResonatorEntity.CResonatorBuilder(4).name("myPortal4").level(4).build();
        AObjectEntity lKey1 = new CKeyEntity.CKeyBuilder(5).name("myKey").build();
        lObjects.add(lReso1);
        lObjects.add(lReso2);
        lObjects.add(lReso3);
        lObjects.add(lReso4);
        lObjects.add(lKey1);
        CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(1).nickname("raul").objects(lObjects).build();
        assertEquals(3, lPlayer.getResonatorsByLevel(3).size());
        assertEquals(1, lPlayer.getResonatorsByLevel(4).size());
    }

    public void testGetTurretById() throws Exception {
        List<AObjectEntity> lObjects = new ArrayList<AObjectEntity>();
        AObjectEntity lReso1 = new CResonatorEntity.CResonatorBuilder(1).name("myPortal1").level(3).build();
        AObjectEntity lReso2 = new CResonatorEntity.CResonatorBuilder(2).name("myPortal2").level(3).build();
        AObjectEntity lReso3 = new CResonatorEntity.CResonatorBuilder(3).name("myPortal3").level(3).build();
        AObjectEntity lReso4 = new CResonatorEntity.CResonatorBuilder(4).name("myPortal4").level(4).build();
        AObjectEntity lKey1 = new CKeyEntity.CKeyBuilder(5).name("myKey").build();
        AObjectEntity lTurret1 = new CTurretEntity.CTurretBuilder(6).name("myTurret1").level(3).build();
        AObjectEntity lTurret2 = new CTurretEntity.CTurretBuilder(7).name("myTurret2").level(3).build();
        AObjectEntity lTurret3 = new CTurretEntity.CTurretBuilder(8).name("myTurret3").level(3).build();
        AObjectEntity lTurret4 = new CTurretEntity.CTurretBuilder(9).name("myTurret4").level(4).build();


        lObjects.add(lReso1);
        lObjects.add(lReso2);
        lObjects.add(lReso3);
        lObjects.add(lReso4);
        lObjects.add(lKey1);
        lObjects.add(lTurret1);
        lObjects.add(lTurret2);
        lObjects.add(lTurret3);
        lObjects.add(lTurret4);
        CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(1).nickname("raul").objects(lObjects).build();
        assertEquals(3, lPlayer.getTurretsByLevel(3).size());
        assertEquals(1, lPlayer.getTurretsByLevel(4).size());
    }
}
