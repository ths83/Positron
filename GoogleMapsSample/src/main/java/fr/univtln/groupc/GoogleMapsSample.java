package fr.univtln.groupc;

import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.*;

/*
full document to display maps with the imageUrl link :

https://developers.google.com/maps/documentation/static-maps/

*/

public class GoogleMapsSample {
    public static void main(String[] args) throws IOException {
        JFrame test = new JFrame("Google Maps");

        try {
            String imageUrl =
                    // Lien api pour pouvoir afficher la map
                    "https://maps.googleapis.com/maps/api/staticmap?" +

                            // centrage map
                            "center=43.136436,6.018022&zoom=16&" +

                            // taille fenêtre
                            "size=600x600&" +

                            // joueurs team bleu
                            "markers=size:tiny%7Ccolor:blue%7Clabel:P%7C43.136466,6.016560&" +
                            "markers=size:tiny%7Ccolor:blue%7Clabel:P%7C43.137476,6.017719&" +

                            // joueurs team rouge
                            "markers=size:tiny%7Ccolor:red%7Clabel:P%7C43.136434,6.020808&" +

                            // portails capturés team bleu
                            "markers=color:blue%7Clabel:P%7C43.137274,6.015640&" +
                            "markers=color:blue%7Clabel:P%7C43.137290,6.016558&" +
                            "markers=color:blue%7Clabel:P%7C43.136577,6.016223&" +

                            // On relie les portails bleu
                            "path=color:0x0000ff|weight:5|43.137274,6.015640|43.137290,6.016558&"+
                            "path=color:0x0000ff|weight:5|43.137290,6.016558|43.136577,6.016223&"+
                            "path=color:0x0000ff|weight:5|43.137274,6.015640|43.136577,6.016223&"+

                            // portails capturés team rouge
                            "markers=color:red%7Clabel:P%7C43.137136,6.018718&" +
                            "markers=color:red%7Clabel:P%7C43.137261,6.019610&" +
                            "markers=color:red%7Clabel:P%7C43.136444,6.019477&" +


                            // On relie les portails rouge
                            "path=color:0xff0000|weight:5|43.137136,6.018718|43.137261,6.019610&"+
                            "path=color:0xff0000|weight:5|43.136444,6.019477|43.137136,6.018718&"+
                            "path=color:0xff0000|weight:5|43.137261,6.019610|43.136444,6.019477&"+

                            // API Key
                            "maptype=roadmap&key=AIzaSyDFJqyWFbnya88SCV5Ezsrfnq9DEIVdT5c\n";

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
        /*
        test.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(test,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        */
    }
}