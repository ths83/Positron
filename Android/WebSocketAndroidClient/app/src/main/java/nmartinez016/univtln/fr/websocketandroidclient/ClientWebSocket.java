package nmartinez016.univtln.fr.websocketandroidclient;

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

/**
 * Created by marti on 23/05/2016.
 */

@ClientEndpoint
public class ClientWebSocket extends Thread {

    private static String LOG_TAG = "test";
    private ClientManager clientManager;
    private  ServiceWS serviceWS;
    private Looper looper;


    public ClientWebSocket(){}

    public ClientWebSocket(ServiceWS serviceWS){
        this.serviceWS = serviceWS;
    }

    /**
     * The session represent the current connection to the websocket server.
     */
    private Session session;

    /**
     * @return true if there is a connection to the server.
     */
    public boolean isConnectedToServer() {
        return session != null;
    }


    @OnOpen
    public void onOpen(final Session session, EndpointConfig endpointConfig) throws IOException, EncodeException {
        Log.i(LOG_TAG, "Connection opened.");
        this.session = session;
    }

    @OnMessage
    public void OnMessage(String msg) {
        Log.d(LOG_TAG, "Message received.");
        Log.d(LOG_TAG, "FROM SERVER :" + msg);
        serviceWS.showNotification(msg);
    }

    @OnClose
    public void OnClose(final Session session, EndpointConfig endpointConfig) {
        Log.d(LOG_TAG, "Session closed.");
        this.session = null;
    }

    /**
     * Send a Message to the websocket server.
     */
    public void sendChatMessage(final String mess) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "Sending message: " + mess);
                try {
                    if (session == null)
                        Log.e(LOG_TAG, "Session null.");
                    else if (session.isOpen())
                        session.getBasicRemote().sendObject(mess);
                    else Log.e(LOG_TAG, "Session not opened.");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void run() {
        Log.d(LOG_TAG, "Websocket BroadcasterWebsocketClient started");

        //Looper.prepare();
        Log.d(LOG_TAG, "step 2");
        //looper = Looper.myLooper();
        Log.d(LOG_TAG, "step 3");
        //This thread does nothing but waiting for
        // process incoming messages here
        URI uri = URI.create("ws://89.234.183.3:8025/echo");
        Log.d(LOG_TAG, "Connecting to " + uri + ".");
        try {
            clientManager = ClientManager.createClient();
            clientManager.connectToServer(ClientWebSocket.this, uri);
        } catch (DeploymentException e) {
            Log.e(LOG_TAG, "Websocket deployment exception: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Test Passage");
        Looper.loop();
        Log.d(LOG_TAG, "Websocket BroadcasterWebsocketClient ended");
    }

    public void close(){
        looper.quitSafely();
    }
}
