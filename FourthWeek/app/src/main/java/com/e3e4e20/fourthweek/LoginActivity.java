package com.e3e4e20.fourthweek;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/*
 * Filename: com.e3e4e20.fourthweek
 * Description:
 * Version: 1.0
 * Created: 2019/9/27 14:42 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow Draco
 * Company:
 * */
public class LoginActivity extends AppCompatActivity {
    // 登录按钮
    private Button loginButton;
    // 忘记密码按钮
    private Button forgetLinkButton;
    // 用户名输入框
    private EditText usernameEditText;
    // 密码输入框
    private EditText passwordEditText;
    // 已存用户名 用户名
    private String user, username;
    // 已存密码 密码
    private String word, password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        // 登录按钮
        loginButton = findViewById(R.id.loginButton);
        // 忘记密码按钮
        forgetLinkButton = findViewById(R.id.forgetLinkButton);
        // 用户名输入框
        usernameEditText = findViewById(R.id.usernameEditText);
        // 密码输入框
        passwordEditText = findViewById(R.id.passwordEditText);

        // 登录按钮点击事件
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUser();
                setWord();
                getUsername();
                getPassword();
                if (!TextUtils.equals(user, username)){
                    Toast.makeText(LoginActivity.this, "帐号不存在", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!TextUtils.equals(word, password)){
                    Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(LoginActivity.this, FindActivity.class);
                            intent.putExtra("username",username);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        }
                    }, 2000);
                }
            }
        });

        // 忘记密码按钮点击事件
        forgetLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "更多功能敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUser() {
        Intent intent = getIntent();
        // 用户名
        user = intent.getStringExtra("username");
        if (TextUtils.isEmpty(user)){
            user = "admin";
        }
    }

    private void setWord() {
        Intent intent = getIntent();
        // 密码
        word = intent.getStringExtra("password");
        if (TextUtils.isEmpty(word)){
            word = "admin";
        }
    }

    private void getUsername() {
        username = usernameEditText.getText().toString().trim();
    }

    private void getPassword() {
        password = passwordEditText.getText().toString().trim();
    }
}
