package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CShieldEntity;

/**
 * Created by toms on 08/05/2016.
 */
public class CRestUpdate extends AsyncTask<String, String, Void> {

    //public final static String API_URL = "http://192.168.1.71:9998";
    public final static String API_URL = "http://10.9.185.52:9998";
    // thom
    //public final static String API_URL = "http://10.9.185.52:9998";

    @Override
    protected Void doInBackground(String... params) {
        String lUrlString = params[0]; // URL to call
        String lJsonString = params[1];
        BufferedOutputStream out = null;
        try {
            URL url = new URL(lUrlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setChunkedStreamingMode(0);
            OutputStream output = new BufferedOutputStream(urlConnection.getOutputStream());
            output.write(lJsonString.getBytes());
            output.flush();
            System.out.println(urlConnection.getInputStream());
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    protected void onPostExecute (Void v){
        super.onPostExecute(v);
    }


    /**
     * envoi au serveur la mise a jour effectuee sur un portal (objets, niveau, equipe ...)
     * -----
     * sends to REST server the portal update (building, team, level ...)
     * @param pPortal
     */
    public void updatePortalRest(CPortalEntity pPortal){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = API_URL + "/portals";
        Log.d("test", "->-> " + lUrlString);
        try {
            String lPortalJson = lMapper.writeValueAsString(pPortal);
            CRestUpdate lUpdate=new CRestUpdate();
            lUpdate.execute(lUrlString,lPortalJson).get();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        }


    /**
     * envoi au serveur la mise a jour effectuee sur un lien
     * -----
     * sends to REST server the link update
     * @param pLink
     */
    public void updateLinkRest(CLinkEntity pLink){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = API_URL + "/links";
        Log.d("test", "->-> " + lUrlString);
        try {
            String lLinkJson = lMapper.writeValueAsString(pLink);
            CRestUpdate lUpdate=new CRestUpdate();
            lUpdate.execute(lUrlString,lLinkJson).get();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * envoi au serveur les modifications apportees a une structure
     * -----
     * sends to REST server the structure update
     * @param pBuilding
     */
    // TODO test this method -> Xavier
    public void updateBuildingRest(ABuildingEntity pBuilding){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = API_URL;

        // Si la structure est un resonateur
        // If its a resonator
        if (pBuilding instanceof CResonatorEntity){
            lUrlString += "/resonators";
        }

        // Si la structure est un bouclier
        // If its a shield
        else if (pBuilding instanceof CShieldEntity){
            lUrlString += "/shields";
        }

        // Si la structure est une tourelle
        // If its a turret
        else {
            lUrlString += "/turrets";
        }
        Log.d("test", "->-> " + lUrlString);
        try {
            String lBuildingJson = lMapper.writeValueAsString(pBuilding);
            CRestUpdate lUpdate=new CRestUpdate();
            lUpdate.execute(lUrlString,lBuildingJson).get();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

