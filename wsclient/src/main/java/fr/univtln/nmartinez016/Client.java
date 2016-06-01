package fr.univtln.nmartinez016;

import fr.univtln.groupc.*;

import fr.univtln.groupc.entities.*;
import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by marti on 29/05/2016.
 */


@ClientEndpoint(encoders = {CPayloadBean.PayloadBeanCode.class},
        decoders = {CPayloadBean.PayloadBeanCode.class})
public class Client {
    public final static String SERVER_IP;
    public final static int SERVER_PORT;


    static {
        String ip = null;
        int port = 8025;
        if (ip == null) ip = "localhost";
        SERVER_IP = ip;
        SERVER_PORT = port;
        System.out.println("Server IP:" + SERVER_IP + " Port: " + SERVER_PORT);
    }

    private Session session;

    @OnOpen
    public void onOpen(final Session session, EndpointConfig endpointConfig) throws IOException, EncodeException {
        this.session = session;
        System.out.println("I am " + session.getId());
        System.out.println("Sending Hello message to server");
        //session.getBasicRemote().sendObject(new PayloadBean(new Date(),sender,"Hello"));
    }

    @OnMessage
    public void OnMessage(CPayloadBean pBean) {
        System.out.println("RECU !");
        /*System.out.println(bean.getDate() + " (" //+ bean.getSender()
                + ") " + bean.getMessage());*/
        if (pBean.getType().equals(EPayloadType.CONNECTED.toString())) {
            System.out.println("if 1");
            System.out.println(pBean.getType());
        }
        else if (pBean.getType().equals(EPayloadType.PORTAL_CHANGING_TEAM.toString())){
            System.out.println("portail recupéré avec nouvelle team : " + pBean.getTeamPortalChanged().getPortal().getTeam());
        }

        else if (pBean.getType().equals(EPayloadType.RESONATOR_POSED.toString())){
            System.out.println("portail recupéré sans changement de team : " + pBean.getResonatorPosed().getPortal().getTeam());
            //pBean.getResonatorPosed().getPortal()
        }

        else if (pBean.getType().equals(EPayloadType.LINK_CREATED.toString())){
            System.out.println("link cree ! " + pBean.getLinkCreated());
        }

        else if (pBean.getType().equals(EPayloadType.FIELD_CREATED.toString())){
            System.out.println("field cree ! " + pBean.getFieldCreated());
        }



    }

    @OnClose
    public void OnClose(final Session session, EndpointConfig endpointConfig) {
        System.out.println("Session closed");
    }

    public void closeSession() throws IOException {
        if (session.isOpen())
            session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "OK"));
    }

    public void sendMessage(CPayloadBean pBean) {
        /*        System.out.println("The PayloadBean toString(): "+bean);

        //To print the JSON encoded version
        try {
            StringWriter sw = new StringWriter();
            new PayloadBean.PayloadBeanCode().encode(bean, sw);
            System.out.println("The JSON from the Payload: "+sw.toString());
        } catch (EncodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
          */
        System.out.println("tentative d envoie de : \n" + pBean.getType());
        try {
            session.getBasicRemote().sendObject(pBean);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client beanClient = new Client();
        try {
            final ClientManager client = ClientManager.createClient();
            client.connectToServer(
                    beanClient,
                    URI.create("ws://" + SERVER_IP + ":" + SERVER_PORT + "/echo")
            );

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Send empty line to stop the client.");
            String line;
            CTeamEntity lTeam = new CTeamEntity.CTeamBuilder(1).color("bleu").build();

            do {
                line = reader.readLine();
                String lSplitLine[] = line.split(" ");
                System.out.println("line = " + line);
                System.out.println("splitline[0] : " + lSplitLine[0]);
                if (!"".equals(lSplitLine[0])){
                    if (lSplitLine[0].equals("connection")) {
                        System.out.println("dans ce if la.... (connection)");
                        beanClient.sendMessage(new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CONNECTION.toString())
                                .objectConnection(new CConnection(Integer.parseInt(lSplitLine[1])))
                                .build());
                    }
                    else if (lSplitLine[0].equals("pose")){
                        if (lSplitLine[1].equals("1")){
                            System.out.println("dans ce if la.... (pose reso objectif changment team)");
                            //CTeamEntity lTeam = new CTeamEntity.CTeamBuilder(1).color("bleu").build();
                            CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(1).nickname("raul").team(lTeam).xp(8000).build();
                            CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(1).build();
                            System.out.println("team du portal de base : " + lPortal.getTeam());
                            CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(2).name("reso1").level(5).owner(lPlayer).build();
                            CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().playerId(1).portal(lPortal).resonator(lResonator).build();
                            CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder()
                                    .type(EPayloadType.POSE_RESONATOR.toString())
                                    .objectPoseResonator(lPose).build();
                            beanClient.sendMessage(lBean);
                        }
                        else{
                            System.out.println("dans ce if la.... (pose reso object pas de changement)");
                            //CTeamEntity lTeam = new CTeamEntity.CTeamBuilder(1).color("bleu").build();
                            CPlayerEntity lPlayer = new CPlayerEntity.CPlayerBuilder(1).nickname("raul").team(lTeam).xp(8000).build();
                            CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(1).team(lTeam).build();
                            System.out.println("team du portal de base : " + lPortal.getTeam());
                            CResonatorEntity lResonator = new CResonatorEntity.CResonatorBuilder(2).name("reso1").level(5).owner(lPlayer).build();
                            CPoseResonator lPose = new CPoseResonator.CPoseResonatorBuilder().playerId(1).portal(lPortal).resonator(lResonator).build();
                            CPayloadBean lBean = new CPayloadBean.CPayloadBeanBuilder()
                                    .type(EPayloadType.POSE_RESONATOR.toString())
                                    .objectPoseResonator(lPose).build();
                            beanClient.sendMessage(lBean);
                        }
                    }
                    else if (lSplitLine[0].equals("link")){
                        System.out.println("dans ce if la.... (creation lien)");
                        CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(1).longitude(15000).latitude(16000).team(lTeam).build();
                        CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(2).longitude(5000).latitude(6000).team(lTeam).build();
                        CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(3).longitude(7000).latitude(12000).team(lTeam).build();




                        if (lSplitLine[1].equals("1")){
                            System.out.println("creation link 1");
                            List<CPortalEntity> lPortals1 = new ArrayList<CPortalEntity>();
                            lPortals1.add(lPortal1);
                            lPortals1.add(lPortal2);
                            CLinkEntity lLink1 = new CLinkEntity.CLinkBuilder(30).portals(lPortals1).build();
                            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CREATE_LINK.toString()).objectCreateLink(new CCreateLink(lLink1)).build();
                            System.out.println("link -> " + lBeanToSend.getCreateLink().getLink());
                            beanClient.sendMessage(lBeanToSend);
                        }
                        else if (lSplitLine[1].equals("2")){
                            System.out.println("creation link 2");
                            List<CPortalEntity> lPortals2 = new ArrayList<CPortalEntity>();
                            lPortals2.add(lPortal1);
                            lPortals2.add(lPortal3);
                            CLinkEntity lLink2 = new CLinkEntity.CLinkBuilder(31).portals(lPortals2).build();
                            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CREATE_LINK.toString()).objectCreateLink(new CCreateLink(lLink2)).build();
                            beanClient.sendMessage(lBeanToSend);
                        }

                        else if(lSplitLine[1].equals("3")){
                            System.out.println("creation link 3");
                            List<CPortalEntity> lPortals3 = new ArrayList<CPortalEntity>();
                            lPortals3.add(lPortal2);
                            lPortals3.add(lPortal3);
                            CLinkEntity lLink3 = new CLinkEntity.CLinkBuilder(32).portals(lPortals3).build();
                            System.out.println("les liens du portail 1 du link 3 : \n" + lLink3.getPortals().get(0).getLinks().size());
                            System.out.println("objectif creation champ");
                            CPayloadBean lBeanToSend = new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CREATE_LINK.toString()).objectCreateLink(new CCreateLink(lLink3)).build();
                            beanClient.sendMessage(lBeanToSend);
                        }


                    }
                }

            } while (!"".equals(line));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                beanClient.closeSession();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
