package lk.sanoj.helaclok.pro.HelaClockPro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class clock extends AppCompatActivity {

    ConstraintLayout layt;
    TextView hh;
    TextView mm;
    TextView amp;

    TextView TextView7;
    TextView TextView8;
    TextView TextView11;


    String ampm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_clock);



        layt = (ConstraintLayout)findViewById(R.id.mnl);
        hh = (TextView)findViewById(R.id.HH);
        mm = (TextView)findViewById(R.id.MM);
        amp = (TextView)findViewById(R.id.AMP);


        TextView7 = (TextView)findViewById(R.id.textView7);
        TextView8 = (TextView)findViewById(R.id.textView8);
        TextView11 = (TextView)findViewById(R.id.textView11);


        Typeface fontHindi = Typeface.createFromAsset(getAssets(),
                "fonts/emanee.ttf");
        TextView7.setTypeface(fontHindi);
        TextView8.setTypeface(fontHindi);
        TextView11.setTypeface(fontHindi);






        try {

            SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            String themes = prefs.getString("ThemeName", null);


            if (themes.equals("AssXSDrrfgssdbh")){
                layt.setBackgroundColor(Color.BLACK);
            }
            else if (themes.equals("WdhbgfhghhdwaSSDfgdfg")){
                layt.setBackgroundResource(R.drawable.sndbackgrund);
            }
            else if (themes.equals("HlknsdakjKJHfdskljhs")){
                layt.setBackgroundResource(R.drawable.sndsback);
            }
            else if (themes.equals("dAsdsQWdsfdSDfdsfS")){
                layt.setBackgroundResource(R.drawable.lastbck);
            }

        } catch (Exception e) {


        }

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /////////////////////////////////////////////////////////////////
                                String datem = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
                                mm.setText(datem);
                                ///////////////////////////////////////////////////////////////
                                /////////////////////////////////////////////////////////////////
                                String date = new SimpleDateFormat("hh").format(Calendar.getInstance().getTime());
                                hh.setText(date);

                                /////////////////////////////////////////////////////////////////
                                /////////////////////////////////////////////////////////////////
                                String datean = new SimpleDateFormat("a").format(Calendar.getInstance().getTime());
                                amp.setText(datean);
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////

                                if(datean.equals("am")){

                                    TextView7.setText("වේලාව පෙරවරු");
                                }
                                if(datean.equals("AM")){

                                    TextView7.setText("වේලාව පෙරවරු");
                                }
                                else if (datean.equals("pm")){

                                    TextView7.setText("වේලාව පස්වරු");
                                }
                                else if (datean.equals("PM")){

                                    TextView7.setText("වේලාව පස්වරු");
                                }
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////

                                if(date.equals("01")){
                                    TextView8.setText("එක පසුවී");
                                }
                                else if (date.equals("1")){
                                    TextView8.setText("එක පසුවී");
                                }
                                else if (date.equals("02")){
                                    TextView8.setText("දෙක පසුවී");
                                }
                                else if (date.equals("2")){
                                    TextView8.setText("දෙක පසුවී");
                                }
                                else if (date.equals("03")){
                                    TextView8.setText("තුන පසුවී");
                                }
                                else if (date.equals("3")){
                                    TextView8.setText("තුන පසුවී");
                                }
                                else if (date.equals("04")){
                                    TextView8.setText("හතර පසුවී");
                                }
                                else if (date.equals("4")){
                                    TextView8.setText("හතර පසුවී");
                                }
                                else if (date.equals("05")){
                                    TextView8.setText("පහ පසුවී");
                                }
                                else if (date.equals("5")){
                                    TextView8.setText("පහ පසුවී");
                                }
                                else if (date.equals("06")){
                                    TextView8.setText("හය පසුවී");
                                }
                                else if (date.equals("6")){
                                    TextView8.setText("හය පසුවී");
                                }
                                else if (date.equals("07")){
                                    TextView8.setText("හත පසුවී");
                                }
                                else if (date.equals("7")){
                                    TextView8.setText("හත පසුවී");
                                }
                                else if (date.equals("08")){
                                    TextView8.setText("අට පසුවී");
                                }
                                else if (date.equals("8")){
                                    TextView8.setText("අට පසුවී");
                                }
                                else if (date.equals("09")){
                                    TextView8.setText("නවය පසුවී");
                                }
                                else if (date.equals("9")){
                                    TextView8.setText("නවය පසුවී");
                                }
                                else if (date.equals("10")){
                                    TextView8.setText("දහය පසුවී");
                                }
                                else if (date.equals("11")){
                                    TextView8.setText("එකොළහ පසුවී");
                                }
                                else if (date.equals("12")) {
                                    TextView8.setText("දොළහ පසුවී");
                                }
                                else if (date.equals("0")) {
                                    TextView8.setText("දොළහ පසුවී");
                                }
                                else if (date.equals("00")) {
                                    TextView8.setText("දොළහ පසුවී");
                                }

                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////
                                if (datem.equals("0")){
                                    TextView11.setText("තත්පර කීපයකි");
                                }
                                else if (datem.equals("00")){
                                    TextView11.setText("තත්පර කීපයකි");

                                }
                                else if (datem.equals("01")){

                                    TextView11.setText("විනාඩි එකයි");
                                }
                                else if (datem.equals("1")){

                                    TextView11.setText("විනාඩි එකයි");
                                }
                                else if (datem.equals("02")){

                                    TextView11.setText("විනාඩි දෙකයි");
                                }
                                else if (datem.equals("2")){
                                    TextView11.setText("විනාඩි දෙකයි");

                                }
                                else if (datem.equals("03")){

                                    TextView11.setText("විනාඩි තුනයි");
                                }
                                else if (datem.equals("3")){

                                    TextView11.setText("විනාඩි තුනයි");
                                }
                                else if (datem.equals("04")){

                                    TextView11.setText("විනාඩි හතරයි");
                                }
                                else if (datem.equals("4")){

                                    TextView11.setText("විනාඩි හතරයි");
                                }
                                else if (datem.equals("05")){

                                    TextView11.setText("විනාඩි පහයි");
                                }
                                else if (datem.equals("5")){

                                    TextView11.setText("විනාඩි පහයි");
                                }
                                else if (datem.equals("06")){
                                    TextView11.setText("විනාඩි හයයි");

                                }
                                else if (datem.equals("6")){

                                    TextView11.setText("විනාඩි හයයි");
                                }
                                else if (datem.equals("07")){
                                    TextView11.setText("විනාඩි හතයි");

                                }
                                else if (datem.equals("7")){

                                    TextView11.setText("විනාඩි හතයි");
                                }
                                else if (datem.equals("08")){

                                    TextView11.setText("විනාඩි අටයි");
                                }
                                else if (datem.equals("8")){
                                    TextView11.setText("විනාඩි අටයි");

                                }
                                else if (datem.equals("09")){

                                    TextView11.setText("විනාඩි නවයයි");
                                }
                                else if (datem.equals("9")){
                                    TextView11.setText("විනාඩි නවයයි");

                                }
                                else if (datem.equals("10")){

                                    TextView11.setText("විනාඩි දහයයි");
                                }
                                else if (datem.equals("11")){

                                    TextView11.setText("විනාඩි එකොළහයි");
                                }
                                else if (datem.equals("12")){
                                    TextView11.setText("විනාඩි දොළහයි");

                                }
                                else if (datem.equals("13")){

                                    TextView11.setText("විනාඩි දහ තුනයි");
                                }
                                else if (datem.equals("14")){

                                    TextView11.setText("විනාඩි දහ හතරයි");
                                }
                                else if (datem.equals("15")){
                                    TextView11.setText("විනාඩි පහ ලොවයි");

                                }
                                else if (datem.equals("16")){
                                    TextView11.setText("විනාඩි දහ සයයි");

                                }
                                else if (datem.equals("17")){

                                    TextView11.setText("විනාඩි දහ හතයි");
                                }
                                else if (datem.equals("18")){
                                    TextView11.setText("විනාඩි දහ අටයි");

                                }
                                else if (datem.equals("19")){
                                    TextView11.setText("විනාඩි දහ නවයයි");

                                }
                                else if (datem.equals("20")){

                                    TextView11.setText("විනාඩි විස්සයි");
                                }
                                else if (datem.equals("21")){
                                    TextView11.setText("විනාඩි විසීඑකයි");

                                }
                                else if (datem.equals("22")){

                                    TextView11.setText("විනාඩි විසිදෙකයි");
                                }
                                else if (datem.equals("23")){

                                    TextView11.setText("විනාඩි විසිතුනයී");
                                }
                                else if (datem.equals("24")){
                                    TextView11.setText("විනාඩි විසිහතරයි");

                                }
                                else if (datem.equals("25")){

                                    TextView11.setText("විනාඩි විසිපහයි");
                                }
                                else if (datem.equals("26")){

                                    TextView11.setText("විනාඩි විසිහයයි");
                                }
                                else if (datem.equals("27")){

                                    TextView11.setText("විනාඩි විසිහතයි");
                                }
                                else if (datem.equals("28")){
                                    TextView11.setText("විනාඩි විසිඅටයි");

                                }
                                else if (datem.equals("29")){
                                    TextView11.setText("විනාඩි විසිනවයයි");

                                }
                                else if (datem.equals("30")){

                                    TextView11.setText("විනාඩි තිහයි");
                                }
                                else if (datem.equals("31")){

                                    TextView11.setText("විනාඩි තිස්එකයි");
                                }
                                else if (datem.equals("32")){

                                    TextView11.setText("විනාඩි තිස්දෙකයි");
                                }
                                else if (datem.equals("33")){

                                    TextView11.setText("විනාඩි තිස්තුනයි");
                                }
                                else if (datem.equals("34")){

                                    TextView11.setText("විනාඩි තිස්හතරයි");
                                }
                                else if (datem.equals("35")){

                                    TextView11.setText("විනාඩි තිස්පහයි");
                                }
                                else if (datem.equals("36")){

                                    TextView11.setText("විනාඩි තිස්හයයි");
                                }
                                else if (datem.equals("37")){

                                    TextView11.setText("විනාඩි තිස්හතයි");
                                }
                                else if (datem.equals("38")){
                                    TextView11.setText("විනාඩි තිස්අටයි");

                                }
                                else if (datem.equals("39")){

                                    TextView11.setText("විනාඩි තිස්නවයයි");
                                }
                                else if (datem.equals("40")){

                                    TextView11.setText("විනාඩි හතලිහයි");
                                }
                                else if (datem.equals("41")){

                                    TextView11.setText("විනාඩි හතලිස් එකයි");
                                }
                                else if (datem.equals("42")){

                                    TextView11.setText("විනාඩි හතලිස් දෙකයි");
                                }
                                else if (datem.equals("43")){

                                    TextView11.setText("විනාඩි හතලිස් තුනයි");
                                }
                                else if (datem.equals("44")){

                                    TextView11.setText("විනාඩි හතලිස් හතරයි");
                                }
                                else if (datem.equals("45")){
                                    TextView11.setText("විනාඩි හතලිස් පහයි");

                                }
                                else if (datem.equals("46")){

                                    TextView11.setText("විනාඩි හතලිස් හයයි");
                                }
                                else if (datem.equals("47")){

                                    TextView11.setText("විනාඩි හතලිස් හතයි");
                                }
                                else if (datem.equals("48")){
                                    TextView11.setText("විනාඩි හතලිස් අටයි");

                                }
                                else if (datem.equals("49")){

                                    TextView11.setText("විනාඩි හතලිස් නවයයි");
                                }
                                else if (datem.equals("50")){

                                    TextView11.setText("විනාඩි පනහයි");
                                }
                                else if (datem.equals("51")){
                                    TextView11.setText("විනාඩි පනස් එකයි");

                                }
                                else if (datem.equals("52")){
                                    TextView11.setText("විනාඩි පනස් දෙකයි");

                                }
                                else if (datem.equals("53")){

                                    TextView11.setText("විනාඩි පනස් තුනයි");
                                }
                                else if (datem.equals("54")){

                                    TextView11.setText("විනාඩි පනස් හතරයි");
                                }
                                else if (datem.equals("55")){

                                    TextView11.setText("විනාඩි පනස් පහයි");
                                }
                                else if (datem.equals("56")){
                                    TextView11.setText("විනාඩි පනස් හයයි");

                                }
                                else if (datem.equals("57")){
                                    TextView11.setText("විනාඩි පනස් හතයි");

                                }
                                else if (datem.equals("58")){
                                    TextView11.setText("විනාඩි පනස් අටයි");

                                }
                                else if (datem.equals("59")){

                                    TextView11.setText("විනාඩි පනස් නවයයි");
                                }


                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }
}