package com.e3e4e20.twelfthweek;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Filename: com.e3e4e20.twelfthweek
 * Description:
 * Version: 1.0
 * Created: 2019/11/22 15:26 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class MediaRecorderDemoActivity extends AppCompatActivity {

    private TextView resultMessage;
    private TextView actionMessage;
    private ExecutorService executorService;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private File recorderFile;
    private long startRecorderTime, stopRecorderTime;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean mIsPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder_demo);
        setTitle("文件录音");
        initView();
        executorService = Executors.newSingleThreadExecutor();
    }

    private void initView() {
        resultMessage = (TextView) findViewById(R.id.resultMessage);
        actionMessage = (TextView) findViewById(R.id.actionMessage);
        actionMessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startRecorder();
                        break;
                    case MotionEvent.ACTION_UP:
                        stopRecorder();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void startRecorder() {
        actionMessage.setText("正在说话...");
        //提交后台任务，开始录音
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                //释放上一次的录音
                releaseRecorder();
                Log.i("action","开始录音");
                //开始录音
                if(!doStart()) {
                    recorderFail();
                }
            }
        });
    }

    private void releaseRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
//        if (mediaRecorder != null) {
//            try {
//                mediaRecorder.stop();
//            } catch (IllegalStateException e) {
//                 TODO 如果当前java状态和jni里面的状态不一致
//                e.printStackTrace();
//                mediaRecorder = null;
//                mediaRecorder = new MediaRecorder();
//            }
//            mediaRecorder.release();
//            mediaRecorder = null;
//        }
    }

    private boolean doStart() {
        try {
            Log.i("action","启动录音");
            //创建MediaRecorder
            mediaRecorder = new MediaRecorder();
            // 创建录音文件
            recorderFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/recorderdemo/" +System.currentTimeMillis() + ".m4a");
            if (!recorderFile.getParentFile().exists()) recorderFile.getParentFile().mkdirs();
            recorderFile.createNewFile();
            //配置MediaRecorder

            // 从麦克风采集
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            // 保存文件为MP4格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

            // 所有android系统都支持的适中采样的频率
            mediaRecorder.setAudioSamplingRate(44100);

            //通用的AAC编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            //设置音质频率
            mediaRecorder.setAudioEncodingBitRate(96000);

            //设置文件录音的位置
            mediaRecorder.setOutputFile(recorderFile.getAbsolutePath());

            //开始录音
            mediaRecorder.prepare();
            mediaRecorder.start();
            startRecorderTime = System.currentTimeMillis();
            Log.i("action","正在录音");

        } catch(Exception e) {
            Toast.makeText(MediaRecorderDemoActivity.this, "录音失败，请重试", Toast.LENGTH_SHORT).show();
            return false;
        }

        //记录开始录音时间,用于统计时长,小于3秒中,录音不发送
        return true;
    }

    private boolean doStop() {
        try {
            Log.i("action","关闭录音");
            mediaRecorder.stop();
            stopRecorderTime = System.currentTimeMillis();
            final int second = (int) (stopRecorderTime - startRecorderTime) / 1000;
            //按住时间小于3秒钟，算作录取失败，不进行发送s
            if(second < 3) return false;
            handler.post(new Runnable() {

                @Override
                public void run() {
                    resultMessage.setText("录制成功:" + second + "秒");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mediaRecorder = null;
            mediaRecorder = new MediaRecorder();
        }
        return true;
    }

    private void recorderFail() {
        recorderFile = null;
        handler.post(new Runnable() {
            @Override
            public void run() {
                actionMessage.setText("录音失败请重新录音");
            }
        });
    }

    private void stopRecorder() {
        actionMessage.setText("停止录音");
        //提交后台任务,停止录音
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                if(!doStop()) {
                    recorderFail();
                }
                releaseRecorder();
            }
        });
    }

    public void playrecorder(View view) {
        Log.i("action","mIsPlaying =" +mIsPlaying);
        if(!mIsPlaying) {
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    doPlay(recorderFile);
                }
            });
        } else {
            Toast.makeText(MediaRecorderDemoActivity.this, "正在播放", Toast.LENGTH_SHORT).show();
        }
    }

    private void doPlay(File audioFile) {
        Log.i("action","doPlay");
        Log.i("action","audioFile ====" +audioFile);
        try {
            //配置播放器mMediaPlayer
            mediaPlayer = new MediaPlayer();
            FileInputStream fis = new FileInputStream(audioFile);
            //设置声音文件
            mediaPlayer.setDataSource(fis.getFD());
            //配置音量,中等音量
            mediaPlayer.setVolume(1, 1);
            //播放是否循环
            mediaPlayer.setLooping(false);

            //设置监听回调, 播放完毕
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                    Log.i("lxm","播放完毕");
                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    stopPlayer();
                    Toast.makeText(MediaRecorderDemoActivity.this,"播放失败",Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            Log.i("action","prepare");
            //设置播放
            mediaPlayer.prepare();
            mediaPlayer.start();
            Log.i("action","正在播放");

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("action","error :" +e.toString());
            stopPlayer();
        }
    }

    private void stopPlayer() {
        Log.i("action","stopPlayer");
        mIsPlaying = false;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当activity 关闭时，停止这个线程，防止内存泄漏
        executorService.shutdownNow();
        releaseRecorder();
    }
}
