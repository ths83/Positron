package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CSkillEntity;
import fr.univtln.groupc.entities.CTeamEntity;

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
        //lCrud.create(lPlayer1);
        System.out.println("team 2 persistee");

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

    }



}
