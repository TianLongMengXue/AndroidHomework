package com.e3e4e20.fourthweek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/*
 * Filename: com.e3e4e20.fourthweek
 * Description:
 * Version: 1.0
 * Created: 2019/9/27 14:46 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow Draco
 * Company:
 * */
public class RegisterActivity extends AppCompatActivity {
    // 注册按钮
    private Button registerButton;
    // 登录跳转按钮
    private Button loginLinkButton;
    // 外部注册信息按钮
    private ImageButton beefButton;
    private ImageButton roastSausageButton;
    private ImageButton porkButton;
    // 用户名输入框
    private EditText usernameEditText;
    // 密码输入框
    private EditText passwordEditText;
    // 用户名
    private String username;
    // 密码
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // 页面业务初始化
        init();
    }

    private void init() {
        // 注册按钮
        registerButton = findViewById(R.id.registerButton);
        // 登录跳转按钮
        loginLinkButton = findViewById(R.id.loginLinkButton);
        // 外部注册信息按钮
        beefButton = findViewById(R.id.beefButton);
        roastSausageButton = findViewById(R.id.roastSausageButton);
        porkButton = findViewById(R.id.porkButton);
        // 用户名输入框
        usernameEditText = findViewById(R.id.usernameEditText);
        // 密码输入框
        passwordEditText = findViewById(R.id.passwordEditText);
        // 注册按钮点击事件
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户名
                getUsername();
                // 获取密码
                getPassword();
                // 对用户名和密码进行验证
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    startActivity(intent);
                    RegisterActivity.this.finish();
                }
            }
        });
        // 登录跳转按钮点击事件
        loginLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivityForResult(intent, 1);
                RegisterActivity.this.finish();
            }
        });
        // 外部注册信息按钮点击事件
        beefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "更多功能敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        porkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "更多功能敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        roastSausageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this,"更多功能敬请期待",Toast.LENGTH_SHORT).show();
            }
        });
    }
    // 获取用户名
    private void getUsername() {
        username = usernameEditText.getText().toString().trim();
    }
    // 获取密码
    private void getPassword() {
        password = passwordEditText.getText().toString().trim();
    }




}
