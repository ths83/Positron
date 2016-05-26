package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tperron710 on 25/05/16.
 */
public class CTestThomas2 {

    public static void main(String[] args) {
        CCrudMethods lCrud = new CCrudMethods();


        List<CResonatorEntity> lResonatorEntityList = new ArrayList<>();
        List<ABuildingEntity> lPortalBuilding = new ArrayList<>();

        List<CPortalEntity> lPortalLinked = new ArrayList<>();
        List<CPortalEntity> lPortalLinked2 = new ArrayList<>();
        List<CPortalEntity> lPortalLinked3 = new ArrayList<>();
        List<CPortalEntity> lPortalLinked4 = new ArrayList<>();
        List<CPortalEntity> lPortalLinked5 = new ArrayList<>();

        List<CKeyEntity> lKeyPortals1 = new ArrayList<>();
        CKeyEntity lKeyEntity = new CKeyEntity.CKeyBuilder(20000).build();
        lKeyPortals1.add(lKeyEntity);

        List<CKeyEntity> lKeyPortals2 = new ArrayList<>();
        CKeyEntity lKeyEntity2 = new CKeyEntity.CKeyBuilder(20010).build();
        lKeyPortals2.add(lKeyEntity2);

        List<CKeyEntity> lKeyPortals3 = new ArrayList<>();
        CKeyEntity lKeyEntity3 = new CKeyEntity.CKeyBuilder(20020).build();
        lKeyPortals3.add(lKeyEntity3);

        List<CKeyEntity> lKeyPortals4 = new ArrayList<>();
        CKeyEntity lKeyEntity4 = new CKeyEntity.CKeyBuilder(20030).build();
        lKeyPortals4.add(lKeyEntity4);

        List<CKeyEntity> lKeyPortals5 = new ArrayList<>();
        CKeyEntity lKeyEntity5 = new CKeyEntity.CKeyBuilder(20040).build();
        lKeyPortals4.add(lKeyEntity5);


        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(20030).latitude(40.1948).longitude(-73.22).keys(lKeyPortals1).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(20040).latitude(40.1948).longitude(-72).keys(lKeyPortals2).build();
        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(20050).latitude(40.1948).longitude(-71).keys(lKeyPortals3).build();
        CPortalEntity lPortal4 = new CPortalEntity.CPortalBuilder(20060).latitude(43.1356975).longitude(6.0187804).keys(lKeyPortals4).build();
        CPortalEntity lPortal5 = new CPortalEntity.CPortalBuilder(20070).latitude(43.1356975).longitude(6.1).keys(lKeyPortals5).build();
        lCrud.create(lPortal);
        lCrud.create(lPortal2);
        lCrud.create(lPortal3);
        lCrud.create(lPortal4);
        lCrud.create(lPortal5);

        CPlayerEntity lPlayer = lCrud.find(CPlayerEntity.class,1);
        lPlayer.addObjects(lKeyEntity5);
        lCrud.update(lPlayer);

        CTeamEntity lAtom = new CTeamEntity.CTeamBuilder(1).color("red").build();
        CTeamEntity lXenom = new CTeamEntity.CTeamBuilder(2).color("blue").build();

        CPortalEntity lPortalGotten = lCrud.find(CPortalEntity.class, 20030);
        CPortalEntity lPortalGotten2 = lCrud.find(CPortalEntity.class, 20040);
        CPortalEntity lPortalGotten3 = lCrud.find(CPortalEntity.class, 20050);

        lPortalLinked.add(lPortalGotten);
        lPortalLinked.add(lPortalGotten2);

        lPortalLinked2.add(lPortalGotten2);
        lPortalLinked2.add(lPortalGotten3);

        lPortalLinked3.add(lPortalGotten);
        lPortalLinked3.add(lPortalGotten3);

        CLinkEntity lLink = new CLinkEntity.CLinkBuilder(10).portals(lPortalLinked).build();
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(20).portals(lPortalLinked2).build();
        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(30).portals(lPortalLinked3).build();

        List<CLinkEntity> lLinkForField = new ArrayList<>();
        lLinkForField.add(lLink);
        lLinkForField.add(lLink2);
        lLinkForField.add(lLink3);

        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(100).build();
        CResonatorEntity lResonator2 = new CResonatorEntity.CResonatorBuilder(110).build();
        CKeyEntity lKey = new CKeyEntity.CKeyBuilder(5000).build();
        CTurretEntity lTurret = new CTurretEntity.CTurretBuilder(6000).build();
        CConsumableEntity lConsumable = new CConsumableEntity.CConsumableBuilder(2000).build();

        lResonatorEntityList.add(lResonator);
        lResonatorEntityList.add(lResonator2);
        lPortal.setResonators(lResonatorEntityList);

        //lPortalObject.add(lKey);
        //lPortalObject.add(lTurret);


        //lPortal.setObjects(lPortalObject);

        CFieldEntity lField = new CFieldEntity.CFieldBuilder(1000).links(lLinkForField).build();

        lLink.setField(lField);
        lLink2.setField(lField);
        lLink3.setField(lField);

        lPortal.setTeam(lAtom);
        lPortal2.setTeam(lAtom);
        lPortal3.setTeam(lAtom);

        //lCrud.create(lResonator);

        lCrud.create(lLink);
        lCrud.create(lLink2);
        lCrud.create(lLink3);
        lCrud.create(lField);

    }
}
