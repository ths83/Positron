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
        CTeamEntity lTeam1 = new CTeamEntity.CTeamBuilder(1).color("blue").build();
        CTeamEntity lTeam2 = new CTeamEntity.CTeamBuilder(2).color("red").build();

        AObjectEntity lResoOfPlayer1 = new CResonatorEntity.CResonatorBuilder(1).energy(100).energyMax(100).level(3).build();
        AObjectEntity lResoOfPlayer2 = new CResonatorEntity.CResonatorBuilder(2).energy(100).energyMax(100).level(4).build();
        AObjectEntity lResoOfPlayer3 = new CResonatorEntity.CResonatorBuilder(3).energy(100).energyMax(100).level(5).build();
        AObjectEntity lKey1 = new CKeyEntity.CKeyBuilder(4).portal(crud.find(CPortalEntity.class, 9)).build();
        AObjectEntity lKey2 = new CKeyEntity.CKeyBuilder(5).portal(crud.find(CPortalEntity.class, 12)).build();
        List<AObjectEntity> lObjects = new ArrayList<AObjectEntity>();
        lObjects.add(lResoOfPlayer1);
        lObjects.add(lResoOfPlayer2);
        lObjects.add(lResoOfPlayer3);

        CPlayerEntity lPlayer1 = new CPlayerEntity.CPlayerBuilder(1).objects(lObjects).xp(1500000).nickname("pogba").energy(100).energyMax(100).team(lTeam1).build();
        CPlayerEntity lPlayer2 = new CPlayerEntity.CPlayerBuilder(2).xp(1500000).nickname("ribery").energy(100).energyMax(100).team(lTeam2).build();
        CPlayerEntity lPlayer3 = new CPlayerEntity.CPlayerBuilder(3).xp(1500000).nickname("marvinmartin").energy(100).energyMax(100).team(lTeam1).build();





        crud.create(lPlayer1);
        crud.create(lPlayer2);
        crud.create(lPlayer3);


        CResonatorEntity lResoOfPortal6 = new CResonatorEntity.CResonatorBuilder(6).energy(100).energyMax(100).level(3).owner(lPlayer2).build();
        CPortalEntity lPortal6 = crud.find(CPortalEntity.class, 6);
        lPortal6.addResonator(lResoOfPortal6);
        crud.update(lPortal6);

        CResonatorEntity lResoOfPortal7 = new CResonatorEntity.CResonatorBuilder(7).energy(100).energyMax(100).level(3).owner(lPlayer3).build();
        CPortalEntity lPortal10 = crud.find(CPortalEntity.class, 10);
        lPortal10.addResonator(lResoOfPortal7);
        crud.update(lPortal10);



    }



}
