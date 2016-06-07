package fr.univtln.groupc;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;

import javax.swing.*;

import static java.lang.Thread.sleep;

/*
full document to display maps with the imageUrl link :

https://developers.google.com/maps/documentation/static-maps/

*/

public class CMap {

    static String path="";


    public static JFrame generateMap(final JFrame pFest, List<String> pBluePlayers, List<String> pRedPlayers, List<String> pBluePortals, List<String> pRedPortals, ArrayList<String> pBlackPortals) {

        try {
            String lImageUrl =
                    // Lien api pour pouvoir afficher la map
                    "https://maps.googleapis.com/maps/api/staticmap?" +
                            // centrage map
                            "center=43.136436,6.018022&zoom=16&" +
                            // taille fenêtre
                            "size=600x600&";

            if (pBluePlayers != null) {
                // joueurs team bleu
                for (String player : pBluePlayers) {
                    lImageUrl = lImageUrl + "markers=size:tiny%7Ccolor:blue%7Clabel:P%7C" + player + "&";
                }
            }

            if (pRedPlayers != null) {
                // joueurs team rouge
                for (String player : pRedPlayers) {
                    lImageUrl = lImageUrl + "markers=size:tiny%7Ccolor:red%7Clabel:P%7C" + player + "&";
                }
            }

            if (pBluePortals != null) {
                path="path" + "=color:0x000000|weight:5";

                // portails capturés team bleu
                for (String portal : pBluePortals) {
                    System.out.println("add portail bleu lien" + portal);
                    path = path + "|"+portal;
                    lImageUrl = lImageUrl + "markers=color:blue%7Clabel:P%7C" + portal + "&";
                }
                path = path + "&";
            }

            lImageUrl = lImageUrl + path;

            if (pRedPortals != null) {
                path="path" + "=color:0x000000|weight:5";

                // portails capturés team rouge
                for (String portal : pRedPortals) {
                    System.out.println("add portail rouge au lien" + portal);
                    path = path + "|"+portal;
                    lImageUrl = lImageUrl + "markers=color:red%7Clabel:P%7C" + portal + "&";
                }
                path = path + "&";

            }

            lImageUrl = lImageUrl + path;

            if (pBlackPortals != null) {
                // portails capturés team rouge
                for (String portal : pBlackPortals) {
                    System.out.println("add portail rouge au lien" + portal);
                    lImageUrl = lImageUrl + "markers=color:black%7Clabel:P%7C" + portal + "&";
                }
            }

            // API lKey
            String lKey = "maptype=roadmap&key=AIzaSyDFJqyWFbnya88SCV5Ezsrfnq9DEIVdT5c\n";
            lImageUrl = lImageUrl + lKey;

            System.out.println(lImageUrl);
            // image dans laquelle on va afficher la map
            String lDestinationFile = "image.jpg";

            String lStr = lDestinationFile;
            URL lUrl = new URL(lImageUrl);
            InputStream lIs = lUrl.openStream();
            OutputStream lOs = new FileOutputStream(lDestinationFile);

            byte[] lB = new byte[2048];
            int lLength;

            // Ecris le contenu de la map généré via l'api lIs dans la nouvelle image lOs
            while ((lLength = lIs.read(lB)) != -1) {
                lOs.write(lB, 0, lLength);
            }

            lIs.close();
            lOs.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // une fois l'image os généré, on l'ajoute à un JLabel afin de l'afficher dans notre IHM
        pFest.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
                java.awt.Image.SCALE_SMOOTH))));

        pFest.setVisible(true);
        pFest.pack();

        // Demande de confirmation avant de fermer la fenêtre et tuer l'application
        pFest.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(pFest,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    pFest.dispose();
                    System.exit(0);
                }
            }
        });
        return pFest;
    }

    public static JFrame generateFrame(JFrame lFrame){
        // Rest Requests
        System.out.println("main");
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);
        String lPortalsJson = webResource.path("/portals").get(String.class);
        System.out.println("portals get");
        String lPlayersJson = webResource.path("/players").get(String.class);
        System.out.println("players get");

        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

        List<CPortalEntity> lPortals = null;
        List<CPlayerEntity> lPlayers = null;
        ArrayList<String> lBluePortals = new ArrayList<String>();
        ArrayList<String> lRedPortals = new ArrayList<String>();
        ArrayList<String> lblackPortals = new ArrayList<String>();

        ArrayList<String> lBluePlayers = new ArrayList<String>();
        ArrayList<String> lRedPlayers = new ArrayList<String>();

        try {
            lPortals = lMapper.readValue(lPortalsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
            lPlayers = lMapper.readValue(lPlayersJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPlayerEntity.class));

        } catch (IOException e) {
            e.printStackTrace();
        }

        String redPortal;
        String bluePortal;
        String blackPortal;

        String redPlayer;
        String bluePlayer;

        // Récuperation des joueurs présents dans la BD via REST
        for (CPlayerEntity lPlayerEntity : lPlayers){
            if (lPlayerEntity.getTeam() != null){
                if (Objects.equals(lPlayerEntity.getTeam().getColor(),"red")){
                    redPlayer = String.valueOf(lPlayerEntity.getLat()) + "," + String.valueOf(lPlayerEntity.getLong());
                    lRedPlayers.add(redPlayer);
                    System.out.println("player rouge");
                    System.out.println(redPlayer);
                }
                else if (Objects.equals(lPlayerEntity.getTeam().getColor(), "blue")) {
                    bluePlayer = String.valueOf(lPlayerEntity.getLat()) + "," + String.valueOf(lPlayerEntity.getLong());
                    lBluePlayers.add(bluePlayer);
                    System.out.println("playerbleu");
                    System.out.println(bluePlayer);
                }
                else System.out.println("players vide");
            }
        }

        // Récuperation des portails présents dans la BD via REST
        for (CPortalEntity lPortalEntity : lPortals) {
            if (lPortalEntity.getTeam() != null) {

                if (Objects.equals(lPortalEntity.getTeam().getColor(), "red")) {
                    redPortal = String.valueOf(lPortalEntity.getLat()) + "," + String.valueOf(lPortalEntity.getLong());
                    lRedPortals.add(redPortal);
                    System.out.println("portal rouge");
                    System.out.println(redPortal);
                }

                else if (Objects.equals(lPortalEntity.getTeam().getColor(), "blue")) {
                    bluePortal = String.valueOf(lPortalEntity.getLat()) + "," + String.valueOf(lPortalEntity.getLong());
                    lBluePortals.add(bluePortal);
                    System.out.println("portal bleu");
                    System.out.println(bluePortal);
                }
                else System.out.println("portals vide");

            }
            else {
                System.out.println("portal black " + lPortalEntity.getLong() + " lat : " + lPortalEntity.getLat() + " team " + lPortalEntity.getTeam());
                blackPortal = String.valueOf(lPortalEntity.getLat()) + "," + String.valueOf(lPortalEntity.getLong());
                lblackPortals.add(blackPortal);
            }
        }

        // Une fois les données REST récupérées, on genere à partir de ça notre map
        lFrame = CMap.generateMap(lFrame, lBluePlayers, lRedPlayers, lBluePortals, lRedPortals, lblackPortals);
        return lFrame;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame jFrame = new JFrame("Google Maps");

        while (true) {
            // On regenere la frame à chaque fois avec les nouvelles données proventante du serveur REST
            jFrame = generateFrame(jFrame);
            System.out.println("new frame generated");
            sleep(10000);
        }
    }

}
