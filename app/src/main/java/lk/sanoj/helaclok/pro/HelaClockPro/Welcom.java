package lk.sanoj.helaclok.pro.HelaClockPro;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.onesignal.OneSignal;
import com.yayandroid.rotatable.Rotatable;


public class Welcom extends AppCompatActivity {

    private final int ANIM_DURATION = 1000;
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
    runAnimationOn();

    new Handler().postDelayed(() -> {
        Intent mainIntent = new Intent(Welcom.this, section.class);
        Welcom.this.startActivity(mainIntent);
        Welcom.this.finish();
    }, 5500);
}

private void runAnimationOn() {
    handler.postDelayed(() -> {
        ImageView imageView = findViewById(R.id.imageView8);
        TextView welcomeText = findViewById(R.id.welcomeText);
        TextView welcomeSubText = findViewById(R.id.welcomeSubText);

        // Create a translation animation
        ObjectAnimator moveUpAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0f, -200f);
        moveUpAnimator.setDuration(ANIM_DURATION);
        moveUpAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        // Create a fade-in animation for the TextView
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(welcomeText, "alpha", 0f, 1f);
        ObjectAnimator fadeInAnimator2 = ObjectAnimator.ofFloat(welcomeSubText, "alpha", 0f, 1f);
        fadeInAnimator.setDuration(ANIM_DURATION);
        fadeInAnimator.setInterpolator(new AccelerateInterpolator());
        fadeInAnimator2.setDuration(ANIM_DURATION);
        fadeInAnimator2.setInterpolator(new AccelerateInterpolator());

        // Combine both animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(moveUpAnimator, fadeInAnimator, fadeInAnimator2);

        animatorSet.start();
    }, 300);
}
}
