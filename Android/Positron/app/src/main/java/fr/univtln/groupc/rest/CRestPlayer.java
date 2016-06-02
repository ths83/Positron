package fr.univtln.groupc.rest;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.CPlayerEntity;

/**
 * Created by arouani277 on 25/05/16.
 */
public class CRestPlayer extends CRest {

    public final static String API_URL = "http://10.0.3.2:9998";


    /**
     * get player by id from database
     * @return
     */
    public CPlayerEntity getPlayerByID(int pId){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/players/"+Integer.toString(pId);
        Log.d("test", "->-> " + lUrlString);
        String lPlayerJson = null;
        CPlayerEntity lPlayer = null;
        try {
            lPlayerJson = new CRestGet().execute(lUrlString).get();
            Log.d("test", lPlayerJson);
            lPlayer = lMapper.readValue(lPlayerJson, CPlayerEntity.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("test", "-> player object :\n  " + lPlayer);
        return lPlayer;
    }

    public CPlayerEntity getPlayerByMail(String pMail){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/players/mails"+pMail;

        Log.d("test", "->-> " + lUrlString);
        String lPlayerJson = null;
        CPlayerEntity lPlayer = null;
        try {
            lPlayerJson = new CRestGet().execute(lUrlString).get();
            Log.d("test", lPlayerJson);
            lPlayer = lMapper.readValue(lPlayerJson, CPlayerEntity.class);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("test", "-> player object :\n  " + lPlayer);
        return lPlayer;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
