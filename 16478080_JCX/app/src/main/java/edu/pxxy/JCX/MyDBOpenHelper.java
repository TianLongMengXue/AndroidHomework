package edu.pxxy.JCX;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBOpenHelper extends SQLiteOpenHelper {
    // 数据库名
    public static final String DB_NAME="jcx.db";
    // 数据库版本
    public static final Integer VERSION=1;

    //带全部参数的构造函数
    public MyDBOpenHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    //初始化数据库，新建了一张表这个方法继承自SQLiteOpenHelper,会自动调用，当新建了一个DatabaseHelper对象时，就会默认新建一张表
    private static final String DATABASE_CREATE = "CREATE TABLE food (_id integer primary key autoincrement,foodname text,foodprice real,count integer)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("action","CreateSuccessfully!");
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }
    //这里应当实现数据库升级等操作
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("action","UpdateSuccessfully!");
    }
}
