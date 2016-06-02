package nmartinez016.univtln.fr.websocketandroidclient;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/*import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;*/
import fr.univtln.groupc.CPayloadBean;
import fr.univtln.groupc.EPayloadType;

public class MainActivity extends AppCompatActivity {
/*
    private String TAG = "tag";
    private ServiceWS mService;
    private boolean mBound = false;
    TextView mConn;
    ClientWebSocket mClient;
    private final WebSocketConnection mConnection = new WebSocketConnection();

    private void start() {

        final String wsuri = "ws://10.21.174.206:8025/echo";

        try {
            mConnection.connect(wsuri, new WebSocketHandler() {

                @Override
                public void onOpen() {
                    ObjectMapper lMapper = new ObjectMapper();
                    Log.d(TAG, "Status: Connected to " + wsuri);
                    //mConnection.sendTextMessage("Hello, world!");
                    try {
                        mConnection.sendTextMessage(lMapper.writeValueAsString(new CPayloadBean.CPayloadBeanBuilder().type(EPayloadType.CONNECTED.toString()).build()));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "Got echo: " + payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "Connection lost.");
                }
            });
        } catch (WebSocketException e) {

            Log.d(TAG, e.toString());
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mConn = (TextView) findViewById(R.id.connect);
        start();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
/*
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            ServiceWS.LocalBinder binder = (ServiceWS.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test", "dans onstart !");

        if(!isMyServiceRunning(ServiceWS.class)) {
            Log.d("test", "intent service ws!");
            Intent intent = new Intent(this, ServiceWS.class);

            startService(intent);
            Log.d("test", "intent start");
            if (!mBound) {
                bindService(intent, mConnection, BIND_AUTO_CREATE);
            }
        }
    }

    public void connectWS(){
        mClient = new ClientWebSocket();
        mClient.run();
    }*/
}
