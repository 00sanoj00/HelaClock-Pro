package lk.sanoj.helaclok.pro.HelaClockPro;


import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import ahmadrosid.com.lib.CustomTextView;
import top.defaults.colorpicker.ColorPickerPopup;


public class widsetting extends Activity {

    private Button colotchange;
    private TextView codeview;
    private ImageView show;
    private CustomTextView textView4;
    private CustomTextView textView5;
    private CustomTextView textView6;
    private Button backgraund;
    private TextView backcode;
    private ImageView backimage;
    private Context context;
    private int bcolour;
    private int textc;
    private Switch onoff;
    private ImageView hintview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_widsetting);


        colotchange = (Button) findViewById(R.id.textcolour);
        codeview = (TextView) findViewById(R.id.codeview);
        textView4 = (CustomTextView)findViewById(R.id.textView4);
        textView5 = (CustomTextView)findViewById(R.id.textView5);
        textView6 = (CustomTextView)findViewById(R.id.textView6);
        show = (ImageView) findViewById(R.id.imageView);
        backgraund = (Button)findViewById(R.id.backgraund);
        backcode = (TextView)findViewById(R.id.back);
        backimage = (ImageView)findViewById(R.id.backcolourimag);
        onoff = (Switch)findViewById(R.id.switch1);
        hintview = (ImageView)findViewById(R.id.imageView5);





        try{

            SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            String colour = prefs.getString("colour", null);//codeview
            textc = prefs.getInt("textc", 0);//maintext colour

            String colourb2 = prefs.getString("b2colour", null);//backgrund colou code text
            String hint = prefs.getString("hint", null);//hint
            bcolour = prefs.getInt("bcolour", 0);//backgraund colour



            if (colour==null){
                codeview.setText("#FFFFFF");
            }else{
                codeview.setText(colour);
            }
            if (colourb2==null){
                backcode.setText("#74000000");
            }else {
                backcode.setText(colourb2);
            }
            if(bcolour==0){
                backimage.setBackgroundColor(Color.parseColor("#74000000"));
            }else {
                backimage.setBackgroundColor(bcolour);
            }
            if(textc==0){
                textView4.setTextColor(Color.WHITE);
                textView5.setTextColor(Color.WHITE);
                textView6.setTextColor(Color.WHITE);
            }else {
                textView4.setTextColor(textc);
                textView5.setTextColor(textc);
                textView6.setTextColor(textc);
            }
            if (hint==null){
                hintview.setVisibility(View.VISIBLE);
                onoff.setChecked(false);
            }else if(hint.equals("ON")) {
                onoff.setChecked(false);
                hintview.setVisibility(View.VISIBLE);
            }else if (hint.equals("OFF")){
                hintview.setVisibility(View.INVISIBLE);
                onoff.setChecked(true);
                onoff.setText("Hint OFF");
            }else {
                hintview.setVisibility(View.INVISIBLE);
                onoff.setChecked(true);
                onoff.setText("Hint OFF");
            }














        }catch (Exception O){



        }











        try{

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Drawable wallpaper = WallpaperManager
                    .getInstance(this)
                    .getFastDrawable();

            show.setBackground(wallpaper);

        }catch (Exception O){


        }

        backgraund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ColorPickerPopup.Builder(widsetting.this)
                        .initialColor(Color.TRANSPARENT) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show( new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {

                                String hexColor = String.format("#%06X", (0xFFFFFF & color));
                                backcode.setText(hexColor);

                                backimage.setBackgroundColor(color);

                                SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
                                editor.putInt("bcolour", color);
                                editor.putString("b2colour", hexColor);
                                editor.apply();


                            }

                        });


            }
        });







        colotchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ColorPickerPopup.Builder(widsetting.this)
                        .initialColor(Color.WHITE) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show( new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {

                                String hexColor = String.format("#%06X", (0xFFFFFF & color));
                                codeview.setText(hexColor);

                                textView4.setTextColor(color);
                                textView5.setTextColor(color);
                                textView6.setTextColor(color);

                                SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
                                editor.putString("colour", hexColor);
                                editor.putInt("textc", color);
                                editor.apply();


                            }

                        });


            }
        });


        onoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (onoff.isChecked()){
                    onoff.setText("Hint OFF");
                    hintview.setVisibility(View.INVISIBLE);
                    SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
                    editor.putString("hint", "OFF");
                    editor.apply();
                }else {
                    onoff.setText("Hint ON");
                    hintview.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
                    editor.putString("hint", "ON");
                    editor.apply();
                }
            }
        });


    }
}