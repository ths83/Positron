package nmartinez016.univtln.fr.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.glassfish.tyrus.client.ClientManager;

import java.io.IOException;
import java.net.URI;


import javax.websocket.ClientEndpoint;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import fr.univtln.groupc.CConnection;
import fr.univtln.groupc.CPayloadBean;
import fr.univtln.groupc.EPayloadType;

/**
 * Created by marti on 01/06/2016.
 */


@ClientEndpoint(encoders = {CPayloadBean.PayloadBeanCode.class},
        decoders = {CPayloadBean.PayloadBeanCode.class})
public class ClientTyrus extends Thread{

    private String LOG_TAG = "tag";
    private String IP_ADRESS = "10.9.185.55";
    private String PORT = "8025";
    private WebSocketService mServiceWS;
    private static Handler mHandler;
    private static ClientManager mClientManager = ClientManager.createClient();
    private static Session mSession;
    public ClientTyrus(){}

    public ClientTyrus(final WebSocketService pService){
        mServiceWS = pService;
    }

    public boolean isConnectedToServer(){
        return mSession != null;
    }

    @OnOpen
    public void onOpen(final Session pSession, EndpointConfig endpointConfig) throws IOException, EncodeException {
        Log.d(LOG_TAG, "Connection opened.");
        pSession.addMessageHandler(new CMessageHandler(mServiceWS));
        mSession = pSession;
        //sendSimpleMessageToService(CONNECTED_TO_SERVER);
        mSession.getBasicRemote().sendObject(new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CONNECTION.toString()).objectConnection(new CConnection(5)).build());
    }
/*
    @OnMessage
    public void OnMessage(CPayloadBean pPayloadBean) {
        Log.d(LOG_TAG, "Message received.");
        if (pPayloadBean.getType().equals(EPayloadType.CONNECTED)) {
            Log.d(LOG_TAG, "JE SUIS OCNNECTE!!!!");
        }
    }*/

    @OnClose
    public void OnClose(final Session session, EndpointConfig endpointConfig) {
        Log.d(LOG_TAG, "Session closed.");
        mSession = null;
    }

    public void run() {
        Looper.prepare();
        mHandler = new Handler();
        URI uri = URI.create("ws://" + IP_ADRESS + ":" + PORT +"/echo");
        try {
            mClientManager.connectToServer(this, uri);
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Looper.loop();
        Log.d(LOG_TAG, "Connecting to " + uri + ".");
    }

    public static void sendMessage(final CPayloadBean pBean){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    mSession.getBasicRemote().sendObject(pBean);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}