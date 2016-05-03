package fr.univtln.groupec.tperron710.algo;


import fr.univtln.groupec.tperron710.objet.Lien;
import fr.univtln.groupec.tperron710.objet.Portail;
import fr.univtln.groupec.tperron710.objet.Territoire;

import java.util.Iterator;
import java.util.List;

/**
 * Created by xdurbec066 on 02/05/16.
 */
public abstract class Algo {


    public static boolean detectionColisionTerritoire(Portail portail, List<Territoire> LT) {
        Iterator<Territoire> iterator = LT.iterator();
        int det[] = {0,0,0};
        int i;
        int Ax = 0, Bx = 0, Ay = 0, By = 0;

        Territoire territoire;

        // On parcours la liste des territoire et on verrifie que le Portail portail n'est pas à l'interrieur.
        while (iterator.hasNext()) {
            territoire = iterator.next();
            det[0]=0;
            det[1]=0;
            det[2]=0;

            // Pour chaque territoire on calcule le déterminant pour chaque segment qui le constitue.
            for (i = 0; i < 3; i++) {
                Ax = territoire.getLP().get(i).getX();
                Ay = territoire.getLP().get(i).getY();

                if (i != 2) {

                    Bx = territoire.getLP().get(i + 1).getX();
                    By = territoire.getLP().get(i + 1).getY();
                } else {
                    Bx = territoire.getLP().get(0).getX();
                    By = territoire.getLP().get(0).getY();
                }

                det[i] = ((Bx - Ax) * (portail.getY() - Ay)) - ((By - Ay) * (portail.getX() - Ax));

            }

            // Si tout les déterminant sont du même signe, le point est dans le territoire et on renvoie faux.
            if((det[0]<0 && det[1]<0 && det[2]<0) ||(det[0]>0 && det[1]>0 && det[2]>0)){
                return false;
            }
        }

        // Si on sort de la boucle c'est que le point n'est dans aucun territoire.
        return true;

    }

    public static boolean detectionColisionLien(Lien lienAcreer, List<Lien> LL){
        Iterator<Lien> lienIterator = LL.iterator();
        int ABx=0,ABy=0,AP1y=0,AP1x=0,AP2y=0,AP2x=0;

        int p1X = lienAcreer.getP1().getX();
        int p2X = lienAcreer.getP2().getX();
        int p1Y = lienAcreer.getP1().getY();
        int p2Y = lienAcreer.getP2().getY();

        Lien lienComparer;
        while(lienIterator.hasNext()){
            lienComparer = lienIterator.next();
            ABx=lienComparer.getP2().getX()-lienComparer.getP1().getX();
            ABy=lienComparer.getP2().getY()-lienComparer.getP1().getY();
            AP1x=p1X-lienComparer.getP1().getX();
            AP1y=p1Y-lienComparer.getP1().getY();
            AP2x=p2X-lienComparer.getP1().getX();
            AP2y=p2Y-lienComparer.getP1().getY();

            if((ABx*AP1y-ABy*AP1x)*(ABx*AP2y-ABy*AP2x)<0){
                return false;
            }

        }

        return true;
    }

    public static boolean detectColision(Lien lienVerifier,List<Lien> LL,List<Territoire> LT){
        if (Algo.detectionColisionTerritoire(lienVerifier.getP1(),LT)) {
            System.out.println("Colision Territoire non détectée");
            if(Algo.detectionColisionLien(lienVerifier,LL)){
                System.out.println("Colision Lien non détectée");
                return true;
            }
            else{
                System.out.println("Colision Lien détectée");
                return false;
            }
        }
        else{
        System.out.println("Colision Territoire détectée");
        return false;
        }
    }

}