package com.e3e4e20.fifthweek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/*
 * Filename: com.e3e4e20.fifthweek
 * Description:
 * Version: 1.0
 * Created: 2019/10/20 0:06 星期日
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class MealDetailActivity extends AppCompatActivity {
    // 用户名
    private String username;
    // 欢迎标语
    private String welcomeTip;
    // 菜单
    private String[] meals = new String[7];
    // 地址
    private String addressContext;
    // 总金额
    double count;
    // 显示用户名
    private TextView thanksForUsernameDisplay;
    // 显示地址
    private TextView addressContextDisplay;
    // 显示菜单
    private ListView mealListDisplay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detial);
        // 初始化
        init();
    }

    private void init() {
        getUsername();
        setWelcomeTip();
        setAddressContext();
        getCount();
        getMeals();
        // 显示菜单总金额
        setMealListItem();
    }

    private void setMealListItem() {
        mealListDisplay = findViewById(R.id.mealListDisplay);
        ArrayAdapter<String> mealList = new ArrayAdapter<String>(MealDetailActivity.this,
                android.R.layout.simple_list_item_1, meals);
        mealListDisplay.setAdapter(mealList);
    }

    private void getCount() {
        Intent intent = getIntent();
        // 总金额
        count = intent.getDoubleExtra("count", 0.00);
    }

    private void setAddressContext() {
        addressContextDisplay = findViewById(R.id.addressContextDisplay);
        Intent intent = getIntent();
        // 地址
        addressContext = intent.getStringExtra("addressContext");
        // 显示地址
        addressContextDisplay.setText(addressContext);
    }

    private void getMeals() {
        Intent intent = getIntent();
        // 菜单
        meals = intent.getStringArrayExtra("meals");
    }
    private void setWelcomeTip() {
        thanksForUsernameDisplay = findViewById(R.id.thanksForUsernameDisplay);
        String welcome = getResources().getString(R.string.thankForUsing);
        welcomeTip = welcome+":"+username;
        // 显示用户名
        thanksForUsernameDisplay.setText(welcomeTip);
    }
    private void getUsername() {
        Intent intent = getIntent();
        // 用户名
        username = intent.getStringExtra("username");
        if (TextUtils.isEmpty(username)){
            username = "admin";
        }
    }
}
