package com.example.user.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorMgrAccelerometer,sensorMgrLight;
    TextView showAccelerometer,showLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorMgrAccelerometer = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorMgrLight = (SensorManager) getSystemService(SENSOR_SERVICE);

        showAccelerometer = (TextView) findViewById(R.id.accelerometer);
        showLight = (TextView) findViewById(R.id.light);
    }

    SensorEventListener listenerACCCELEROMETER=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor sensor=event.sensor;
            StringBuilder sensorInfo=new StringBuilder();
            sensorInfo.append("Sensor Name "+sensor.getName()+"\n");

            sensorInfo.append("Value: \n");
            float[] values=event.values;
            sensorInfo.append("values[0] "+values[0]+"\n");
            sensorInfo.append("values[1] "+values[1]+"\n");
            sensorInfo.append("values[2] "+values[2]+"\n");

            showAccelerometer.setText(sensorInfo);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    SensorEventListener listenerLIGHT=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor sensor=event.sensor;
            StringBuilder sensorInfo=new StringBuilder();
            sensorInfo.append("Sensor Name "+sensor.getName()+"\n");
            sensorInfo.append("values "+event.values[0]+"\n");

            showLight.setText(sensorInfo);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onResume(){
        super.onResume();
        sensorMgrAccelerometer.registerListener(listenerACCCELEROMETER, sensorMgrAccelerometer.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorMgrAccelerometer.registerListener(listenerLIGHT,sensorMgrLight.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorMgrAccelerometer.unregisterListener(listenerACCCELEROMETER);
        sensorMgrLight.unregisterListener(listenerLIGHT);

    }
}
