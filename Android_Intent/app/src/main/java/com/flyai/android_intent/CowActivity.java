package com.flyai.android_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaDataSource;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CowActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnOK;
    private EditText edtSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow);

        btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            Intent CallIntent = getIntent();
            edtSound = (EditText) findViewById(R.id.editInputSound);
            CallIntent.putExtra("Animal_Sound", edtSound.getText().toString());
            setResult(RESULT_OK, CallIntent);
            finish();
        }
    }
}