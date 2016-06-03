package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.server.CServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CCrudMethods crud = new CCrudMethods();
        CPlayerEntity p1 = crud.find(CPlayerEntity.class,1);
        CPlayerEntity p2 = crud.find(CPlayerEntity.class,2);
        CPortalEntity po1 = crud.find(CPortalEntity.class,7);
        CPortalEntity po2 = crud.find(CPortalEntity.class,8);
        CPortalEntity po3 = crud.find(CPortalEntity.class,6);
        CPortalEntity po4 = crud.find(CPortalEntity.class,9);
        /*p1.addObjects(new CConsumableEntity.CConsumableBuilder(11).name("Bombe").rarity(2).build());
        crud.update(p1);
        //po1.setRadius(40);
        //crud.update(po1);
        CResonatorEntity r1= new CResonatorEntity.CResonatorBuilder(1).level(8).owner(p1).energy(100).energyMax(50).portal(po1).build();
        CResonatorEntity r2= new CResonatorEntity.CResonatorBuilder(2).level(5).owner(p2).energy(100).energyMax(100).portal(po1).build();
        //CResonatorEntity r3= new CResonatorEntity.CResonatorBuilder(3).level(1).portal(po1).build();
        //CResonatorEntity r4= new CResonatorEntity.CResonatorBuilder(4).level(2).portal(po1).build();
        CResonatorEntity r5= new CResonatorEntity.CResonatorBuilder(3).level(7).owner(p2).energy(100).energyMax(100).portal(po1).build();
        CResonatorEntity r6= new CResonatorEntity.CResonatorBuilder(4).level(1).owner(p2).energy(100).energyMax(100).portal(po1).build();
        //CResonatorEntity r7= new CResonatorEntity.CResonatorBuilder(7).level(3).portal(po1).build();
        CResonatorEntity r8= new CResonatorEntity.CResonatorBuilder(5).level(8).owner(p1).energy(100).energyMax(100).portal(po1).build();
        crud.create(r1);
        crud.create(r2);
        //crud.create(r3);
        //crud.create(r4);
        crud.create(r5);
        crud.create(r6);
        //crud.create(r7);
        crud.create(r8);
        CResonatorEntity r9= new CResonatorEntity.CResonatorBuilder(6).level(8).owner(p1).energy(100).energyMax(100).portal(po3).build();
        CResonatorEntity r10= new CResonatorEntity.CResonatorBuilder(7).level(5).owner(p2).energy(100).energyMax(100).portal(po2).build();
        CResonatorEntity r13= new CResonatorEntity.CResonatorBuilder(8).level(7).owner(p2).energy(100).energyMax(100).portal(po2).build();
        CResonatorEntity r14= new CResonatorEntity.CResonatorBuilder(9).level(1).owner(p2).energy(100).energyMax(100).portal(po4).build();
        //CResonatorEntity r16= new CResonatorEntity.CResonatorBuilder(10).level(8).owner(p1).energy(100).energyMax(100).portal(po4).build();
        crud.create(r9);
        crud.create(r10);
        crud.create(r13);
        crud.create(r14);*/
        //crud.create(r16);
        List<CPortalEntity> pi1 = new ArrayList<CPortalEntity>();
        pi1.add(po1);
        pi1.add(po3);
        CLinkEntity l1 = new CLinkEntity.CLinkBuilder(1).portals(pi1).build();
        crud.create(l1);
        List<CPortalEntity> pi2 = new ArrayList<CPortalEntity>();
        pi2.add(po4);
        pi2.add(po3);
        CLinkEntity l2 = new CLinkEntity.CLinkBuilder(2).portals(pi2).build();
        crud.create(l2);
        List<CPortalEntity> pi3 = new ArrayList<CPortalEntity>();
        pi3.add(po1);
        pi3.add(po3);
        CLinkEntity l3 = new CLinkEntity.CLinkBuilder(3).portals(pi3).build();
        crud.create(l3);
        List<CLinkEntity> fi2 = new ArrayList<CLinkEntity>();
        fi2.add(l1);
        fi2.add(l2);
        fi2.add(l3);
        CFieldEntity f2 = new CFieldEntity.CFieldBuilder(1).links(fi2).build();
        crud.create(f2);
        //CFieldEntity p1 = crud.find(CFieldEntity.class,101);
        //crud.delete(CFieldEntity.class,101);*/
        CKeyEntity k1 = new CKeyEntity.CKeyBuilder(14).name("Key 4").portal(po4).build();
        CKeyEntity k2= new CKeyEntity.CKeyBuilder(12).name("Key 2").portal(po4).build();
        CKeyEntity k3 = new CKeyEntity.CKeyBuilder(13).name("Key 3").portal(po3).build();
        p1.addObjects(k1);
        p1.addObjects(k2);
        p1.addObjects(k3);
        crud.update(p1);
        //crud.create(k1);
        //crud.create(k2);
        //crud.create(k3);

        CResonatorEntity r21= new CResonatorEntity.CResonatorBuilder(15).level(8).owner(p1).energy(100).energyMax(100).build();
        CResonatorEntity r22= new CResonatorEntity.CResonatorBuilder(16).level(5).owner(p1).energy(100).energyMax(100).build();
        crud.create(r22);
        crud.create(r21);
        CResonatorEntity r24= new CResonatorEntity.CResonatorBuilder(17).level(8).owner(p1).energy(100).energyMax(100).build();
        //CResonatorEntity r25= new CResonatorEntity.CResonatorBuilder(18).level(5).owner(p1).energy(100).energyMax(100).build();*/
        //crud.create(r9);
        //crud.create(r10);
        //System.out.println(p1.getObjects());
        //System.out.println(p1.getResonators());
        p1.setXp(7700);
        crud.update(p1);
    }
}
