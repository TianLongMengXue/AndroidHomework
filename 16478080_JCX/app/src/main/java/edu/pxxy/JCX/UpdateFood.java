package edu.pxxy.JCX;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateFood extends Activity {
    TextView foodname;
    TextView foodprice;
    Button add;
    int foodId;

    SQLiteOpenHelper helper;
    SQLiteDatabase sqLiteDatabase;

    Food food;

    Dao foodDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();

        helper = new SQLiteOpenHelper(this);

        sqLiteDatabase = helper.getReadableDatabase();

        Intent intent = getIntent();
        foodId = Integer.parseInt(intent.getStringExtra("foodId"));


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food=new Food(foodId,foodname.getText().toString(),Double.parseDouble(foodprice.getText().toString()),0);
                foodDao = new DaoImpl(helper,sqLiteDatabase);
                foodDao.updateFoodPrice(food);
                Intent intent = new Intent(UpdateFood.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }

    private void initView() {
        foodname = findViewById(R.id.et_name);
        foodprice = findViewById(R.id.et_price);
        add = findViewById(R.id.btn_add);
    }
}
