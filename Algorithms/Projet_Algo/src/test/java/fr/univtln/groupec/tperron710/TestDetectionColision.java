package fr.univtln.groupec.tperron710;



import fr.univtln.groupec.tperron710.algo.Algo;
import fr.univtln.groupec.tperron710.objet.Lien;
import fr.univtln.groupec.tperron710.objet.Portail;
import fr.univtln.groupec.tperron710.objet.Territoire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdurbec066 on 02/05/16.
 */
public class TestDetectionColision {

    public static void main(String[] args) {

        Portail a = new Portail(4,4,1);
        Portail b = new Portail(7,3,2);
        Lien lienAB = new Lien(a,b);

        Portail p2 = new Portail(2,2,3);
        Portail p3 = new Portail(6,2,4);
        Portail p4 = new Portail(10,2,5);
        Portail p5 = new Portail(2,6,6);
        Portail p6 = new Portail(6,6,7);
        Portail p7 = new Portail(10,6,8);
        Portail p8 = new Portail(2,10,9);
        Portail p9 = new Portail(6,10,10);
        Portail p10 = new Portail(10,10,11);

        Lien l1= new Lien(p6,p2);
        Lien l2= new Lien(p2,p5);
        Lien l3= new Lien(p5,p6);

        Lien l4= new Lien(p6,p8);
        Lien l5= new Lien(p8,p9);
        Lien l6= new Lien(p9,p6);

        Lien l7= new Lien(p6,p10);
        Lien l8= new Lien(p10,p7);
        Lien l9= new Lien(p7,p6);

        Lien l10= new Lien(p6,p4);
        Lien l11= new Lien(p4,p3);
        Lien l12= new Lien(p3,p6);

        Portail pX = new Portail(1,1,666);
        Portail pY = new Portail(100,1,6666);
        Portail pZ = new Portail(100,1,66666);



        Lien lx= new Lien(pX,pY);
        Lien ly= new Lien(pY,pZ);
        Lien lz= new Lien(pZ,pX);

        List<Lien> LL = new ArrayList<Lien>();
        LL.add(l1);
        LL.add(l2);
        LL.add(l3);
        LL.add(l4);
        LL.add(l5);
        LL.add(l6);
        LL.add(l7);
        LL.add(l8);
        LL.add(l9);
        LL.add(l10);
        LL.add(l11);
        LL.add(l12);
        LL.add(lx);
        LL.add(ly);
        LL.add(lz);

        List<Portail> LP2=new ArrayList<Portail>();
        LP2.add(p5);
        LP2.add(p6);
        LP2.add(p2);

        List<Portail> LP3=new ArrayList<Portail>();
        LP3.add(p8);
        LP3.add(p9);
        LP3.add(p6);

        List<Portail> LP4=new ArrayList<Portail>();
        LP4.add(p6);
        LP4.add(p10);
        LP4.add(p7);

        List<Portail> LP1=new ArrayList<Portail>();
        LP1.add(p6);
        LP1.add(p4);
        LP1.add(p3);




        List<Portail> LP5=new ArrayList<Portail>();
        LP5.add(pX);
        LP5.add(pY);
        LP5.add(pZ);

        Territoire t1 = new Territoire(LP1);
        Territoire t2 = new Territoire(LP2);
        Territoire t3 = new Territoire(LP3);
        Territoire t4 = new Territoire(LP4);
        Territoire t5 = new Territoire(LP5);

        List<Territoire> LT = new ArrayList<Territoire>();
        LT.add(t1);
        LT.add(t2);
        LT.add(t3);
        LT.add(t4);




        Boolean good = Algo.detectColision(lienAB,LL,LT);

        if(good){
            System.out.println("GOOD");
        }
        else{
            System.out.println("NooOooP");
        }
       List<Lien>liste= Algo.detectInternalLink(t5, LL);
        System.out.println(liste);
    }
}
