package com.example.andreibacalu.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button) v).setText(((Button) v).getText().toString() + ((Button) v).getText());
            }
        });
    }
}
