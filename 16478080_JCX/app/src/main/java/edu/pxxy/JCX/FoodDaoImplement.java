package edu.pxxy.JCX;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

/*
 * Filename: edu.pxxy.JCX
 * Description:
 * Version: 1.0
 * Created: 2019/12/13 16:03 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class FoodDaoImplement implements FoodDao {
    private Food food;
    private SQLiteDatabase sqLiteDatabase;
    private MyDBOpenHelper openHelper;

    @Override
    public Map<Integer, Food> selectAllFood(MyDBOpenHelper openHelper) {
        sqLiteDatabase = openHelper.getWritableDatabase();
        //创建游标对象
        Cursor cursor = sqLiteDatabase.query("food", new String[]{"food"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中
        String textview_data = "";
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("_id"));
            textview_data = textview_data + "\n" + name;
        }
        return null;
    }

    @Override
    public Food selectFood(String foodName, MyDBOpenHelper openHelper) {
        return null;
    }

    @Override
    public boolean insertFood(Food food, MyDBOpenHelper openHelper) {
        return false;
    }

    @Override
    public boolean updateFood(Food food, MyDBOpenHelper openHelper) {
        return false;
    }

    @Override
    public boolean deleteFood(String foodName, MyDBOpenHelper openHelper) {
        return false;
    }
}
