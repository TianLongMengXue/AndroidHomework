package com.e3e4e20.thirteenthweek;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AngleActivity extends AppCompatActivity implements SensorEventListener {

    private TextView azimuthTextView;
    private TextView tiltAngleTextView;
    private TextView rollAngleTextView;
    private SensorManager sensorManager;
    private Sensor sensorOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_step);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorOrientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this, sensorOrientation, SensorManager.SENSOR_DELAY_UI);
        bindViews();
    }

    private void bindViews() {
        azimuthTextView = (TextView) findViewById(R.id.azimuthTextView);
        tiltAngleTextView = (TextView) findViewById(R.id.tiltAngleTextView);
        rollAngleTextView = (TextView) findViewById(R.id.rollAngleTextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        azimuthTextView.setText("方位角：" + (float) (Math.round(event.values[0] * 100)) / 100);
        tiltAngleTextView.setText("倾斜角：" + (float) (Math.round(event.values[1] * 100)) / 100);
        rollAngleTextView.setText("滚动角：" + (float) (Math.round(event.values[2] * 100)) / 100);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
