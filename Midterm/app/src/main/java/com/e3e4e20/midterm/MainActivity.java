package com.e3e4e20.midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    // 电话卡卡号输入框
    private EditText telNumberEditText;
    // 电话卡卡号
    private String telNumber;
    // 密码输入框
    private EditText telPasswordEditText;
    // 密码
    private String telPassword;
    // 登录确认按钮
    private Button loginButton;
    // 电话卡详细信息显示区域
    private LinearLayout telDetailLinearLayout;
    // 电话卡卡号显示框
    private TextView telNumberTextView;
    // 电话卡余额显示框
    private TextView telMoneyTextView;
    // 电话充值金额输入框
    private EditText telChargeEditText;
    // 充值金额
    private double telMoney;
    // 电话充值按钮
    private Button telChargeButton;
    // 拨打电话号码输入框
    private EditText telCallEditText;
    // 拨打电话号码
    private String callNumber;
    // 拨打电话按钮
    private Button telCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        // 获取页面组件
        getAllView();
        // 设置电话卡详细信息区域默认为不显示
        telDetailLinearLayout.setVisibility(View.GONE);
        // 登录确认点击事件
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIM sim = new SIM();
                // 获取输入的电话卡卡号
                getTelNumber();
                // 获取输入的密码
                getTelPassword();
                if (TextUtils.isEmpty(telNumber)){
                    Toast.makeText(MainActivity.this, "请输入电话卡卡号！", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(telPassword)){
                    Toast.makeText(MainActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.equals(telNumber, sim.getSIMNumber())){
                    Toast.makeText(MainActivity.this, "电话卡卡号不存在！", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.equals(telPassword, sim.getSIMPassword())){
                    Toast.makeText(MainActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                } else {
                    telPassword = "";
                    telDetailLinearLayout.setVisibility(View.VISIBLE);
                    telNumberTextView.setText("卡号:" + sim.getSIMNumber());
                    telMoneyTextView.setText("余额:" + String.valueOf(sim.getSIMMoney()));
                }
            }
        });
        // 电话充值点击事件
        telChargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "更多功能敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        // 拨打电话点击事件
        telCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "更多功能敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTelPassword() {
        telPassword = (String)telPasswordEditText.getText().toString().trim();
    }

    private void getTelNumber() {
        telNumber = (String)telNumberEditText.getText().toString().trim();
    }

    private void getAllView() {
        // 电话卡卡号输入框
        telNumberEditText = findViewById(R.id.telNumberEditText);
        // 密码输入框
        telPasswordEditText = findViewById(R.id.telPasswordEditText);
        // 登录确认按钮
        loginButton = findViewById(R.id.loginButton);
        // 电话卡详细信息显示区域
        telDetailLinearLayout = findViewById(R.id.telDetailLinearLayout);
        // 电话卡卡号显示框
        telNumberTextView = findViewById(R.id.telNumberTextView);
        // 电话卡余额显示框
        telMoneyTextView = findViewById(R.id.telMoneyTextView);
        // 电话充值金额输入框
        telChargeEditText = findViewById(R.id.telChargeEditText);
        // 电话充值按钮
        telChargeButton = findViewById(R.id.telChargeButton);
        // 拨打电话号码输入框
        telCallEditText = findViewById(R.id.telCallEditText);
        // 拨打电话按钮
        telCallButton = findViewById(R.id.telCallButton);
    }
}
