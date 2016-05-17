package fr.univtln.groupc.algorithm;

import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


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
                lAx = lField.getmLinks().get(li).getPortals().get(0).getLong();
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
                System.out.println("Field causant la colision: ");
                return false;
            }
        }


        // Si on sort de la boucle c'est que le point n'est dans aucun territoire.
        return true;

    }




    public static boolean detectLinkCollision(CLinkEntity pLinkToDo, List<CLinkEntity> pLinkList){
        double lP1X = pLinkToDo.getPortals().get(0).getLong();
        double lP2X = pLinkToDo.getPortals().get(1).getLong();
        double lP1Y = pLinkToDo.getPortals().get(0).getLat();
        double lP2Y = pLinkToDo.getPortals().get(1).getLat();
        int lCount = 0;
        for (CLinkEntity lLink : pLinkList){
            if (detectCollisionBetween2Links(lP1X, lP1Y, lP2X, lP2Y, lLink)){
                return false;
            }
        }
        return true;
    }

    public static boolean detectCollisionBetween2Links(double lP1X,double lP1Y,double lP2X,double lP2Y,CLinkEntity lComparedLink){
        double lABx=0,lABy=0,lAP1y=0,lAP1x=0,lAP2y=0,lAP2x=0;

        lABx = lComparedLink.getPortals().get(1).getLong() - lComparedLink.getPortals().get(0).getLong();
        lABy = lComparedLink.getPortals().get(1).getLat() - lComparedLink.getPortals().get(0).getLat();
        lAP1x = lP1X - lComparedLink.getPortals().get(0).getLong();
        lAP1y = lP1Y - lComparedLink.getPortals().get(0).getLat();
        lAP2x = lP2X - lComparedLink.getPortals().get(0).getLong();
        lAP2y = lP2Y - lComparedLink.getPortals().get(0).getLat();
        //
        if ((lABx * lAP1y - lABy * lAP1x) ==0 && (lABx * lAP2y - lABy * lAP2x)==0) {
            System.out.println("Lien déjà existant");
            return true;
        }
        //
        else {
            if ((lABx * lAP1y - lABy * lAP1x) * (lABx * lAP2y - lABy * lAP2x) < 0) {

                if ((lABy * lAP1x - lABx * lAP1y) * (lABy * lAP2x - lABx * lAP2y) < 0) {


                    return true;

                }
            }

        }
        return false;
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
        System.out.println( "FieldCreated =" +pFieldCreated);

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

            }

        }

        for(CLinkEntity lLinkVerified : pLinkList){

            if(lLinkVerified.getId()!= pFieldCreated.getmLinks().get(0).getId() && lLinkVerified.getId()!= pFieldCreated.getmLinks().get(1).getId() && lLinkVerified.getId()!= pFieldCreated.getmLinks().get(2).getId()) {

                for (li = 0; li < 2; li++) {
                    for (lu = 0; lu < 2; lu++) {
                        if (lu == 0) {
                            lLinkVector[li][lu] = lLinkVerified.getPortals().get(0).getLong() - pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong();
                        } else {
                            lLinkVector[li][lu] = lLinkVerified.getPortals().get(0).getLat() - pFieldCreated.getmLinks().get(li).getPortals().get(0).getLat();

                        }
                    }
                }


                for (li = 0; li < 2; li++) {

                    det[li] = lFieldVector[li][0] * lLinkVector[li][1] - lFieldVector[li][1] * lLinkVector[li][0];
                }
            //    System.out.println(det[0] + "   " + det[1] + "   " + det[2]);
                if (det[0] + det[1] + det[2] != 0) {

                    if (((det[0] <= 0 && det[1] <= 0 && det[2] <= 0) || (det[0] >= 0 && det[1] >= 0 && det[2] >= 0))) {

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

    public static List<CFieldEntity> convertLinkListToFieldList(List<CLinkEntity> lLinkListToExtracte){
       List<CFieldEntity> lListFieldToCreate = new ArrayList<>();


        for (int li = 0; li < lLinkListToExtracte.size(); li += 3) {
            List<CLinkEntity> lLinkListToCreate= new ArrayList<>();

            for (int lu = 0; lu < 3; lu++) {
               lLinkListToCreate.add(lLinkListToExtracte.get(lu + li));
            }
            lListFieldToCreate.add(new CFieldEntity.CFieldBuilder(64).links(lLinkListToCreate).build());
        }

        Collections.sort(lListFieldToCreate);
        return lListFieldToCreate;


    }

}
