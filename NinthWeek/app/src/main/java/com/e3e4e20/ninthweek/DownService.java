package com.e3e4e20.ninthweek;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Handler;


public class DownService extends Service {
    Bitmap bitmap;
    private Messenger messenger;
    private Handler handler = new Handler();

    public DownService(){

    }

    private MyBinder binder=new MyBinder();
    class MyBinder extends Binder {
        public void startDown(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("myapp","download start...");
                    try{
                        URL url=new URL("http://e.hiphotos.baidu.com/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg");
                        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                        InputStream in=connection.getInputStream();
                        bitmap= BitmapFactory.decodeStream(in);
                        Message msg=Message.obtain();
                        msg.what=1;
                        msg.obj=bitmap;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    };
    @Override
    public void onCreate() {
        Log.d("myapp","onCreate...");
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(messenger==null)
            messenger = (Messenger) intent.getExtras().get("messenger");
        Log.d("myapp","onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("myapp","onDestroy...");
        super.onDestroy();
    }
}
