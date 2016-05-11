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
        CPortalEntity lP1 = new CPortalEntity.CPortalBuilder(123).latitude(10.1).longitude(10.2).build();
        CPortalEntity lP2 = new CPortalEntity.CPortalBuilder(234).latitude(20.10).longitude(20.20).build();
        CPortalEntity lP3 = new CPortalEntity.CPortalBuilder(345).latitude(10.30).longitude(20.30).build();

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

        CPortalEntity lPA = new CPortalEntity.CPortalBuilder(666).latitude(10.0).longitude(30.2).build();
        CPortalEntity lPB = new CPortalEntity.CPortalBuilder(6660).latitude(10.6).longitude(20.450).build();

        List<CPortalEntity> lPortalsA = new ArrayList<>();
        lPortalsA.add(lPA);
        lPortalsA.add(lPB);

        CLinkEntity lLA = new CLinkEntity.CLinkBuilder(129).portals(lPortalsA).build();


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
        CPortalEntity PG1=new CPortalEntity.CPortalBuilder(1001).latitude(1.02).longitude(1.01).build();

        CPortalEntity PG2=new CPortalEntity.CPortalBuilder(1002).latitude(1002).longitude(1.03).build();

        CPortalEntity PG3=new CPortalEntity.CPortalBuilder(1003).latitude(1.04).longitude(1003).build();

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
        GrosFieldLink.add(new CLinkEntity.CLinkBuilder(100).portals(PortalGRos1).build());
        GrosFieldLink.add(new CLinkEntity.CLinkBuilder(200).portals(PortalGRos2).build());
        GrosFieldLink.add(new CLinkEntity.CLinkBuilder(300).portals(PortalGRos3).build());

        lLinks.add(GrosFieldLink.get(0));
        lLinks.add(GrosFieldLink.get(1));
        lLinks.add(GrosFieldLink.get(2));
        CFieldEntity GroField = new CFieldEntity.CFieldBuilder(4000).links(GrosFieldLink).build();

        for(CLinkEntity lL : lLinks) {
            System.out.println(("Liste : " + lL.getId() ));
        }
        System.out.println("\n");
        System.out.println(" \n\n\nListe de lien a suprimer:" + CAlgorithm.detectInternalLink(GroField, lLinks));

    }


}
