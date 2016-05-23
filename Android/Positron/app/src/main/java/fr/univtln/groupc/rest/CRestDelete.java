package fr.univtln.groupc.rest;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by toms on 08/05/2016.
 */
public class CRestDelete{

    public final static String API_URL = "http://10.9.185.52:9998";

    private URL mUrl;
    private HttpURLConnection mConnection;
    private int mResponseCode;

    /**
     * Delete a link in the database
     * @param pId
     * @return
     */
    // TODO unit test
    public int onDeleteLink(int pId){

        try{
            mUrl = new URL(API_URL + "/links/" + Integer.toString(pId));
            mConnection = (HttpURLConnection) mUrl.openConnection();
            mConnection.setRequestMethod("DELETE");
            mResponseCode = mConnection.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mResponseCode;
    }

}
