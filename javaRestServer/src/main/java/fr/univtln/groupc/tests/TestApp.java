package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpesnel786 on 03/06/16.
 */
public class TestApp {

    public static void main(String[] args) {
        CCrudMethods crud = new CCrudMethods();
        /*CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).color("blue").build();
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).color("red").build();*/
        CTeamEntity lTeam1 = crud.find(CTeamEntity.class, 1);
        CTeamEntity lTeam2 = crud.find(CTeamEntity.class,2);

        /*AObjectEntity lResoOfPlayer1 = new CResonatorEntity.CResonatorBuilder().energy(100).energyMax(100).level(3).build();
        AObjectEntity lResoOfPlayer2 = new CResonatorEntity.CResonatorBuilder().energy(100).energyMax(100).level(4).build();
        AObjectEntity lResoOfPlayer3 = new CResonatorEntity.CResonatorBuilder().energy(100).energyMax(100).level(5).build();
        AObjectEntity lKey1 = new CKeyEntity.CKeyBuilder().portal(crud.find(CPortalEntity.class, 9)).build();
        AObjectEntity lKey2 = new CKeyEntity.CKeyBuilder().portal(crud.find(CPortalEntity.class, 12)).build();
        List<AObjectEntity> lObjects = new ArrayList<AObjectEntity>();
        lObjects.add(lResoOfPlayer1);
        lObjects.add(lResoOfPlayer2);
        lObjects.add(lResoOfPlayer3);*/

        //CPlayerEntity lPlayer1 = new CPlayerEntity.CPlayerBuilder(1).objects(lObjects).xp(1500000).nickname("pogba").energy(100).energyMax(100).team(lTeam1).build();
        //CPlayerEntity lPlayer2 = new CPlayerEntity.CPlayerBuilder(2).xp(1500000).nickname("ribery").energy(100).energyMax(100).team(lTeam2).build();
        //CPlayerEntity lPlayer3 = new CPlayerEntity.CPlayerBuilder(3).xp(1500000).nickname("marvinmartin").energy(100).energyMax(100).team(lTeam1).build();




        /*if (crud.openTransaction()) {
            crud.create(lPlayer1);
            crud.commitTransaction();
        }
        if (crud.openTransaction()) {
            crud.create(lPlayer2);
            crud.commitTransaction();
        }
        if (crud.openTransaction()) {
            crud.create(lPlayer3);
            crud.commitTransaction();
        }*/

       // CPlayerEntity p1 = crud.find(CPlayerEntity.class,2);

        /*CResonatorEntity lResoOfPortal6 = new CResonatorEntity.CResonatorBuilder().energy(100).energyMax(100).level(3).owner(p1).build();
        CPortalEntity lPortal6 = crud.find(CPortalEntity.class, 6);
        lPortal6.addResonator(lResoOfPortal6);
        lPortal6.attributeTeam();
        p1.addObjects(new CConsumableEntity.CConsumableBuilder().name("Bombe").rarity(2).build());
        if (crud.openTransaction()) {
            crud.update(lPortal6);
            crud.commitTransaction();
        }
        else {
            System.out.println("prob transac");
        }

        CResonatorEntity lResoOfPortal7 = new CResonatorEntity.CResonatorBuilder().energy(100).energyMax(100).level(3).owner(p1).build();
        lPortal6.addResonator(lResoOfPortal7);
        lPortal6.attributeTeam();
        if (crud.openTransaction()) {
            crud.update(lPortal6);
            crud.commitTransaction();
        }
        else {
            System.out.println("prob transac");
        }*/

        CPlayerEntity p1 = crud.find(CPlayerEntity.class,1);
        p1.addObjects(new CConsumableEntity.CConsumableBuilder().name("Bombe").rarity(2).build());

        if (crud.openTransaction()) {
            crud.update(p1);
            crud.commitTransaction();
        }
        else {
            System.out.println("prob transac");
        }


        /*CResonatorEntity lResoOfPortal8 = new CResonatorEntity.CResonatorBuilder().energy(100).energyMax(100).level(3).owner(p1).build();
        lPortal6.addResonator(lResoOfPortal8);
        lPortal6.attributeTeam();
        if (crud.openTransaction()) {
            crud.update(lPortal6);
            crud.commitTransaction();
        }
        else {
            System.out.println("prob transac");
        }*/


       /* CTurretEntity l6 = new CTurretEntity.CTurretBuilder().energy(100).energyMax(100).level(3).build();
        lPortal6.addBuilding(l6);
        if (crud.openTransaction()) {
            crud.update(lPortal6);
            crud.commitTransaction();
        }*/


        /*CResonatorEntity lResoOfPortal7 = new CResonatorEntity.CResonatorBuilder().energy(100).energyMax(100).level(3).owner(lPlayer3).build();
        CPortalEntity lPortal10 = crud.find(CPortalEntity.class, 10);
        lPortal10.addResonator(lResoOfPortal7);
        crud.update(lPortal10);*/


        /*CPlayerEntity p1=crud.find(CPlayerEntity.class,1);
        p1.addObjects(new CConsumableEntity.CConsumableBuilder(8).name("Bombe").rarity(2).build());
        p1.addObjects(new CConsumableEntity.CConsumableBuilder(9).name("Attack").rarity(0).build());
        p1.addObjects(new CConsumableEntity.CConsumableBuilder(10).name("Attack").rarity(2).build());
        p1.addObjects(new CConsumableEntity.CConsumableBuilder(11).name("Attack").rarity(0).build());
        p1.addObjects(new CConsumableEntity.CConsumableBuilder(12).name("Attack").rarity(1).build());
        crud.update(p1);*/
        /*CResonatorEntity r1 = new CResonatorEntity.CResonatorBuilder(13).energy(100).energyMax(100).level(4).build();

        CPlayerEntity p2=crud.find(CPlayerEntity.class, 2);
        r1.setOwner(p2);
        crud.update(r1);*/
        //CResonatorEntity r1 = crud.find(CResonatorEntity.class,13);
        //CConsumableEntity o1 = crud.find(CConsumableEntity.class,9);
        //CConsumableEntity o2 = crud.find(CConsumableEntity.class,10);
        //CConsumableEntity o3 = crud.find(CConsumableEntity.class, 11);
        //CConsumableEntity o4 = crud.find(CConsumableEntity.class, 12);
        //p1.addObjects(o1);
        //p1.addObjects(o2);
        //p1.addObjects(o3);
        //p1.addObjects(o4);
        //CPortalEntity lP0 = crud.find(CPortalEntity.class, 7);
        //lP0.addResonator(r1);
        //crud.update(p1);
        /*
        CTeamEntity lTeams2 =crud.find(CTeamEntity.class,2);

        CPlayerEntity p6 = crud.find(CPlayerEntity.class,3);

        AObjectEntity lResoOfP1 = new CResonatorEntity.CResonatorBuilder(1).energy(100).energyMax(100).level(3).build();
        AObjectEntity lResoOfP2 = new CResonatorEntity.CResonatorBuilder(2).energy(100).energyMax(100).level(4).build();
        AObjectEntity lResoOfP3 = new CResonatorEntity.CResonatorBuilder(3).energy(100).energyMax(100).level(5).build();
        List<AObjectEntity> lObjects2 = new ArrayList<AObjectEntity>();
        lObjects2.add(lResoOfP1);
        lObjects2.add(lResoOfP2);
        lObjects2.add(lResoOfP3);
        CPlayerEntity lPlayer4 = new CPlayerEntity.CPlayerBuilder(4).objects(lObjects2).xp(1500000).nickname("olé").energy(100).energyMax(100).team(lTeams2).build();
        crud.create(lPlayer4);
*/


    }



}
