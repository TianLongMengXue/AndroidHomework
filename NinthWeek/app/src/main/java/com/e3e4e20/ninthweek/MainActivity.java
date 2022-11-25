package com.e3e4e20.ninthweek;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView imageOne,imageTwo,imageThree,imageFour;
    Button loadingButton;
    Bitmap bitmap;
    DownService.MyBinder binder;

    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (DownService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @SuppressLint("HandlerLeak")
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            switch (msg.what){
                case 1:
                    imageOne.setImageBitmap((Bitmap) msg.obj);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                URL url=new URL("http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg") ;
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                InputStream in =connection.getInputStream();
                bitmap= BitmapFactory.decodeStream(in);
               }catch (Exception e){
                   e.printStackTrace();
               }
            }
        }).start();
    }

    private void initEvent() {
        Intent in = new Intent(this, DownService.class);
        in.putExtra("messenger",new Messenger(handler));
        startService(in);
        bindService(in, sc, BIND_AUTO_CREATE);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                img_1.setImageBitmap(bitmap);
            }
        });
    }

    private void initView() {
        imageOne=findViewById(R.id.imageOne);
        imageTwo=findViewById(R.id.imageTwo);
        imageThree=findViewById(R.id.imageThree);
        imageFour=findViewById(R.id.imageFour);
        loadingButton=findViewById(R.id.loadingButton);
    }
}
