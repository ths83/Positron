package fr.univtln.groupc;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.*;
import fr.univtln.groupc.stats.CStatPortalHacked;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by marti on 26/05/2016.
 */


@ServerEndpoint(value = "/echo",
        encoders = {CPayloadBean.PayloadBeanCode.class},
        decoders = {CPayloadBean.PayloadBeanCode.class})
public class CServer {
    private static List<Session> mSessions = new ArrayList<Session>();
    private static List<Session> sessionsTeamBlue = new ArrayList<Session>();
    private static List<Session> sessionsTeamsRed = new ArrayList<Session>();
    //private HashMap<Integer, Session> mSessionsByPlayer = new HashMap<Integer, Session>();
    public final static String SERVER_IP;
    public final static int SERVER_PORT;
    private CCrudMethods mCrudMethods = new CCrudMethods();

    static {
        String ip = null;
        int port = 8025;
        try {
            //ip = System.getProperty("fr.univtln.bruno.test.simple.websocket.server.ip");
            //ip = System.getProperty("fr.univtln.nmartinez016.ip");
        } catch (NullPointerException e) {
        }

        try {
            //port = Integer.parseInt(System.getProperty("fr.univtln.bruno.test.simple.websocket.server.port"));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        if (ip == null) ip = "0.0.0.0";
        SERVER_IP = ip;
        SERVER_PORT = port;
        System.out.println("Server IP:" + SERVER_IP + " Port: " + SERVER_PORT);
    }


    @OnOpen
    public void onOpen(final Session session, EndpointConfig endpointConfig) {
        System.out.println("new Client connected in session " + session.getId());
        mSessions.add(session);
        System.out.println("session ajoutee");
    }


    @OnMessage
    public void echo(CPayloadBean pBean, Session pPeer) throws IOException, EncodeException {
        System.out.println("Received: " + pBean);
        // CAS CONNECTION
        if (pBean.getType().equals(EPayloadType.CONNECTION.toString())) {
            System.out.println("un cas de connexion");
            System.out.println(pBean.getConnection());
            int lId = pBean.getConnection().getPlayerId();
            System.out.println("id de session : " + lId);
            pPeer.getBasicRemote().sendObject(new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CONNECTED.toString()).build());
        } else if (pBean.getType().equals(EPayloadType.POSE_RESONATOR.toString())) {
            System.out.println("un cas de pose");
            System.out.println("la pose du resonator : " + pBean.getPoseResonator());
            CResonatorEntity lResonator = mCrudMethods.find(CResonatorEntity.class, pBean.getPoseResonator().getResonatorId());
            CPortalEntity lPortal = mCrudMethods.find(CPortalEntity.class, pBean.getPoseResonator().getPortalId());
            CTeamEntity lTeam = lPortal.getTeam();
            lPortal = CAction.attachResonatorToPortal(lPortal, lResonator);
            //System.out.println("team du player ! " + pBean.getPoseResonator().getResonator().getOwner().getTeam());
            //System.out.println("avant -->"+pBean.getPoseResonator().getPortal().getResonators().size());
            if (CAction.isTeamChangedAfterResonatorPoseOnPortal(lPortal, lTeam)) {


                System.out.println("changement de team");
                if (lPortal.getTeam() != null) {
                    System.out.println("team portal avant attribute " + lPortal.getTeam().getId());
                } else {
                    System.out.println("avant attribute : portal null");
                }
                lPortal.attributeTeam();
                System.out.println("team portal apres attribute " + lPortal.getTeam().getId());


                CTeamPortalChanged lTeamPortalChanged = new CTeamPortalChanged.CTeamPortalChangedBuilder().portal(lPortal).player(lResonator.getOwner()).build();
                System.out.println("team du portal dans le if : " + lTeamPortalChanged.getPortal().getTeam());
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.PORTAL_CHANGING_TEAM.toString()).objectTeamPortalChanged(lTeamPortalChanged).build();
                if (mCrudMethods.openTransaction()){
                    mCrudMethods.update(lBeanToSend.getTeamPortalChanged().getPortal());
                    mCrudMethods.commitTransaction();
                }
                else{
                    System.out.println("pb de transaction");
                }

                System.out.println("après -->" + mCrudMethods.find(CPortalEntity.class, lBeanToSend.getTeamPortalChanged().getPortal().getId()).getResonators().size());
                for (Session lSession : mSessions) {
                    lSession.getBasicRemote().sendObject(lBeanToSend);
                }
            } else {
                System.out.println("pas de changement");
                CResonatorPosed lResonatorPosed = new CResonatorPosed.CResonatorPosedBuilder().portal((lPortal)).player(lResonator.getOwner()).build();
                System.out.println(" portal " + lResonatorPosed.getPortal().getTeam());
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().objectResonatorPosed(lResonatorPosed).type(EPayloadType.RESONATOR_POSED.toString()).build();
                if (mCrudMethods.openTransaction()){
                    mCrudMethods.update(lBeanToSend.getResonatorPosed().getPortal());
                    mCrudMethods.commitTransaction();
                }
                else{
                    System.out.println("pb de transaction");
                }
                pPeer.getBasicRemote().sendObject(lBeanToSend);

            }
        } else if (pBean.getType().equals(EPayloadType.POSE_VIRUS.toString())) {
            System.out.println("Pose d'un virus !!!!!!");
            CConsumableEntity lVirus = mCrudMethods.find(CConsumableEntity.class, pBean.getPoseVirus().getVirusId());
            CPortalEntity lPortal = mCrudMethods.find(CPortalEntity.class, pBean.getPoseVirus().getPortalId());
            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getPoseVirus().getPlayerId());
            System.out.println("Pose du virus par le joueur : " + lPlayer);
            System.out.println("liens du portail pre clear : " + lPortal.getLinks().size());
            for (CLinkEntity lLink : lPortal.getLinks()) {
                lLink.getPortals().remove(lPortal);
            }
            System.out.println();

            lPortal.clearLinks();
            lPlayer.removeObject(lVirus);
            lPlayer.loseEnergy(5);
            lPlayer.addXP(lVirus.getRarity()*100);
            System.out.println("liens du portail post clear : " + lPortal.getLinks().size());
            if (lVirus.getRarity() == 3) {
                for (ABuildingEntity lBuilding : lPortal.getBuildings()) {
                    lPortal.removeBuilding(lBuilding);
                }
            }
            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lPortal);
                mCrudMethods.commitTransaction();
            }
            else{
                System.out.println("pb de transaction");
            }

            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lPlayer);
                mCrudMethods.commitTransaction();
            }
            else{
                System.out.println("pb de transaction");
            }

            // todo : virus posed list id
            CVirusPosed lVirusPosed = new CVirusPosed.CVirusPosedBuilder().player(lPlayer).build();
            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().objectVirusPosed(lVirusPosed).type(EPayloadType.VIRUS_POSED.toString()).build();
            for (Session lSession : mSessions) {
                lSession.getBasicRemote().sendObject(lBeanToSend);
            }
        } else if (pBean.getType().equals(EPayloadType.CREATE_LINK.toString())) {

            System.out.println("un cas de creation de lien");
            System.out.println("id de clef : " + pBean.getCreateLink().getKeyId());
            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getCreateLink().getPlayerId());
            CKeyEntity lKey = mCrudMethods.find(CKeyEntity.class, pBean.getCreateLink().getKeyId());
            System.out.println("key null ? " + lKey == null);
            System.out.println("on me voit ?");
            CPortalEntity lPortal1 = mCrudMethods.find(CPortalEntity.class, pBean.getCreateLink().getPortalId());
            System.out.println("lPortal1 null ? " + lPortal1 == null);
            System.out.println("key -> " + lKey);
            System.out.println("key - portal : " + lKey.getPortal().getId());
            CPortalEntity lPortal2 = lKey.getPortal();
            List<CPortalEntity> lPortals = new ArrayList<CPortalEntity>();
            lPortals.add(lPortal1);
            lPortals.add(lPortal2);
            CLinkEntity lLink = new CLinkEntity.CLinkBuilder().portals(lPortals).build();
            System.out.println("lLink nb portals -> " + lLink.getPortals().size());
            List<Integer> lListIDLinktoDestoy = new ArrayList<Integer>();
            List<CLinkEntity> lLinkStorageField = new ArrayList<CLinkEntity>();
            List<CLinkEntity> lLinkListField = new ArrayList<CLinkEntity>();
            List<CFieldEntity> lListFieldToCreate = new ArrayList<CFieldEntity>();
            List<CFieldEntity> lListAllFields = new ArrayList<CFieldEntity>();
            List<CLinkEntity> lLinks = mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
            List<CFieldEntity> lFields = mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);

            if (CAlgorithm.detectColision(lLink, lLinks, lFields) && CAlgorithm.inRange(lPortal1, lKey.getPortal())) {
                System.out.println("Aucune colision detectee");// \n!!!!!!!!!!!!!!!" + lLink+"!!!!!!!!!!!!!!");
                if (mCrudMethods.openTransaction()){
                    mCrudMethods.create(lLink);
                    mCrudMethods.commitTransaction();
                }
                else{
                    System.out.println("pb de transaction");
                }

                lPlayer.removeObject(lKey);
                lPlayer.addXP(300);
                lPlayer.loseEnergy(5);
                if (mCrudMethods.openTransaction()){
                    mCrudMethods.update(lPlayer);
                    mCrudMethods.commitTransaction();
                }
                else{
                    System.out.println("pb de transaction");
                }

                System.out.println(" ! ! ! ! ! ! ! ! ! ! Link Cree ! ! ! ! !! ! ! ! !  :" + lLink.getId());
                System.out.println("Pre-Detection Field");

                lLinkListField = CAlgorithm.detecteNewFields(lLink);
                //System.out.println("ListeLinkField = "+lLinkListField);

                //
                if (lLinkListField.size() > 0) {
                    System.out.println(lLinkListField.size() / 3 + " field à créer");

                    lListFieldToCreate = CAlgorithm.convertLinkListToFieldList(lLinkListField);
                    lPlayer.addXP(500);
                    System.out.println("Aprés trie: " + lListFieldToCreate);
                    System.out.println("Pre-CreationField");

                    for (CFieldEntity lField : lListFieldToCreate) {
                        System.out.println("Field créer: ID = " + lField.getId() + "  et Lien:" + lField.getLinks());
                        if (mCrudMethods.openTransaction()){
                            mCrudMethods.create(lField);
                            mCrudMethods.commitTransaction();
                        }
                        else{
                            System.out.println("pb de transaction");
                        }

                        //mCrudMethods.persist(lField);
                        for (CLinkEntity lLinkInField : lField.getLinks()) {
                            //mCrudMethods.update(lLinkInField);
                        }

                        // Ligne pour suprimer un id
                        lListIDLinktoDestoy = CAlgorithm.detectInternalLink(lField, lLinks);
                        System.out.println("Lien à détruire :" + lListIDLinktoDestoy);
                        for (Integer lID : lListIDLinktoDestoy) {
                            System.out.println("Lien " + lID + " détruit");
                            mCrudMethods.delete(CLinkEntity.class, lID);
                        }
                        lLink.setField(lField);
                        CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.FIELD_CREATED.toString()).objectFieldCreated(new CFieldCreated.CFieldCreatedBuilder().link(lLink).player(lPlayer).build()).build();
                        for (Session lSession : mSessions) {
                            lSession.getBasicRemote().sendObject(lBeanToSend);
                        }
                    }
                } else {
                    System.out.println("Pas de field à créer");
                    // todo : linkcreated to change
                    CLinkCreated lLinkCreated = new CLinkCreated.CLinkCreatedBuilder().link(lLink).player(lPlayer).build();
                    CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.LINK_CREATED.toString()).objectLinkCreated(lLinkCreated).build();
                    System.out.println(lBeanToSend);
                    for (Session lSession : mSessions) {
                        lSession.getBasicRemote().sendObject(lBeanToSend);
                    }
                }
            }
        }

        else if (pBean.getType().equals(EPayloadType.ATTACK_BUILDING.toString())) {
            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getAttackBuilding().getPlayerId());
            ABuildingEntity lBuilding = mCrudMethods.find(ABuildingEntity.class, pBean.getAttackBuilding().getBuildingId());
            CConsumableEntity lConsumable = mCrudMethods.find(CConsumableEntity.class, pBean.getAttackBuilding().getConsumableId());
            CPortalEntity lPortal = lBuilding.getPortal();
            System.out.println("Attaque de la structure : " + lBuilding.getId() + " par le joueur : " + lPlayer.getNickName() + " " + lPlayer.getId());
            int lBuildStartEnergy = lBuilding.getEnergy();
            lBuilding = CAction.applyAttack(lBuilding, lConsumable, lPlayer);
            lPlayer.removeObject(lConsumable);
            lPlayer.addXP((lBuildStartEnergy - lBuilding.getEnergy()) * 10);
            lPlayer.loseEnergy(5);
            //lPlayer.attack(lBuilding, lConsumable);
            if (CAction.isPortalTeamOfBuildingChanged(lBuilding)) {
                lPortal.attributeTeam();
            }
            if (CAction.isDeadBuilding(lBuilding)) {
                if (lBuilding instanceof CResonatorEntity) {
                    lPortal.removeResonator((CResonatorEntity) lBuilding);
                    ((CResonatorEntity) lBuilding).getOwner().removeObject(lBuilding);
                    lPortal.attributeTeam();
                    if (mCrudMethods.openTransaction()){
                        mCrudMethods.delete(ABuildingEntity.class, lBuilding.getId());
                        mCrudMethods.commitTransaction();
                    }
                    if (mCrudMethods.openTransaction()){
                        mCrudMethods.update(((CResonatorEntity) lBuilding).getOwner());
                        mCrudMethods.commitTransaction();
                    }
                } else {
                    lBuilding.getPortal().removeBuilding(lBuilding);
                    if (mCrudMethods.openTransaction()){
                        mCrudMethods.delete(ABuildingEntity.class, lBuilding.getId());
                        mCrudMethods.commitTransaction();
                    }
                }
            }
            CBuildingAttacked lBuildingAttacked = new CBuildingAttacked.CBuildingAttackedBuilder().portal(lPortal).player(lPlayer).build();
            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.BUILDING_ATTACKED.toString()).objectBuildingAttacked(lBuildingAttacked).build();
            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lBeanToSend.getBuildingAttacked().getPortal());
                mCrudMethods.commitTransaction();
            }

            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lBeanToSend.getBuildingAttacked().getPlayer());
                mCrudMethods.commitTransaction();
            }

            System.out.println(lBeanToSend);
            for (Session lSession : mSessions) {
                lSession.getBasicRemote().sendObject(lBeanToSend);
            }
        }

        //if (pBean.get)


        else if (pBean.getType().equals(EPayloadType.HACK_PORTAL.toString())) {

            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getHackPortal().getPlayerId());
            CPortalEntity lPortal = mCrudMethods.find(CPortalEntity.class, pBean.getHackPortal().getPortalId());
            System.out.println("Hack du portail : " + lPortal.getId() + " par le joueur : " + lPlayer.getNickName() + " " + lPlayer.getId());
            AObjectEntity lObjetCreated = CAlgorithm.createObject(CAlgorithm.calculTypeObject(), CAlgorithm.calculLevel(lPortal.getLevel(), lPlayer.getLevel()), CAlgorithm.calculRarety(lPortal.getLevel()));
            System.out.println("objet cree : " + lObjetCreated.getName());
            Date lCurrentDate = new Date();

            List<Long> lTimes = new ArrayList<Long>();
            List<CStatPortalHacked> lList = (List<CStatPortalHacked>)mCrudMethods.findWithNamedQuery(CStatPortalHacked.GET_BY_PLAYER_ID, CQueryParameter.with("mPlayerId", lPlayer.getId()).parameters());
            lPlayer.loseEnergy(2);

            for (CStatPortalHacked lStatHack : lList){
                long lDiff = lCurrentDate.getTime() - lStatHack.getDate().getTime();
                long lDiffDays = lDiff / (24 * 60 * 60 - 1000);
                long lDiffHours = lDiff / (60 * 60 * 1000) % 24;
                long lDiffMinutes = lDiff / (60 * 1000) % 60;
                long lDiffSeconds = lDiff / 1000 % 60;
                /*if (lDiffDays < 1 && lDiffHours < 1 && lDiffMinutes < 1 && lDiffSeconds < 45){
                    lCount++;
                }*/
                if ((lDiff / 1000) < 60){
                    lTimes.add(lDiff / 1000);
                }
            }
            // MultiPiratage Calcule
            List<CMultiHackEntity> lMultiHackList = lPortal.getMultiHack();
            int lBonusHack = 0;
            for (CMultiHackEntity lMHack : lMultiHackList){
                lBonusHack = lBonusHack + lMHack.getHackBonus();
            }
           // Fin calcule MultiHack
            if (lTimes.size() < 5  || ((lTimes.size() < 5 + lBonusHack) && lPlayer.havingSkill(33)) ){
                System.out.println("ok hack faisable");
                lPlayer.addObjects(lObjetCreated);
                lPlayer = CAction.hacking(lPlayer, lPortal);

                if (mCrudMethods.openTransaction()){
                    lPlayer = mCrudMethods.update(lPlayer);
                    mCrudMethods.commitTransaction();
                }
                CStatPortalHacked lNewPortalHacked = new CStatPortalHacked.CStatPortalHackedBuilder().portalId(lPortal.getId()).player(lPlayer.getId()).date(lCurrentDate).build();
                if (mCrudMethods.openTransaction()){
                    mCrudMethods.create(lNewPortalHacked);
                    mCrudMethods.commitTransaction();
                }

                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.PORTAL_HACKED.toString()).objectPortalHacked(new CPortalHacked(lPlayer)).build();
                System.out.println(lBeanToSend);
                pPeer.getBasicRemote().sendObject(lBeanToSend);
            }
            else{
                System.out.println("trop de hacks recents");
                Collections.sort(lTimes);
                double lTimeLeft = 60 - lTimes.get(lTimes.size() - 5);
                System.out.println("prochain hack dispo dans : " + Double.toString(lTimeLeft));
                CHackLimitation lHackLimitation = new CHackLimitation(Double.toString(lTimeLeft));
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.HACK_LIMITATION.toString()).objectHackLimitation(lHackLimitation).build();
                pPeer.getBasicRemote().sendObject(lBeanToSend);
            }

            /*
            System.out.println("--- APRES UPDATE ---");
            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 5).getName());
            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 5).getId() + "\n");

            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 4).getName());
            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 4).getId() + "\n");

            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 3).getName());
            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 3).getId() + "\n");

            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 2).getName());
            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 2).getId() + "\n");


            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 1).getName());
            System.out.println(lPlayer.getObjects().get(lPlayer.getObjects().size() - 1).getId() + "\n");

*/

        }

        else if (pBean.getType().equals(EPayloadType.HACK_PORTAL_KEY.toString())) {
            CPortalEntity lPortal = mCrudMethods.find(CPortalEntity.class, pBean.getHackPortalKey().getPortalId());
            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getHackPortalKey().getPlayerId());
            System.out.println("Hack de clef du portail : " + lPortal.getId() + " par le joueur : " + lPlayer.getNickName() + " " + lPlayer.getId());
            AObjectEntity lKey = new CKeyEntity.CKeyBuilder().portal(lPortal).build();
            System.out.println("team du player: " + lPlayer.getTeam().getId());
            System.out.println("team du portal: " + ((CKeyEntity)lKey).getPortal().getId());

            lPlayer.addObjects(lKey);
            lPlayer.addXP(100);
            lPlayer.loseEnergy(2);

            // todo : update
            if (mCrudMethods.openTransaction()){
                lPlayer = mCrudMethods.update(lPlayer);
                mCrudMethods.commitTransaction();
            }
            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.PORTAL_KEY_HACKED.toString()).objectPortalKeyHacked(new CPortalKeyHacked(lPlayer)).build();
            System.out.println(lBeanToSend);
            for (Session lSession : mSessions) {
                lSession.getBasicRemote().sendObject(lBeanToSend);
            }

        }

        else if (pBean.getType().equals(EPayloadType.POSE_BUILDING.toString())) {

            CPortalEntity lPortal = mCrudMethods.find(CPortalEntity.class, pBean.getPoseBulding().getPortalId());
            ABuildingEntity lBuilding = mCrudMethods.find(ABuildingEntity.class, pBean.getPoseBulding().getBuildingId());
            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getPoseBulding().getPlayerId());

            // TODO Metre double sécurité pour les vérification Team, Level et place libre.
            if (lPortal.getTeam()!=null && lPortal.getBuildings().size()-lPortal.getResonators().size()<5 && lBuilding.getLevel()<=lPlayer.getLevel()) {
            lPlayer.removeObject(lBuilding);
            lPortal.addBuilding(lBuilding);
            lPlayer.loseEnergy(5);
            lPlayer.addXP(lBuilding.getLevel() * 100);

            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lPlayer);
                mCrudMethods.commitTransaction();
            }
            else{
                System.out.println("pb de transaction");
            }

            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lPortal);
                mCrudMethods.commitTransaction();
            }
            else{
                System.out.println("pb de transaction");
            }


                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.BUILDING_POSED.toString()).objectBuildingPosed(new CBuildingPosed.CBuildingPosedBuilder().player(lPlayer).portal(lPortal).build()).build();

                System.out.println(lBeanToSend);
                for (Session lSession : mSessions) {
                    lSession.getBasicRemote().sendObject(lBeanToSend);
                }
            }

        } else if (pBean.getType().equals(EPayloadType.ATTACK_AOE.toString())) {
            CPortalEntity lPortal = mCrudMethods.find(CPortalEntity.class, pBean.getAttackAOE().getPortalId());
            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getAttackAOE().getPlayerId());
            CConsumableEntity lAmmuniton = mCrudMethods.find(CConsumableEntity.class, pBean.getAttackAOE().getConsumableId());
            System.out.println("id port: " + pBean.getAttackAOE().getPortalId());
            int OriginalEnergy = 0;
            if (lPortal.getBuildings() != null){
                System.out.println("taille portails : " + lPortal.getBuildings().size());
            }
            else{
                System.out.println("portal getbuildings null");
            }

            lPlayer.loseEnergy(10);

            for (ABuildingEntity lBuilding : lPortal.getBuildings()) {
                OriginalEnergy = lBuilding.getEnergy();
                lBuilding.takeDamage(null, (lAmmuniton.getRarity() * 20) + lPlayer.getLevel() * 2);
                lPlayer.addXP((OriginalEnergy - lBuilding.getEnergy()) / 10);
            }
            lPlayer.removeObject(lAmmuniton);

            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lPlayer);
                mCrudMethods.commitTransaction();
            }
            else{
                System.out.println("pb de transaction");
            }


            if (mCrudMethods.openTransaction()){
                mCrudMethods.update(lPortal);
                mCrudMethods.commitTransaction();
            }
            else{
                System.out.println("pb de transaction");
            }


            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.AOE_ATTACKED.toString()).objectAOEAttacked(new CAOEAttacked.CAOEAttackedBuilder().player(lPlayer).portal(lPortal).build()).build();

            System.out.println(lBeanToSend);
            for (Session lSession : mSessions) {
                lSession.getBasicRemote().sendObject(lBeanToSend);
            }

        }

        else if (pBean.getType().equals(EPayloadType.REPAIR_BUILDING.toString())) {
            CPlayerEntity lPlayer = mCrudMethods.find(CPlayerEntity.class, pBean.getRepairBuilding().getPlayerId());
            ABuildingEntity lBuilding = mCrudMethods.find(ABuildingEntity.class, pBean.getRepairBuilding().getBuildingId());
            CConsumableEntity lConsomable = mCrudMethods.find(CConsumableEntity.class, pBean.getRepairBuilding().getConsomableId());

            lPlayer.loseEnergy(5);
            if (lPlayer.havingSkill(122) && lConsomable.getName().equals("RepaireKit")) {
                int lBuildingEnergy = lBuilding.getEnergy();
                lBuilding.gainEnergy(lConsomable.getRarity() * 20 + lPlayer.getLevel() * 2);

                if(lBuilding.getEnergy() > lBuildingEnergy){
                    lPlayer.addXP((lBuilding.getEnergy()-lBuildingEnergy)*20);
                }

                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.BUILDING_REPAIRED.toString()).objectBuildingRepaired(new CBuildingRepaired.CBuildingRepairedBuidler().player(lPlayer).building(lBuilding).build()).build();

                System.out.println(lBeanToSend);
                for (Session lSession : mSessions) {
                    lSession.getBasicRemote().sendObject(lBeanToSend);
                }

            }
        }

    }




    @OnClose
    public void onClose(final Session session, EndpointConfig endpointConfig) {
        System.out.println(session.getId() + " leaved.");
        mSessions.remove(session);
    }

    public static void main(String[] args) {
        System.out.println("Server starting...");
        org.glassfish.tyrus.server.Server server =
                new org.glassfish.tyrus.server.Server(SERVER_IP, SERVER_PORT, "/", null, CServer.class);

        try {

               server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please press a key to stop the server.");
            while(true){
                reader.readLine();
            }
            //reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("salut2");
            server.stop();
        }
    }
}