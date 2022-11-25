package edu.pxxy.JCX;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import edu.pxxy.JCX.MainActivity;
import java.util.ArrayList;
import java.util.List;
public class FoodAdapte extends ArrayAdapter<Food> {

         Button update;
         Button delect;
        private Context context;

        private List<Food> foods = new ArrayList<>();
        private Food food;



        private int resourceId;
        public FoodAdapte(@NonNull Context context, int textViewResourceId, @NonNull List<Food> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;

            this.context=context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            food = getItem(position);
            foods.add(food);

            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //获取实例
            TextView foodname = (TextView) view.findViewById(R.id.tv_name);
            TextView foodprice = view.findViewById(R.id.tv_price);
            //设置菜名价格
            foodname.setText(food.getFoodname());
            foodprice.setText(food.getFoodprice()+"");


            update = view.findViewById(R.id.btn_update);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(context, UpdateFood.class);
                    System.out.println(food.get_id()+"");
                    intent.putExtra("foodId",food.get_id()+"");
                    context.startActivity(intent);
                    MainActivity activity = (MainActivity) context;
                    activity.finish();
                }
            });

            delect = view.findViewById(R.id.btn_delect);
            delect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(context, DelectFood.class);
                    intent.putExtra("foodId",food.get_id()+"");
                    context.startActivity(intent);
                    MainActivity activity = (MainActivity) context;
                    activity.finish();
                }
            });



            return view;
        }
    }

