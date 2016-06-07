package fr.univtln.groupc.services.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

import fr.univtln.groupc.services.CPodometer;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CPodometerService extends IntentService{

    private SensorManager mSensorManager;
    private TextView mStepNumber;
    boolean mActivityIsRunning;

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.
    private int NOTIFICATION = 1;
    private NotificationManager mNotificationManager;
    private android.support.v4.app.NotificationCompat.Builder mNoti;
    private CPodometer mPodo = new CPodometer();

    public CPodometerService(){
        super("Podomerter-service");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CPodometerService(String name) {
        super(name);
    }


    /**
     * Show a notification while this service is running.
     */
    public void createNotification() throws InterruptedException {
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNoti = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.logpositronlarge)
                .setContentTitle(getText(R.string.title_positron_in_progress))
                .setContentText(getText(R.string.podo_text_value) + Float.toString(mPodo.getStepNumber()) + getText(R.string.step_string))
                .setColor(Color.CYAN);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(NOTIFICATION, mNoti.build());

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            createNotification();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}