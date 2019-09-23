package lk.sanoj.helaclok.pro.HelaClockPro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class section extends AppCompatActivity {

    private Button btn1 , btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        btn1 = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(section.this, MainActivity.class);
                section.this.startActivity(mainIntent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(section.this, widsetting.class);
                section.this.startActivity(mainIntent);
            }
        });


    }
}
