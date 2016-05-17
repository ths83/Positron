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
        List<CPortalEntity> lPortalLinked = new ArrayList<>();
        List<CPortalEntity> lPortalLinked2 = new ArrayList<>();
        List<CPortalEntity> lPortalLinked3 = new ArrayList<>();
        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(2000).latitude(45.1948).longitude(5.6045).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(2001).latitude(43.1948).longitude(5.6045).build();
        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(2002).latitude(42.1948).longitude(6.6045).build();

        CTeamEntity lAtom = new CTeamEntity.CTeamBuilder(1).color("red").build();
        CTeamEntity lXenom = new CTeamEntity.CTeamBuilder(2).color("blue").build();

        lPortalLinked.add(lPortal);
        lPortalLinked.add(lPortal2);

        lPortalLinked2.add(lPortal2);
        lPortalLinked2.add(lPortal3);

        lPortalLinked3.add(lPortal);
        lPortalLinked3.add(lPortal3);

        CLinkEntity lLink = new CLinkEntity.CLinkBuilder(1).portals(lPortalLinked).build();
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(2).portals(lPortalLinked2).build();
        CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(3).portals(lPortalLinked3).build();

        List<CLinkEntity> lLinkForField = new ArrayList<>();
        lLinkForField.add(lLink);
        lLinkForField.add(lLink2);
        lLinkForField.add(lLink3);

        CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(10).build();
        CResonatorEntity lResonator2 = new CResonatorEntity.CResonatorBuilder(11).build();
        CKeyEntity lKey = new CKeyEntity.CKeyBuilder(500).build();
        CTurretEntity lTurret = new CTurretEntity.CTurretBuilder(600).build();
        CConsumableEntity lConsumable = new CConsumableEntity.CConsumableBuilder(200).build();

        lResonatorEntityList.add(lResonator);
        lResonatorEntityList.add(lResonator2);
        lPortal.setResonators(lResonatorEntityList);

        lPortalObject.add(lKey);
        lPortalObject.add(lTurret);
        lPortal.setObjects(lPortalObject);

        CFieldEntity lField = new CFieldEntity.CFieldBuilder(1).links(lLinkForField).build();

        lLink.setField(lField);
        lLink2.setField(lField);
        lLink3.setField(lField);

        lPortal.setTeam(lAtom);
        lPortal2.setTeam(lAtom);
        lPortal3.setTeam(lAtom);

        lCrud.create(lAtom);
        lCrud.create(lXenom);
        lCrud.create(lResonator);
        lCrud.create(lPortal);
        lCrud.create(lPortal2);
        lCrud.create(lPortal3);
        lCrud.create(lLink);
        lCrud.create(lLink2);
        lCrud.create(lLink3);
        lCrud.create(lField);

    }
}
