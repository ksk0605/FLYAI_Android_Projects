package com.flyai.environmentsensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager objSMG;
    Sensor sensor_Ambient_Pressure;
    Sensor sensor_Ambient_Temperature;
    Sensor sensor_Temperature;
    Sensor sensor_Illuminance;

    TextView objTV_Ambient_Pressure;
    TextView objTV_Ambient_Temperature;
    TextView objTV_Temperature;
    TextView objTV_Illuminance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_Ambient_Pressure = objSMG.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensor_Ambient_Temperature = objSMG.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensor_Temperature = objSMG.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        sensor_Illuminance = objSMG.getDefaultSensor(Sensor.TYPE_LIGHT);

        objTV_Ambient_Pressure = (TextView) findViewById(R.id.txtValue_AP);
        objTV_Ambient_Temperature = (TextView) findViewById(R.id.txtValue_AT);
        objTV_Temperature = (TextView) findViewById(R.id.txtValue_T);
        objTV_Illuminance = (TextView) findViewById(R.id.txtValue_I);
    }

    @Override
    public void onResume(){
        super.onResume();
        objSMG.registerListener(this, sensor_Ambient_Pressure, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Ambient_Temperature, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Temperature, SensorManager.SENSOR_DELAY_NORMAL);
        objSMG.registerListener(this, sensor_Illuminance, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this);

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_PRESSURE:
                objTV_Ambient_Pressure.setText("Ambient Pressure: " + event.values[0]);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                objTV_Ambient_Temperature.setText("Ambient Temperature: " + event.values[0]);
                break;
            case Sensor.TYPE_TEMPERATURE:
                objTV_Temperature.setText("Temperature: " + event.values[0]);
                break;
            case Sensor.TYPE_LIGHT:
                objTV_Illuminance.setText("Illuminance: " + event.values[0]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}