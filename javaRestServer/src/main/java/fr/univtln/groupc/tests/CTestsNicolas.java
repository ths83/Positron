package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arouani277 on 02/05/16.
 */
public class CTestsNicolas {

    public static void main(String[] args) {
        CCrudMethods lCrud = new CCrudMethods();
        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).color("red").build();

        CPlayerEntity lPlayer1 = new CPlayerEntity.CPlayerBuilder(1).email("martinez1nicolas@gmail.com")
        .nickname("nicolas").team(lTeam1).bagSize(15).longitude(6.016560).latitude(43.136466).xp(280).energy(180).energyMax(230).build();

        CPlayerEntity lPlayer2 = new CPlayerEntity.CPlayerBuilder(2).email("martinez1nicolas@gmail.com")
                .nickname("martinez").team(lTeam1).bagSize(15).longitude(6.020808).latitude(43.136434).xp(280).energy(180).energyMax(230).build();

        CPlayerEntity lPlayer3 = new CPlayerEntity.CPlayerBuilder(3).email("martinez1nicolas@gmail.com")
                .nickname("martinez").team(lTeam1).bagSize(15).longitude(6.017719).latitude(43.137476).xp(280).energy(180).energyMax(230).build();

        System.out.println(lTeam1);
        System.out.println(lPlayer1);
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).color("blue").build();

        // skills niveau 1
        CSkillEntity lSkill1 = new CSkillEntity.CSkillBuilder(1).level(1).name("bouclier +").cost(0).build();
        CSkillEntity lSkill2 = new CSkillEntity.CSkillBuilder(2).level(1).name("arme a feu 1 +").cost(0).build();
        CSkillEntity lSkill3 = new CSkillEntity.CSkillBuilder(3).level(1).name("emplacement +").cost(0).build();
        CSkillEntity lSkill4 = new CSkillEntity.CSkillBuilder(4).level(1).name("detecteur autour coord").cost(0).build();

        //skills niveau 2
        CSkillEntity lSkill5 = new CSkillEntity.CSkillBuilder(5).level(2).name("renfort lien").cost(15).build();
        CSkillEntity lSkill6 = new CSkillEntity.CSkillBuilder(6).level(2).name("reparation structure").cost(15).build();

        lCrud.create(lTeam1);
        System.out.println("team 1 persistee");
        lCrud.create(lPlayer1);
        lCrud.create(lPlayer2);
        lCrud.create(lPlayer3);
        System.out.println("team 2 persistee");

        lCrud.create(lTeam2);

        CResonatorEntity lResonator1 = new CResonatorEntity.CResonatorBuilder(1).level(2).owner(lPlayer1).radius(16).energy(150).energyMax(150).longitude(120.3).latitude(442.3).build();
        lCrud.create(lResonator1);
        lCrud.create(lSkill1);
        lCrud.create(lSkill2);
        lCrud.create(lSkill3);
        lCrud.create(lSkill4);
        lCrud.create(lSkill5);
        lCrud.create(lSkill6);

        lPlayer1.addSkill(lSkill1);
        lPlayer1.addSkill(lSkill2);
        lPlayer1.addSkill(lSkill3);
        lPlayer1.addSkill(lSkill4);
        lPlayer1.addSkill(lSkill5);
        lPlayer1.addSkill(lSkill6);

        lCrud.create(lPlayer1);
/*
        CPlayerEntity lPlayerGotten = lCrud.find(CPlayerEntity.class, 1);
        System.out.println(lPlayerGotten);
*/
        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(1).latitude(43.137274).longitude(6.015640).team(lTeam1).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(2).latitude(43.137274).longitude(6.016558).team(lTeam1).build();
        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(3).latitude(43.136577).longitude(6.016223).team(lTeam1).build();

        CPortalEntity lPortal4 = new CPortalEntity.CPortalBuilder(4).latitude(43.137136).longitude(6.018718).team(lTeam2).build();
        CPortalEntity lPortal5 = new CPortalEntity.CPortalBuilder(5).latitude(43.137261).longitude(6.019610).team(lTeam2).build();
        CPortalEntity lPortal6 = new CPortalEntity.CPortalBuilder(6).latitude(43.137261).longitude(6.019477).team(lTeam2).build();

        lCrud.create(lPortal1);
        lCrud.create(lPortal2);
        lCrud.create(lPortal3);
        lCrud.create(lPortal4);
        lCrud.create(lPortal5);
        lCrud.create(lPortal6);

        CPortalEntity lPortalDeTest1 = new CPortalEntity.CPortalBuilder(77).longitude(200).latitude(200).build();
        CPortalEntity lPortalDeTest2 = new CPortalEntity.CPortalBuilder(78).longitude(300).latitude(300).build();
        CPortalEntity lPortalDeTest3 = new CPortalEntity.CPortalBuilder(79).longitude(200).latitude(300).build();

        List<CPortalEntity> lList1_2 = new ArrayList<>();
        lList1_2.add(lPortalDeTest1);
        lList1_2.add(lPortalDeTest2);

        List<CPortalEntity> lList1_3 = new ArrayList<>();
        lList1_3.add(lPortalDeTest1);
        lList1_3.add(lPortalDeTest3);

        List<CPortalEntity> lList2_3 = new ArrayList<>();
        lList2_3.add(lPortalDeTest2);
        lList2_3.add(lPortalDeTest3);

        CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(77).portals(lList1_2).build();
        CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(78).portals(lList1_3).build();

        lCrud.create(lPortalDeTest1);
        lCrud.create(lPortalDeTest2);
        lCrud.create(lPortalDeTest3);
        lCrud.create(lLink1);
        lCrud.create(lLink2);

    }



}
