package fr.univtln.groupc;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;

import javax.swing.*;

/*
full document to display maps with the imageUrl link :

https://developers.google.com/maps/documentation/static-maps/

*/

public class App {

    public static void generateMap(final JFrame test, List<String> pBluePlayers, List<String> pRedPlayers, List<String> pBluePortals, List<String> pRedPortals){

        try {
            String imageUrl =
                    // Lien api pour pouvoir afficher la map
                    "https://maps.googleapis.com/maps/api/staticmap?" +
                            // centrage map
                            "center=43.136436,6.018022&zoom=16&" +
                            // taille fenêtre
                            "size=600x600&";

            if (pBluePlayers!=null) {
                // joueurs team bleu
                for (String player : pBluePlayers) {
                    imageUrl = imageUrl + "markers=size:tiny%7Ccolor:blue%7Clabel:P%7C" + player + "&";
                }
            }

            if (pRedPlayers!=null) {
                // joueurs team rouge
                for (String player : pRedPlayers) {
                    imageUrl = imageUrl + "markers=size:tiny%7Ccolor:red%7Clabel:P%7C" + player + "&";
                }
            }

            if (pBluePortals!=null) {
                // portails capturés team bleu
                for (String portal : pBluePortals) {
                    imageUrl = imageUrl + "markers=color:blue%7Clabel:P%7C" + portal + "&";
                }
                imageUrl = imageUrl +
                        // On relie les portails bleu
                        "path=color:0x0000ff|weight:5|" + pBluePortals.get(0) + "|" + pBluePortals.get(1) + "&" +
                        "path=color:0x0000ff|weight:5|" + pBluePortals.get(1) + "|" + pBluePortals.get(0) + "&" +
                        "path=color:0x0000ff|weight:5|" + pBluePortals.get(1) + "|" + pBluePortals.get(2) + "&" +
                        "path=color:0x0000ff|weight:5|" + pBluePortals.get(2) + "|" + pBluePortals.get(1) + "&" +
                        "path=color:0x0000ff|weight:5|" + pBluePortals.get(0) + "|" + pBluePortals.get(2) + "&" +
                        "path=color:0x0000ff|weight:5|" + pBluePortals.get(2) + "|" + pBluePortals.get(0) + "&" ;

            }

            if (pRedPortals!=null) {
                // portails capturés team rouge
                for (String portal : pRedPortals) {
                    imageUrl = imageUrl + "markers=color:red%7Clabel:P%7C" + portal + "&";
                }
                // On relie les portails rouge
                imageUrl = imageUrl +
                        "path=color:0xff0000|weight:5|"+pRedPortals.get(0)+"|"+pRedPortals.get(1)+"&"+
                        "path=color:0xff0000|weight:5|"+pRedPortals.get(1)+"|"+pRedPortals.get(0)+"&"+
                        "path=color:0xff0000|weight:5|"+pRedPortals.get(1)+"|"+pRedPortals.get(2)+"&"+
                        "path=color:0xff0000|weight:5|"+pRedPortals.get(2)+"|"+pRedPortals.get(1)+"&"+
                        "path=color:0xff0000|weight:5|"+pRedPortals.get(0)+"|"+pRedPortals.get(2)+"&";

            }



            // API Key
            String Key = "maptype=roadmap&key=AIzaSyDFJqyWFbnya88SCV5Ezsrfnq9DEIVdT5c\n";
            imageUrl = imageUrl + Key;

            // image dans laquelle on va afficher la map
            String destinationFile = "image.jpg";

            String str = destinationFile;
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            // Ecris le contenu de la map généré via l'api is dans la nouvelle image os
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // une fois l'image os généré, on l'ajoute à un JLabel afin de l'afficher dans notre IHM
        test.add(new JLabel(new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(630, 600,
                java.awt.Image.SCALE_SMOOTH))));

        test.setVisible(true);
        test.pack();

        // Demande de confirmation avant de fermer la fenêtre et tuer l'application
        test.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(test,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    test.dispose();
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        JFrame test = new JFrame("Google Maps");

        // Rest Requests
        System.out.println("salut bg");
        Client c = Client.create();
        WebResource webResource = c.resource(CServer.BASE_URI);
        String lJson = webResource.path("/portals").get(String.class);
        List<CPortalEntity> lPortals = null;

        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            lPortals = lMapper.readValue(lJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("que se passe t il?");
        List<String> bluePlayers = new ArrayList();
        bluePlayers.add("43.136466,6.016560");
        bluePlayers.add("43.137476,6.017719");

        List<String> redPlayers = new ArrayList();
        redPlayers.add("43.136434,6.020808");
        redPlayers.add("43.136434,6.020777");
        redPlayers.add("43.137290,6.016552");

        List<String> bluePortals = new ArrayList();
        List<String> redPortals = new ArrayList();
        redPortals.add("43.137136,6.018718");
        redPortals.add("43.137261,6.019610");
        redPortals.add("43.136444,6.019477");

        String redPortal;
        String bluePortal;


        for (CPortalEntity cPortalEntity : lPortals) {

            if (cPortalEntity.getTeam() != null) {

                if (Objects.equals(cPortalEntity.getTeam().getColor(), "red")) {

                    redPortal = String.valueOf(cPortalEntity.getLat()) + "," + String.valueOf(cPortalEntity.getLong());
                    redPortals.add(redPortal);
                    System.out.println("ok");
                } else if (Objects.equals(cPortalEntity.getTeam().getColor(), "red")) {
                    bluePortal = String.valueOf(cPortalEntity.getLat()) + "," + String.valueOf(cPortalEntity.getLong());
                    bluePortals.add(bluePortal);
                }
            }
        }
    }

/*
        App.generateMap(test, bluePlayers, redPlayers, bluePortals, redPortals);
*/
    }
}