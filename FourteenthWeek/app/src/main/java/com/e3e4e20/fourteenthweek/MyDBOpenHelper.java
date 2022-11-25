package com.e3e4e20.fourteenthweek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyDBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="jcx.db";
    public static final Integer VERSION=1;

    public MyDBOpenHelper(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    private static final String DATABASE_CREATE = "CREATE TABLE food (_id integer primary key autoincrement,foodname text,foodprice real,count integer)";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("action","CreateSuccessfully!");
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("action","UpdateSuccessfully!");
    }
}
