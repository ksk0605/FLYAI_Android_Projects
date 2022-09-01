package com.flyai.positionsensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager objSMG;
    Sensor sensor_Orientation;
    Sensor sensor_Magnetic_Field;
    Sensor sensor_Proximity;

    TextView objTV_Azimuth;
    TextView objTV_Pitch;
    TextView objTV_Roll;

    TextView objTV_X;
    TextView objTV_Y;
    TextView objTV_Z;

    TextView objTV_Value_Proximity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor_Orientation = objSMG.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensor_Magnetic_Field = objSMG.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensor_Proximity = objSMG.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        objTV_Azimuth = (TextView) findViewById(R.id.txtX_Orientation);
        objTV_Pitch = (TextView) findViewById(R.id.txtY_Orientation);
        objTV_Roll = (TextView) findViewById(R.id.txtZ_Orientation);

        objTV_X = (TextView) findViewById(R.id.txtX_Magnetic_Field);
        objTV_Y = (TextView) findViewById(R.id.txtY_Magnetic_Field);
        objTV_Z = (TextView) findViewById(R.id.txtZ_Magnetic_Field);

        objTV_Value_Proximity = (TextView) findViewById(R.id.txtValue_Proximity);
    }

    @Override
    public void onResume(){
        super.onResume();

        //Resister listener for changing sensor value
        objSMG.registerListener(this, sensor_Orientation, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Magnetic_Field, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Proximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this); // Release sensor Listener
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION:
                objTV_Azimuth.setText("Azimuth: " + event.values[0]);
                objTV_Pitch.setText("Pitch: " + event.values[1]);
                objTV_Roll.setText("Roll: " + event.values[2]);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                objTV_X.setText("X: " + event.values[0]);
                objTV_Y.setText("Y: " + event.values[1]);
                objTV_Z.setText("Z: " + event.values[2]);
                break;
            case Sensor.TYPE_PROXIMITY:
                objTV_Value_Proximity.setText(" " + event.values[0]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Call when sensor accuracy changed
    }
}