package fr.univtln.groupec.tperron710;



import fr.univtln.groupec.tperron710.algo.Algo;
import fr.univtln.groupec.tperron710.objet.Portail;
import fr.univtln.groupec.tperron710.objet.Territoire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdurbec066 on 02/05/16.
 */
public class TestAlgoTerri {

    public static void main(String[] args) {

        Portail p1 = new Portail(40,15);

        Portail p2 = new Portail(1,1);
        Portail p3 = new Portail(1,3);
        Portail p4 = new Portail(4,1);


        Portail p5 = new Portail(40,30);
        Portail p6 = new Portail(30,10);
        Portail p7 = new Portail(50,10);

        List<Portail> LP1 = new ArrayList<Portail>();
        LP1.add(p2);
        LP1.add(p3);
        LP1.add(p4);

        List<Portail> LP2 = new ArrayList<Portail>();
        LP2.add(p5);
        LP2.add(p6);
        LP2.add(p7);

        Territoire t1 = new Territoire(LP1);
        Territoire t2 = new Territoire(LP2);


        List<Territoire> LT = new ArrayList<Territoire>();
        LT.add(t1);
        LT.add(t2);

        Boolean good = Algo.validationTerritoire(p1, LT);

        if(good){
            System.out.println("GOOD");
        }
        else{
            System.out.println("NooOooP");
        }
    }
}
