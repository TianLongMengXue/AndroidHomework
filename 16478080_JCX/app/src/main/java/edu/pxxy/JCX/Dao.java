package edu.pxxy.JCX;

import java.util.List;

public interface Dao {
    List<Food> findAllFood();
    List<Food> findFoodByName(String foodname);
    long insertFood(Food food);
    int delectFood(int foodId);
    int updateFoodPrice(Food food);


}
