package nmartinez016.univtln.fr.websocketandroidclient;


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
    private Session mSession;
    private ClientManager mClientManager;


    @OnOpen
    public void onOpen(final Session pSession, EndpointConfig endpointConfig) throws IOException, EncodeException {
        Log.d(LOG_TAG, "Connection opened.");
        mSession = pSession;
        //sendSimpleMessageToService(CONNECTED_TO_SERVER);
        mSession.getBasicRemote().sendObject(new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CONNECTION.toString()).objectConnection(new CConnection(5)).build());
    }

    @OnMessage
    public void OnMessage(CPayloadBean pPayloadBean) {
        Log.d(LOG_TAG, "Message received.");
        if (pPayloadBean.getType().equals(EPayloadType.CONNECTED)) {
            Log.d(LOG_TAG, "JE SUIS OCNNECTE!!!!");
        }
    }

    @OnClose
    public void OnClose(final Session session, EndpointConfig endpointConfig) {
        Log.d(LOG_TAG, "Session closed.");
        mSession = null;
    }

    public void run() {
        URI uri = URI.create("ws://10.190.3.177:8025/echo");

        Log.d(LOG_TAG, "Connecting to " + uri + ".");
        try {
            mClientManager = ClientManager.createClient();
            Log.d(LOG_TAG, "create client");
            mClientManager.connectToServer(this, uri);
            Log.d(LOG_TAG, "connectToServer");
        } catch (DeploymentException e) {
            Log.d(LOG_TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }
}
