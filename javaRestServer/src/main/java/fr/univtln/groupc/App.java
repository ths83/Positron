package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CTeamEntity;
import fr.univtln.groupc.server.CServer;

import java.io.IOException;

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
        CPortalEntity po1 = crud.find(CPortalEntity.class,1);
        CResonatorEntity r1= new CResonatorEntity.CResonatorBuilder(1).level(2).owner(p1).portal(po1).build();
        CResonatorEntity r2= new CResonatorEntity.CResonatorBuilder(2).level(5).owner(p2).portal(po1).build();
        //CResonatorEntity r3= new CResonatorEntity.CResonatorBuilder(3).level(1).portal(po1).build();
        //CResonatorEntity r4= new CResonatorEntity.CResonatorBuilder(4).level(2).portal(po1).build();
        CResonatorEntity r5= new CResonatorEntity.CResonatorBuilder(3).level(7).owner(p2).portal(po1).build();
        CResonatorEntity r6= new CResonatorEntity.CResonatorBuilder(4).level(1).owner(p2).portal(po1).build();
        //CResonatorEntity r7= new CResonatorEntity.CResonatorBuilder(7).level(3).portal(po1).build();
        CResonatorEntity r8= new CResonatorEntity.CResonatorBuilder(5).level(2).owner(p1).portal(po1).build();
        crud.create(r1);
        crud.create(r2);
        //crud.create(r3);
        //crud.create(r4);
        crud.create(r5);
        crud.create(r6);
        //crud.create(r7);
        crud.create(r8);

    }
}
