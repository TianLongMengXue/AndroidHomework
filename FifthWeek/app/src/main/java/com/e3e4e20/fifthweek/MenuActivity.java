package com.e3e4e20.fifthweek;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/*
 * Filename: com.e3e4e20.fifthweek
 * Description:
 * Version: 1.0
 * Created: 2019/10/19 19:55 星期六
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class MenuActivity extends AppCompatActivity {
    // 京酱肉丝(30RMB)
    private CheckBox mealOne;
    // 土豆肉丝(20RMB)
    private CheckBox mealTwo;
    // 糖醋排骨(40RMB)
    private CheckBox mealThree;
    // 红烧剥皮鱼(36RMB)
    private CheckBox mealFour;
    // 酸菜鱼(30RMB)
    private CheckBox mealFive;
    // 啤酒鸭(24RMB)
    private CheckBox mealSix;
    // 菜单
    private StringBuilder mealList = new StringBuilder();
    // 优惠券
    private RadioButton useCouponButton;
    private RadioButton noCouponButton;
    // 优惠券编号输入框
    private EditText couponNumberEditText;
    // 优惠券编号
    private String couponNumber;
    // 地址输入框
    private EditText addressEditText;
    // 地址
    private String addressContext;
    // 菜单提交按钮
    private Button submitButton;
    // 总金额
    private double count;
    // 用户名
    private String username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // 页面初始化
        init();
    }

    // 页面初始化
    private void init() {
        // 京酱肉丝(30RMB)
        mealOne = findViewById(R.id.mealOne);
        // 土豆肉丝(20RMB)
        mealTwo = findViewById(R.id.mealTwo);
        // 糖醋排骨(40RMB)
        mealThree = findViewById(R.id.mealThree);
        // 红烧剥皮鱼(36RMB)
        mealFour = findViewById(R.id.mealFour);
        // 酸菜鱼(30RMB)
        mealFive = findViewById(R.id.mealFive);
        // 啤酒鸭(24RMB)
        mealSix = findViewById(R.id.mealSix);
        // 优惠券
        useCouponButton = findViewById(R.id.useCouponButton);
        noCouponButton = findViewById(R.id.noCouponButton);
        // 优惠券编号输入框
        couponNumberEditText = (EditText) findViewById(R.id.couponNumberEditText);
        // 地址输入框
        addressEditText = findViewById(R.id.addressEditText);
        // 菜单提交按钮
        submitButton = findViewById(R.id.submitButton);
        // 使用优惠券点击事件
        useCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*EditText couponNumberEditText = new EditText(MenuActivity.this);
                couponNumberEditText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                couponNumberEditText.setSingleLine(true);
                couponNumberEditText.setHint(getResources().getString(R.string.couponNumber));
                couponNumberEditText.setTextSize(20);
                couponNumberEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                couponNumberEditText.setTextColor(getResources().getColor(R.color.colorRed));
                couponNumberEditText.setHintTextColor(getResources().getColor(R.color.colorSkyblue));
                couponLinearLayout.addView(couponNumberEditText);*/
                /*
                * android view的setVisibility方法值
                * 1.View.VISIBLE,常量值为0，意思是可见的
                * 2.View.INVISIBLE,常量值是4，意思是不可见的
                * 3.View.GONE,常量值是8，意思是不可见的，并且不占用布局空间
                * */
                couponNumberEditText.setVisibility(View.VISIBLE);
            }
        });
        // 不使用优惠券点击事件
        noCouponButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                couponLinearLayout.removeView(couponNumberEditText);
                couponNumberEditText.setVisibility(View.GONE);
            }
        });
        // 订单提交点击事件
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMeal();
                // 对菜单进行状态检查,若菜单为空则状态为不可提交
//                boolean flag = false;
//                for (int i = 0; i < mealList.length; i++){
//                    if (mealList[i]==null){
//                        flag = true;
//                    } else {
//                        flag = false;
//                        break;
//                    }
//                }
                // 若需要填写优惠券则获取
                if (useCouponButton.isChecked()){
                    getCouponNumber();
                }
                getAddressContext();
                getCount();
                setUsername();
                if (mealList.length()==0){
                    Toast.makeText(MenuActivity.this, "请点菜", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(couponNumber) && useCouponButton.isChecked()){
                    Toast.makeText(MenuActivity.this, "请输入优惠券编码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(addressContext)){
                    Toast.makeText(MenuActivity.this, "请输入地址", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(MenuActivity.this, "订单已提交", Toast.LENGTH_SHORT).show();
                    String str = "共计:" + String.valueOf(count);
                    mealList.append(str);
                    String[] meals = mealList.toString().split(",");
                    /*for (int i = 0; i < meals.length; i++) {
                        System.out.println(meals[i]);
                    }*/
                    Intent intent = new Intent(MenuActivity.this, MealDetailActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("meals",meals);
                    intent.putExtra("addressContext",addressContext);
                    intent.putExtra("count",count);
                    startActivity(intent);
                    MenuActivity.this.finish();
                }
            }
        });
    }
    // 记录菜单内容部分
    private void getMeal() {
        mealList = new StringBuilder();
        if (mealOne.isChecked()){
            mealList.append("京酱肉丝,");
        }
        if (mealTwo.isChecked()){
            mealList.append("土豆肉丝,");
        }
        if (mealThree.isChecked()){
            mealList.append("糖醋排骨,");
        }
        if (mealFour.isChecked()){
            mealList.append("红烧剥皮鱼,");
        }
        if (mealFive.isChecked()){
            mealList.append("酸菜鱼,");
        }
        if (mealSix.isChecked()){
            mealList.append("啤酒鸭,");
        }
    }

    private void getCouponNumber() {
        couponNumber = couponNumberEditText.getText().toString().trim();
    }

    private void getAddressContext() {
        addressContext = addressEditText.getText().toString().trim();
    }

    private void getCount() {
        count = 0.00;
        if (mealOne.isChecked()){
            count+=30;
        }
        if (mealTwo.isChecked()){
            count+=20;
        }
        if (mealThree.isChecked()){
            count+=40;
        }
        if (mealFour.isChecked()){
            count+=30;
        }
        if (mealFive.isChecked()){
            count+=36;
        }
        if (mealSix.isChecked()){
            count+=24;
        }
    }
    // 获取用户名
    private void setUsername() {
        Intent intent = getIntent();
        // 用户名
        username = intent.getStringExtra("username");
        if (TextUtils.isEmpty(username)){
            username = "admin";
        }
    }
}
