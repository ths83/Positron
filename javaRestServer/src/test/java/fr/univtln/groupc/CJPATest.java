package fr.univtln.groupc;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 18/05/2016.
 */
public class CJPATest extends TestCase {

    private CCrudMethods mCrud = new CCrudMethods();

    @Test
    public void testPostPortalWithResonators() throws Exception {
        CTurretEntity lTurret1 = new CTurretEntity.CTurretBuilder(365).build();
        List<ABuildingEntity> lBuildings = new ArrayList<>();
        lBuildings.add(lTurret1);
        CPortalEntity lPort1 = new CPortalEntity.CPortalBuilder(462).build();
        mCrud.create(lPort1);

        lTurret1.setPortal(lPort1);
        mCrud.create(lTurret1);

        CResonatorEntity lReso1 = new CResonatorEntity.CResonatorBuilder(451).portal(lPort1).build();
        CResonatorEntity lReso2 = new CResonatorEntity.CResonatorBuilder(453).portal(lPort1).build();

        System.out.println(lPort1.getResonators());

        mCrud.create(lReso1);
        mCrud.create(lReso2);
        System.out.println("done");
    }


    @Test
    public void testGetPortalWithResonators() throws Exception {
        CPortalEntity lPortGotten = mCrud.find(CPortalEntity.class, 462);
        if (lPortGotten == null){
            System.out.println("c est null");
        }
        else{
            System.out.println("-> " + lPortGotten);
        }
        System.out.println(lPortGotten.getResonators());

        System.out.println(lPortGotten.getResonators().size());

    }
/*
    public void testGetKeyByPlayers() throws Exception{
        CKeyEntity lKey1 = new CKeyEntity.CKeyBuilder(55).name("la clef numero 1").build();
        CKeyEntity lKey2 = new CKeyEntity.CKeyBuilder(56).name("la clef numero 2").build();
        CKeyEntity lKey3 = new CKeyEntity.CKeyBuilder(57).name("la clef numero 3").build();
        List<AObjectEntity> lObjects = new ArrayList<>();
        lObjects.add(lKey1);
        lObjects.add(lKey2);

        CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(41).nickname("raul").objects(lObjects).build();
        mCrud.create(lPlayer);
*/

    public void testDeleteLink() throws Exception {

        CLinkEntity lLink10 = mCrud.find(CLinkEntity.class, 10);
        CLinkEntity lLink20 = mCrud.find(CLinkEntity.class, 20);
        lLink10.setField(null);
        lLink20.setField(null);
        mCrud.update(lLink10);
        mCrud.update(lLink20);
        mCrud.delete(CLinkEntity.class, 30);
    }
}
