package fr.univtln.groupc.services;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CPodometerService extends Service {

    private SensorManager mSensorManager;
    private TextView mStepNumber;
    boolean mActivityIsRunning;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = 0;
    private NotificationManager mNM;

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        CPodometerService getService() {
            return CPodometerService.this;
        }
    }

    @Override
    public void onCreate() {
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // Display a notification about us starting.  We put an icon in the status bar.
        showNotification();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        mNM.cancel(NOTIFICATION);

        // Tell the user we stopped.
        Toast.makeText(this, "END", Toast.LENGTH_SHORT).show();
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();

    /**
     * Show a notification while this service is running.
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
       // CharSequence text = getText(0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.logpositronlarge)  // the status icon
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("hello")  // the label of the entry
                .setContentText("test")  // the contents of the entry
                .build();
                //.setContentIntent(contentIntent)  // The intent to send when the entry is clicked


        // Send the notification.
        mNM.notify(NOTIFICATION, notification);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}