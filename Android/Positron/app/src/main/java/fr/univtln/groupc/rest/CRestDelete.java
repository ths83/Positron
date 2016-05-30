package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.entities.CShieldEntity;


/**
 * Created by toms on 08/05/2016.
 */

public class CRestDelete extends AsyncTask<String, String, Void> {

    public final static String API_URL = "http://10.9.185.52:9998";

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
     * Supprime le consomable identifie par le parametre
     *
     * ------
     *
     * Deletes the consumable identified by parameter
     * @param pId
     */
    public void deleteConsumableRest(int pId){
        String lUrlString = API_URL + "/consumables/" + Integer.toString(pId);
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
     * ------
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

    /**
     * Supprime la structure identifie par le parametre
     * -----
     * Deletes building identified with parameter
     * @param pId
     */
    // TODO test this method -> Xavier
    public void deleteBuildingRest(ABuildingEntity pBuilding, int pId){
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
        lUrlString += Integer.toString(pId);
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
