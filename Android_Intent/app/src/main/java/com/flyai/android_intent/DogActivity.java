package com.flyai.android_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DogActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnOK;
    private EditText edtSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        btnOK = (Button) findViewById(R.id.btnOk2);
        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnOK) {
            Intent CallIntent = getIntent();
            edtSound = (EditText) findViewById(R.id.editDogSound);
            CallIntent.putExtra("Animal_Sound", edtSound.getText().toString());
            setResult(RESULT_OK, CallIntent);
            finish();
        }
    }
}