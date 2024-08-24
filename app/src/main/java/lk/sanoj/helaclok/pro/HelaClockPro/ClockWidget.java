package lk.sanoj.helaclok.pro.HelaClockPro;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.AlarmClock;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import lk.sanoj.helaclok.pro.HelaClockPro.Models.Clock;

public class ClockWidget extends AppWidgetProvider {

    AppWidgetManager appWidgetManager;
    private RemoteViews views;
    ComponentName widget;
    private int amPM_Track;
    private static final String[] HOUR_TEXTS = {
            "දොළහ පසුවී", "එක පසුවී", "දෙක පසුවී", "තුන පසුවී", "හතර පසුවී",
            "පහ පසුවී", "හය පසුවී", "හත පසුවී", "අට පසුවී", "නවය පසුවී",
            "දහය පසුවී", "එකොළහ පසුවී", "දොළහ පසුවී"
    };
    private static final String[] MINUTE_TEXTS = {
            "තත්පර කීපයක් පමනයි", "විනාඩි එකයි", "විනාඩි දෙකයි", "විනාඩි තුනයි", "විනාඩි හතරයි",
            "විනාඩි පහයි", "විනාඩි හයයි", "විනාඩි හතයි", "විනාඩි අටයි", "විනාඩි නවයයි",
            "විනාඩි දහයයි", "විනාඩි එකොළහයි", "විනාඩි දොළහයි", "විනාඩි දහ තුනයි", "විනාඩි දහ හතරයි",
            "විනාඩි පහ ලොවයි", "විනාඩි දහ සයයි", "විනාඩි දහ හතයි", "විනාඩි දහ අටයි", "විනාඩි දහ නවයයි",
            "විනාඩි විස්සයි", "විනාඩි විසීඑකයි", "විනාඩි විසිදෙකයි", "විනාඩි විසිතුනයී", "විනාඩි විසිහතරයි",
            "විනාඩි විසිපහයි", "විනාඩි විසිහයයි", "විනාඩි විසිහතයි", "විනාඩි විසිඅටයි", "විනාඩි විසිනවයයි",
            "විනාඩි තිහයි", "විනාඩි තිස්එකයි", "විනාඩි තිස්දෙකයි", "විනාඩි තිස්තුනයි", "විනාඩි තිස්හතරයි",
            "විනාඩි තිස්පහයි", "විනාඩි තිස්හයයි", "විනාඩි තිස්හතයි", "විනාඩි තිස්අටයි", "විනාඩි තිස්නවයයි",
            "විනාඩි හතලිහයි", "විනාඩි හතලිස් එකයි", "විනාඩි හතලිස් දෙකයි", "විනාඩි හතලිස් තුනයි", "විනාඩි හතලිස් හතරයි",
            "විනාඩි හතලිස් පහයි", "විනාඩි හතලිස් හයයි", "විනාඩි හතලිස් හතයි", "විනාඩි හතලිස් අටයි", "විනාඩි හතලිස් නවයය",
            "විනාඩි පනහයි", "විනාඩි පනස් එකයි", "විනාඩි පනස් දෙකයි", "විනාඩි පනස් තුනයි", "විනාඩි පනස් හතරයි",
            "විනාඩි පනස් පහයි", "විනාඩි පනස් හයයි", "විනාඩි පනස් හතයි", "විනාඩි පනස් අටයි", "විනාඩි පනස් නවයයි"
    };


    public void onEnabled(Context context) {
        Log.d("ClockWithHandle", "onEnabled");
    }

    public void onReceive(final Context context, Intent intent) {
        String str = "ClockWithHandle";
        Log.d(str, "OnReceive");
        super.onReceive(context, intent);
        String action = intent.getAction();
        String sb = "Action = " + action;
        Log.d(str, sb);

        // Start Tick Service
        startWidgetBackgroundThread(context);

        assert action != null;
        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            int hintcontorl;
            try {
                SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
                hintcontorl = prefs.getInt("hint", 0);
            } catch (Exception hint) {
                hintcontorl = 0;
            }

            // Load widget with or without hint
            if (hintcontorl == 1) {
                this.views = new RemoteViews(context.getPackageName(), R.layout.withouthint);
            } else if (hintcontorl == 0) {
                this.views = new RemoteViews(context.getPackageName(), R.layout.hellowidget_layout);
            }

            final Clock clock = new Clock();
            // SinhalaTimeConverter converter = new SinhalaTimeConverter(clock.getTime());

            switch (clock.getTime().getMeridiem()) {
                case "AM":
                case "am":
                    amPM_Track = 1;
                    break;
                case "PM":
                case "pm":
                    amPM_Track = 2;
                    break;
            }

            String imageHours = clock.getTime().getHours();

            // Set visibility of the hint image
            setHintImage(context, hintcontorl, imageHours);

            // Set clock meridiem
            switch (clock.getTime().getMeridiem()) {
                case "am":
                case "AM":
                    views.setImageViewBitmap(R.id.AMPM, textAsBitmap(context, "වේලාව පෙරවරු", 220, false));
                    break;
                case "pm":
                case "PM":
                    views.setImageViewBitmap(R.id.AMPM, textAsBitmap(context, "වේලාව පස්වරු", 220, false));
                    break;
            }

            // Set clock hours
            int hour = Integer.parseInt(clock.getTime().getHours());
            int index = hour % 12;
            views.setImageViewBitmap(R.id.hou, textAsBitmap(context, HOUR_TEXTS[index], 195, true));
            /*switch (clock.getTime().getHours()) {
                case "01":
                case "13":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "එක පසුවී"));
                    break;
                case "02":
                case "14":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "දෙක පසුවී"));
                    break;
                case "03":
                case "15":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "තුන පසුවී"));
                    break;
                case "04":
                case "16":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "හතර පසුවී"));
                    break;
                case "05":
                case "17":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "පහ පසුවී"));
                    break;
                case "06":
                case "18":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "හය පසුවී"));
                    break;
                case "07":
                case "19":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "හත පසුවී"));
                    break;
                case "08":
                case "20":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "අට පසුවී"));
                    break;
                case "09":
                case "21":
                case "22":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "නවය පසුවී"));
                    break;
                case "10":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "දහය පසුවී"));
                    break;
                case "11":
                case "23":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "එකොළහ පසුවී"));
                    break;
                case "12":
                case "24":
                case "0":
                case "00":
                    views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "දොළහ පසුවී"));
                    break;
            }*/

            // Setting clock minutes
            int minutes = Integer.parseInt(clock.getTime().getMinutes());
            views.setImageViewBitmap(R.id.mini, textAsBitmap(context, MINUTE_TEXTS[minutes], 195, true));
            /*switch (clock.getTime().getMinutes()) {
                case "0":
                case "00":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "තත්පර කීපයක් පමනයි"));
                    break;
                case "01":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි එකයි"));
                    break;
                case "02":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දෙකයි"));
                    break;
                case "03":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තුනයි"));
                    break;
                case "04":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතරයි"));
                    break;
                case "05":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පහයි"));
                    break;
                case "06":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හයයි"));
                    break;
                case "07":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතයි"));
                    break;
                case "08":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි අටයි"));
                    break;
                case "09":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි නවයයි"));
                    break;
                case "10":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දහයයි"));
                    break;
                case "11":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි එකොළහයි"));
                    break;
                case "12":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දොළහයි"));
                    break;
                case "13":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දහ තුනයි"));
                    break;
                case "14":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දහ හතරයි"));
                    break;
                case "15":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පහ ලොවයි"));
                    break;
                case "16":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දහ සයයි"));
                    break;
                case "17":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දහ හතයි"));
                    break;
                case "18":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දහ අටයි"));
                    break;
                case "19":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි දහ නවයයි"));
                    break;
                case "20":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විස්සයි"));
                    break;
                case "21":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසීඑකයි"));
                    break;
                case "22":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිදෙකයි"));
                    break;
                case "23":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිතුනයී"));
                    break;
                case "24":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිහතරයි"));
                    break;
                case "25":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිපහයි"));
                    break;
                case "26":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිහයයි"));
                    break;
                case "27":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිහතයි"));
                    break;
                case "28":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිඅටයි"));
                    break;
                case "29":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි විසිනවයයි"));
                    break;
                case "30":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිහයි"));
                    break;
                case "31":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්එකයි"));
                    break;
                case "32":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්දෙකයි"));
                    break;
                case "33":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්තුනයි"));
                    break;
                case "34":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්හතරයි"));
                    break;
                case "35":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්පහයි"));
                    break;
                case "36":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්හයයි"));
                    break;
                case "37":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්හතයි"));
                    break;
                case "38":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්අටයි"));
                    break;
                case "39":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි තිස්නවයයි"));
                    break;
                case "40":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිහයි"));
                    break;
                case "41":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් එකයි"));
                    break;
                case "42":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් දෙකයි"));
                    break;
                case "43":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් තුනයි"));
                    break;
                case "44":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් හතරයි"));
                    break;
                case "45":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් පහයි"));
                    break;
                case "46":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් හයයි"));
                    break;
                case "47":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් හතයි"));
                    break;
                case "48":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් අටයි"));
                    break;
                case "49":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි හතලිස් නවයය"));
                    break;
                case "50":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනහයි"));
                    break;
                case "51":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් එකයි"));
                    break;
                case "52":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් දෙකයි"));
                    break;
                case "53":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් තුනයි"));
                    break;
                case "54":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් හතරයි"));
                    break;
                case "55":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් පහයි"));
                    break;
                case "56":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් හයයි"));
                    break;
                case "57":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් හතයි"));
                    break;
                case "58":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් අටයි"));
                    break;
                case "59":
                    views.setImageViewBitmap(R.id.mini, textAsBitmap2(context, "විනාඩි පනස් නවයයි"));
                    break;
            }*/


            Intent intents = new Intent(context, WidgetSetting.class);
            intent.setAction("Click");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intents, PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.myRelativeLayout, pendingIntent);

            this.widget = new ComponentName(context, ClockWidget.class);
            this.appWidgetManager = AppWidgetManager.getInstance(context);
            this.appWidgetManager.updateAppWidget(this.widget, this.views);

            // This method will update the clock time and no cases found for changing colors
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int appWidgetId : iArr) {
            // Create an Intent to open the clock app
            Intent openClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
            openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Create a PendingIntent with the Intent
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Create a RemoteViews object to update the widget
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.hellowidget_layout);
            // Set the PendingIntent to the widget’s view
            views.setOnClickPendingIntent(R.id.status, pendingIntent);
            views.setOnClickPendingIntent(R.id.hou, pendingIntent);
            views.setOnClickPendingIntent(R.id.mini, pendingIntent);
            views.setOnClickPendingIntent(R.id.AMPM, pendingIntent);

            // Update the widget with the RemoteViews
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private void setHintImage(Context context, int hintcontorl, String imageHours) {
        if (hintcontorl == 1) {
            views.setViewVisibility(R.id.status, INVISIBLE);
        } else if (hintcontorl == 0) {
            views.setViewVisibility(R.id.status, VISIBLE);

            // Set hint image day or night
            if (amPM_Track == 1) {
                switch (imageHours) {
                    case "00":
                    case "12":
                    case "01":
                    case "02":
                    case "03":
                    case "04":
                    case "05":
                        // views.setImageViewResource(R.id.status, R.drawable.night);
                        views.setImageViewBitmap(R.id.status, tintDrawable(context, "night"));
                        break;
                    case "06":
                    case "07":
                    case "08":
                    case "09":
                    case "10":
                    case "11":
                        // views.setImageViewResource(R.id.status, R.drawable.morningimg);
                        views.setImageViewBitmap(R.id.status, tintDrawable(context, "morning"));
                        break;
                }

            }

            if (amPM_Track == 2) {
                switch (imageHours) {
                    case "00":
                    case "12":
                    case "01":
                    case "02":
                    case "03":
                    case "04":
                    case "05":
                    case "06":
                    case "07":
                        // views.setImageViewResource(R.id.status, R.drawable.after);
                        views.setImageViewBitmap(R.id.status, tintDrawable(context, "after"));
                        break;
                    case "08":
                    case "09":
                    case "10":
                    case "11":
                        // views.setImageViewResource(R.id.status, R.drawable.night);
                        views.setImageViewBitmap(R.id.status, tintDrawable(context, "night"));
                        break;
                }
            }
        }
    }

    private static void startWidgetBackgroundThread(Context context) {
        if (VERSION.SDK_INT >= 26) {
            context.startForegroundService(new Intent(context, TickService.class));
        } else {
            context.startService(new Intent(context, TickService.class));
        }
    }

    private Bitmap tintDrawable(Context context, String drawableName) {
        int color = Color.WHITE;

        try {
            SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);

            if (prefs.getInt("textc", 0) != 0) {
                color = prefs.getInt("textc", 0);
            }
        } catch (Exception ignore) {
        }

        int resourceId;
        switch (drawableName) {
            case "night":
                resourceId = R.drawable.night;
                break;
            case "morning":
                resourceId = R.drawable.morningimg;
                break;
            case "after":
                resourceId = R.drawable.after;
                break;
            default:
                return null;
        }

        Drawable drawable = ContextCompat.getDrawable(context, resourceId);
        if (drawable == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return bitmap;
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        String sb = "onDeleted " +
                Arrays.toString(iArr);
        Log.d("ClockWithHandle", sb);
    }

    private Bitmap textAsBitmap(Context context, String text, float fontSize, boolean useAntiAlias) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(fontSize);
        Typeface clock = Typeface.createFromAsset(context.getAssets(), "emanee.ttf");
        paint.setColor(getTextColor(context));
        paint.setTypeface(clock);
        paint.setTextAlign(Paint.Align.LEFT);

        if (useAntiAlias) {
            paint.setAntiAlias(true);
            paint.setSubpixelText(true);
        }

        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        int width = bounds.width();
        int height = bounds.height();

        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, -bounds.left, -bounds.top, paint);

        return image;
    }

    private int getTextColor(Context context) {
        try {
            SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
            int color = prefs.getInt("textc", 0);
            return color != 0 ? color : Color.WHITE;
        } catch (Exception e) {
            return Color.WHITE;
        }
    }

    /*public Bitmap textAsBitmap2(Context context, String time, int fontSize) {

        try {

            SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            color = prefs.getInt("textc", 0);

            if (color == 0) {
                textColor01 = Color.WHITE;
            } else {
                textColor01 = color;
            }


        } catch (Exception e) {
            textColor01 = Color.WHITE;
        }
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        // paint.setTextSize(195);
        paint.setTextSize(fontSize);
        Typeface clock = Typeface.createFromAsset(context.getAssets(), "emanee.ttf");
        paint.setColor(textColor01);
        paint.setTypeface(clock);
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(time) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(time, 0, baseline, paint);
        return image;
    }*/

    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d("ClockWithHandle", "onDisabled");
    }

}
