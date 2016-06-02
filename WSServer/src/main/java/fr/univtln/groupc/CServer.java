package fr.univtln.groupc;


import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        }

        else if (pBean.getType().equals(EPayloadType.POSE_RESONATOR.toString())) {
            System.out.println("un cas de pose");
            System.out.println("la pose du resonator : " + pBean.getPoseResonator());
            System.out.println("team du player ! " + pBean.getPoseResonator().getResonator().getOwner().getTeam());
            if (CAction.isTeamChangedAfterResonatorPoseOnPortal(pBean.getPoseResonator())) {
                System.out.println("changement de team");
                CTeamPortalChanged lTeamPortalChanged = new CTeamPortalChanged(pBean.getPoseResonator().getPortal());
                System.out.println("team du portal dans le if : " + lTeamPortalChanged.getPortal().getTeam());
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.PORTAL_CHANGING_TEAM.toString()).objectTeamPortalChanged(lTeamPortalChanged).build();
                //mCrudMethods.update(lBeanToSend.getTeamPortalChanged().getPortal());
                for (Session lSession : mSessions) {
                    lSession.getBasicRemote().sendObject(lBeanToSend);
                }
            } else {
                System.out.println("pas de changement");
                CResonatorPosed lResonatorPosed = new CResonatorPosed(pBean.getPoseResonator().getPortal());
                System.out.println(" portal " + lResonatorPosed.getPortal().getTeam());
                CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().objectResonatorPosed(lResonatorPosed).type(EPayloadType.RESONATOR_POSED.toString()).build();
                mCrudMethods.update(lBeanToSend.getResonatorPosed().getPortal());

                pPeer.getBasicRemote().sendObject(lBeanToSend);

            }
        }

        else if (pBean.getType().equals(EPayloadType.POSE_VIRUS.toString())){
            System.out.println("Pose d'un virus !!!!!!");
            System.out.println("Pose du virus par le joueur : " + pBean.getPoseVirus().getPlayer().toString());
            System.out.println();
            CPortalEntity lPortal = pBean.getPoseVirus().getPortal();
            lPortal.clearLinks();
            if (pBean.getPoseVirus().getVirus().getRarity() == 3 ){
                for (ABuildingEntity lBuilding : lPortal.getBuildings()){
                    lPortal.removeBuilding(lBuilding);
                }
            }
            CVirusPosed lVirusPosed = new CVirusPosed(lPortal);
            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().objectVirusPosed(lVirusPosed).type(EPayloadType.VIRUS_POSED.toString()).build();
            mCrudMethods.update(lBeanToSend.getPosedVirus().getPortal());
        }

        else if (pBean.getType().equals(EPayloadType.CREATE_LINK.toString())) {
            System.out.println("un cas de creation de lien");
            CLinkEntity lLink = pBean.getCreateLink().getLink();
            System.out.println("lLink nb portals -> " + lLink.getPortals().size());
            List<Integer> lListIDLinktoDestoy = new ArrayList<Integer>();
            List<CLinkEntity> lLinkStorageField = new ArrayList<CLinkEntity>();
            List<CLinkEntity> lLinkListField = new ArrayList<CLinkEntity>();
            List<CFieldEntity> lListFieldToCreate = new ArrayList<CFieldEntity>();
            List<CFieldEntity> lListAllFields = new ArrayList<CFieldEntity>();
            List<CLinkEntity> lLinks = mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
            List<CFieldEntity> lFields = mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);
            if (CAlgorithm.detectColision(lLink, lLinks, lFields)) {
                System.out.println("Aucune colision detectee");// \n!!!!!!!!!!!!!!!" + lLink+"!!!!!!!!!!!!!!");
                mCrudMethods.create(lLink);
                System.out.println(" ! ! ! ! ! ! ! ! ! ! Link Cree ! ! ! ! !! ! ! ! !  :" + lLink.getId());
                System.out.println("Pre-Detection Field");

                lLinkListField = CAlgorithm.detecteNewFields(lLink);
                //System.out.println("ListeLinkField = "+lLinkListField);

                //
                if (lLinkListField.size() > 0) {
                    System.out.println(lLinkListField.size() / 3 + " field à créer");

                    lListFieldToCreate = CAlgorithm.convertLinkListToFieldList(lLinkListField);


                    System.out.println("Aprés trie: " + lListFieldToCreate);
                    System.out.println("Pre-CreationField");

                    for (CFieldEntity lField : lListFieldToCreate) {
                        System.out.println("Field créer: ID = " + lField.getId() + "  et Lien:" + lField.getLinks());
                        mCrudMethods.create(lField);
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
                        CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.FIELD_CREATED.toString()).objectFieldCreated(new CFieldCreated(lField)).build();
                        for (Session lSession : mSessions){
                            lSession.getBasicRemote().sendObject(lBeanToSend);
                        }
                    }
                } else {
                    System.out.println("Pas de field à créer");
                    CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.LINK_CREATED.toString()).objectLinkCreated(new CLinkCreated(lLink)).build();
                    System.out.println(lBeanToSend);
                    for (Session lSession : mSessions){
                        lSession.getBasicRemote().sendObject(lBeanToSend);
                    }
                }


            }
        }

        else if (pBean.getType().equals(EPayloadType.ATTACK_BUILDING.toString())){
            System.out.println("un cas d'attaque de building");
            //if (pBean.get)
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