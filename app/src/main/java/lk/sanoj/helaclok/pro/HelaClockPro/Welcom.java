package lk.sanoj.helaclok.pro.HelaClockPro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.onesignal.OneSignal;
import com.yayandroid.rotatable.Rotatable;


public class Welcom extends AppCompatActivity {

    private final int ANIM_DURATION = 5000;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        setContentView(R.layout.activity_welcom);


        handler = new Handler();
        runAnimationOn(R.id.imageView8, Rotatable.ROTATE_Y, 3600, 300);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                Intent mainIntent = new Intent(Welcom.this, section.class);
                Welcom.this.startActivity(mainIntent);
                Welcom.this.finish();


            }
        }, 5500);



    }
    private void runAnimationOn(final int resId, final int direction, final int degree, int delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Rotatable rotatable = new Rotatable.Builder(findViewById(resId))
                        .direction(Rotatable.ROTATE_BOTH)
                        .build();
                rotatable.rotate(direction, degree, ANIM_DURATION);
            }
        }, delay);
    }
}
