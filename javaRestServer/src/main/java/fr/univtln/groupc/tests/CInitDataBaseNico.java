package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 07/06/2016.
 */
public class CInitDataBaseNico {

    /*
     * contexte de jeu à initaliser pour les tests
     */

    public static void main(String[] args) {

        CCrudMethods lCrud = new CCrudMethods();
/*
        CPortalEntity lPortalId6 = lCrud.find(CPortalEntity.class, 6);
        CPortalEntity lPortalId8 = lCrud.find(CPortalEntity.class, 8);



        // team
        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).color("blue").build();
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).color("red").build();

        // resonators
        AObjectEntity lReso1 = new CResonatorEntity.CResonatorBuilder(1).energyMax(100).level(4).energy(100).build();
        AObjectEntity lReso2 = new CResonatorEntity.CResonatorBuilder(2).energyMax(100).energy(100).level(3).build();
        AObjectEntity lReso3 = new CResonatorEntity.CResonatorBuilder(3).energyMax(100).energy(100).level(5).build();
        AObjectEntity lReso4 = new CResonatorEntity.CResonatorBuilder(4).energyMax(100).energy(100).level(3).build();
        AObjectEntity lReso5 = new CResonatorEntity.CResonatorBuilder(5).energy(100).energyMax(100).level(2).build();

        // keys
        AObjectEntity lKey6 = new CKeyEntity.CKeyBuilder(6).portal(lPortalId6).build();
        AObjectEntity lKey8 = new CKeyEntity.CKeyBuilder(8).portal(lPortalId8).build();

        // attack
        AObjectEntity lAttack1 = new CConsumableEntity.CConsumableBuilder(9).name("Attack").rarity(1).build();
        AObjectEntity lAttack2 = new CConsumableEntity.CConsumableBuilder(10).name("Attack").rarity(2).build();

        List<AObjectEntity> lObjects = new ArrayList<>();
        lObjects.add(lReso1);
        lObjects.add(lReso2);
        lObjects.add(lReso3);
        lObjects.add(lReso4);
        lObjects.add(lReso5);
        lObjects.add(lKey6);
        lObjects.add(lKey8);
        lObjects.add(lAttack1);
        lObjects.add(lAttack2);




        CPlayerEntity lPlayer1 = new CPlayerEntity.CPlayerBuilder(1).team(lTeam1).objects(lObjects).xp(90000).email("fcna44@gmail.com").energyMax(100).energy(100).build();

        lCrud.create(lPlayer1);
        lCrud.create(lTeam2);
*/

        CPortalEntity lPortalId7 = lCrud.find(CPortalEntity.class, 7);
        CPortalEntity lPortalId9 = lCrud.find(CPortalEntity.class, 9);
        CPortalEntity lPortalId8 = lCrud.find(CPortalEntity.class, 8);
        CPortalEntity lPortalId10 = lCrud.find(CPortalEntity.class, 10);
        CPortalEntity lPortalId11 = lCrud.find(CPortalEntity.class, 11);
        CPortalEntity lPortalId12 = lCrud.find(CPortalEntity.class, 12);
        CPortalEntity lPortalId13 = lCrud.find(CPortalEntity.class, 13);
        CPortalEntity lPortalId14 = lCrud.find(CPortalEntity.class, 14);
        CPortalEntity lPortalId15 = lCrud.find(CPortalEntity.class, 15);




        CPlayerEntity lPlayerGotten = lCrud.find(CPlayerEntity.class, 1);

        //AObjectEntity lKeyPortal9 = new CKeyEntity.CKeyBuilder(19).portal(lPortalId9).build();
        //AObjectEntity lKeyPortal7 = new CKeyEntity.CKeyBuilder(17).portal(lPortalId7).build();

        AObjectEntity lKeyPortal8 = new CKeyEntity.CKeyBuilder(18).portal(lPortalId8).build();
        AObjectEntity lKeyPortal28 = new CKeyEntity.CKeyBuilder(8).portal(lPortalId8).build();
        AObjectEntity lKeyPortal27 = new CKeyEntity.CKeyBuilder(27).portal(lPortalId7).build();
        AObjectEntity lKeyPortal30 = new CKeyEntity.CKeyBuilder(28).portal(lPortalId10).build();
        AObjectEntity lKeyPortal31 = new CKeyEntity.CKeyBuilder(31).portal(lPortalId11).build();
        AObjectEntity lKeyPortal32 = new CKeyEntity.CKeyBuilder(32).portal(lPortalId12).build();
        AObjectEntity lKeyPortal33 = new CKeyEntity.CKeyBuilder(33).portal(lPortalId13).build();
        AObjectEntity lKeyPortal34 = new CKeyEntity.CKeyBuilder(34).portal(lPortalId14).build();
        AObjectEntity lKeyPortal35 = new CKeyEntity.CKeyBuilder(35).portal(lPortalId15).build();
        AObjectEntity lKeyPortal42 = new CKeyEntity.CKeyBuilder(42).portal(lPortalId12).build();

        lPlayerGotten.addObjects(lKeyPortal8);
        lPlayerGotten.addObjects(lKeyPortal28);
        lPlayerGotten.addObjects(lKeyPortal27);
        lPlayerGotten.addObjects(lKeyPortal30);
        lPlayerGotten.addObjects(lKeyPortal31);
        lPlayerGotten.addObjects(lKeyPortal32);
        lPlayerGotten.addObjects(lKeyPortal33);
        lPlayerGotten.addObjects(lKeyPortal34);
        lPlayerGotten.addObjects(lKeyPortal35);
        lPlayerGotten.addObjects(lKeyPortal42);



        //lPlayerGotten.addObjects(lKeyPortal7);
        //lPlayerGotten.addObjects(lKeyPortal9);

        lCrud.update(lPlayerGotten);


    }
}
