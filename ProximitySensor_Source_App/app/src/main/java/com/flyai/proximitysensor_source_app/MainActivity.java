package com.flyai.proximitysensor_source_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Animatable;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager objSMG;
    Sensor sensor_Proximity;

    ImageView objIV_Dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_Proximity = objSMG.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        objIV_Dog = (ImageView) findViewById(R.id.ivDog);
    }

    @Override
    public void onResume(){
        super.onResume();
        objSMG.registerListener(this, sensor_Proximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] <= 5) {
                Animation objANI = AnimationUtils.loadAnimation((this), R.anim.jumping);
                objIV_Dog.setImageResource(R.drawable.barkingdog);
                objIV_Dog.startAnimation(objANI);
                Vibrator objVIB = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                objVIB.vibrate(1000);
            } else {
                objIV_Dog.setImageResource(R.drawable.cutedog);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}