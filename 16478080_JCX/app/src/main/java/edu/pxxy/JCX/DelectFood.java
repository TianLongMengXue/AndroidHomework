package edu.pxxy.JCX;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class DelectFood extends Activity {

    int foodId;

    SQLiteOpenHelper helper;
    SQLiteDatabase sqLiteDatabase;


    Dao foodDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        helper = new SQLiteOpenHelper(this);

        sqLiteDatabase = helper.getReadableDatabase();

        Intent intent = getIntent();
        foodId = Integer.parseInt(intent.getStringExtra("foodId"));
        //dao初始化
        foodDao = new DaoImpl(helper,sqLiteDatabase);
        //删除菜品
        foodDao.delectFood(foodId);
        Intent in = new Intent(DelectFood.this, MainActivity.class);
        startActivity(in);
        finish();





    }

}
