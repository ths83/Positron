package fr.univtln.groupeC.tests;

import fr.univtln.groupeC.dao.CCrudMethods;
import fr.univtln.groupeC.entities.CPlayerEntity;
import fr.univtln.groupeC.entities.CSkillEntity;
import fr.univtln.groupeC.entities.CTeamEntity;

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
        lCrud.create(lPlayer1);
        System.out.println("team 2 persistee");

    }



}
