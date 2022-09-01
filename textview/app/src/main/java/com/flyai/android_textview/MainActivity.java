package com.flyai.android_textview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        TextView objTV = new TextView(this);
        objTV.setText("Android Programming!");
        objTV.setTextSize(0x20);
        objTV.setGravity(0x11);
        objTV.setTextColor(0xFF0000FF);
        //objTV.setTextColor(Color.RED);
        setContentView(objTV);
    }
}