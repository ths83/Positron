package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;


/**
 * Created by toms on 5/3/16.
 */
public class CCrudGet extends AsyncTask<String,String,String> {

    //public final static String apiURL = "http://10.9.185.57:9998";
    public final static String apiURL = "http://10.21.174.206:9998";

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

    public List<CPortalEntity> getPortalsRestv2(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lUrlString = apiURL + "/portals";
        Log.d("test", "->-> " + lUrlString);
        String lPortalsJson = null;
        List<CPortalEntity> lPortals = new ArrayList<>();
        try {
            lPortalsJson = new CCrudGet().execute(lUrlString).get();
            JSONArray lArray = new JSONArray(lPortalsJson);
            Log.d("test", "nombre de tour : " + lArray.length());
            Log.d("test", "jsonarray : \n" + lArray);/*
            for (int i = 0; i < lArray.length(); i++){
                Log.d("test", "tour de boucle : " + i);
                Log.d("test", "-> " + lArray.getJSONObject(i));
                JSONObject lPortalObject = lArray.getJSONObject(i);
                Log.d("test", "la1 ?");
                int lPortalId = lPortalObject.optInt("id");
                Log.d("test", "la2 ?");
                double lPortalLong = lPortalObject.optDouble("long");
                double lPortalLat = lPortalObject.optDouble("lat");
                CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(lPortalId).latitude(lPortalLat).longitude(lPortalLong).build();
                Log.d("test", lPortal.toString());
                lPortals.add(lPortal);
                Log.d("test", "taille_prov : " + lPortals.size());
            }*/
            Log.d("test", "get 0");
            JSONObject lPortalObject0 = lArray.getJSONObject(0);
            Log.d("test", "get 1");
            JSONObject lPortalObject1 = lArray.getJSONObject(1);
            Log.d("test", "get 2");
            JSONObject lPortalObject2 = lArray.getJSONObject(2);
            Log.d("test", "get 3");
            JSONObject lPortalObject3 = lArray.getJSONObject(3);
            Log.d("test", "get 4");
            JSONObject lPortalObject4 = lArray.getJSONObject(4);
            Log.d("test", "get 5");
            JSONObject lPortalObject5 = lArray.getJSONObject(5);
            Log.d("test", "get 6");
            JSONObject lPortalObject6 = lArray.getJSONObject(6);

            CPortalEntity lPortal0 = new CPortalEntity.CPortalBuilder(lPortalObject0.optInt("id")).latitude(lPortalObject0.optDouble("lat")).longitude(lPortalObject0.optDouble("long")).build();
            Log.d("test", "portal 0 : " + lPortal0);
            CPortalEntity lPortal1 = new CPortalEntity.CPortalBuilder(lPortalObject1.optInt("id")).latitude(lPortalObject1.optDouble("lat")).longitude(lPortalObject1.optDouble("long")).build();
            Log.d("test", "portal 1");
            CPortalEntity lPortal2 = new CPortalEntity.CPortalBuilder(lPortalObject2.optInt("id")).latitude(lPortalObject2.optDouble("lat")).longitude(lPortalObject2.optDouble("long")).build();
            Log.d("test", "portal 2");
            CPortalEntity lPortal3 = new CPortalEntity.CPortalBuilder(lPortalObject3.optInt("id")).latitude(lPortalObject3.optDouble("lat")).longitude(lPortalObject3.optDouble("long")).build();
            Log.d("test", "portal 3");
            CPortalEntity lPortal4 = new CPortalEntity.CPortalBuilder(lPortalObject4.optInt("id")).latitude(lPortalObject4.optDouble("lat")).longitude(lPortalObject4.optDouble("long")).build();
            Log.d("test", "portal 4");
            CPortalEntity lPortal5 = new CPortalEntity.CPortalBuilder(lPortalObject5.optInt("id")).latitude(lPortalObject5.optDouble("lat")).longitude(lPortalObject5.optDouble("long")).build();
            Log.d("test", "portal 5");
            CPortalEntity lPortal6 = new CPortalEntity.CPortalBuilder(lPortalObject6.optInt("id")).latitude(lPortalObject6.optDouble("lat")).longitude(lPortalObject6.optDouble("long")).build();
            Log.d("test", "portal 6");
            lPortals.add(lPortal0);
            lPortals.add(lPortal1);
            lPortals.add(lPortal2);
            lPortals.add(lPortal3);
            lPortals.add(lPortal4);
            lPortals.add(lPortal5);
            lPortals.add(lPortal6);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("test", " ! salut tu vas bien !");
        return lPortals;
    }

    public List<CPortalEntity> getPortalsRest(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        String lUrlString = apiURL + "/portals";
        Log.d("test", "->-> " + lUrlString);
        String lPortalsJson = null;
        List<CPortalEntity> lPortals = new ArrayList<>();
        JSONObject lPortalObject = null;
        try {
            Log.d("test","get portals :");
            lPortalsJson = new CCrudGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPortalsJson);
            lPortals = lMapper.readValue(lPortalsJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPortalEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
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

    public List<CPlayerEntity> getPlayersRest(){
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lUrlString = apiURL + "/players";
        Log.d("test", "->-> " + lUrlString);
        String lPlayersJson = null;
        List<CPlayerEntity> lPlayers = null;
        try {
            Log.d("test","get players");
            lPlayersJson = new CCrudGet().execute(lUrlString).get();
            //Log.d("test", " -> " + lPlayersJson);
            lPlayers = lMapper.readValue(lPlayersJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPlayerEntity.class));
            //lPortals = Arrays.asList(lMapper.readValue(lPortalsJson, CPortalEntity[].class));
            //Log.d("test", "_>>" + lPlayers.get(0).getId());
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
