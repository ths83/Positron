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

    // TODO unit tests in java server
    public static final double EARTH_RADIUS = 6372.8; // In kilometers
    public static final double MILLE = 1000;


    /**
     *      detectFieldCollision a pour but de détécter si un portail est à l'intérieur d'un champ ou non.
     * Pour celas on calcule trois déterminant, Un pour chaque lien du champ et chacun avec le portail vérifier.
     * Si les déterminants sont tous du même signe le portail est à l'intérieur du champ.
     * @param pPortal :Portail vérifier.
     * @param pListField : Liste de tout les champs existant dans la base de donnée.
     * @return : Retourne Vrais/True si aucune colision, Faux/False sinon.
     */
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

                lDet[li] = calculDetermiant((lBx-lAx),(lBy-lAy),(pPortal.getLong()-lAx),(pPortal.getLat()-lAy));

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


    /**
     *    detectLinkCollision calcule les coordonées du lien voulant être créer. Il appelle alors la méthode
     * detectCollisionBetween2Link pour chaque lien de la liste pLinkList en y injectant les coordonées calculer
     * et le lien de la liste. Celas pour savoir si le lien voulus croise ou on un autre lien.
     *
     * @param pLinkToDo : Lien à vérifier et voulant être créer.
     * @param pLinkList : Liste des liens existant dans la base de donnée.
     * @return Retourne Vrais/True si aucune collision n'a était détectée. Retourne Faux/False à la premiére collision détécter.
     */
    public static boolean detectLinkCollision(CLinkEntity pLinkToDo, List<CLinkEntity> pLinkList){
        double lP1X = pLinkToDo.getPortals().get(0).getLong();
        double lP2X = pLinkToDo.getPortals().get(1).getLong();
        double lP1Y = pLinkToDo.getPortals().get(0).getLat();
        double lP2Y = pLinkToDo.getPortals().get(1).getLat();

        for (CLinkEntity lLink : pLinkList){
            if (detectCollisionBetween2Links(lP1X, lP1Y, lP2X, lP2Y, lLink)){
                return false;
            }
        }
        return true;
    }


    /**
     *    Méthode appelée par detectLinkCollision. Verifie si le lien donné en coordonée croise le second donné en
     * CLinkEntity. Pour celas on prend un premier lien L1 comme vecteur principal. On créer alors deux vecteur partant de la
     * base de L1 (son 1er portail) et ayant comme deuxiéme point les deux portail du deuxiéme lien L2.
     * On se retrouve alors avec trois vecteur partant tous du 1er portail de L1. On calcule alors les determinant des
     * deux duo que forme le vecteur V1 (L1) avec les deux autres.
     *    Si ces deux déterminant n'on pas le même signe, il y à croisement et on renvoie true. Sinon on refait la
     * même chose en prenant L2 comme vecteur principal cette fois si.
     *
     * @param pP1X Coordonée X du premier point du premier lien vérifier.
     * @param pP1Y Coordonée Y du premier point du premier lien vérifier.
     * @param pP2X Coordonée X du deuxiéme point du premier lien vérifier.
     * @param pP2Y Coordonée Y du deuxiéme point du premier lien vérifier.
     * @param lComparedLink deuxiéme Lien comparé.
     * @return Retourne Vrais/True dés qu'une collision est détecter. False sinon.
     */
    public static boolean detectCollisionBetween2Links(double pP1X,double pP1Y,double pP2X,double pP2Y,CLinkEntity lComparedLink){
        double lABx=0,lABy=0,lAP1y=0,lAP1x=0,lAP2y=0,lAP2x=0,lP1P2x=0,lP1P2y=0,lP1Ax=0,lP1Ay=0,lP1Bx=0,lP1By=0;

        // Calcule des coordoné puis des vecteurs utiliser pour le calcule du determinant fait par la méthode éponyme.
        lABx = lComparedLink.getPortals().get(1).getLong() - lComparedLink.getPortals().get(0).getLong();
        lABy = lComparedLink.getPortals().get(1).getLat() - lComparedLink.getPortals().get(0).getLat();
        lAP1x = pP1X - lComparedLink.getPortals().get(0).getLong();
        lAP1y = pP1Y - lComparedLink.getPortals().get(0).getLat();
        lAP2x = pP2X - lComparedLink.getPortals().get(0).getLong();
        lAP2y = pP2Y - lComparedLink.getPortals().get(0).getLat();

        lP1P2x = pP2X-pP1X;
        lP1P2y = pP2Y-pP1Y;
        lP1Ax = lComparedLink.getPortals().get(0).getLong() - pP1X;
        lP1Ay = lComparedLink.getPortals().get(0).getLat() - pP1Y;
        lP1Bx = lComparedLink.getPortals().get(1).getLong() - pP1X;
        lP1By = lComparedLink.getPortals().get(1).getLat() - pP1Y;

        // Si les deux vecteur sont null, les deux liens comparer sont similaire. Celas veux dire que le lien existe déjà.
        if ((calculDetermiant(lABx,lABy,lAP1x,lAP1y)) ==0 && (calculDetermiant(lABx,lABy,lAP2x,lAP2y))==0) {
            System.out.println("Lien déjà existant");
            return true;
        }
        // Les determinant de même signe multiplier entre eux sont forcément positif. On fait alors le test dans les deux sens.
        // TODO Vérifier la gestion des determinant null.
        else {
            System.out.println("Lien non existant");
            if ((calculDetermiant(lABx,lABy,lAP1x,lAP1y)) * (calculDetermiant(lABx,lABy,lAP2x,lAP2y)) < 0) {
                System.out.println("Croisement A vers B");
                if (calculDetermiant(lP1P2x,lP1P2y,lP1Ax,lP1Ay) * calculDetermiant(lP1P2x,lP1P2y,lP1Bx,lP1By) < 0) {
                    System.out.println("Croisement B vers A");

                    return true;

                }
            }

        }
        return false;
    }


    /**
     *      detectColision appéle tout les méthodes nécessaire pour vérifier si un lien vérifie tout les conditions de
     * spatial (pas de croisement et pas de lien dans un champ) avant d'être créer Il appelle detectLinkColiision et
     * detectFieldCollision.
     *
     * @param pCheckedLink : Lien devant être vérifier avant d'être créer.
     * @param pLinkList : Liste de tout les liens de la base de donnée.
     * @param pFieldList : Liste de tout les champs de la base de donnée.
     * @return True/Vrais si le lien peut être créer, False/Faux sinon.
     */
    public static boolean detectColision(CLinkEntity pCheckedLink,List<CLinkEntity> pLinkList,List<CFieldEntity> pFieldList){


        System.out.println("Detection de collision en cours....");
        if    (CAlgorithm.detectLinkCollision(pCheckedLink, pLinkList)) {

            System.out.println("Collision Lien non détectée");

            if(CAlgorithm.detectFieldCollision(pCheckedLink.getPortals().get(0), pFieldList)) {
                System.out.println("Collision Territoire non détectée");
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

    /**
     *      detectInternalLink prend tout les liens existant et vérifie si ils ne sont pas à l'interieur du champ venant
     * d'être créé. Pour vérifier cela on prend le premier portail de chaque lien et on applique la même méthode que
     * dans detectFieldCollision pour savoir si il est à l'intérieur.
     *
     * @param pFieldCreated : Champ venant d'être créer. On veux suprimmer tout les liens qui pourait être à l'intérieur.
     * @param pLinkList : Liste de tout les liens existant dans la base de donnée.
     * @return Retourne la liste des liens présent dans le champ pFieldCreated et qu'il faut suprimer. Peut être vide.
     */
    public static List<Integer> detectInternalLink (CFieldEntity pFieldCreated, List<CLinkEntity> pLinkList){
        List<Integer> lInternalLinkList = new ArrayList<Integer>();

        /**
         * lFieldVector est le regroupement des 3 vecteurs, un pour chaque lien, du champ pFieldCreated.
         * lLinkVector regroupe tout les vecteurs composer du 1er portail du lien comparé et le 1er portail de chaque
         * lien du champ. Donc 3 vecteur.
         */
        double lPx=0,lPy=0,lFieldVector[][]={{0,0},{0,0},{0,0}},det[]={0,0,0},lLinkVector[][]={{0,0},{0,0},{0,0}};
        int li=0,lu=0;
        System.out.println( "FieldCreated =" +pFieldCreated);

        // Calcule des lFieldVector
        for(li=0;li<2;li++){
            for(lu=0;lu<2;lu++){

                if(li==2){

                    if(lu==0){
                        lFieldVector[li][lu] = pFieldCreated.getmLinks().get(li+1).getPortals().get(0).getLong() - pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong();
                    }
                    else{
                        lFieldVector[li][lu] = pFieldCreated.getmLinks().get(li+1).getPortals().get(0).getLong() - pFieldCreated.getmLinks().get(li).getPortals().get(0).getLat();
                    }
                }
                else {
                    if(lu==0){
                        lFieldVector[li][lu] = pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong() - pFieldCreated.getmLinks().get(0).getPortals().get(0).getLong();
                    }
                    else{
                        lFieldVector[li][lu] = pFieldCreated.getmLinks().get(li).getPortals().get(0).getLong() - pFieldCreated.getmLinks().get(0).getPortals().get(0).getLat();
                    }
                }

            }

        }

        // Pour chaque lien on calcule les lLinkVector
        for(CLinkEntity lLinkVerified : pLinkList){

            if(lLinkVerified.getId() != pFieldCreated.getmLinks().get(0).getId() && lLinkVerified.getId() != pFieldCreated.getmLinks().get(1).getId() && lLinkVerified.getId() != pFieldCreated.getmLinks().get(2).getId()) {

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

                    det[li] = calculDetermiant(lFieldVector[li][0], lFieldVector[li][1], lLinkVector[li][0], lLinkVector[li][1]);
                }
            //    System.out.println(det[0] + "   " + det[1] + "   " + det[2]);
                if (det[0] + det[1] + det[2] != 0) {

                    if (((det[0] <= 0 && det[1] <= 0 && det[2] <= 0) || (det[0] >= 0 && det[1] >= 0 && det[2] >= 0))){

                        lInternalLinkList.add(lLinkVerified.getId());

                    }
                }
            }
        }
        return lInternalLinkList;

    }


    /**
     *      detectNewField a pour but de verifier si un champ est fermer par le lien venant d'être créer.
     * Pour cela on récupére dans deux tableau séparer tout les liens déjà existant des deux portail du lien créer P1 et P2.
     * Pour chaque lien récupérer de cette maniére on regarde le portail à l'autre extrimité et ce pour chaque tableau.
     * Enfin on compare un à un chaque tableau pour voir si il existe un ou plusieur portail similaire. Si c'est le cas
     * cela veux dire qu'un champ est créer par portail similaire.
     *
     * @param pLinkCreated : Lien venant d'être créer.
     * @return On retourne une liste de lien regrouper 3 par 3 pour chaque champ à créer.
     */
    public static List<CLinkEntity> detecteNewFields(CLinkEntity pLinkCreated){
        List<CLinkEntity> lLinkNewField = new ArrayList<>();
        CPortalEntity lPortalVerified1 = null,lPortalVerified2= null;
        CPortalEntity lPortals[] = {pLinkCreated.getPortals().get(0),pLinkCreated.getPortals().get(1)};

        for(CLinkEntity lLinks : lPortals[0].getLinks()){
           lPortalVerified1 = getOtherPortalOfLink(lPortals[0],lLinks);
           for(CLinkEntity lLinks2 : lPortals[1].getLinks()){
               lPortalVerified2 = getOtherPortalOfLink(lPortals[1],lLinks2);
               if(lPortalVerified1 == lPortalVerified2){
                   lLinkNewField.add(lLinks);
                   lLinkNewField.add(lLinks2);
                   lLinkNewField.add(pLinkCreated);
               }
           }
        }
        return lLinkNewField;
    }


    /**
     * @param pPortalIn
     * @param pLink
     * @return  Retourne l'autre portail d'un lien donnée en précisant le portail que l'on connait à la base.
     */
    public static CPortalEntity getOtherPortalOfLink(CPortalEntity pPortalIn,CLinkEntity pLink){

        if(pPortalIn != pLink.getPortals().get(0)){
            return pLink.getPortals().get(0);
        }
        else {
            return pLink.getPortals().get(1);
        }

    }

    /**
     *      Convertie une liste de lien en liste de champ. Pour cela on prend les liens trois par trois pour créer les
     *  field que l'on ajoute à une liste.
     *
     * @param lLinkListToExtracte
     * @return Renvoie une liste de champ.
     */
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


    /**
     *       Calcule du determinant.
     *
     * @param pABx valeur x du vecteur AB.
     * @param pABy valeur y du vecteur AB.
     * @param pACx valeur x du vecteur AC.
     * @param pACy valeur y du vecteur AC.
     * @return retourne un determinant sous la forme d'un double..
     */
    public static double calculDetermiant(double pABx,double pABy,double pACx,double pACy){
        double lDet = pABx * pACy - pABy * pACx;
        return lDet;

    }

    /**
     * calcule et retourne la distance en metre entre deux coordonnee mise en parametre
     *
     * -----
     *
     * calculates and returns distance between two coordinates in meters
     * coordinates are in method's parameters
     * @param lLat1
     * @param lLon1
     * @param lLat2
     * @param lLon2
     * @return
     */
    public static double haversine(double lLat1, double lLon1, double lLat2, double lLon2) {

        double lLat = Math.toRadians(lLat2 - lLat1);
        double lLon = Math.toRadians(lLon2 - lLon1);

        lLat1 = Math.toRadians(lLat1);
        lLat2 = Math.toRadians(lLat2);

        double lA = Math.pow(Math.sin(lLat / 2),2) + Math.pow(Math.sin(lLon / 2),2) * Math.cos(lLat1) * Math.cos(lLat2);
        double lC = 2 * Math.asin(Math.sqrt(lA));

        return EARTH_RADIUS * lC * MILLE ;
    }


    /**
     *  Similaire à la méthode detecteCollisionInFIeld mais pour un joueur.
     *
     * @param pPlayer : Joueur à vérifier.
     * @param lFieldList : Liste de tout les champs existant dans la base de donnée.
     * @return Retourne 0 si pas dans un champ, 1 si dans un champ allié et 2 si dans un champ ennemie.
     */
    public int inAField (CPlayerEntity pPlayer, List<CFieldEntity> lFieldList ){

        double lDet[] = {0, 0, 0};
        int li;
        double lAx = 0, lBx = 0, lAy = 0, lBy = 0;


        // On parcours la liste des territoire et on verrifie que le Portail portail n'est pas à l'interrieur.

        for(CFieldEntity lField : lFieldList){
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

                lDet[li] = calculDetermiant((lBx-lAx),(lBy-lAy),(pPlayer.getLong()-lAx),(pPlayer.getLat()-lAy));

            }

            // Si tout les déterminant sont du même signe, le point est dans le territoire et on renvoie faux.
            if ((lDet[0] < 0 && lDet[1] < 0 && lDet[2] < 0) || (lDet[0] > 0 && lDet[1] > 0 && lDet[2] > 0)) {
                if( pPlayer.getTeam().equals(lField.getLinks().get(0).getPortals().get(1).getTeam()) ){
                    return 1;
                }
                else{
                    return 2;
                }
            }
        }


        // Si on sort de la boucle c'est que le point n'est dans aucun territoire.
        return 0;

    }



}


