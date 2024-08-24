package lk.sanoj.helaclok.pro.HelaClockPro;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class TickService extends Service {

    Intent intent;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private static final int UPDATE_INTERVAL = 1000; // 1 second interval

    public IBinder onBind(Intent intent2) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.d("TickService", "OnCreate");

        Context applicationContext = getApplicationContext();
        intent = new Intent(applicationContext, ClockWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        alarmIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Start foreground service
        if (Build.VERSION.SDK_INT >= 26) {
            startMyOwnForeground();
        } else {
            startForeground(1, new Notification());
        }

        // Schedule the first alarm
        scheduleNextUpdate();
    }

    private void scheduleNextUpdate() {
        long triggerTime = System.currentTimeMillis() + UPDATE_INTERVAL;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For API 23 and above, use setExactAndAllowWhileIdle to handle Doze mode
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, alarmIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // For API 19 and above, use setExact
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, alarmIntent);
        } else {
            // For older devices, use set method
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, alarmIntent);
        }

        Log.d("TickService", "Scheduled next update in 1 second");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Re-schedule the update when the service is started
        scheduleNextUpdate();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TickService", "OnDestroy");
        // Cancel the alarm when the service is destroyed
        if (alarmManager != null && alarmIntent != null) {
            alarmManager.cancel(alarmIntent);
        }
    }

    @SuppressLint("WrongConstant")
    @TargetApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String channelId = BuildConfig.APPLICATION_ID;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, "ClockWidget Background Service", NotificationManager.IMPORTANCE_LOW);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Clock Widget is running in background")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();

        startForeground(2, notification);


        /*String str = BuildConfig.APPLICATION_ID;
        NotificationChannel notificationChannel = new NotificationChannel(str, "ClockWidget Background Service", 0);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
        startForeground(2, new Notification.Builder(this, str).setOngoing(true).setContentTitle("Clock Widget is running in background").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());*/

    }
}