package fr.univtln.groupec.tperron710.algo;


import fr.univtln.groupec.tperron710.objet.Portail;
import fr.univtln.groupec.tperron710.objet.Territoire;

import java.util.Iterator;
import java.util.List;

/**
 * Created by xdurbec066 on 02/05/16.
 */
public abstract class Algo {


    public static boolean validationTerritoire(Portail portail, List<Territoire> LT) {
        Iterator<Territoire> iterator = LT.iterator();
        int det[] = {0,0,0};
        int i;
        int flag = 0;
        int Ax = 0, Bx = 0, Ay = 0, By = 0;

        Territoire territoire;
        while (iterator.hasNext()) {
            territoire = iterator.next();
            det[0]=0;
            det[1]=0;
            det[2]=0;
            //
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

                // Le point est à l'exterieur
                System.out.println("Boucle"+i+" = "+det[i]);

            }
            if((det[0]<0 && det[1]<0 && det[2]<0) ||(det[0]>0 && det[1]>0 && det[2]>0)){
                return false;
            }
        }
        return true;

    }
}