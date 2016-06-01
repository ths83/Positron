package fr.univtln.groupc.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.SimpleTimeZone;
import java.util.concurrent.ExecutionException;

import fr.univtln.groupc.entities.CLinkEntity;

/**
 * Created by toms on 08/05/2016.
 */
public class CRestPost extends CRest{


    @Override
    protected void onPostExecute (String s){
        super.onPostExecute(s);
    }


    public void postLinkRest(CLinkEntity pLink){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = CRest.API_URL + "/links";
        Log.d("test", "->-> " + lUrlString);
        try {
            String lLinkJson = lMapper.writeValueAsString(pLink);
            CRestPost lPost = new CRestPost();
            lPost.execute(lUrlString,lLinkJson).get();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

