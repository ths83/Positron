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
        double lDet[] = {0,0,0};
        int lI;
        double lAx = 0, lBx = 0, lAy = 0, lBy = 0;

        CFieldEntity lField;

        // On parcours la liste des territoire et on verrifie que le Portail portail n'est pas à l'interrieur.
        while (lIterator.hasNext()) {
            lField = lIterator.next();
            lDet[0]=0;
            lDet[1]=0;
            lDet[2]=0;

            // Pour chaque territoire on calcule le déterminant pour chaque segment qui le constitue.
            for (lI = 0; lI < 3; lI++) {

                lAx = lField.getmLinks().get(lI).getmPortals().get(0).getLong();
                lAy = lField.getmLinks().get(lI).getmPortals().get(0).getLat();

                if (lI != 2) {

                    lBx = lField.getmLinks().get(lI+1).getmPortals().get(0).getLong();
                    lBy = lField.getmLinks().get(lI+1).getmPortals().get(0).getLat();
                } else {
                    lBx = lField.getmLinks().get(0).getmPortals().get(0).getLong();
                    lBy = lField.getmLinks().get(0).getmPortals().get(0).getLat();
                }

                lDet[lI] = ((lBx - lAx) * (pPortal.getLat() - lAy)) - ((lBy - lAy) * (pPortal.getLong() - lAx));


            }

            // Si tout les déterminant sont du même signe, le point est dans le territoire et on renvoie faux.
            if((lDet[0]<0 && lDet[1]<0 && lDet[2]<0) ||(lDet[0]>0 && lDet[1]>0 && lDet[2]>0)){

                return false;
            }
        }

        // Si on sort de la boucle c'est que le point n'est dans aucun territoire.
        return true;

    }


    public static boolean detectLinkCollision(CLinkEntity pLinkToDo, List<CLinkEntity> pLinkList){
        

        Iterator<CLinkEntity> lIteratorLink = pLinkList.iterator();
        System.out.println("PreIteration");
        double lABx=0,lABy=0,lAP1y=0,lAP1x=0,lAP2y=0,lAP2x=0;
        System.out.println("1 "+ pLinkToDo);

        double lP1X = pLinkToDo.getmPortals().get(0).getLong();

        System.out.println("2");
        double lP2X = pLinkToDo.getmPortals().get(1).getLong();
        System.out.println("3");
        double lP1Y = pLinkToDo.getmPortals().get(0).getLat();
        System.out.println("4");
        double lP2Y = pLinkToDo.getmPortals().get(1).getLat();

        System.out.println("5");
        CLinkEntity lComparedLinks;

        System.out.println("6");
        while(lIteratorLink.hasNext()){
            System.out.println("7");

            lComparedLinks = lIteratorLink.next();

            lABx=lComparedLinks.getmPortals().get(1).getLong()-lComparedLinks.getmPortals().get(0).getLong();
            lABy=lComparedLinks.getmPortals().get(1).getLat()-lComparedLinks.getmPortals().get(0).getLat();
            lAP1x=lP1X-lComparedLinks.getmPortals().get(0).getLong();
            lAP1y=lP1Y-lComparedLinks.getmPortals().get(0).getLat();
            lAP2x=lP2X-lComparedLinks.getmPortals().get(0).getLong();
            lAP2y=lP2Y-lComparedLinks.getmPortals().get(0).getLat();
            System.out.println("Dans le while aprés les Initit");

            if((lABx*lAP1y-lABy*lAP1x)*(lABx*lAP2y-lABy*lAP2x)<0) {
                System.out.println("1er eme det");
                if ((lABy * lAP1x - lABx * lAP1y) * (lABy * lAP2x - lABx * lAP2y) < 0) {
                    System.out.println("2eme det");

                    return false;

                }
            }

        }

        return true;
    }




    public static boolean detectColision(CLinkEntity pCheckedLink,List<CLinkEntity> pLinkList,List<CFieldEntity> pFieldList){
        System.out.println("PreDetection Lien");
        if  (CAlgorithm.detectLinkCollision(pCheckedLink, pLinkList)){
            System.out.println("Colision Lien non détectée");
            if (CAlgorithm.detectFieldCollision(pCheckedLink.getmPortals().get(0), pFieldList)){
                System.out.println("Colision Territoire non détectée");
                return true;
            }
            else{
                System.out.println("Colision Territoiredétectée");
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


        for(li=0;li<3;li++){
            for(lu=0;lu<2;lu++){
                if(li==2){

                    if(lu==0){
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li+1).getmPortals().get(0).getLong()-pFieldCreated.getmLinks().get(li).getmPortals().get(0).getLong();
                    }
                    else{
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li+1).getmPortals().get(0).getLong()-pFieldCreated.getmLinks().get(li).getmPortals().get(0).getLat();
                    }
                }
                else {
                    if(lu==0){
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li).getmPortals().get(0).getLong()-pFieldCreated.getmLinks().get(0).getmPortals().get(0).getLong();
                    }
                    else{
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li).getmPortals().get(0).getLong()-pFieldCreated.getmLinks().get(0).getmPortals().get(0).getLat();
                    }
                }
            }
        }

        for(CLinkEntity lLinkVerified : pLinkList){

            if(lLinkVerified.getLinkId()!= pFieldCreated.getmLinks().get(0).getLinkId() && lLinkVerified.getLinkId()!= pFieldCreated.getmLinks().get(1).getLinkId() && lLinkVerified.getLinkId()!= pFieldCreated.getmLinks().get(2).getLinkId()) {

                for (li = 0; li < 3; li++) {
                    for (lu = 0; lu < 2; lu++) {
                        if (lu == 0) {
                            lLinkVector[li][lu] = lLinkVerified.getmPortals().get(0).getLong() - pFieldCreated.getmLinks().get(li).getmPortals().get(0).getLong();
                        } else {
                            lLinkVector[li][lu] = lLinkVerified.getmPortals().get(0).getLat() - pFieldCreated.getmLinks().get(li).getmPortals().get(0).getLat();
                        }
                    }
                }


                for (li = 0; li < 3; li++) {

                    det[li] = lFieldVector[li][0] * lLinkVector[li][1] - lFieldVector[li][1] * lLinkVector[li][0];
                }
                System.out.println(det[0] + "   " + det[1] + "   " + det[2]);
                if (det[0] + det[1] + det[2] != 0) {
                    if (((det[0] <= 0 && det[1] <= 0 && det[2] <= 0) || (det[0] >= 0 && det[1] >= 0 && det[2] >= 0))) {
                        lInternalLinkList.add(lLinkVerified.getLinkId());
                    }
                }
            }
        }
        return lInternalLinkList;

    }



    public static List<CLinkEntity> detecteNewFields(CLinkEntity pLinkCreated){
        List<CLinkEntity> lLinkNewField = new ArrayList<>();
        CPortalEntity lPortalVerified1= null,lPortalVerified2= null;
        CPortalEntity lPortals[]={pLinkCreated.getmPortals().get(0),pLinkCreated.getmPortals().get(1)};



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
