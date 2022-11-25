package edu.pxxy.JCX;

import java.util.Map;

/*
 * Filename: edu.pxxy.JCX
 * Description:
 * Version: 1.0
 * Created: 2019/12/13 16:06 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public interface FoodDao {
    abstract Map<Integer,Food> selectAllFood(MyDBOpenHelper openHelper);
    abstract Food selectFood (String foodName,MyDBOpenHelper openHelper);
    abstract boolean insertFood (Food food,MyDBOpenHelper openHelper);
    abstract boolean updateFood (Food food,MyDBOpenHelper openHelper);
    abstract boolean deleteFood (String foodName,MyDBOpenHelper openHelper);
}
