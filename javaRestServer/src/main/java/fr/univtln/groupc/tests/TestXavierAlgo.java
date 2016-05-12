package fr.univtln.groupc.tests;

import fr.univtln.groupc.algorithm.CAlgorithm;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdurbec066 on 10/05/16.
 */
public class TestXavierAlgo {

    public static void main(String[] args) {
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(123).latitude(1.0).longitude(1.0).build();
        CPortalEntity lP2 = new CPortalEntity.CPortalBuilder(234).latitude(2.0).longitude(2.0).build();
        CPortalEntity lP3 = new CPortalEntity.CPortalBuilder(345).latitude(1.0).longitude(2.0).build();

        List<CPortalEntity> lPortals1 = new ArrayList<>();
        lPortals1.add(lP1);
        lPortals1.add(lP2);

        List<CPortalEntity> lPortals2 = new ArrayList<>();
        lPortals2.add(lP2);
        lPortals2.add(lP3);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(123).portals(lPortals1).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(124).portals(lPortals2).build();

        List<CPortalEntity> lPortals3 = new ArrayList<>();
        lPortals3.add(lP1);
        lPortals3.add(lP3);

        CLinkEntity lL3= new CLinkEntity.CLinkBuilder(123).portals(lPortals3).build();

        List<CLinkEntity> lLinks = new ArrayList<>();
        lLinks.add(lL1);
        lLinks.add(lL2);

        List<CFieldEntity> lFiels = new ArrayList<>();

        if(CAlgorithm.detectColision(lL3,lLinks,lFiels)) {
            System.out.println("Pas de croisement");
            lLinks.add(lL3);
        }
        else{
            System.out.println("Croisement");
        }

        CPortalEntity lPA = new CPortalEntity.CPortalBuilder(666).latitude(0.0).longitude(0.5).build();
        CPortalEntity lPB = new CPortalEntity.CPortalBuilder(6660).latitude(2.0).longitude(0.5).build();

        List<CPortalEntity> lPortalsA = new ArrayList<>();
        lPortalsA.add(lPA);
        lPortalsA.add(lPB);
        CLinkEntity lLA = new CLinkEntity.CLinkBuilder(129).portals(lPortalsA).build();

        if(CAlgorithm.detectColision(lLA,lLinks,lFiels)) {
            System.out.println("Pas de croisement");
        }
        else{
            System.out.println("Croisement");
        }

    }
}
