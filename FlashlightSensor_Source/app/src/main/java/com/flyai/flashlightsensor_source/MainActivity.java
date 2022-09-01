package com.flyai.flashlightsensor_source;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager objSMG;
    Sensor sensor_Illuminance;

//    Camera objCAM;
//    Camera.Parameters objCamPara;

    CameraManager mCameraManager;
    ImageButton mImageButton;

    TextView objTV;
    ImageView objIV_Flashlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        objSMG = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_Illuminance = objSMG.getDefaultSensor(Sensor.TYPE_LIGHT);

//        objCAM = Camera.open();
        objIV_Flashlight = (ImageView) findViewById(R.id.imageView);
        objTV = (TextView) findViewById(R.id.textView);

        mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        objSMG.registerListener(this, sensor_Illuminance, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause(){
        super.onPause();
        objSMG.unregisterListener(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
//        objCAM.release();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            if (event.values[0] > 80) {
                objIV_Flashlight.setImageResource(R.drawable.on);
                objTV.setText("Lux: " + event.values[0]);
//                objCamPara = objCAM.getParameters();
//                objCamPara.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//                objCAM.setParameters(objCamPara);
//                objCAM.startPreview();
            } else {
                objIV_Flashlight.setImageResource(R.drawable.off);
                objTV.setText("Lux: " + event.values[0]);
//                objCamPara = objCAM.getParameters();
//                objCamPara.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//                objCAM.setParameters(objCamPara);
//                objCAM.startPreview();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}