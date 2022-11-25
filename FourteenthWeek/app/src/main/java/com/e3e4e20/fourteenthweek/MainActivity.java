package com.e3e4e20.fourteenthweek;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyDBOpenHelper openHelper;
    SQLiteDatabase sqLiteDatabase;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper=new MyDBOpenHelper(this);
        sqLiteDatabase=openHelper.getReadableDatabase();
        //创建游标对象
        Cursor cursor = sqLiteDatabase.query("user", new String[]{"name"}, null, null, null, null, null);
        //利用游标遍历所有数据对象
        //为了显示全部，把所有对象连接起来，放到TextView中
        String textview_data = "";
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            textview_data = textview_data + "\n" + name;
        }
        textView = findViewById(R.id.textView);
        textView.setText(textview_data);
    }
}
