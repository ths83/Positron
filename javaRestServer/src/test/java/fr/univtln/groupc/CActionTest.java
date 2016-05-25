package fr.univtln.groupc;

import fr.univtln.groupc.algorithm.CAlgorithm;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.tests.CTestCActions;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdurbec066 on 25/05/16.
 */
public class CActionTest extends TestCase {

    public void testCreatResonatorGoodIssue() throws Exception {
        CTestCActions lAction = new CTestCActions();
        CPlayerEntity lRobert = new CPlayerEntity.CPlayerBuilder(15).xp(900000).build();
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(10).build();
        CResonatorEntity lR1 = new CResonatorEntity.CResonatorBuilder(11).owner(lRobert).level(1).build();

       // assertEquals(lAction.buildResonator(lP1,lR1).getResonators().get(0).getId(),11);

        lAction.buildResonator(lP1, lR1);
        assertEquals(lR1.getPortal().getId(), 10);

    }

    public void testCreatResonatorBadIssueLowLevel() throws Exception {
        CTestCActions lAction = new CTestCActions();
        CPlayerEntity lRobert = new CPlayerEntity.CPlayerBuilder(15).xp(10).build();
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(10).build();
        CResonatorEntity lR1 = new CResonatorEntity.CResonatorBuilder(11).owner(lRobert).level(8).build();
        lAction.buildResonator(lP1,lR1);
        assertEquals(lR1.getPortal(),null);
    }

    public void testCreatResonatorBadIssuNoPlace() throws Exception {
        List<CResonatorEntity> lListReso = new ArrayList<>();
        lListReso.add(new CResonatorEntity.CResonatorBuilder(1).build());
        lListReso.add(new CResonatorEntity.CResonatorBuilder(2).build());
        lListReso.add(new CResonatorEntity.CResonatorBuilder(3).build());
        lListReso.add(new CResonatorEntity.CResonatorBuilder(4).build());
        lListReso.add(new CResonatorEntity.CResonatorBuilder(5).build());
        lListReso.add(new CResonatorEntity.CResonatorBuilder(6).build());
        lListReso.add(new CResonatorEntity.CResonatorBuilder(7).build());
        lListReso.add(new CResonatorEntity.CResonatorBuilder(8).build());


        CTestCActions lAction = new CTestCActions();
        CPlayerEntity lRobert = new CPlayerEntity.CPlayerBuilder(15).xp(900000).build();
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(10).resonators(lListReso).build();
        CResonatorEntity lR1 = new CResonatorEntity.CResonatorBuilder(11).owner(lRobert).level(1).build();

        lAction.buildResonator(lP1, lR1);
        assertEquals(lR1.getPortal(), null);
        //assertEquals(lAction.buildResonator(lP1,lR1).getResonators().get(0).getId(),11);
    }


    public void testAttackBuildingGoodIssueWithRespondOfTurret() throws Exception {
        Boolean lFlag = false;
        CTestCActions lAction = new CTestCActions();

        CConsumableEntity lAmmunition = new CConsumableEntity.CConsumableBuilder(1).name("Attack").rarity(2).build();

        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).build();
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).build();

        CPlayerEntity lMarion = new CPlayerEntity.CPlayerBuilder(1).nickname("marion").energy(100).energyMax(100).team(lTeam1).build();
        CPlayerEntity lRobert = new CPlayerEntity.CPlayerBuilder(2).nickname("robert").energy(100).energyMax(100).team(lTeam2).build();

        CResonatorEntity lR1 = new CResonatorEntity.CResonatorBuilder(1).owner(lMarion).energyMax(100).energy(100).build();
        CResonatorEntity lR2 = new CResonatorEntity.CResonatorBuilder(2).owner(lRobert).energyMax(100).energy(100).build();

        CTurretEntity lT1 = new CTurretEntity.CTurretBuilder(1).energy(100).energyMax(100).damage(50).build();

    List<ABuildingEntity> lListBuilding = new ArrayList<>();
        lListBuilding.add((ABuildingEntity) lT1);

        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(1).buildings(lListBuilding).team(lTeam1).build();
        lP1.addResonator(lR1);
        lP1.addResonator(lR2);


        lAction.attackBuilding(lAmmunition, lR1, lRobert);
        if(lR1.getEnergy() == 40 && lRobert.getEnergy() == 50){
            lFlag = true;
        }
        else {
            lFlag = false;
        }

        assertTrue(lFlag);

    }


    public void testAttackBuildingGoodIssueWithoutTurretRespons() throws Exception {
        CTestCActions lAction = new CTestCActions();
        Boolean lFlag = false;

        CConsumableEntity lAmmunition = new CConsumableEntity.CConsumableBuilder(1).name("Attack").rarity(2).build();

        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).build();
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).build();

        CPlayerEntity lMarion = new CPlayerEntity.CPlayerBuilder(1).nickname("marion").energy(100).energyMax(100).team(lTeam1).build();
        CPlayerEntity lRobert = new CPlayerEntity.CPlayerBuilder(2).nickname("robert").energy(100).energyMax(100).team(lTeam2).build();

        CResonatorEntity lR1 = new CResonatorEntity.CResonatorBuilder(1).owner(lMarion).energyMax(100).energy(100).build();
        CResonatorEntity lR2 = new CResonatorEntity.CResonatorBuilder(2).owner(lRobert).energyMax(100).energy(100).build();

        CTurretEntity lT1 = new CTurretEntity.CTurretBuilder(1).energy(100).energyMax(100).damage(50).build();

        List<ABuildingEntity> lListBuilding = new ArrayList<>();
        lListBuilding.add((ABuildingEntity) lT1);

        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(1).buildings(lListBuilding).team(lTeam1).build();
        lP1.addResonator(lR1);
        lP1.addResonator(lR2);

        lAction.attackBuilding(lAmmunition, lR2, lMarion);

        if(lR2.getEnergy() == 40 && lMarion.getEnergy() == 100){
            lFlag = true;
        }
        else {
            lFlag = false;
        }

        assertTrue(lFlag);
    }

    public void testAttackAlliedBuilding() throws Exception {
        CTestCActions lAction = new CTestCActions();
        Boolean lFlag = false;
        CConsumableEntity lAmmunition = new CConsumableEntity.CConsumableBuilder(1).name("Attack").rarity(2).build();

        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).build();
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).build();

        CPlayerEntity lMarion = new CPlayerEntity.CPlayerBuilder(1).nickname("marion").energy(100).energyMax(100).team(lTeam1).build();
        CPlayerEntity lRobert = new CPlayerEntity.CPlayerBuilder(2).nickname("robert").energy(100).energyMax(100).team(lTeam2).build();

        CResonatorEntity lR1 = new CResonatorEntity.CResonatorBuilder(1).owner(lMarion).energyMax(100).energy(100).build();
        CResonatorEntity lR2 = new CResonatorEntity.CResonatorBuilder(2).owner(lRobert).energyMax(100).energy(100).build();

        CTurretEntity lT1 = new CTurretEntity.CTurretBuilder(1).energy(100).energyMax(100).damage(50).build();

        List<ABuildingEntity> lListBuilding = new ArrayList<>();
        lListBuilding.add((ABuildingEntity) lT1);

        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(1).buildings(lListBuilding).team(lTeam1).build();
        lP1.addResonator(lR1);
        lP1.addResonator(lR2);



        lAction.attackBuilding(lAmmunition, lR2, lRobert);


        if(lR2.getEnergy() == 100 && lRobert.getEnergy() == 100){
            lFlag = true;
        }
        else {
            lFlag = false;
        }

        assertTrue(lFlag);

    }



    public void testCreatShieldObject() throws Exception {
    CTestCActions lAction = new CTestCActions();

    assertEquals(lAction.createObject(2, 5, 3).getClass(), new CShieldEntity.CShieldBuilder(1).build().getClass());
}

    public void testCreatResonatorObject() throws Exception {
        CTestCActions lAction = new CTestCActions();

        assertEquals(lAction.createObject(0, 5, 3).getClass(), new CResonatorEntity.CResonatorBuilder(10).build().getClass());
    }

    public void testGetLevelPortal() throws Exception {
        CTestCActions lAction = new CTestCActions();

        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).build();
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).build();
        CPlayerEntity lMarion = new CPlayerEntity.CPlayerBuilder(1).nickname("marion").energy(100).energyMax(100).team(lTeam1).build();
        CPlayerEntity lRobert = new CPlayerEntity.CPlayerBuilder(2).nickname("robert").energy(100).energyMax(100).team(lTeam2).build();

        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(1).team(lTeam1).build();
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(1).level(8).owner(lRobert).build());
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(2).level(8).owner(lMarion).build());
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(3).level(8).owner(lMarion).build());
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(4).level(8).owner(lMarion).build());
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(5).level(8).owner(lMarion).build());
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(6).level(8).owner(lMarion).build());
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(7).level(8).owner(lMarion).build());
        lP1.addResonator(new CResonatorEntity.CResonatorBuilder(8).level(8).owner(lMarion).build());
        //lP1.addResonator(new CResonatorEntity.CResonatorBuilder(9).level(6).owner(lMarion).build());


        assertEquals(lP1.getLevel(),7);
    }

    public void testCalculLevelObjectWhen8_8() throws Exception {
        CTestCActions lAction = new CTestCActions();

        assertEquals(lAction.calculLevel(8,8), 8);
    }
    /*
    public void testCalculLevelObject() throws Exception {
        CTestCActions lAction = new CTestCActions();

        System.out.println(lAction.calculLevel(8,8));
        System.out.println(lAction.calculLevel(3,8));
        System.out.println(lAction.calculLevel(2,8));
        System.out.println(lAction.calculLevel(4,7));
        System.out.println(lAction.calculLevel(6, 6));

    }
    */

    public void testKeyHacking() throws Exception{
        CTestCActions lAction = new CTestCActions();
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(1).build();

        assertEquals(lAction.keyHacking(lP1).getPortal(),lP1);


    }
}
