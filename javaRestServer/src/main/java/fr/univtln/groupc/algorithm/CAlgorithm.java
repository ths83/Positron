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

    public static boolean detectFieldCollision(CPortalEntity mPortal, List<CFieldEntity> mListField) {
        Iterator<CFieldEntity> iterator = mListField.iterator();
        double pDet[] = {0,0,0};
        int i;
        double pAx = 0, pBx = 0, pAy = 0, pBy = 0;

        CFieldEntity pField;

        // On parcours la liste des territoire et on verrifie que le Portail portail n'est pas à l'interrieur.
        while (iterator.hasNext()) {
            pField = iterator.next();
            pDet[0]=0;
            pDet[1]=0;
            pDet[2]=0;

            // Pour chaque territoire on calcule le déterminant pour chaque segment qui le constitue.
            for (i = 0; i < 3; i++) {
                pAx = pField.getmLinks().get(i).getmPortals().get(0).getmLong();
                pAy = pField.getmLinks().get(i).getmPortals().get(0).getmLat();

                if (i != 2) {

                    pBx = pField.getmLinks().get(i+1).getmPortals().get(0).getmLong();
                    pBy = pField.getmLinks().get(i+1).getmPortals().get(0).getmLat();
                } else {
                    pBx = pField.getmLinks().get(0).getmPortals().get(0).getmLong();
                    pBy = pField.getmLinks().get(0).getmPortals().get(0).getmLat();
                }

                pDet[i] = ((pBx - pAx) * (mPortal.getmLat() - pAy)) - ((pBy - pAy) * (mPortal.getmLong() - pAx));

            }

            // Si tout les déterminant sont du même signe, le point est dans le territoire et on renvoie faux.
            if((pDet[0]<0 && pDet[1]<0 && pDet[2]<0) ||(pDet[0]>0 && pDet[1]>0 && pDet[2]>0)){
                return false;
            }
        }

        // Si on sort de la boucle c'est que le point n'est dans aucun territoire.
        return true;

    }


    public static boolean detectLinkCollision(CLinkEntity mLinkToDo, List<CLinkEntity> mLinkList){
        
        Iterator<CLinkEntity> pIteratorLink = mLinkList.iterator();
        double pABx=0,pABy=0,pAP1y=0,pAP1x=0,pAP2y=0,pAP2x=0;
        
        double pP1X = mLinkToDo.getmPortals().get(0).getmLong();
        double pP2X = mLinkToDo.getmPortals().get(1).getmLong();
        double pP1Y = mLinkToDo.getmPortals().get(0).getmLat();
        double pP2Y = mLinkToDo.getmPortals().get(1).getmLat();
        
        CLinkEntity pComparedLinks;
        
        while(pIteratorLink.hasNext()){
            pComparedLinks = pIteratorLink.next();
            pABx=pComparedLinks.getmPortals().get(1).getmLong()-pComparedLinks.getmPortals().get(0).getmLong();
            pABy=pComparedLinks.getmPortals().get(1).getmLat()-pComparedLinks.getmPortals().get(0).getmLat();
            pAP1x=pP1X-pComparedLinks.getmPortals().get(0).getmLong();
            pAP1y=pP1Y-pComparedLinks.getmPortals().get(0).getmLat();
            pAP2x=pP2X-pComparedLinks.getmPortals().get(0).getmLong();
            pAP2y=pP2Y-pComparedLinks.getmPortals().get(0).getmLat();

            if((pABx*pAP1y-pABy*pAP1x)*(pABx*pAP2y-pABy*pAP2x)<0){
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
