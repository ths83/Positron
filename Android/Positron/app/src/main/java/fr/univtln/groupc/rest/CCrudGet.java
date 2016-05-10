package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.CPortalEntity;

/**
 * Created by toms on 5/3/16.
 */
public class CCrudGet extends AsyncTask<String,String,String> {

    public final static String apiURL = "http://10.9.185.57:9998";

    /*
         * Permet de faire les GET de rest.
         * On se connecte à l'URL et on recupere le json.
         */

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        String resultToDisplay = "";
        InputStream in = null;
        String json = "";

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            in.close();
            json = sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return json;
    }

    public List<CPortalEntity> getPortalsRest(){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = apiURL + "/portals";
        Log.d("test", "->-> " + lUrlString);
        String lPortalsJson = null;
        List<CPortalEntity> lPortals = null;
        try {
            Log.d("test","50");
            lPortalsJson = new CCrudGet().execute(lUrlString).get();
            Log.d("test", "62");
            Log.d("test", " -> " + lPortalsJson);
            lPortals = lMapper.readValue(lPortalsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
            Log.d("test", "_>>" + lPortals.get(0).getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("hello" + lPortals);
        return lPortals;
    }

    public CPortalEntity getPortalByIdRest(){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = apiURL + "/portals/1";
        Log.d("test", "->-> " + lUrlString);
        String lPortalJson = null;
        CPortalEntity lPortal = null;
        try {
            Log.d("test", "salut ?");
            lPortalJson = new CCrudGet().execute(lUrlString).get();
            Log.d("test", lPortalJson);
            System.out.println(" -> la dedans ?");
            lPortal = lMapper.readValue(lPortalJson, CPortalEntity.class);
            System.out.println("-> !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lPortal);
        return lPortal;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
