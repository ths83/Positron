package nmartinez016.univtln.fr.websocketandroidclient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;

/**
 * Created by marti on 23/05/2016.
 */


public class ServiceWS extends Service {

    private static String LOG_TAG = ServiceWS.class.getName();
    private static NotificationManager notificationManager;
    private final IBinder mBinder = new LocalBinder();
    private static BroadcastReceiver broadcastReceiver;

    /**
     * the different message types the activity can send to the service.
     */
    public enum MessageType {
        CONNECTION_REQUEST,SEND_MESSAGE_REQUEST, MESSAGE_RECEIVED_FROM_SERVER
    }


    private ClientWebSocket websocketClient = new ClientWebSocket(this);


    /**
     * The messenger used to send message to the BroadcasterWebsocketClient.
     */
    private static Messenger clientMessenger;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = 12;

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "Service started.");
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification("COucou");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                websocketClient.close();
                websocketClient = new ClientWebSocket(ServiceWS.this);
                websocketClient.start();
            }
        };
        registerReceiver(broadcastReceiver,new IntentFilter("ipchange"));
        //Now the service is started and the activity can receive message from it
        //We start the client
        websocketClient.start();
    }


    public void showNotification(String msg) {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = msg;

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("Titre pls")  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();

        // Set the info for the views that show in the notification panel.
        notificationManager.notify(NOTIFICATION, notification);
    }

    public class LocalBinder extends Binder {
        ServiceWS getService() {
            return ServiceWS.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "Service destroyed.");
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        websocketClient.close();
        notificationManager.cancel(NOTIFICATION);
    }

    public void testInterfaceMethod(String action) {
        System.out.println("Je suis dans le service salut steph");
        websocketClient.sendChatMessage(action);
    }
}
