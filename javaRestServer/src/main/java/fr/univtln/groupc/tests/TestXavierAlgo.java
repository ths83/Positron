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
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(1001).latitude(11.15).longitude(12.015).build();
        CPortalEntity lP2 = new CPortalEntity.CPortalBuilder(1002).latitude(21.58198).longitude(22.88).build();
        CPortalEntity lP3 = new CPortalEntity.CPortalBuilder(1003).latitude(13.198).longitude(23.1085).build();

        List<CPortalEntity> lPortals1 = new ArrayList<>();
        lPortals1.add(lP1);
        lPortals1.add(lP2);

        List<CPortalEntity> lPortals2 = new ArrayList<>();
        lPortals2.add(lP2);
        lPortals2.add(lP3);


        CLinkEntity lL1 = new CLinkEntity.CLinkBuilder(101).portals(lPortals1).build();
        CLinkEntity lL2 = new CLinkEntity.CLinkBuilder(102).portals(lPortals2).build();

        List<CPortalEntity> lPortals3 = new ArrayList<>();
        lPortals3.add(lP1);
        lPortals3.add(lP3);

        CLinkEntity lL3= new CLinkEntity.CLinkBuilder(103).portals(lPortals3).build();

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

        CPortalEntity lPA = new CPortalEntity.CPortalBuilder(1050).latitude(10.198).longitude(30.98).build();
        CPortalEntity lPB = new CPortalEntity.CPortalBuilder(1050).latitude(10.198).longitude(20.189).build();

        List<CPortalEntity> lPortalsA = new ArrayList<>();
        lPortalsA.add(lPA);
        lPortalsA.add(lPB);

        CLinkEntity lLA = new CLinkEntity.CLinkBuilder(150).portals(lPortalsA).build();


        lFiels.add(new CFieldEntity.CFieldBuilder(123).links(lLinks).build() );
        System.out.println("Links :"+lLinks);
        System.out.println("Vision Lfield= " + lFiels + "\nLinksField:" + lFiels.get(0) + "\n");


////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("\n CA BUG LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: "+lFiels.get(0).getLinks().get(0)+"\n" );
///////////////////////////////////////////////////////////////////////////////////


        if(CAlgorithm.detectColision(lLA,lLinks,lFiels)) {
            System.out.println("Pas de croisement");
        }
        else{
            System.out.println("Croisement");
        }
        CPortalEntity PG1=new CPortalEntity.CPortalBuilder(1011).latitude(1.111).longitude(2.222).build();

        CPortalEntity PG2=new CPortalEntity.CPortalBuilder(1012).latitude(1002.1898).longitude(3.1895149).build();

        CPortalEntity PG3=new CPortalEntity.CPortalBuilder(1013).latitude(4.1988).longitude(1002.4198).build();

        List<CPortalEntity> PortalGRos1 = new ArrayList<>();
        PortalGRos1.add(PG1);
        PortalGRos1.add(PG2);

        List<CPortalEntity> PortalGRos2 = new ArrayList<>();
        PortalGRos2.add(PG2);
        PortalGRos2.add(PG3);

        List<CPortalEntity> PortalGRos3 = new ArrayList<>();
        PortalGRos3.add(PG3);
        PortalGRos3.add(PG2);



        List<CLinkEntity> GrosFieldLink = new ArrayList<>();
        GrosFieldLink.add(new CLinkEntity.CLinkBuilder(201).portals(PortalGRos1).build());
        GrosFieldLink.add(new CLinkEntity.CLinkBuilder(202).portals(PortalGRos2).build());
        GrosFieldLink.add(new CLinkEntity.CLinkBuilder(203).portals(PortalGRos3).build());

        lLinks.add(GrosFieldLink.get(0));
        lLinks.add(GrosFieldLink.get(1));
        lLinks.add(GrosFieldLink.get(2));
        CFieldEntity GroField = new CFieldEntity.CFieldBuilder(1).links(GrosFieldLink).build();

        for(CLinkEntity lL : lLinks) {
            System.out.println(("Liste : " + lL.getId() ));
        }
        System.out.println("\n");
        System.out.println(" \n\n\nListe de lien a suprimer:" + CAlgorithm.detectInternalLink(GroField, lLinks));

    }
}
