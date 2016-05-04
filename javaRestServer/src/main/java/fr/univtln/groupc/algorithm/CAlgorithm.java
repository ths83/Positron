package fr.univtln.groupc.algorithm;

import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by xdurbec066 on 03/05/16.
 */
public class CAlgorithm {


    /*public static boolean detectFieldCollision(CPortalEntity pPortal, List<CFieldEntity> pListField) {
        Iterator<CFieldEntity> mIterator = pListField.iterator();
        double lDet[] = {0,0,0};
        int lI;
        double mAx = 0, mBx = 0, mAy = 0, mBy = 0;

        CFieldEntity mField;

        // On parcours la liste des territoire et on verrifie que le Portail portail n'est pas à l'interrieur.
        while (mIterator.hasNext()) {
            mField = mIterator.next();
            lDet[0]=0;
            lDet[1]=0;
            lDet[2]=0;

            // Pour chaque territoire on calcule le déterminant pour chaque segment qui le constitue.
            for (lI = 0; lI < 3; lI++) {
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

            }

            // Si tout les déterminant sont du même signe, le point est dans le territoire et on renvoie faux.
            if((lDet[0]<0 && lDet[1]<0 && lDet[2]<0) ||(lDet[0]>0 && lDet[1]>0 && lDet[2]>0)){
                return false;
            }
        }

        // Si on sort de la boucle c'est que le point n'est dans aucun territoire.
        return true;

    }


    public static boolean detectLinkCollision(CLinkEntity mLinkToDo, List<CLinkEntity> mLinkList){
        
        Iterator<CLinkEntity> mIteratorLink = mLinkList.iterator();
        double mABx=0,mABy=0,mAP1y=0,mAP1x=0,mAP2y=0,mAP2x=0;
        
        double mP1X = mLinkToDo.getPortals().get(0).getLong();
        double mP2X = mLinkToDo.getPortals().get(1).getLong();
        double mP1Y = mLinkToDo.getPortals().get(0).getLat();
        double mP2Y = mLinkToDo.getPortals().get(1).getLat();
        
        CLinkEntity mComparedLinks;
        
        while(mIteratorLink.hasNext()){
            mComparedLinks = mIteratorLink.next();
            mABx=mComparedLinks.getPortals().get(1).getLong()-mComparedLinks.getPortals().get(0).getLong();
            mABy=mComparedLinks.getPortals().get(1).getLat()-mComparedLinks.getPortals().get(0).getLat();
            mAP1x=mP1X-mComparedLinks.getPortals().get(0).getLong();
            mAP1y=mP1Y-mComparedLinks.getPortals().get(0).getLat();
            mAP2x=mP2X-mComparedLinks.getPortals().get(0).getLong();
            mAP2y=mP2Y-mComparedLinks.getPortals().get(0).getLat();

            if((mABx*mAP1y-mABy*mAP1x)*(mABx*mAP2y-mABy*mAP2x)<0){
                return false;
            }

        }

        return true;
    }



    public static boolean detectColision(CLinkEntity mCheckedLink,List<CLinkEntity> mLinkList,List<CFieldEntity> mFieldList){
        if (CAlgorithm.detectFieldCollision(mCheckedLink.getPortals().get(0), mFieldList)) {
            System.out.println("Colision Territoire non détectée");
            if(CAlgorithm.detectLinkCollision(mCheckedLink, mLinkList)){
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


    public static double[] calculateHeronFunction(double mTab[], int mValueToCalc){

        double pResult[] = new double[mValueToCalc];
        double pPerimeterByTwo;

        long pInit = System.currentTimeMillis();
        for (int i = 2; i < mValueToCalc; i ++ ){
            pPerimeterByTwo = (mTab[i-2] + mTab[i-1] + mTab[i]) / 2;
            pResult[i] =Math.abs(Math.sqrt(pPerimeterByTwo * (pPerimeterByTwo - mTab[i-2])
                    * (pPerimeterByTwo - mTab[i-1]) * (pPerimeterByTwo - mTab[i])));
        }
        System.out.println(System.currentTimeMillis()- pInit);
        return pResult;
    }

    public static double[] calculateSinusLaw(double mTab[], int mValueToCalc){

        double[] pResult = new double[mValueToCalc];
        return pResult;
    }*/

}
