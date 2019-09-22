package lk.sanoj.helaclok.pro.HelaClockPro;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class ClockWidget extends AppWidgetProvider {

    private static final DateFormat df = new SimpleDateFormat("hh:mm:ss");
    final String LOG_TAG = "ClockWithHandle";
    AppWidgetManager appWidgetManager;
    private Intent clickIntent;
    private PendingIntent clickPI;
    Context cont;
    private double dTime;
    private PendingIntent piPI;
    private RemoteViews views;
    ComponentName widget;
    private PendingIntent pendingIntent;
    private int texctcolour;
    private int sexy;
    private String time;
    private Context con;
    private int ampmtrack;
    private int textcolour2;
    private int hintcontorl;
    private int click;




    public void onEnabled(Context context) {
        Log.d("ClockWithHandle", "onEnabled");

    }

    public void onReceive(final Context context, Intent intent) {



        String str = "ClockWithHandle";
        Log.d(str, "OnReceive");
        super.onReceive(context, intent);
        String action = intent.getAction();
        StringBuilder sb = new StringBuilder();
        sb.append("Action = ");
        sb.append(action);
        Log.d(str, sb.toString());


            if (VERSION.SDK_INT >= 26) {
                context.startForegroundService(new Intent(context, TickService.class));

            } else {
                context.startService(new Intent(context, TickService.class));
            }

         if (action.equals("android.appwidget.action.APPWIDGET_UPDATE")) {

             try{
                 SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
                 hintcontorl = prefs.getInt("hint", 0);
             }catch (Exception hint){

                 hintcontorl = 0;
             }



             if(hintcontorl==1){
                 this.views = new RemoteViews(context.getPackageName(), R.layout.withouthint);
             }else if (hintcontorl==0) {
                 this.views = new RemoteViews(context.getPackageName(), R.layout.hellowidget_layout);
             }




             //////////////////////////////////////////////////




             String date = new SimpleDateFormat("hh").format(Calendar.getInstance().getTime());
             String datem = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
             String datean = new SimpleDateFormat("a").format(Calendar.getInstance().getTime());


             String imageampm = new SimpleDateFormat("a").format(Calendar.getInstance().getTime());
             if (imageampm.equals("AM")){
                 ampmtrack = 1;
             }else if(imageampm.equals("am")){
                 ampmtrack = 1;
             }else if(imageampm.equals("PM")){
                 ampmtrack = 2;
             }else if(imageampm.equals("pm")){
                 ampmtrack = 2;
             }



             String imagehh = new SimpleDateFormat("hh").format(Calendar.getInstance().getTime());



             



             try {

                 SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
                 sexy = prefs.getInt("bcolour", 0);
             } catch (Exception e) {

             }



             try{

                 //views.setInt(R.id.back, "setBackgroundColor", android.graphics.Color.BLACK); //(Color.parseColor(cccc));
                 views.setInt(R.id.back, "setBackgroundColor", sexy);
             }catch (Exception ty){


             }




             ///////////////////////////////////////////////////////////////////////////////////////








             if(hintcontorl==1){

                 //
                 views.setViewVisibility(R.id.status, INVISIBLE);

             }else if(hintcontorl==0){
                 views.setViewVisibility(R.id.status, VISIBLE);
                 if(ampmtrack==1){
                     //views.setImageViewResource(R.id.status, R.drawable.after);

                     if(imagehh.equals("00")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("12")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("01")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("02")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("03")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("04")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("05")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("06")){
                         views.setImageViewResource(R.id.status, R.drawable.morningimg);
                     }
                     else if(imagehh.equals("07")){
                         views.setImageViewResource(R.id.status, R.drawable.morningimg);
                     }
                     else if(imagehh.equals("08")){
                         views.setImageViewResource(R.id.status, R.drawable.morningimg);
                     }
                     else if(imagehh.equals("09")){
                         views.setImageViewResource(R.id.status, R.drawable.morningimg);
                     }
                     else if(imagehh.equals("10")){
                         views.setImageViewResource(R.id.status, R.drawable.morningimg);
                     }
                     else if(imagehh.equals("11")){
                         views.setImageViewResource(R.id.status, R.drawable.morningimg);
                     }

                 }

                 if (ampmtrack==2){
                     if(imagehh.equals("00")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("12")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("01")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("02")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("03")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("04")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("05")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("06")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("07")){
                         views.setImageViewResource(R.id.status, R.drawable.after);
                     }
                     else if(imagehh.equals("08")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("09")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("10")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                     else if(imagehh.equals("11")){
                         views.setImageViewResource(R.id.status, R.drawable.night);
                     }
                 }
             }







             ////////////////////////////////////////////////////////////////////////////////////////

             ///////////////////////////////////////////////////////////////////////////////////////

             if (datean.equals("am")){
                 views.setImageViewBitmap(R.id.AMPM, textAsBitmap(context, "වේලාව පෙරවරු",220));
             }else if (datean.equals("AM")){
                 views.setImageViewBitmap(R.id.AMPM, textAsBitmap(context, "වේලාව පෙරවරු",220));
             }else if (datean.equals("pm")){
                 views.setImageViewBitmap(R.id.AMPM, textAsBitmap(context, "වේලාව පස්වරු",220));
             }else if(datean.equals("PM")){
                 views.setImageViewBitmap(R.id.AMPM, textAsBitmap(context, "වේලාව පස්වරු",220));
             }




             ///////////////////////////////////////////////////////////////////////////////////////
             ///////////////////////////////////////////////////////////////////////////////////////
             if(date.equals("01")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context, "එක පසුවී"));
             }else if (date.equals("02")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"දෙක පසුවී"));
             }else if (date.equals("03")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"තුන පසුවී"));
             }else if (date.equals("04")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"හතර පසුවී"));
             }else if (date.equals("05")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"පහ පසුවී"));
             }else if (date.equals("06")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"හය පසුවී"));
             }else if (date.equals("07")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"හත පසුවී"));
             }else if (date.equals("08")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"අට පසුවී"));
             }else if (date.equals("09")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"නවය පසුවී"));
             }else if (date.equals("10")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"දහය පසුවී"));
             }else if (date.equals("11")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"එකොළහ පසුවී"));
             }else if (date.equals("12")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"දොළහ පසුවී"));
             }else if (date.equals("13")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"එක පසුවී"));
             }else if (date.equals("14")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"දෙක පසුවී"));
             }else if (date.equals("15")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"තුන පසුවී"));
             }else if (date.equals("16")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"හතර පසුවී"));
             }else if (date.equals("17")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"පහ පසුවී"));
             }else if (date.equals("18")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"හය පසුවී"));
             }else if (date.equals("19")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"හත පසුවී"));
             }else if (date.equals("20")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"අට පසුවී"));
             }else if (date.equals("21")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"නවය පසුවී"));
             }else if (date.equals("22")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"නවය පසුවී"));
             }else if (date.equals("23")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"එකොළහ පසුවී"));
             }else if (date.equals("24")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"දොළහ පසුවී"));
             }else if (date.equals("0")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"දොළහ පසුවී"));
             }else if (date.equals("00")){
                 views.setImageViewBitmap(R.id.hou, textAsBitmap2(context,"දොළහ පසුවී"));
             }



             ///////////////////////////////////////////////////////////////////////////////////////

             //if (datem.equals("0")){
             // views.setImageViewBitmap(R.id.textView5, ("තත්පර කීපයක් පමනයි"));
             // }else if (datem.equals("00")){
             //    views.setImageViewBitmap(R.id.textView5, ("තත්පර කීපයක් පමනයි"));
             // }else{
             //   views.setImageViewBitmap(R.id.textView5, ("විනාඩි"));
             // }

             /////////////////////////////////////////////////////////////////////////////////////
             if (datem.equals("0")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"තත්පර කීපයක් පමනයි"));
             }else if (datem.equals("00")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"තත්පර කීපයක් පමනයි"));
             }else if (datem.equals("01")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි එකයි"));
             }else if (datem.equals("02")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දෙකයි"));
             }else if (datem.equals("03")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තුනයි"));
             }else if (datem.equals("04")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතරයි"));
             }else if (datem.equals("05")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පහයි"));
             }else if (datem.equals("06")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හයයි"));
             }else if (datem.equals("07")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතයි"));
             }else if (datem.equals("08")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි අටයි"));
             }else if (datem.equals("09")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි නවයයි"));
             }else if (datem.equals("10")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දහයයි"));
             }else if (datem.equals("11")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි එකොළහයි"));
             }else if (datem.equals("12")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දොළහයි"));
             }else if (datem.equals("13")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දහ තුනයි"));
             }else if (datem.equals("14")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දහ හතරයි"));
             }else if (datem.equals("15")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පහ ලොවයි"));
             }else if (datem.equals("16")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දහ සයයි"));
             }else if (datem.equals("17")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දහ හතයි"));
             }else if (datem.equals("18")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දහ අටයි"));
             }else if (datem.equals("19")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි දහ නවයයි"));
             }else if (datem.equals("20")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විස්සයි"));
             }else if (datem.equals("21")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසීඑකයි"));
             }else if (datem.equals("22")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිදෙකයි"));
             }else if (datem.equals("23")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිතුනයී"));
             }else if (datem.equals("24")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිහතරයි"));
             }else if (datem.equals("25")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිපහයි"));
             }else if (datem.equals("26")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිහයයි"));
             }else if (datem.equals("27")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිහතයි"));
             }else if (datem.equals("28")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිඅටයි"));
             }else if (datem.equals("29")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි විසිනවයයි"));
             }else if (datem.equals("30")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිහයි"));
             }else if (datem.equals("31")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්එකයි"));
             }else if (datem.equals("32")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්දෙකයි"));
             }else if (datem.equals("33")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්තුනයි"));
             }else if (datem.equals("34")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්හතරයි"));
             }else if (datem.equals("35")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්පහයි"));
             }else if (datem.equals("36")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්හයයි"));
             }else if (datem.equals("37")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්හතයි"));
             }else if (datem.equals("38")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්අටයි"));
             }else if (datem.equals("39")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි තිස්නවයයි"));
             }else if (datem.equals("40")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිහයි"));
             }else if (datem.equals("41")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් එකයි"));
             }else if (datem.equals("42")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් දෙකයි"));
             }else if (datem.equals("43")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් තුනයි"));
             }else if (datem.equals("44")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් හතරයි"));
             }else if (datem.equals("45")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් පහයි"));
             }else if (datem.equals("46")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් හයයි"));
             }else if (datem.equals("47")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් හතයි"));
             }else if (datem.equals("48")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් අටයි"));
             }else if (datem.equals("49")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි හතලිස් නවයය"));
             }else if (datem.equals("50")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනහයි"));
             }else if (datem.equals("51")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් එකයි"));
             }else if (datem.equals("52")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් දෙකයි"));
             }else if (datem.equals("53")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් තුනයි"));
             }else if (datem.equals("54")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් හතරයි"));
             }else if (datem.equals("55")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් පහයි"));
             }else if (datem.equals("56")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් හයයි"));
             }else if (datem.equals("57")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් හතයි"));
             }else if (datem.equals("58")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් අටයි"));
             }else if (datem.equals("59")){
                 views.setImageViewBitmap(R.id.mini, textAsBitmap2(context,"විනාඩි පනස් නවයයි"));
             }



             Intent intents = new Intent(context, widsetting.class);
             intent.setAction("Click");
             PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intents, 0);
             views.setOnClickPendingIntent(R.id.myRelativeLayout, pendingIntent);






             this.widget = new ComponentName(context, ClockWidget.class);
             this.appWidgetManager = AppWidgetManager.getInstance(context);
             this.appWidgetManager.updateAppWidget(this.widget, this.views);


        }
    }

    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        StringBuilder sb = new StringBuilder();
        sb.append("onDeleted ");
        sb.append(Arrays.toString(iArr));
        Log.d("ClockWithHandle", sb.toString());
    }

    public Bitmap textAsBitmap(Context context,String time, int fontsiz) {


        try {

            SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            textcolour2 = prefs.getInt("textc", 0);

            if(textcolour2==0){
                texctcolour = Color.WHITE;
            }else {
                texctcolour = textcolour2;
            }

        } catch (Exception e) {
            texctcolour = Color.WHITE;
        }


        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(fontsiz);
        Typeface clock = Typeface.createFromAsset(context.getAssets(),"emanee.ttf");
        paint.setColor(texctcolour);
        paint.setTypeface(clock);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(time) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(time, 0, baseline, paint);
        return image;
    }

    public Bitmap textAsBitmap2(Context context,String time) {

        try {

            SharedPreferences prefs = context.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            textcolour2 = prefs.getInt("textc", 0);

            if(textcolour2==0){
                texctcolour = Color.WHITE;
            }else {
                texctcolour = textcolour2;
            }


        } catch (Exception e) {
            texctcolour = Color.WHITE;
        }
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(195);
        Typeface clock = Typeface.createFromAsset(context.getAssets(),"emanee.ttf");
        paint.setColor(texctcolour);
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
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }



    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d("ClockWithHandle", "onDisabled");
    }

}
