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
<<<<<<< HEAD
                lAx = lField.getmLinks().get(lI).getmPortals().get(0).getmLong();
                lAy = lField.getmLinks().get(lI).getmPortals().get(0).getmLat();

                if (lI != 2) {

                    lBx = lField.getmLinks().get(lI+1).getmPortals().get(0).getmLong();
                    lBy = lField.getmLinks().get(lI+1).getmPortals().get(0).getmLat();
                } else {
                    lBx = lField.getmLinks().get(0).getmPortals().get(0).getmLong();
                    lBy = lField.getmLinks().get(0).getmPortals().get(0).getmLat();
                }

                lDet[lI] = ((lBx - lAx) * (pPortal.getmLat() - lAy)) - ((lBy - lAy) * (pPortal.getmLong() - lAx));
=======
                mAx = mField.getLinks().get(lI).getPortals().get(0).getLong();
                mAy = mField.getLinks().get(lI).getPortals().get(0).getLat();

                if (lI != 2) {

                    mBx = mField.getLinks().get(lI+1).getPortals().get(0).getLong();
                    mBy = mField.getLinks().get(lI+1).getPortals().get(0).getLat();
                } else {
                    mBx = mField.getLinks().get(0).getPortals().get(0).getLong();
                    mBy = mField.getLinks().get(0).getPortals().get(0).getLat();
                }

                lDet[lI] = ((mBx - mAx) * (pPortal.getLat() - mAy)) - ((mBy - mAy) * (pPortal.getLong() - mAx));
>>>>>>> 51f1f4e7593151e39a3838baf86ccf4d190b8214

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
        
<<<<<<< HEAD
        Iterator<CLinkEntity> lIteratorLink = pLinkList.iterator();

        double lABx=0,lABy=0,lAP1y=0,lAP1x=0,lAP2y=0,lAP2x=0;
=======
        Iterator<CLinkEntity> mIteratorLink = mLinkList.iterator();
        double mABx=0,mABy=0,mAP1y=0,mAP1x=0,mAP2y=0,mAP2x=0;
        
        double mP1X = mLinkToDo.getPortals().get(0).getLong();
        double mP2X = mLinkToDo.getPortals().get(1).getLong();
        double mP1Y = mLinkToDo.getPortals().get(0).getLat();
        double mP2Y = mLinkToDo.getPortals().get(1).getLat();
>>>>>>> 51f1f4e7593151e39a3838baf86ccf4d190b8214
        
        double lP1X = pLinkToDo.getmPortals().get(0).getmLong();
        double lP2X = pLinkToDo.getmPortals().get(1).getmLong();
        double lP1Y = pLinkToDo.getmPortals().get(0).getmLat();
        double lP2Y = pLinkToDo.getmPortals().get(1).getmLat();
        
<<<<<<< HEAD
        CLinkEntity lComparedLinks;

        while(lIteratorLink.hasNext()){

            lComparedLinks = lIteratorLink.next();

            lABx=lComparedLinks.getmPortals().get(1).getmLong()-lComparedLinks.getmPortals().get(0).getmLong();
            lABy=lComparedLinks.getmPortals().get(1).getmLat()-lComparedLinks.getmPortals().get(0).getmLat();
            lAP1x=lP1X-lComparedLinks.getmPortals().get(0).getmLong();
            lAP1y=lP1Y-lComparedLinks.getmPortals().get(0).getmLat();
            lAP2x=lP2X-lComparedLinks.getmPortals().get(0).getmLong();
            lAP2y=lP2Y-lComparedLinks.getmPortals().get(0).getmLat();

            if((lABx*lAP1y-lABy*lAP1x)*(lABx*lAP2y-lABy*lAP2x)<0){
=======
        while(mIteratorLink.hasNext()){
            mComparedLinks = mIteratorLink.next();
            mABx=mComparedLinks.getPortals().get(1).getLong()-mComparedLinks.getPortals().get(0).getLong();
            mABy=mComparedLinks.getPortals().get(1).getLat()-mComparedLinks.getPortals().get(0).getLat();
            mAP1x=mP1X-mComparedLinks.getPortals().get(0).getLong();
            mAP1y=mP1Y-mComparedLinks.getPortals().get(0).getLat();
            mAP2x=mP2X-mComparedLinks.getPortals().get(0).getLong();
            mAP2y=mP2Y-mComparedLinks.getPortals().get(0).getLat();

            if((mABx*mAP1y-mABy*mAP1x)*(mABx*mAP2y-mABy*mAP2x)<0){
>>>>>>> 51f1f4e7593151e39a3838baf86ccf4d190b8214
                return false;
            }


        }

        return true;
    }



<<<<<<< HEAD
    public static boolean detectColision(CLinkEntity pCheckedLink,List<CLinkEntity> pLinkList,List<CFieldEntity> pFieldList){
        if (CAlgorithm.detectFieldCollision(pCheckedLink.getmPortals().get(0), pFieldList)) {
=======
    public static boolean detectColision(CLinkEntity mCheckedLink,List<CLinkEntity> mLinkList,List<CFieldEntity> mFieldList){
        if (CAlgorithm.detectFieldCollision(mCheckedLink.getPortals().get(0), mFieldList)) {
>>>>>>> 51f1f4e7593151e39a3838baf86ccf4d190b8214
            System.out.println("Colision Territoire non détectée");
            if(CAlgorithm.detectLinkCollision(pCheckedLink, pLinkList)){
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
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li+1).getmPortals().get(0).getmLong()-pFieldCreated.getmLinks().get(li).getmPortals().get(0).getmLong();
                    }
                    else{
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li+1).getmPortals().get(0).getmLong()-pFieldCreated.getmLinks().get(li).getmPortals().get(0).getmLat();
                    }
                }
                else {
                    if(lu==0){
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li).getmPortals().get(0).getmLong()-pFieldCreated.getmLinks().get(0).getmPortals().get(0).getmLong();
                    }
                    else{
                        lFieldVector[li][lu]=pFieldCreated.getmLinks().get(li).getmPortals().get(0).getmLong()-pFieldCreated.getmLinks().get(0).getmPortals().get(0).getmLat();
                    }
                }
            }
        }

        for(CLinkEntity lLinkVerified : pLinkList){

            if(lLinkVerified.getmId()!= pFieldCreated.getmLinks().get(0).getmId() && lLinkVerified.getmId()!= pFieldCreated.getmLinks().get(1).getmId() && lLinkVerified.getmId()!= pFieldCreated.getmLinks().get(2).getmId()) {

                for (li = 0; li < 3; li++) {
                    for (lu = 0; lu < 2; lu++) {
                        if (lu == 0) {
                            lLinkVector[li][lu] = lLinkVerified.getmPortals().get(0).getmLong() - pFieldCreated.getmLinks().get(li).getmPortals().get(0).getmLong();
                        } else {
                            lLinkVector[li][lu] = lLinkVerified.getmPortals().get(0).getmLat() - pFieldCreated.getmLinks().get(li).getmPortals().get(0).getmLat();
                        }
                    }
                }


                for (li = 0; li < 3; li++) {

                    det[li] = lFieldVector[li][0] * lLinkVector[li][1] - lFieldVector[li][1] * lLinkVector[li][0];
                }
                System.out.println(det[0] + "   " + det[1] + "   " + det[2]);
                if (det[0] + det[1] + det[2] != 0) {
                    if (((det[0] <= 0 && det[1] <= 0 && det[2] <= 0) || (det[0] >= 0 && det[1] >= 0 && det[2] >= 0))) {
                        lInternalLinkList.add(lLinkVerified.getmId());
                    }
                }
            }
        }
        return lInternalLinkList;

    }



}
