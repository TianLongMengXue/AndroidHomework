package edu.pxxy.JCX;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper helper;
    SQLiteDatabase sqLiteDatabase;

    ListView lv_food;
    Button add;

    Dao foodDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SQLiteOpenHelper(this);

        sqLiteDatabase = helper.getReadableDatabase();

        initView();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddFood.class);
                startActivity(intent);
                finish();
            }
        });



        //dao初始化
        foodDao = new DaoImpl(helper,sqLiteDatabase);
        //获取所有菜品
        List<Food> foods = foodDao.findAllFood();


        FoodAdapte dishAdapte = new FoodAdapte(MainActivity.this,R.layout.activity_foodlist,foods);
        ListView listView = findViewById(R.id.lv_food);
        listView.setAdapter(dishAdapte);



    }

    private void initView() {
        lv_food = findViewById(R.id.lv_food);
        add = findViewById(R.id.btn_add);

    }
}
