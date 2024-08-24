package lk.sanoj.helaclok.pro.HelaClockPro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import lk.sanoj.helaclok.pro.HelaClockPro.Controller.SinhalaTimeConverter;
import lk.sanoj.helaclok.pro.HelaClockPro.Models.Clock;

public class section extends AppCompatActivity {
    // private RemoteViews views;
    private Dialog dialog;
    private CheckBox cbBlank;
    private CheckBox cbPattern;
    private CheckBox cbGrunge;
    private CheckBox cbRock;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        Button btn2 = findViewById(R.id.button3);

        final TextView textMeridiem = findViewById(R.id.textAM_PM);
        final TextView textHour = findViewById(R.id.textHour);
        final TextView textMinutes = findViewById(R.id.textMinutes);
        final ImageView hintImage = findViewById(R.id.hintImage);

        final Clock clock = new Clock();
        SinhalaTimeConverter converter = new SinhalaTimeConverter(clock.getTime());

        textHour.setText(converter.getHour());
        textMinutes.setText(converter.getMinutes());
        textMeridiem.setText(converter.getMeridiem());

        // Update the clock
        // Update UI elements
        // Update image based on time
        // Schedule the next update
        // Repeat every second
        Runnable updateTask = new Runnable() {
            @Override
            public void run() {
                try {
                    clock.updateTime(); // Update the clock
                    SinhalaTimeConverter converter1 = new SinhalaTimeConverter(clock.getTime());

                    Log.d("Meridiem", clock.getTime().getMeridiem());

                    // Update UI elements
                    textHour.setText(converter1.getHour());
                    textMinutes.setText(converter1.getMinutes());
                    textMeridiem.setText(converter1.getMeridiem());

                    // Update image based on time
                    if (clock.getTime().getMeridiem().equals("PM")) {
                        hintImage.setImageResource(R.drawable.night);
                    } else {
                        hintImage.setImageResource(R.drawable.morningimg);
                    }
                } catch (Exception e) {
                    Log.e("Error in section.java", e.getMessage(), e);
                }

                // Schedule the next update
                handler.postDelayed(this, 1000); // Repeat every second
            }
        };

        // Start the periodic task
        handler.post(updateTask);

        /*Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(() -> {
                            try {
                                clock.updateTime();
                                // clock.setTime("11:30:AM");
                                SinhalaTimeConverter converter1 = new SinhalaTimeConverter(clock.getTime());

                                Log.d("Meridiem", clock.getTime().getMeridiem());

                                textHour.setText(converter1.getHour());
                                textMinutes.setText(converter1.getMinutes());
                                textMeridiem.setText(converter1.getMeridiem());
                                if (clock.getTime().getMeridiem().equals("PM")) {
                                    hintImage.setImageResource(R.drawable.night);
                                } else {
                                    hintImage.setImageResource(R.drawable.morningimg);
                                }
                            } catch (Exception e) {
                                Log.e("Error in section.java", Objects.requireNonNull(e.getMessage()));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    Log.e("Error in section.java", Objects.requireNonNull(e.getMessage()));
                }
            }
        };

        t.start();*/

        // TODO: Make the hint image changeable.
        // TODO: Enable disable the hint image according to user preferences

        btn2.setOnClickListener(v -> {
            Intent mainIntent = new Intent(section.this, WidgetSetting.class);
            section.this.startActivity(mainIntent);
        });

        createDialog();
    }

    private void createDialog() {
        dialog = new Dialog(section.this);
        dialog.setContentView(R.layout.clock_settings);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        }
        dialog.setCancelable(true);

        // Set CheckBoxes
        cbBlank = dialog.findViewById(R.id.checkBoxBlank);
        cbPattern = dialog.findViewById(R.id.checkBoxPattern);
        cbGrunge = dialog.findViewById(R.id.checkBoxGrunge);
        cbRock = dialog.findViewById(R.id.checkBoxRock);

        // Set User Settings
        try {
            SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
            String themes = prefs.getString("ThemeName", null);

            if (themes != null) {
                switch (themes) {
                    case "AssXSDrrfgssdbh":
                        cbBlank.setChecked(true);
                        break;
                    case "WdhbgfhghhdwaSSDfgdfg":
                        cbPattern.setChecked(true);
                        break;
                    case "HlknsdakjKJHfdskljhs":
                        cbGrunge.setChecked(true);
                        break;
                    case "dAsdsQWdsfdSDfdsfS":
                        cbRock.setChecked(true);
                        break;
                }
            }
        } catch (Exception e) {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.putString("ThemeName", "AssXSDrrfgssdbh");
            editor.apply();
            cbBlank.setChecked(true);
        }
    }

    public void clockSettings(View view) {
        Log.d("Clock Settings", "Clock Settings");
        dialog.show();
    }

    public void clockSettingBlank(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "AssXSDrrfgssdbh");
        editor.apply();
        cbPattern.setChecked(false);
        cbGrunge.setChecked(false);
        cbRock.setChecked(false);
    }

    public void clockSettingPattern(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "WdhbgfhghhdwaSSDfgdfg");
        editor.apply();
        cbBlank.setChecked(false);
        cbGrunge.setChecked(false);
        cbRock.setChecked(false);
    }

    public void clockSettingGrunge(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "HlknsdakjKJHfdskljhs");
        editor.apply();
        cbBlank.setChecked(false);
        cbPattern.setChecked(false);
        cbRock.setChecked(false);
    }

    public void clockSettingRock(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
        editor.putString("ThemeName", "dAsdsQWdsfdSDfdsfS");
        editor.apply();
        cbBlank.setChecked(false);
        cbPattern.setChecked(false);
        cbGrunge.setChecked(false);
    }

    public void onSaveChanges(View view) {
        if (cbBlank.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.putString("ThemeName", "AssXSDrrfgssdbh");
            editor.apply();
            cbPattern.setChecked(false);
            cbGrunge.setChecked(false);
            cbRock.setChecked(false);
            Snackbar.make(section.this.findViewById(R.id.linearLayout), "Applied Setting: Blank", Snackbar.LENGTH_SHORT).show();
        } else if (cbPattern.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.putString("ThemeName", "WdhbgfhghhdwaSSDfgdfg");
            editor.apply();
            cbBlank.setChecked(false);
            cbGrunge.setChecked(false);
            cbRock.setChecked(false);
            Snackbar.make(section.this.findViewById(R.id.linearLayout), "Applied Setting: Pattern", Snackbar.LENGTH_SHORT).show();
        } else if (cbGrunge.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.putString("ThemeName", "HlknsdakjKJHfdskljhs");
            editor.apply();
            cbBlank.setChecked(false);
            cbPattern.setChecked(false);
            cbRock.setChecked(false);
            Snackbar.make(section.this.findViewById(R.id.linearLayout), "Applied Setting: Grunge", Snackbar.LENGTH_SHORT).show();
        } else if (cbRock.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.putString("ThemeName", "dAsdsQWdsfdSDfdsfS");
            editor.apply();
            cbBlank.setChecked(false);
            cbPattern.setChecked(false);
            cbGrunge.setChecked(false);
            Snackbar.make(section.this.findViewById(R.id.linearLayout), "Applied Setting: Rock Wall", Snackbar.LENGTH_SHORT).show();
        }

        dialog.dismiss();
    }

    public void checkBoxBlank(View view) {
        cbPattern.setChecked(false);
        cbGrunge.setChecked(false);
        cbRock.setChecked(false);
    }

    public void checkBoxPattern(View view) {
        cbBlank.setChecked(false);
        cbGrunge.setChecked(false);
        cbRock.setChecked(false);
    }

    public void checkBoxGrunge(View view) {
        cbBlank.setChecked(false);
        cbPattern.setChecked(false);
        cbRock.setChecked(false);
    }

    public void checkBoxRock(View view) {
        cbBlank.setChecked(false);
        cbPattern.setChecked(false);
        cbGrunge.setChecked(false);
    }

    public void startClock(View view) {
        startActivity(new Intent(section.this, clock.class));
    }
}
