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

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;


/**
 * Created by toms on 5/3/16.
 */
public class CRestGet extends AsyncTask<String,String,String> {

    //public final static String API_URL = "http://10.9.185.57:9998";
    //public final static String API_URL = "http://10.21.174.206:9998";
    //thom' home
    //public final static String API_URL = "http://192.168.1.83:9998";
    // wifi
    public final static String API_URL = "http://192.168.43.44:9998";
    //public final static String API_URL = "http://192.168.1.71:9998";

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

    /**
     * get all portals from database to display them on Android Map
     * @return
     */
    public List<CPortalEntity> getPortalsRest(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        String lUrlString = API_URL + "/portals";
        Log.d("test", "->-> " + lUrlString);
        String lPortalsJson = null;
        List<CPortalEntity> lPortals = new ArrayList<>();
        JSONObject lPortalObject = null;
        try {
            Log.d("test","get portals :");
            lPortalsJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPortalsJson);
            lPortals = lMapper.readValue(lPortalsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
            //Log.d("test", "objects numero 1 ->\n" + lPortals.get(0).getObjects().get(0));
            //Log.d("test", "objects numero 2 ->\n" + lPortals.get(0).getObjects().get(1));

            Log.d("test", "deserialized!!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());

        } catch (JsonParseException e) {
            e.printStackTrace();
            Log.d("test", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("hello" + lPortals);
        Log.d("test", lPortals.toString());
        return lPortals;
    }

    /**
     * get players
     * @return
     */
    public List<CPlayerEntity> getPlayersRest(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lUrlString = API_URL + "/players";
        Log.d("test", "->-> " + lUrlString);
        String lPlayersJson = null;
        List<CPlayerEntity> lPlayers = null;
        try {
            Log.d("test","get players");
            lPlayersJson = new CRestGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPlayersJson);
            lPlayers = lMapper.readValue(lPlayersJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPlayerEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("hello" + lPortals);
        return lPlayers;
    }


    /**
     * get portals by id from database
     * @return
     */
    public CPortalEntity getPortalByIdRest(){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = API_URL + "/portals/1";
        Log.d("test", "->-> " + lUrlString);
        String lPortalJson = null;
        CPortalEntity lPortal = null;
        try {
            Log.d("test", "salut ?");
            lPortalJson = new CRestGet().execute(lUrlString).get();
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