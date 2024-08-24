package lk.sanoj.helaclok.pro.HelaClockPro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import top.defaults.colorpicker.ColorPickerView;

public class CPicker extends Activity {
    ColorPickerView colorPickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker);

        colorPickerView = findViewById(R.id.colorPicker);
        Button btnPick = findViewById(R.id.btnPick);
        final EditText colorHex = findViewById(R.id.colorHex);

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int color = colorPickerView.getColor();
                String hexColor = String.format("#%06X", (0xFFFFFF & color));
                colorHex.setText(hexColor);
                System.out.println("Color: " + hexColor);
            }
        });

    }
}
