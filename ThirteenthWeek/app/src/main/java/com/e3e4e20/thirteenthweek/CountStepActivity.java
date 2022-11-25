package com.e3e4e20.thirteenthweek;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountStepActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private SensorManager sManager;
    private Sensor sensorAccelerometer;
    private TextView countStepTextView;
    private Button controlButton;
    private int step = 0;   //步数
    private double startValue = 0;  //原始值
    private double lastValue = 0;  //上次的值
    private double currentValue = 0;  //当前值
    private boolean motiveState = true;   //是否处于运动状态
    private boolean processState = false;   //标记当前是否已经在计步


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_step);
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorAccelerometer = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
        bindViews();
    }

    private void bindViews() {
        countStepTextView = (TextView) findViewById(R.id.countStepTextView);
        controlButton = (Button) findViewById(R.id.controlButton);
        controlButton.setOnClickListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        double range = 1;   //设定一个精度范围
        float[] value = event.values;
        currentValue = magnitude(value[0], value[1], value[2]);   //计算当前的模
        //向上加速的状态
        if (motiveState == true) {
            if (currentValue >= lastValue) lastValue = currentValue;
            else {
                //检测到一次峰值
                if (Math.abs(currentValue - lastValue) > range) {
                    startValue = currentValue;
                    motiveState = false;
                }
            }
        }
        //向下加速的状态
        if (motiveState == false) {
            if (currentValue <= lastValue) lastValue = currentValue;
            else {
                if (Math.abs(currentValue - lastValue) > range) {
                    //检测到一次峰值
                    startValue = currentValue;
                    if (processState == true) {
                        step++;  //步数 + 1
                        if (processState == true) {
                            countStepTextView.setText(step + "");    //读数更新
                        }
                    }
                    motiveState = true;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onClick(View v) {
        step = 0;
        countStepTextView.setText("0");
        if (processState == true) {
            controlButton.setText("开始");
            processState = false;
        } else {
            controlButton.setText("停止");
            processState = true;
        }
    }

    //向量求模
    public double magnitude(float x, float y, float z) {
        double magnitude = 0;
        magnitude = Math.sqrt(x * x + y * y + z * z);
        return magnitude;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sManager.unregisterListener(this);
    }
}
