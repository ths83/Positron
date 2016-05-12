package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by toms on 5/12/16.
 */
public class CTestThomas {

    public static void main(String[] args) {
        CCrudMethods lCrud = new CCrudMethods();
        List<CResonatorEntity> lResonatorEntityList = new ArrayList<>();
        List<AObjectEntity> lPortalObject = new ArrayList<>();
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(2000).latitude(45.1948).longitude(5.6045).build();

        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(10).build();
        CResonatorEntity lResonator2 = new CResonatorEntity.CResonatorBuilder(11).build();
        CKeyEntity lKey = new CKeyEntity.CKeyBuilder(500).build();
        CTurretEntity lTurret = new CTurretEntity.CTurretBuilder(500).build();
        CConsumableEntity lConsumable = new CConsumableEntity.CConsumableBuilder(200).build();

        lResonatorEntityList.add(lResonator);
        lResonatorEntityList.add(lResonator2);
        lPortal.setResonators(lResonatorEntityList);

        lPortalObject.add(lKey);
        lPortalObject.add(lTurret);
        lPortal.setObjects(lPortalObject);

        lCrud.create(lResonator);
        lCrud.create(lPortal);
    }
}
