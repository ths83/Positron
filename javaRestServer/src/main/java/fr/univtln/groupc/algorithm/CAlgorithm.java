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


    public static boolean detectFieldCollision(CPortalEntity pPortal, List<CFieldEntity> pListField) {
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
                mAx = mField.getmLinks().get(lI).getmPortals().get(0).getmLong();
                mAy = mField.getmLinks().get(lI).getmPortals().get(0).getmLat();

                if (lI != 2) {

                    mBx = mField.getmLinks().get(lI+1).getmPortals().get(0).getmLong();
                    mBy = mField.getmLinks().get(lI+1).getmPortals().get(0).getmLat();
                } else {
                    mBx = mField.getmLinks().get(0).getmPortals().get(0).getmLong();
                    mBy = mField.getmLinks().get(0).getmPortals().get(0).getmLat();
                }

                lDet[lI] = ((mBx - mAx) * (pPortal.getmLat() - mAy)) - ((mBy - mAy) * (pPortal.getmLong() - mAx));

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
        
        double mP1X = mLinkToDo.getmPortals().get(0).getmLong();
        double mP2X = mLinkToDo.getmPortals().get(1).getmLong();
        double mP1Y = mLinkToDo.getmPortals().get(0).getmLat();
        double mP2Y = mLinkToDo.getmPortals().get(1).getmLat();
        
        CLinkEntity mComparedLinks;
        
        while(mIteratorLink.hasNext()){
            mComparedLinks = mIteratorLink.next();
            mABx=mComparedLinks.getmPortals().get(1).getmLong()-mComparedLinks.getmPortals().get(0).getmLong();
            mABy=mComparedLinks.getmPortals().get(1).getmLat()-mComparedLinks.getmPortals().get(0).getmLat();
            mAP1x=mP1X-mComparedLinks.getmPortals().get(0).getmLong();
            mAP1y=mP1Y-mComparedLinks.getmPortals().get(0).getmLat();
            mAP2x=mP2X-mComparedLinks.getmPortals().get(0).getmLong();
            mAP2y=mP2Y-mComparedLinks.getmPortals().get(0).getmLat();

            if((mABx*mAP1y-mABy*mAP1x)*(mABx*mAP2y-mABy*mAP2x)<0){
                return false;
            }

        }

        return true;
    }



    public static boolean detectColision(CLinkEntity mCheckedLink,List<CLinkEntity> mLinkList,List<CFieldEntity> mFieldList){
        if (CAlgorithm.detectFieldCollision(mCheckedLink.getmPortals().get(0), mFieldList)) {
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

}
