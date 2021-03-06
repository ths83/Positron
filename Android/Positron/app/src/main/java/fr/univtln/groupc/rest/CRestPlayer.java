package fr.univtln.groupc.rest;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.CPlayerEntity;

/**
 * Created by arouani277 on 25/05/16.
 */
public class CRestPlayer extends CRest {

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

    public void postPlayerRest(CPlayerEntity pPlayer){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/players";
        Log.d("test", "->-> " + lUrlString);
        try {
            String lPlayerJson = lMapper.writeValueAsString(pPlayer);
            CRestPost lPost = new CRestPost();
            lPost.execute(lUrlString,lPlayerJson).get();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public CPlayerEntity getPlayerByMail(String pMail) throws ExecutionException, InterruptedException {
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/players/mails"+pMail;

        Log.d("test", "->-> " + lUrlString);
        String lPlayerJson = null;
        CPlayerEntity lPlayer = null;

        lPlayerJson = new CRestGet().execute(lUrlString).get();
        Log.d("test", lPlayerJson);
        try {
            lPlayer = lMapper.readValue(lPlayerJson, CPlayerEntity.class);
        } catch (IOException e) {
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
