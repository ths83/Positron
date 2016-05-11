package fr.univtln.groupc.algorithm;

import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by xdurbec066 on 03/05/16.
 */
public class CAlgorithm {


    public static boolean detectFieldCollision(CPortalEntity pPortal, List<CFieldEntity> pListField) {
        Iterator<CFieldEntity> lIterator = pListField.iterator();
        double lDet[] = {0, 0, 0};
        int li;
        double lAx = 0, lBx = 0, lAy = 0, lBy = 0;
        System.out.println("Detection colision field en cours ....");
        CFieldEntity lField;
        System.out.println(pListField);

        // On parcours la liste des territoire et on verrifie que le Portail portail n'est pas à l'interrieur.


        while (lIterator.hasNext()) {

            lField = lIterator.next();
            lDet[0] = 0;
            lDet[1] = 0;
            lDet[2] = 0;


            // Pour chaque territoire on calcule le déterminant pour chaque segment qui le constitue.
            for (li = 0; li < 3; li++) {
                System.out.println("For li:" + li);
                System.out.println("Lfield: "+lField.getmLinks());
                lAx = lField.getmLinks().get(li).getPortals().get(0).getLong();

                System.out.println("lAx =" + lAx);
                lAy = lField.getmLinks().get(li).getPortals().get(0).getLat();

                if (li != 2) {

                    lBx = lField.getmLinks().get(li + 1).getPortals().get(0).getLong();
                    lBy = lField.getmLinks().get(li + 1).getPortals().get(0).getLat();
                } else {
                    lBx = lField.getmLinks().get(0).getPortals().get(0).getLong();
                    lBy = lField.getmLinks().get(0).getPortals().get(0).getLat();
                }

                lDet[li] = ((lBx - lAx) * (pPortal.getLat() - lAy)) - ((lBy - lAy) * (pPortal.getLong() - lAx));


            }

            // Si tout les déterminant sont du même signe, le point est dans le territoire et on renvoie faux.
            if ((lDet[0] < 0 && lDet[1] < 0 && lDet[2] < 0) || (lDet[0] > 0 && lDet[1] > 0 && lDet[2] > 0)) {

                return false;
            }
        }


        // Si on sort de la boucle c'est que le point n'est dans aucun territoire.
        return true;

    }


    public static boolean detectLinkCollision(CLinkEntity pLinkToDo, List<CLinkEntity> pLinkList){
        

        Iterator<CLinkEntity> lIteratorLink = pLinkList.iterator();
        double lABx=0,lABy=0,lAP1y=0,lAP1x=0,lAP2y=0,lAP2x=0;

        double lP1X = pLinkToDo.getPortals().get(0).getLong();
        double lP2X = pLinkToDo.getPortals().get(1).getLong();
        double lP1Y = pLinkToDo.getPortals().get(0).getLat();
        double lP2Y = pLinkToDo.getPortals().get(1).getLat();


        CLinkEntity lComparedLinks;


        while(lIteratorLink.hasNext()){


            lComparedLinks = lIteratorLink.next();


            lABx=lComparedLinks.getPortals().get(1).getLong()-lComparedLinks.getPortals().get(0).getLong();
            lABy=lComparedLinks.getPortals().get(1).getLat()-lComparedLinks.getPortals().get(0).getLat();
            lAP1x=lP1X-lComparedLinks.getPortals().get(0).getLong();
            lAP1y=lP1Y-lComparedLinks.getPortals().get(0).getLat();
            lAP2x=lP2X-lComparedLinks.getPortals().get(0).getLong();
            lAP2y=lP2Y-lComparedLinks.getPortals().get(0).getLat();

            if((lABx*lAP1y-lABy*lAP1x)*(lABx*lAP2y-lABy*lAP2x)<0) {

                if ((lABy * lAP1x - lABx * lAP1y) * (lABy * lAP2x - lABx * lAP2y) < 0) {


                    return false;

                }
            }

        }

        return true;
    }




    public static boolean detectColision(CLinkEntity pCheckedLink,List<CLinkEntity> pLinkList,List<CFieldEntity> pFieldList){

        System.out.println("Detection de colision en cours....");
        if    (CAlgorithm.detectLinkCollision(pCheckedLink, pLinkList)) {

            System.out.println("Colision Lien non détectée");

            if(CAlgorithm.detectFieldCollision(pCheckedLink.getPortals().get(0), pFieldList)) {
                System.out.println("Colision Territoire non détectée");
                return true;
            }

            else{
                System.out.println("Colision Territoire détectée");
                return false;
            }

        }

        else{
            
            System.out.println("Colision Lien détectée");
            return false;

        }
    }


    public static double[] calculateHeronFunction(double pTab[], int pValueToCalc){

        double lResult[] = new double[pValueToCalc];
        double lPerimeterByTwo;

        long lInit = System.currentTimeMillis();
        for (int i = 2; i < pValueToCalc; i ++ ){
            lPerimeterByTwo = (pTab[i-2] + pTab[i-1] + pTab[i]) / 2;
            lResult[i] =Math.abs(Math.sqrt(lPerimeterByTwo * (lPerimeterByTwo - pTab[i-2])
                    * (lPerimeterByTwo - pTab[i-1]) * (lPerimeterByTwo - pTab[i])));
        }
        System.out.println(System.currentTimeMillis()- lInit);
        return lResult;
    }


    public static double[] calculateSinusLaw(double pTab[], int pValueToCalc){

        double[] lResult = new double[pValueToCalc];
        return lResult;
    }


    public static List<Integer> detectInternalLink (CFieldEntity pFieldCreated,List<CLinkEntity> pLinkList){
        List<Integer> lInternalLinkList = new ArrayList<Integer>();

        double lPx=0,lPy=0,lFieldVector[][]={{0,0},{0,0},{0,0}},det[]={0,0,0},lLinkVector[][]={{0,0},{0,0},{0,0}};
        int li=0,lu=0;

        System.out.println("lFielde[0][0] ="+ pFieldCreated.getmLinks().get(1).getPortals().get(0).getLong()+" - "+pFieldCreated.getmLinks().get(0).getPortals().get(0).getLong());

        for(li=0;li<2;li++){
            for(lu=0;lu<2;lu++){

                if(li==2){

                    if(lu==0){
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li+1).getPortals().get(0).getLong()-pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong();
                    }
                    else{
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li+1).getPortals().get(0).getLong()-pFieldCreated.getmLinks().get(li).getPortals().get(0).getLat();
                    }
                }
                else {
                    if(lu==0){
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong()-pFieldCreated.getmLinks().get(0).getPortals().get(0).getLong();
                    }
                    else{
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong()-pFieldCreated.getmLinks().get(0).getPortals().get(0).getLat();
                    }
                }
                System.out.println("lFieldVector["+li+"]["+lu+"] = "+lFieldVector[li][lu]);
            }

        }

        for(CLinkEntity lLinkVerified : pLinkList){

            if(lLinkVerified.getId()!= pFieldCreated.getmLinks().get(0).getId() && lLinkVerified.getId()!= pFieldCreated.getmLinks().get(1).getId() && lLinkVerified.getId()!= pFieldCreated.getmLinks().get(2).getId()) {

                for (li = 0; li < 2; li++) {
                    for (lu = 0; lu < 2; lu++) {
                        if (lu == 0) {
                            lLinkVector[li][lu] = lLinkVerified.getPortals().get(0).getLong() - pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong();
                            System.out.println("Verified Portail: "+lLinkVerified.getPortals().get(0).getLong()+" - created "+pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong());
                            System.out.println("lVec "+lLinkVector[li][lu]);
                        } else {
                            lLinkVector[li][lu] = lLinkVerified.getPortals().get(0).getLat() - pFieldCreated.getmLinks().get(li).getPortals().get(0).getLat();
                            System.out.println("Verified Portail: "+lLinkVerified.getPortals().get(0).getLat()+" - created "+pFieldCreated.getmLinks().get(li).getPortals().get(0).getLat());
                            System.out.println("lVec "+lLinkVector[li][lu]);
                        }
                    }
                }


                for (li = 0; li < 2; li++) {

                    det[li] = lFieldVector[li][0] * lLinkVector[li][1] - lFieldVector[li][1] * lLinkVector[li][0];
                }
                System.out.println(det[0] + "   " + det[1] + "   " + det[2]);
                if (det[0] + det[1] + det[2] != 0) {

                    if (((det[0] <= 0 && det[1] <= 0 && det[2] <= 0) || (det[0] >= 0 && det[1] >= 0 && det[2] >= 0))) {
                        System.out.println("On inejecte le Lien:");
                        System.out.println("On inejecte le Lien:"+lLinkVerified.getId());
                        lInternalLinkList.add(lLinkVerified.getId());
                    }
                }
            }
        }
        return lInternalLinkList;

    }


    public static List<CLinkEntity> detecteNewFields(CLinkEntity pLinkCreated){
        List<CLinkEntity> lLinkNewField = new ArrayList<>();
        CPortalEntity lPortalVerified1= null,lPortalVerified2= null;
        CPortalEntity lPortals[]={pLinkCreated.getPortals().get(0),pLinkCreated.getPortals().get(1)};



        for(CLinkEntity lLinks : lPortals[0].getLinks()){
           lPortalVerified1= getOtherPortalOfLink(lPortals[0],lLinks);
           for(CLinkEntity lLinks2 : lPortals[1].getLinks()){
               lPortalVerified2= getOtherPortalOfLink(lPortals[1],lLinks2);
               if(lPortalVerified1 == lPortalVerified2){
                   lLinkNewField.add(lLinks);
                   lLinkNewField.add(lLinks2);
                   lLinkNewField.add(pLinkCreated);
               }

           }
        }
        return lLinkNewField;
    }

    // A voir où le metre entre Algo,Portail et Lien
    public static CPortalEntity getOtherPortalOfLink(CPortalEntity pPortalIn,CLinkEntity pLink){

        if(pPortalIn != pLink.getPortals().get(0)){
            return pLink.getPortals().get(0);
        }
        else {
            return pLink.getPortals().get(1);
        }

    }

}
