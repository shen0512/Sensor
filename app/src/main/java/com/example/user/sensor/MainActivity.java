package com.example.user.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorMgr;
    TextView showAccelerometer,showGravity,showRotationV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorMgr= (SensorManager) getSystemService(SENSOR_SERVICE);

        showAccelerometer = (TextView) findViewById(R.id.acceleromter);
        showGravity = (TextView) findViewById(R.id.gravity);
        showRotationV=(TextView) findViewById(R.id.rotationV);
    }

    SensorEventListener listener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            Sensor sensor=event.sensor;
            switch(sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    showAccelerometer.setText("Sensor name: "+sensor.getName()+"\n");
                    showAccelerometer.append("Values[0]: "+event.values[0]+"\n");
                    showAccelerometer.append("Values[1]: "+event.values[1]+"\n");
                    showAccelerometer.append("Values[2]: "+event.values[2]+"\n");
                    break;
                case Sensor.TYPE_GRAVITY:
                    showGravity.setText("Sensor name: "+sensor.getName()+"\n");
                    showGravity.append("Values[0]: "+event.values[0]+"\n");
                    showGravity.append("Values[1]: "+event.values[1]+"\n");
                    showGravity.append("Values[2]: "+event.values[2]+"\n");
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    showRotationV.setText("Sensor name: "+sensor.getName()+"\n");
                    showRotationV.append("Values[0]: "+event.values[0]+"\n");
                    showRotationV.append("Values[2]: "+event.values[1]+"\n");
                    showRotationV.append("Values[2]: "+event.values[1]+"\n");
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    @Override
    protected void onResume(){
        super.onResume();
        sensorMgr.registerListener(listener, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorMgr.registerListener(listener,sensorMgr.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorMgr.registerListener(listener,sensorMgr.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_NORMAL);

    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorMgr.unregisterListener(listener);
    }
}
