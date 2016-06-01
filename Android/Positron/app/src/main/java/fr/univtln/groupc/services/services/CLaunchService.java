package fr.univtln.groupc.services.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;

import fr.univtln.m1dapm.groupec.tperron710.positron.R;

/**
 * Created by tperron710 on 01/06/16.
 */
public class CLaunchService extends IntentService {

    public static final int NOTIF_ID = 0;
    private Notification mNoti;
    private NotificationManager mNotificationManager;

    /**
     * Obligation de creer un constructeur generique
     * -----
     * Needs a empty constructor
     */
    public CLaunchService() {
        super("launch-service");
    }


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CLaunchService(String name) {
        super(name);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        createNotification();
    }

    /**
     * cree une notification lorsque l'application est lancé sur le client
     * ------
     * notification for positron in progress
     */
    public void createNotification() {
        mNoti = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.logpositronlarge)
                        .setContentTitle(getText(R.string.title_positron_in_progress))
                        .setContentText(getText(R.string.text_positron_in_progress))
                        .setColor(Color.CYAN)
                        .setUsesChronometer(true)
                        .build();

        //lNoti.flags |= Notification.FLAG_FOREGROUND_SERVICE;
        mNoti.flags |= Notification.FLAG_AUTO_CANCEL;
        //lNoti.flags |= Notification.PRIORITY_MAX;
        mNoti.flags |= Notification.COLOR_DEFAULT;

        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(NOTIF_ID, mNoti);
    }
}
