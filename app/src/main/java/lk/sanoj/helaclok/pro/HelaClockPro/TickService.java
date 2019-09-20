package lk.sanoj.helaclok.pro.HelaClockPro;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class TickService extends Service {

    double dTime;
    Intent intent;
    PendingIntent piPI;

    public IBinder onBind(Intent intent2) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.d("TickService", "OnCreate");
        Context applicationContext = getApplicationContext();
        intent = new Intent(applicationContext, ClockWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        piPI = PendingIntent.getBroadcast(applicationContext, 0, intent, 0);
        new Thread() {
            public void run() {
                int i = 0;
                while (true) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("TickThread ");
                    sb.append(i);
                    String str = "TickService";
                    Log.d(str, sb.toString());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        TickService.this.dTime = (double) Calendar.getInstance().getTimeInMillis();
                    }
                    Log.d(str, Long.toString((1000 - (((long) TickService.this.dTime) % 1000)) + 3));
                    SystemClock.sleep((1000 - (((long) TickService.this.dTime) % 1000)) + 3);
                    try {
                        TickService.this.piPI.send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }.start();
        if (Build.VERSION.SDK_INT >= 26) {
            startMyOwnForeground();
        } else {
            startForeground(1, new Notification());
        }
    }

    @SuppressLint("WrongConstant")
    @TargetApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {

        String str = BuildConfig.APPLICATION_ID;
        NotificationChannel notificationChannel = new NotificationChannel(str, "ClockWidget Background Service", 0);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
        startForeground(2, new Notification.Builder(this, str).setOngoing(true).setContentTitle("Clock Widget is running in background").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());

    }
}