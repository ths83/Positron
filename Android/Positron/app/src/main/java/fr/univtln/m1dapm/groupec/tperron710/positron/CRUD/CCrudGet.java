package fr.univtln.m1dapm.groupec.tperron710.positron.CRUD;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by toms on 5/3/16.
 */
public class CCrudGet extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... params) {
        BufferedInputStream br = null;
        HashMap<String,String> resultToDisplay = null;
        String result = null;
        URL url = null;

        // HTTP Get
        try {
            url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            result = InputStreamOperations.InputStreamToString(inputStream);
            System.out.println(result);

        } catch (Exception e ) {
            e.printStackTrace();
        }
        // je retourne l'url pour vérifier si c'est pas un pb d'url, aucun soucis de ce cote la chez moi
        return ( "Valeur de retour du GET : \n" + url);

        // pour voir si il y a un résultat
        // il retourne null pour l'instant, je compte bien le vaincre ;)
        // return ( "Valeur de retour du GET : \n" + result);

    }
}
