package fr.univtln.groupc.rest;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by arouani277 on 25/05/16.
 */

public class  CRest extends AsyncTask<String,String,String> {

    public final static String API_URL = "http://10.21.174.206:9998";

    @Override
    protected String doInBackground(String... params) {
        String lUrlString = params[0]; // URL to call
        String lResultToDisplay = "";
        InputStream lIn = null;
        String lJson = "";
        try {
            URL lUrl = new URL(lUrlString);
            HttpURLConnection urlConnection = (HttpURLConnection) lUrl.openConnection();
            lIn = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader lReader = new BufferedReader(new InputStreamReader(lIn), 8);
            StringBuilder lSb = new StringBuilder();
            String lLine = null;
            while ((lLine = lReader.readLine()) != null) {
                lSb.append(lLine + "\n");
            }
            lIn.close();
            lJson = lSb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return lJson;
    }
}
