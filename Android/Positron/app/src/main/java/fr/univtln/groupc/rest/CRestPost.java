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

import fr.univtln.groupc.entities.CLinkEntity;

/**
 * Created by toms on 08/05/2016.
 */
public class CRestPost extends AsyncTask<String, String, Void> {
    public final static String API_URL = "http://10.9.185.55:9998";


    @Override
    protected Void doInBackground(String... params) {
        String lUrlString = params[0]; // URL to call
        String lJsonString = params[1];
        BufferedOutputStream out = null;
        try {
            URL url = new URL(lUrlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
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


    public void postLinkRest(CLinkEntity pLink){
        ObjectMapper lMapper = new ObjectMapper();
        String lUrlString = API_URL + "/links";
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

