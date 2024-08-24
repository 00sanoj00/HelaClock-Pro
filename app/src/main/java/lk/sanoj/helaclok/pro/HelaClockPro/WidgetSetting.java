package lk.sanoj.helaclok.pro.HelaClockPro;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import ahmadrosid.com.lib.CustomTextView;
import lk.sanoj.helaclok.pro.HelaClockPro.Controller.SinhalaTimeConverter;
import lk.sanoj.helaclok.pro.HelaClockPro.Models.Clock;
import top.defaults.colorpicker.ColorPickerView;


public class WidgetSetting extends Activity {

    private CustomTextView textMeridiem;
    private CustomTextView textHour;
    private CustomTextView textMinutes;
    private Button btnHint;
    private ImageView hintImage;
    private Dialog dialog;
    private ColorPickerView colorPickerView;
    private EditText colorHex;
    private boolean hintStatus;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_widsetting);

        Button btnTextColor = findViewById(R.id.textcolour);
        textMeridiem = findViewById(R.id.textAM_PM);
        textHour = findViewById(R.id.textHour);
        textMinutes = findViewById(R.id.textMinutes);
        ImageView clockBG = findViewById(R.id.ivClockBG);
        ImageView backgroundImage = findViewById(R.id.backcolourimag);
        hintImage = findViewById(R.id.hintImage);
        Button clearSettings = findViewById(R.id.reset);
        btnHint = findViewById(R.id.clnoty);

        // Get User Preferences for Clock Theme
        SharedPreferences prefs = this.getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String themes = prefs.getString("ThemeName", null);
        int textColor = prefs.getInt("textc", 0);
        String initialHexColor = String.format("#%06X", (0xFFFFFF & textColor));

        if (themes != null) {
            switch (themes) {
                case "AssXSDrrfgssdbh":
                    // show.setImageResource(BLA);
                    break;
                case "WdhbgfhghhdwaSSDfgdfg":
                    clockBG.setImageResource(R.drawable.sndbackgrund);
                    break;
                case "HlknsdakjKJHfdskljhs":
                    clockBG.setImageResource(R.drawable.sndsback);
                    break;
                case "dAsdsQWdsfdSDfdsfS":
                    clockBG.setImageResource(R.drawable.lastbck);
                    break;
            }
        }

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

        dialog = new Dialog(WidgetSetting.this);
        dialog.setContentView(R.layout.color_picker);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        }
        dialog.setCancelable(true);

        Button btnDialogPick = dialog.findViewById(R.id.btnPick);
        colorPickerView = dialog.findViewById(R.id.colorPicker);
        colorHex = dialog.findViewById(R.id.colorHex);
        colorHex.setText(initialHexColor);
        colorPickerView.setInitialColor(textColor);

        btnDialogPick.setOnClickListener(view -> {
            int color = colorPickerView.getColor();
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            // codeview.setText(hexColor);
            textMeridiem.setTextColor(color);
            textHour.setTextColor(color);
            textMinutes.setTextColor(color);
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.putString("colour", hexColor);
            editor.putInt("textc", color);
            editor.apply();
            System.out.println("Color: " + hexColor);
            colorHex.setText(hexColor);
            dialog.dismiss();
            hintImage.setColorFilter(color);
            Snackbar.make(WidgetSetting.this.findViewById(R.id.linearLayout1), "Applied Color: " + hexColor, Snackbar.LENGTH_SHORT).show();
        });

        colorHex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String hexColor = editable.toString().trim();

                // #66e6ff

                if (hexColor.length() == 6) {
                    // Add the '#' symbol if it's missing
                    hexColor = "#" + hexColor;
                    // Validate the hex color
                    validateHexColor(hexColor);
                }

                if (hexColor.length() == 7 && hexColor.charAt(0) == '#') {
                    // Validate the hex color
                    validateHexColor(hexColor);
                }

            }
        });

        try {
            //code view
            // int textc = prefs.getInt("textc", 0);//maintext colour

            //background colour code text
            int hint = prefs.getInt("hint", 0);//hint
            int bcolour = prefs.getInt("bcolour", 0);//backgraund colour


            if (bcolour == 0) {
                backgroundImage.setBackgroundColor(Color.parseColor("#74000000"));
            } else {
                backgroundImage.setBackgroundColor(bcolour);
            }
            if (textColor == 0) {
                textMeridiem.setTextColor(Color.WHITE);
                textHour.setTextColor(Color.WHITE);
                textMinutes.setTextColor(Color.WHITE);
            } else {
                hintImage.setColorFilter(textColor);
                textMeridiem.setTextColor(textColor);
                textHour.setTextColor(textColor);
                textMinutes.setTextColor(textColor);
            }
            if (hint == 0) {
                hintImage.setVisibility(View.VISIBLE);
                btnHint.setText(R.string.turn_off_hint);
                hintStatus = true;
            } else if (hint == 1) {
                hintImage.setVisibility(View.INVISIBLE);
                btnHint.setText(R.string.turn_on_hint);
                hintStatus = false;
            }
        } catch (Exception ignored) {
        }

        btnTextColor.setOnClickListener(view -> dialog.show());

        clearSettings.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            try {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            } catch (Exception ignored) {

            }
        });

        btnHint.setOnClickListener(v -> {
            if (hintStatus) {
                btnHint.setText(R.string.turn_on_hint);

                hintStatus = false;

                hintImage.setVisibility(View.INVISIBLE);

                SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
                editor.putInt("hint", 1);
                editor.apply();

                Snackbar.make(WidgetSetting.this.findViewById(R.id.linearLayout1), "Hint Turned Off", Snackbar.LENGTH_SHORT).show();
            } else {
                btnHint.setText(R.string.turn_off_hint);

                hintStatus = true;

                hintImage.setVisibility(View.VISIBLE);

                SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
                editor.putInt("hint", 0);
                editor.apply();

                Snackbar.make(WidgetSetting.this.findViewById(R.id.linearLayout1), "Hint Turned On", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void validateHexColor(String hexColor) {
        if (isValidHexColor(hexColor)) {
            // Convert the hex color to int
            int color = Color.parseColor(hexColor);

            // Set the initial color in the ColorPickerView
            colorPickerView.setInitialColor(color);
        } else {
            // Handle the case where the hex color is not valid
            // You can display an error message or take any other appropriate action
            Toast.makeText(WidgetSetting.this, "Invalid hex color: " + hexColor, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidHexColor(String hexColor) {
        return hexColor.matches("#([A-Fa-f0-9]{6})");
    }

}