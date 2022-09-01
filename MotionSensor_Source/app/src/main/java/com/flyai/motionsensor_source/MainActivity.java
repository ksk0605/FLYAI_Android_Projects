package com.flyai.motionsensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager objSMG;
    Sensor sensor_Gravity;
    Sensor sensor_Accelerometer;
    Sensor sensor_LinearAcceleration;
    Sensor sensor_Gyroscope;

    TextView objTV_X_Gravity, objTV_Y_Gravity, objTV_Z_Gravity;
    TextView objTV_X_Accelerometer, objTV_Y_Accelerometer, objTV_Z_Accelerometer;
    TextView objTV_X_LinearAcceleration, objTV_Y_LinearAcceleration, objTV_Z_LinearAcceleration;

    TextView objTV_X_Gyroscope;
    TextView objTV_Y_Gyroscope;
    TextView objTV_Z_Gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_Gravity = objSMG.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor_Accelerometer = objSMG.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_LinearAcceleration = objSMG.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensor_Gyroscope = objSMG.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        objTV_X_Gravity = (TextView) findViewById(R.id.txtX);
        objTV_Y_Gravity = (TextView) findViewById(R.id.txtY);
        objTV_Z_Gravity = (TextView) findViewById(R.id.txtZ);

        objTV_X_Accelerometer = (TextView) findViewById(R.id.txtX_ACC);
        objTV_Y_Accelerometer = (TextView) findViewById(R.id.txtY_ACC);
        objTV_Z_Accelerometer = (TextView) findViewById(R.id.txtZ_ACC);

        objTV_X_LinearAcceleration = (TextView) findViewById(R.id.txtX_LACC);
        objTV_Y_LinearAcceleration = (TextView) findViewById(R.id.txtY_LACC);
        objTV_Z_LinearAcceleration = (TextView) findViewById(R.id.txtZ_LACC);

        objTV_X_Gyroscope = (TextView) findViewById(R.id.txtX_Gyro);
        objTV_Y_Gyroscope = (TextView) findViewById(R.id.txtY_Gyro);
        objTV_Z_Gyroscope = (TextView) findViewById(R.id.txtZ_Gyro);
    }

    public void onResume() {
        super.onResume();

        //Register Listener for changing sensor value
        objSMG.registerListener(this, sensor_Gravity, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_LinearAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        objSMG.unregisterListener(this); // Release sensor Listener
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_GRAVITY:
                objTV_X_Gravity.setText("X: " + event.values[0]);
                objTV_Y_Gravity.setText("Y: " + event.values[1]);
                objTV_Z_Gravity.setText("Z: " + event.values[2]);
                break;

            case Sensor.TYPE_ACCELEROMETER:
                objTV_X_Accelerometer.setText("X: " + event.values[0]);
                objTV_Y_Accelerometer.setText("Y: "+ event.values[1]);
                objTV_Z_Accelerometer.setText("Z: "+ event.values[2]);
                break;

            case Sensor.TYPE_LINEAR_ACCELERATION:
                objTV_X_LinearAcceleration.setText("X: " + event.values[0]);
                objTV_Y_LinearAcceleration.setText("Y: "+ event.values[1]);
                objTV_Z_LinearAcceleration.setText("Z: "+ event.values[2]);
                break;

            case Sensor.TYPE_GYROSCOPE:
                objTV_X_Gyroscope.setText("X: " + event.values[0]);
                objTV_Y_Gyroscope.setText("Y: "+ event.values[1]);
                objTV_Z_Gyroscope.setText("Z: "+ event.values[2]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Call when sensor accuracy changed
    }
}