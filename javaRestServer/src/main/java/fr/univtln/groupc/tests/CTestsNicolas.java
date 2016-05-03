package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;

/**
 * Created by arouani277 on 02/05/16.
 */
public class CTestsNicolas {

    public static void main(String[] args) {
        CCrudMethods lCrud = new CCrudMethods();
        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).color("red").build();
        CPlayerEntity lPlayer1 = new CPlayerEntity.CPlayerBuilder(1).email("martinez1nicolas@gmail.com")
        .nickname("nicolas").team(lTeam1).bagSize(15).longitude(120.3).latitude(145.8).xp(280).energy(180).energyMax(230).build();
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

        CPlayerEntity lPlayerGotten = lCrud.find(CPlayerEntity.class, 1);
        System.out.println(lPlayerGotten);

        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(1).latitude(250.2).longitude(283.1).team(lTeam1).build();
        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(2).latitude(112.6).longitude(48.35).team(lTeam2).build();

        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(3).latitude(280.2).longitude(213.1).team(lTeam1).build();
        CPortalEntity lPortal4 = new CPortalEntity.CPortalBuilder(4).latitude(142.6).longitude(48.35).team(lTeam2).build();

        CPortalEntity lPortal5 = new CPortalEntity.CPortalBuilder(5).latitude(112.2).longitude(92.1).team(lTeam1).build();
        CPortalEntity lPortal6 = new CPortalEntity.CPortalBuilder(6).latitude(115.6).longitude(115.35).team(lTeam2).build();

        lCrud.create(lPortal1);
        lCrud.create(lPortal2);
        lCrud.create(lPortal3);
        lCrud.create(lPortal4);
        lCrud.create(lPortal5);
        lCrud.create(lPortal6);
    }



}
