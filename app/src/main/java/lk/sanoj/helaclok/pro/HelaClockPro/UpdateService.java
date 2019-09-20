package lk.sanoj.helaclok.pro.HelaClockPro;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class UpdateService extends Service {

    private Context context;
    private String cccc;
    private int texctcolour;
    private int sexy;





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //////////////////////////////////////////////////

        String time = getCurrentDateTime();



        //////////////////////////////////////////////////




        String date = new SimpleDateFormat("hh").format(Calendar.getInstance().getTime());
        String datem = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
        String datean = new SimpleDateFormat("a").format(Calendar.getInstance().getTime());


        String imageampm = new SimpleDateFormat("a").format(Calendar.getInstance().getTime());
        String imagehh = new SimpleDateFormat("hh").format(Calendar.getInstance().getTime());



        RemoteViews view = new RemoteViews(getPackageName(), R.layout.hellowidget_layout);








        try {

            SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            sexy = prefs.getInt("bcolour", 0);
        } catch (Exception e) {

        }



        try{

            //view.setInt(R.id.back, "setBackgroundColor", android.graphics.Color.BLACK); //(Color.parseColor(cccc));
            view.setInt(R.id.back, "setBackgroundColor", sexy);
        }catch (Exception ty){


        }



        ///////////////////////////////////////////////////////////////////////////////////////


        if(imageampm.equals("AM")){
            //view.setImageViewResource(R.id.status, R.drawable.after);

            if(imagehh.equals("00")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("12")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("01")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("02")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("03")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("04")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("05")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("06")){
                view.setImageViewResource(R.id.status, R.drawable.morningimg);
            }
            if(imagehh.equals("07")){
                view.setImageViewResource(R.id.status, R.drawable.morningimg);
            }
            if(imagehh.equals("08")){
                view.setImageViewResource(R.id.status, R.drawable.morningimg);
            }
            if(imagehh.equals("09")){
                view.setImageViewResource(R.id.status, R.drawable.morningimg);
            }
            if(imagehh.equals("10")){
                view.setImageViewResource(R.id.status, R.drawable.morningimg);
            }
            if(imagehh.equals("11")){
                view.setImageViewResource(R.id.status, R.drawable.morningimg);
            }

        }else if (imageampm.equals("PM")){
            if(imagehh.equals("00")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("12")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("01")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("02")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("03")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("04")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("05")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("06")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("07")){
                view.setImageViewResource(R.id.status, R.drawable.after);
            }
            if(imagehh.equals("08")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("09")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("10")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
            if(imagehh.equals("11")){
                view.setImageViewResource(R.id.status, R.drawable.night);
            }
        }




        ////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////

        if (datean.equals("am")){
            view.setImageViewBitmap(R.id.AMPM, textAsBitmap("වේලාව පෙරවරු"));
        }else if (datean.equals("AM")){
            view.setImageViewBitmap(R.id.AMPM, textAsBitmap("වේලාව පෙරවරු"));
        }else if (datean.equals("pm")){
            view.setImageViewBitmap(R.id.AMPM, textAsBitmap("වේලාව පස්වරු"));
        }else if(datean.equals("PM")){
            view.setImageViewBitmap(R.id.AMPM, textAsBitmap("වේලාව පස්වරු"));
        }




        ///////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        if(date.equals("01")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("එක පසුවී"));
        }else if (date.equals("02")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("දෙක පසුවී"));
        }else if (date.equals("03")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("තුන පසුවී"));
        }else if (date.equals("04")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("හතර පසුවී"));
        }else if (date.equals("05")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("පහ පසුවී"));
        }else if (date.equals("06")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("හය පසුවී"));
        }else if (date.equals("07")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("හත පසුවී"));
        }else if (date.equals("08")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("අට පසුවී"));
        }else if (date.equals("09")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("නවය පසුවී"));
        }else if (date.equals("10")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("දහය පසුවී"));
        }else if (date.equals("11")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("එකොළහ පසුවී"));
        }else if (date.equals("12")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("දොළහ පසුවී"));
        }else if (date.equals("13")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("එක පසුවී"));
        }else if (date.equals("14")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("දෙක පසුවී"));
        }else if (date.equals("15")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("තුන පසුවී"));
        }else if (date.equals("16")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("හතර පසුවී"));
        }else if (date.equals("17")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("පහ පසුවී"));
        }else if (date.equals("18")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("හය පසුවී"));
        }else if (date.equals("19")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("හත පසුවී"));
        }else if (date.equals("20")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("අට පසුවී"));
        }else if (date.equals("21")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("නවය පසුවී"));
        }else if (date.equals("22")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("නවය පසුවී"));
        }else if (date.equals("23")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("එකොළහ පසුවී"));
        }else if (date.equals("24")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("දොළහ පසුවී"));
        }else if (date.equals("0")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("දොළහ පසුවී"));
        }else if (date.equals("00")){
            view.setImageViewBitmap(R.id.hou, textAsBitmap2("දොළහ පසුවී"));
        }



        ///////////////////////////////////////////////////////////////////////////////////////

        //if (datem.equals("0")){
           // view.setImageViewBitmap(R.id.textView5, textAsBitmap2("තත්පර කීපයක් පමනයි"));
       // }else if (datem.equals("00")){
        //    view.setImageViewBitmap(R.id.textView5, textAsBitmap2("තත්පර කීපයක් පමනයි"));
       // }else{
         //   view.setImageViewBitmap(R.id.textView5, textAsBitmap2("විනාඩි"));
       // }

        /////////////////////////////////////////////////////////////////////////////////////
        if (datem.equals("0")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("තත්පර කීපයක් පමනයි"));
        }else if (datem.equals("00")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("තත්පර කීපයක් පමනයි"));
        }else if (datem.equals("01")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි එකයි"));
        }else if (datem.equals("02")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දෙකයි"));
        }else if (datem.equals("03")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තුනයි"));
        }else if (datem.equals("04")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතරයි"));
        }else if (datem.equals("05")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පහයි"));
        }else if (datem.equals("06")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හයයි"));
        }else if (datem.equals("07")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතයි"));
        }else if (datem.equals("08")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි අටයි"));
        }else if (datem.equals("09")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි නවයයි"));
        }else if (datem.equals("10")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දහයයි"));
        }else if (datem.equals("11")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි එකොළහයි"));
        }else if (datem.equals("12")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දොළහයි"));
        }else if (datem.equals("13")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දහ තුනයි"));
        }else if (datem.equals("14")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දහ හතරයි"));
        }else if (datem.equals("15")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පහ ලොවයි"));
        }else if (datem.equals("16")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දහ සයයි"));
        }else if (datem.equals("17")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දහ හතයි"));
        }else if (datem.equals("18")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දහ අටයි"));
        }else if (datem.equals("19")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි දහ නවයයි"));
        }else if (datem.equals("20")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විස්සයි"));
        }else if (datem.equals("21")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසීඑකයි"));
        }else if (datem.equals("22")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිදෙකයි"));
        }else if (datem.equals("23")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිතුනයී"));
        }else if (datem.equals("24")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිහතරයි"));
        }else if (datem.equals("25")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිපහයි"));
        }else if (datem.equals("26")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිහයයි"));
        }else if (datem.equals("27")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිහතයි"));
        }else if (datem.equals("28")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිඅටයි"));
        }else if (datem.equals("29")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි විසිනවයයි"));
        }else if (datem.equals("30")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිහයි"));
        }else if (datem.equals("31")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්එකයි"));
        }else if (datem.equals("32")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්දෙකයි"));
        }else if (datem.equals("33")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්තුනයි"));
        }else if (datem.equals("34")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්හතරයි"));
        }else if (datem.equals("35")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්පහයි"));
        }else if (datem.equals("36")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්හයයි"));
        }else if (datem.equals("37")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්හතයි"));
        }else if (datem.equals("38")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්අටයි"));
        }else if (datem.equals("39")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි තිස්නවයයි"));
        }else if (datem.equals("40")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිහයි"));
        }else if (datem.equals("41")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් එකයි"));
        }else if (datem.equals("42")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් දෙකයි"));
        }else if (datem.equals("43")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් තුනයි"));
        }else if (datem.equals("44")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් හතරයි"));
        }else if (datem.equals("45")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් පහයි"));
        }else if (datem.equals("46")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් හයයි"));
        }else if (datem.equals("47")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් හතයි"));
        }else if (datem.equals("48")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් අටයි"));
        }else if (datem.equals("49")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි හතලිස් නවයය"));
        }else if (datem.equals("50")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනහයි"));
        }else if (datem.equals("51")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් එකයි"));
        }else if (datem.equals("52")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් දෙකයි"));
        }else if (datem.equals("53")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් තුනයි"));
        }else if (datem.equals("54")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් හතරයි"));
        }else if (datem.equals("55")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් පහයි"));
        }else if (datem.equals("56")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් හයයි"));
        }else if (datem.equals("57")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් හතයි"));
        }else if (datem.equals("58")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් අටයි"));
        }else if (datem.equals("59")){
            view.setImageViewBitmap(R.id.mini, textAsBitmap2("විනාඩි පනස් නවයයි"));
        }


        //view.setImageViewBitmap(R.id.imageView4, buildUpdate("පනස් නවයයි"));



        //view.setTextViewText(R.id.txt_widget, getCurrentDateTime());
        ComponentName theWidget = new ComponentName(this, ClockWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget, view);

        return super.onStartCommand(intent, flags, startId);

    }



    public Bitmap buildUpdate(String times)
    {
        Bitmap myBitmap = Bitmap.createBitmap(200, 100, Bitmap.Config.ARGB_8888);
        Canvas myCanvas = new Canvas(myBitmap);
        Paint paint = new Paint();
        Typeface clock = Typeface.createFromAsset(this.getAssets(),"emanee.ttf");
        //paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(clock);
        //paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(16 * getResources().getDisplayMetrics().density);

       paint.setTextAlign(Paint.Align.CENTER);

        myCanvas.drawText(times, 130, 50, paint);
        return myBitmap;
    }
    public Bitmap textAsBitmap(String time) {

        Context context;

        try {

            SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            texctcolour = prefs.getInt("textc", 0);

        } catch (Exception e) {
            texctcolour = Color.WHITE;
        }


        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(95);
        Typeface clock = Typeface.createFromAsset(this.getAssets(),"emanee.ttf");
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
    public Bitmap textAsBitmap2(String time) {

        try {

            SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            texctcolour = prefs.getInt("textc", 0);
        } catch (Exception e) {
            texctcolour = Color.WHITE;
        }
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(95);
        Typeface clock = Typeface.createFromAsset(this.getAssets(),"emanee.ttf");
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




    private String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int second = c.get(Calendar.SECOND);

        return hour + ":" + minute + ":" + second ;
    }


}