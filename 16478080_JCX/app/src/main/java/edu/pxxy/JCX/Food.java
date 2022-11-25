package edu.pxxy.JCX;

public class Food {
    private int _id;
    private String foodname;
    private double foodprice;
    private int count;

    public Food(int _id, String foodname, double foodprice, int count) {
        this._id = _id;
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.count = count;
    }

    public Food(String foodname, double foodprice) {
        this.foodname = foodname;
        this.foodprice = foodprice;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public double getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(double foodprice) {
        this.foodprice = foodprice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
