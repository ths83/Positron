package fr.univtln.groupc.rest;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by toms on 5/3/16.
 */
public class CInputStreamOperations {
    /**
     * @param pIn : buffer with the php result
     * @param pBufSize : size of the buffer
     * @return : the string corresponding to the buffer
     */
    public static String InputStreamToString (InputStream pIn, int pBufSize) {
        final StringBuilder lOut = new StringBuilder();
        final byte[] lBuffer = new byte[pBufSize];
        try {
            for (int ctr; (ctr = pIn.read(lBuffer)) != -1;) {
                lOut.append(new String(lBuffer, 0, ctr));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot convert stream to string", e);
        }
        // On retourne la chaine contenant les donnees de l'InputStream
        return lOut.toString();
    }

    /**
     * @param pIn : buffer with the php result
     * @return : the string corresponding to the buffer
     */
    public static String InputStreamToString (InputStream pIn) {
        // On appelle la methode precedente avec une taille de buffer par defaut
        return InputStreamToString(pIn, 1024);
    }
}
