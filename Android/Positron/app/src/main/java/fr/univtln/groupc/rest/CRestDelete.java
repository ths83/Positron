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

public class CRestDelete extends CRest{


    /**
     * Supprime le lien identifie par le parametre
     *
     * ------
     *
     * Deletes the link identified by parameter
     * @param pId
     */
    public void deleteLinkRest(int pId){
        String lUrlString = CRest.API_URL + "/links/" + Integer.toString(pId);
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
        String lUrlString = CRest.API_URL + "/consumables/" + Integer.toString(pId);
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
        String lUrlString =  CRest.API_URL + "/fields/" + Integer.toString(pId);
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
        String lUrlString = CRest.API_URL;

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
