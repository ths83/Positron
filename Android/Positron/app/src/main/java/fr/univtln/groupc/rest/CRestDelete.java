package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

/**
 * Created by toms on 08/05/2016.
 */
public class CRestDelete extends AsyncTask<String, String, Void> {

    //public final static String API_URL = "http://10.9.185.57:9998";
    //public final static String API_URL = "http://10.9.185.52:9998";
    //public final static String API_URL = "http://10.9.185.55:9998";
    //public final static String API_URL = "http://10.9.185.52:9998";
    //public final static String API_URL = "http://10.21.174.206:9998";
    //thom' home
    //public final static String API_URL = "http://192.168.1.83:9998";
    // wifi
    //public final static String API_URL = "http://192.168.43.44:9998";
    //public final static String API_URL = "http://192.168.1.71:9998";
    public final static String API_URL = "http://127.0.0.1:9998";
    @Override
    protected Void doInBackground(String... params) {
        String lUrlString = params[0]; // URL to call
        String lResultToDisplay = "";
        InputStream lIn = null;
        String lJson = "";
        try {
            URL lUrl = new URL(lUrlString);
            HttpURLConnection urlConnection = (HttpURLConnection) lUrl.openConnection();
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * get all portals from database to display them on Android Map
     * @return
     */
    public void deleteLinkRest(int pId){
        String lUrlString = API_URL + "/links/" + Integer.toString(pId);
        Log.d("test", "delete ->-> " + lUrlString);
        try {
            new CRestDelete().execute(lUrlString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
