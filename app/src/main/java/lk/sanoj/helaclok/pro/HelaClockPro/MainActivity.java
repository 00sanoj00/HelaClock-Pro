package lk.sanoj.helaclok.pro.HelaClockPro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox ch1;
    CheckBox ch2;
    CheckBox ch3;
    CheckBox ch4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);


        ch1 = (CheckBox) findViewById(R.id.checkBox);
        ch2 = (CheckBox) findViewById(R.id.checkBox2);
        ch3 = (CheckBox) findViewById(R.id.checkBox3);
        ch4 = (CheckBox) findViewById(R.id.checkBox4);
        final Button stat = (Button) findViewById(R.id.button2);
        TextView tx1 = (TextView) findViewById(R.id.textView);
        TextView tx2 = (TextView) findViewById(R.id.textView2);

        Typeface fontHindi = Typeface.createFromAsset(getAssets(),
                "fonts/emanee.ttf");
        stat.setTypeface(fontHindi);
        tx1.setTypeface(fontHindi);
        tx2.setTypeface(fontHindi);






        try {

            SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            String themes = prefs.getString("ThemeName", null);


            if (themes.equals("AssXSDrrfgssdbh")){
                ch1.setChecked(true);
            }
            else if (themes.equals("WdhbgfhghhdwaSSDfgdfg")){
                ch2.setChecked(true);
            }
            else if (themes.equals("HlknsdakjKJHfdskljhs")){
                ch3.setChecked(true);
            }
            else if (themes.equals("dAsdsQWdsfdSDfdsfS")){
                ch4.setChecked(true);
            }

        } catch (Exception e) {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.putString("ThemeName", "AssXSDrrfgssdbh");
            editor.apply();
            ch1.setChecked(true);

        }


       // btn.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent open = new Intent(MainActivity.this, widsetting.class);
        ///        startActivity(open);
         //   }
       // });



    }

    public void aaaa (View v){
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "AssXSDrrfgssdbh");
        editor.apply();
        ch2.setChecked(false);
        ch3.setChecked(false);
        ch4.setChecked(false);


    }

    public void bbbb (View v){
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "WdhbgfhghhdwaSSDfgdfg");
        editor.apply();
        ch1.setChecked(false);
        ch3.setChecked(false);
        ch4.setChecked(false);


    }
    public void cccc (View v){
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "HlknsdakjKJHfdskljhs");
        editor.apply();
        ch1.setChecked(false);
        ch2.setChecked(false);
        ch4.setChecked(false);


    }
    public void dddd (View v){
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "dAsdsQWdsfdSDfdsfS");
        editor.apply();
        ch1.setChecked(false);
        ch2.setChecked(false);
        ch3.setChecked(false);

    }

    public void bbb (View v){

        startActivity(new Intent(MainActivity.this, clock.class));
    }
    public void getfont(){
        Typeface fontHindi = Typeface.createFromAsset(getAssets(),
                "fonts/emanee.ttf");
    }
}