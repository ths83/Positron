package fr.univtln.groupc;

import fr.univtln.groupc.algorithm.CAlgorithm;
import fr.univtln.groupc.entities.*;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdurbec066 on 11/05/16.
 */
public class CAlgorithmeTest  extends TestCase {

    public void testCrossingDetectionWith2LinksWhereCrossingOne() throws Exception {

        CPortalEntity lp1 =  new CPortalEntity.CPortalBuilder(1001).latitude(10).longitude(11).build();
        CPortalEntity lp2 =  new CPortalEntity.CPortalBuilder(1002).latitude(20).longitude(10).build();
        CPortalEntity lp3 =  new CPortalEntity.CPortalBuilder(2001).latitude(15).longitude(7).build();
        CPortalEntity lp4 =  new CPortalEntity.CPortalBuilder(2002).latitude(16).longitude(13).build();
        CPortalEntity lp5 =  new CPortalEntity.CPortalBuilder(3001).latitude(40).longitude(29).build();
        CPortalEntity lp6 =  new CPortalEntity.CPortalBuilder(3002).latitude(41).longitude(30).build();


        List<CPortalEntity> lLP1 = new ArrayList<>();
        lLP1.add(lp1);
        lLP1.add(lp2);

        List<CPortalEntity> lLP2 = new ArrayList<>();
        lLP2.add(lp3);
        lLP2.add(lp4);

        List<CPortalEntity> lLP3 = new ArrayList<>();
        lLP3.add(lp5);
        lLP3.add(lp6);

        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP1).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP2).build();
        CLinkEntity lL3= new CLinkEntity.CLinkBuilder(300).portals(lLP3).build();

        List<CLinkEntity> lListL1 = new ArrayList<>();
        lListL1.add(lL2);
        lListL1.add(lL3);


        assertFalse(CAlgorithm.detectLinkCollision(lL1, lListL1));
    }


    public void testCrossingDetectionWith2LinksWhereCrossingBoth() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(10).longitude(11).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(20).longitude(10).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(2001).latitude(15).longitude(7).build();
        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2002).latitude(16).longitude(13).build();
        CPortalEntity lp5 = new CPortalEntity.CPortalBuilder(3001).latitude(17).longitude(19).build();
        CPortalEntity lp6 = new CPortalEntity.CPortalBuilder(3002).latitude(19).longitude(2).build();

        List<CPortalEntity> lLP1 = new ArrayList<>();
        lLP1.add(lp1);
        lLP1.add(lp2);

        List<CPortalEntity> lLP2 = new ArrayList<>();
        lLP2.add(lp3);
        lLP2.add(lp4);

        List<CPortalEntity> lLP3 = new ArrayList<>();
        lLP3.add(lp5);
        lLP3.add(lp6);

        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP1).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP2).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP3).build();

        List<CLinkEntity> lListL1 = new ArrayList<>();
        lListL1.add(lL2);
        lListL1.add(lL3);

        assertFalse("Test OK", CAlgorithm.detectLinkCollision(lL1, lListL1));
    }

    public void testCrossingDetectionWith2LinksWhereCrossingNone() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(10).longitude(11).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(20).longitude(10).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(2001).latitude(8).longitude(12).build();
        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2002).latitude(7).longitude(9).build();
        CPortalEntity lp5 = new CPortalEntity.CPortalBuilder(3001).latitude(25).longitude(19).build();
        CPortalEntity lp6 = new CPortalEntity.CPortalBuilder(3002).latitude(22).longitude(2).build();


        List<CPortalEntity> lLP1 = new ArrayList<>();
        lLP1.add(lp1);
        lLP1.add(lp2);

        List<CPortalEntity> lLP2 = new ArrayList<>();
        lLP2.add(lp3);
        lLP2.add(lp4);

        List<CPortalEntity> lLP3 = new ArrayList<>();
        lLP3.add(lp5);
        lLP3.add(lp6);

        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP1).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP2).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP3).build();

        List<CLinkEntity> lListL1 = new ArrayList<>();
        lListL1.add(lL2);
        lListL1.add(lL3);

        assertTrue("Test OK", CAlgorithm.detectLinkCollision(lL1, lListL1));
    }

    public void testCrossingDetectionWithNoneField() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(10).longitude(11).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(20).longitude(10).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(8).longitude(12).build();

        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2001).latitude(20).longitude(21).build();
        CPortalEntity lp5 = new CPortalEntity.CPortalBuilder(2002).latitude(23).longitude(32).build();
        CPortalEntity lp6 = new CPortalEntity.CPortalBuilder(2003).latitude(31).longitude(33).build();

        CPortalEntity lp7 = new CPortalEntity.CPortalBuilder(3001).latitude(40).longitude(50).build();
        CPortalEntity lp8 = new CPortalEntity.CPortalBuilder(3002).latitude(41).longitude(30).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);

        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp1);

        List<CPortalEntity> lLP21 = new ArrayList<>();
        lLP21.add(lp4);
        lLP21.add(lp5);

        List<CPortalEntity> lLP22 = new ArrayList<>();
        lLP22.add(lp5);
        lLP22.add(lp6);

        List<CPortalEntity> lLP23 = new ArrayList<>();
        lLP23.add(lp4);
        lLP23.add(lp6);


        List<CPortalEntity> lLP31 = new ArrayList<>();
        lLP31.add(lp7);
        lLP31.add(lp8);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();

        CLinkEntity lL4 = new CLinkEntity.CLinkBuilder(400).portals(lLP21).build();
        CLinkEntity lL5 = new CLinkEntity.CLinkBuilder(500).portals(lLP22).build();
        CLinkEntity lL6 = new CLinkEntity.CLinkBuilder(600).portals(lLP23).build();

        CLinkEntity lL7 = new CLinkEntity.CLinkBuilder(700).portals(lLP31).build();


        List<CLinkEntity> lListL1 = new ArrayList<>();
        lListL1.add(lL1);
        lListL1.add(lL2);
        lListL1.add(lL3);

        List<CLinkEntity> lListL2 = new ArrayList<>();
        lListL2.add(lL4);
        lListL2.add(lL5);
        lListL2.add(lL6);

        List<CFieldEntity> lListField = new ArrayList<>();
        lListField.add(new CFieldEntity.CFieldBuilder(10).links(lListL1).build());
        lListField.add(new CFieldEntity.CFieldBuilder(20).links(lListL2).build());

        assertTrue("Test OK", CAlgorithm.detectFieldCollision(lL7.getPortals().get(0), lListField));
    }

    public void testCrossingDetectionInOneField() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(100).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(50).longitude(51).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(3).longitude(2).build();

        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2001).latitude(101).longitude(300).build();
        CPortalEntity lp5 = new CPortalEntity.CPortalBuilder(2002).latitude(200).longitude(600).build();
        CPortalEntity lp6 = new CPortalEntity.CPortalBuilder(2003).latitude(400).longitude(500).build();

        CPortalEntity lp7 = new CPortalEntity.CPortalBuilder(3001).latitude(10).longitude(45).build();
        CPortalEntity lp8 = new CPortalEntity.CPortalBuilder(3002).latitude(11).longitude(55).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);

        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp1);

        List<CPortalEntity> lLP21 = new ArrayList<>();
        lLP21.add(lp4);
        lLP21.add(lp5);

        List<CPortalEntity> lLP22 = new ArrayList<>();
        lLP22.add(lp5);
        lLP22.add(lp6);

        List<CPortalEntity> lLP23 = new ArrayList<>();
        lLP23.add(lp4);
        lLP23.add(lp6);


        List<CPortalEntity> lLP31 = new ArrayList<>();
        lLP31.add(lp7);
        lLP31.add(lp8);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();

        CLinkEntity lL4 = new CLinkEntity.CLinkBuilder(400).portals(lLP21).build();
        CLinkEntity lL5 = new CLinkEntity.CLinkBuilder(500).portals(lLP22).build();
        CLinkEntity lL6 = new CLinkEntity.CLinkBuilder(600).portals(lLP23).build();

        CLinkEntity lL7 = new CLinkEntity.CLinkBuilder(700).portals(lLP31).build();


        List<CLinkEntity> lListL1 = new ArrayList<>();
        lListL1.add(lL1);
        lListL1.add(lL2);
        lListL1.add(lL3);

        List<CLinkEntity> lListL2 = new ArrayList<>();
        lListL2.add(lL4);
        lListL2.add(lL5);
        lListL2.add(lL6);

        List<CFieldEntity> lListField = new ArrayList<>();
        lListField.add(new CFieldEntity.CFieldBuilder(10).links(lListL1).build());
        lListField.add(new CFieldEntity.CFieldBuilder(20).links(lListL2).build());

        assertFalse("Test OK", CAlgorithm.detectFieldCollision(lL7.getPortals().get(0), lListField));
    }

    public void testDetectionNewOneFieldFalse() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(3).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(2).longitude(52).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(50).longitude(53).build();
        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2001).latitude(51).longitude(4).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);

        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp4);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();


        assertEquals("Test OK", CAlgorithm.detecteNewFields(lL2).size(), 0);

    }

    public void testDetectionNewOneFieldTrue() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(3).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(2).longitude(52).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(50).longitude(53).build();
        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2001).latitude(51).longitude(4).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);

        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp1);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();


        assertEquals("Test OK", CAlgorithm.detecteNewFields(lL2).size() / 3, 1);

    }

    public void testDetectionNewTwoFieldsTrue() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(3).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(2).longitude(52).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(50).longitude(53).build();
        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2001).latitude(51).longitude(4).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);


        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp1);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);


        List<CPortalEntity> lLP21 = new ArrayList<>();
        lLP11.add(lp4);
        lLP11.add(lp3);

        List<CPortalEntity> lLP22 = new ArrayList<>();
        lLP12.add(lp4);
        lLP12.add(lp2);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();

        CLinkEntity lL4 = new CLinkEntity.CLinkBuilder(400).portals(lLP21).build();
        CLinkEntity lL5 = new CLinkEntity.CLinkBuilder(500).portals(lLP22).build();

        assertEquals("Test OK", CAlgorithm.detecteNewFields(lL2).size() / 3, 2);

    }


    public void testGetOtherPortalTrue() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(3).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(2).longitude(52).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();


        assertEquals("Test OK", CAlgorithm.getOtherPortalOfLink(lp1, lL1), lp2);

    }

    public void testConvertListLinkToOneFieldListTrue() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(100).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(50).longitude(51).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(3).longitude(2).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);

        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp1);

        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();


        List<CLinkEntity> lListL1 = new ArrayList<>();
        lListL1.add(lL1);
        lListL1.add(lL2);
        lListL1.add(lL3);


        assertEquals("Test OK", CAlgorithm.convertLinkListToFieldList(lListL1).size(), 1);

    }

    public void testConvertListLinkToTwoFieldListTrue() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(100).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(50).longitude(51).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(3).longitude(2).build();

        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2001).latitude(101).longitude(300).build();
        CPortalEntity lp5 = new CPortalEntity.CPortalBuilder(2002).latitude(200).longitude(600).build();
        CPortalEntity lp6 = new CPortalEntity.CPortalBuilder(2003).latitude(400).longitude(500).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);

        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp1);

        List<CPortalEntity> lLP21 = new ArrayList<>();
        lLP21.add(lp4);
        lLP21.add(lp5);

        List<CPortalEntity> lLP22 = new ArrayList<>();
        lLP22.add(lp5);
        lLP22.add(lp6);

        List<CPortalEntity> lLP23 = new ArrayList<>();
        lLP23.add(lp4);
        lLP23.add(lp6);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();

        CLinkEntity lL4 = new CLinkEntity.CLinkBuilder(400).portals(lLP21).build();
        CLinkEntity lL5 = new CLinkEntity.CLinkBuilder(500).portals(lLP22).build();
        CLinkEntity lL6 = new CLinkEntity.CLinkBuilder(600).portals(lLP23).build();


        List<CLinkEntity> lListL1 = new ArrayList<>();
        lListL1.add(lL1);
        lListL1.add(lL2);
        lListL1.add(lL3);
        lListL1.add(lL4);
        lListL1.add(lL5);
        lListL1.add(lL6);


        assertEquals("Test OK", CAlgorithm.convertLinkListToFieldList(lListL1).size(), 2);

    }

    public void testDetectInternalLinkTrue() throws Exception {

        CPortalEntity lp1 = new CPortalEntity.CPortalBuilder(1001).latitude(1).longitude(100).build();
        CPortalEntity lp2 = new CPortalEntity.CPortalBuilder(1002).latitude(50).longitude(51).build();
        CPortalEntity lp3 = new CPortalEntity.CPortalBuilder(1003).latitude(3).longitude(2).build();

        CPortalEntity lp4 = new CPortalEntity.CPortalBuilder(2001).latitude(9).longitude(30).build();
        CPortalEntity lp5 = new CPortalEntity.CPortalBuilder(2002).latitude(11).longitude(51).build();
        CPortalEntity lp6 = new CPortalEntity.CPortalBuilder(2003).latitude(8).longitude(80).build();


        List<CPortalEntity> lLP11 = new ArrayList<>();
        lLP11.add(lp1);
        lLP11.add(lp2);

        List<CPortalEntity> lLP12 = new ArrayList<>();
        lLP12.add(lp2);
        lLP12.add(lp3);

        List<CPortalEntity> lLP13 = new ArrayList<>();
        lLP13.add(lp3);
        lLP13.add(lp1);

        List<CPortalEntity> lLP21 = new ArrayList<>();
        lLP21.add(lp4);
        lLP21.add(lp5);

        List<CPortalEntity> lLP22 = new ArrayList<>();
        lLP22.add(lp5);
        lLP22.add(lp6);

        List<CPortalEntity> lLP23 = new ArrayList<>();
        lLP23.add(lp4);
        lLP23.add(lp6);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(100).portals(lLP11).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(200).portals(lLP12).build();
        CLinkEntity lL3 = new CLinkEntity.CLinkBuilder(300).portals(lLP13).build();

        CLinkEntity lL4 = new CLinkEntity.CLinkBuilder(400).portals(lLP21).build();
        CLinkEntity lL5 = new CLinkEntity.CLinkBuilder(500).portals(lLP22).build();


        List<CLinkEntity> lLinksForField = new ArrayList<>();
        lLinksForField.add(lL1);
        lLinksForField.add(lL2);
        lLinksForField.add(lL3);


        List<CLinkEntity> lLinks = new ArrayList<>();
        lLinks.add(lL1);
        lLinks.add(lL2);
        lLinks.add(lL3);
        lLinks.add(lL4);
        lLinks.add(lL5);


        CFieldEntity lField = new CFieldEntity.CFieldBuilder(12).links(lLinksForField).build();

        assertEquals("Test OK", CAlgorithm.detectInternalLink(lField, lLinks).size(), 2);

    }

    public void testCalculDeterminant() throws Exception {
        assertEquals("Test PAS OK", CAlgorithm.calculDetermiant(3, 2, 5, 3), -1.0);

    }


    public void testName() throws Exception {

        CTeamEntity t1 = new  CTeamEntity.CTeamBuilder(1).build();

        CPlayerEntity richard = new CPlayerEntity.CPlayerBuilder(45).team(t1).build();
        CResonatorEntity reso = new CResonatorEntity.CResonatorBuilder(46).owner(richard).build();
        System.out.println("dddd");
        assertEquals("dd",getTeamOfTarget(reso),1);
    }

    public void testName2() throws Exception {

        CTeamEntity t1 = new  CTeamEntity.CTeamBuilder(2).build();

        CPlayerEntity richard = new CPlayerEntity.CPlayerBuilder(45).team(t1).build();
       CShieldEntity shield = new CShieldEntity.CShieldBuilder(54).build();
        /*CTurretEntity turret = new CTurretEntity.CTurretBuilder(45).
        System.out.println("dddd");
        assertEquals("dd",getTeamOfTarget(reso),2);*/
    }


    public int getTeamOfTarget(ABuildingEntity BLBALBA) {
        System.out.println("Step 1");
        if(BLBALBA instanceof CResonatorEntity){
            System.out.println("Step 2");
            CResonatorEntity lR=(CResonatorEntity)BLBALBA;
            System.out.println("Step 3");
            return lR.getOwner().getTeam().getId();
        }
        else{
            return BLBALBA.getPortal().getTeam().getId();
        }
    }




}






