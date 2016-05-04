package fr.univtln.groupc.tests;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CKeyEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */
public class Testmat {
    public static void main(String[] args) {
        CCrudMethods crud = new CCrudMethods();
        List<CPortalEntity> lp = new ArrayList<CPortalEntity>();
        CPortalEntity p1 = new CPortalEntity.CPortalBuilder(1).radius(10).build();
        CPortalEntity p2 = new CPortalEntity.CPortalBuilder(2).radius(10).build();
        CPortalEntity p3 = new CPortalEntity.CPortalBuilder(3).radius(10).build();
        CKeyEntity k1 = new CKeyEntity.CKeyBuilder(1).portal(p1).build();
        CKeyEntity k2 = new CKeyEntity.CKeyBuilder(2).portal(p1).build();
        CKeyEntity k3 = new CKeyEntity.CKeyBuilder(3).portal(p2).build();
        CKeyEntity k4 = new CKeyEntity.CKeyBuilder(4).portal(p1).build();
        CKeyEntity k5 = new CKeyEntity.CKeyBuilder(5).portal(p1).build();
        //lp.add(p1);
        //lp.add(p2);
        //System.out.printf("liste : " + lp.toString());
        CPlayerEntity j1 = new CPlayerEntity.CPlayerBuilder(1).nickname("pogba").build();
        CPlayerEntity j2 = new CPlayerEntity.CPlayerBuilder(2).nickname("dybala").build();
        CPlayerEntity j3 = new CPlayerEntity.CPlayerBuilder(3).nickname("morata").build();
        CPlayerEntity j4 = new CPlayerEntity.CPlayerBuilder(2).nickname("marchisio").build();
        //CLinkEntity l = new CLinkEntity.CLinkBuilder(1).portals(lp).build();
        crud.create(p1);
        crud.create(p2);
        crud.create(p3);
        crud.create(k1);
        crud.create(k2);
        crud.create(k3);
        crud.create(k4);
        crud.create(k5);
        crud.create(j1);
        crud.create(j2);
        crud.create(j3);
        crud.create(j4);

    }
}
