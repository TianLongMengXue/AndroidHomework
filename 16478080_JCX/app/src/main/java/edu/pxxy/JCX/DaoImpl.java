package edu.pxxy.JCX;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao{

    SQLiteOpenHelper helper;
    SQLiteDatabase sqLiteDatabase;

    public DaoImpl(SQLiteOpenHelper helper, SQLiteDatabase sqLiteDatabase) {
        this.helper = helper;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<Food> findAllFood() {

        List<Food> foods = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(false,"food", null, null,null,null,null,null,null);
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Double price = cursor.getDouble(2);
            int count = cursor.getInt(3);
            Food food = new Food(id,name,price,count);
            foods.add(food);
            Log.d("oyh", name);
        }
        return foods;
    }

    @Override
    public List<Food> findFoodByName(String foodname) {
        return null;
    }

    @Override
    public long insertFood(Food food) {
        //sql
        String sql = "insert into food(foodname,foodprice) values('"+food.getFoodname()+"','"+food.getFoodprice()+"')";
        sqLiteDatabase.execSQL(sql);
        return 0;
    }

    @Override
    public int delectFood(int foodId) {
        String sql = "delete from food where _id = '"+foodId+"'";
        sqLiteDatabase.execSQL(sql);
        return 0;
    }

    @Override
    public int updateFoodPrice(Food food) {
       String sql = "update food set foodname='"+food.getFoodname()+"' ,foodprice='"+food.getFoodprice()+"' where _id="+food.get_id();
       sqLiteDatabase.execSQL(sql);
       Log.d("oyh",sql);
        return 0;
    }
}
