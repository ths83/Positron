package nmartinez016.univtln.fr.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Binder;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Created by marti on 01/06/2016.
 */
public class WebSocketService extends Service {
    private static NotificationManager mNotificaitonManager;
    private final IBinder mBinder = new LocalBinder();

    private ClientTyrus mClientTyrus = new ClientTyrus(this);

    private Handler mHandler;
    private Runnable mRunnable;

    private static Messenger mClientMessenger;

    private int NOTIFICATION = 12;

    @Override
    public void onCreate() {
        mNotificaitonManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mClientTyrus.start();
        mHandler = new Handler();
    }

    public void showNotification(String pMsg){
        CharSequence lText = pMsg;

        PendingIntent lContentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        Notification lNotification = new Notification.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(lText)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("notif!")
                .setContentText(lText)
                .setContentIntent(lContentIntent)
                .build();

        mNotificaitonManager.notify(NOTIFICATION, lNotification);

    }

    public class LocalBinder extends Binder {
        WebSocketService getService(){
            return WebSocketService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
