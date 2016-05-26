package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;


/**
 * Created by toms on 08/05/2016.
 */

public class CRestDelete extends AsyncTask<String, String, Void> {

    //public final static String API_URL = "http://10.9.185.57:9998";
    //public final static String API_URL = "http://10.9.185.52:9998";
    //public final static String API_URL = "http://10.9.185.55:9998";
    public final static String API_URL = "http://10.9.185.52:9998";
    //public final static String API_URL = "http://10.21.174.206:9998";
    //thom' home
    //public final static String API_URL = "http://192.168.1.83:9998";
    // wifi
    //public final static String API_URL = "http://192.168.43.44:9998";
    //public final static String API_URL = "http://192.168.1.71:9998";
   //public final static String API_URL = "http://127.0.0.1:9998";

    @Override
    protected Void doInBackground(String... params) {
        String lUrlString = params[0]; // URL to call
        String lResultToDisplay = "";
        InputStream lIn = null;
        String lJson = "";
        HttpDelete lHttpDelete;
        try {
            lHttpDelete = new HttpDelete(lUrlString);
            HttpClient lHttpClient = new DefaultHttpClient();
            HttpResponse lHttpResponse = lHttpClient.execute(lHttpDelete);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * Supprime le lien identifie par le parametre
     *
     * ------
     *
     * Deletes the link identified by parameter
     * @param pId
     */
    public void deleteLinkRest(int pId){
        String lUrlString = API_URL + "/links/" + Integer.toString(pId);
        Log.d("test2", "delete ->-> " + lUrlString);
        try {
            new CRestDelete().execute(lUrlString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * Supprime le champ identifie par le parametre
     *
     * ------
     *
     * Deletes the field identified by parameter
     * @param pId
     */
    public void deleteFieldRest(int pId){
        String lUrlString =  API_URL + "/fields/" + Integer.toString(pId);
        Log.d("test2", "delete ->-> " + lUrlString);
        try {
            new CRestDelete().execute(lUrlString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
