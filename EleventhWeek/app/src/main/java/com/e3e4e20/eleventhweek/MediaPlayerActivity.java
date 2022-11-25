package com.e3e4e20.eleventhweek;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Filename: com.e3e4e20.eleventhweek
 * Description:
 * Version: 1.0
 * Created: 2019/11/15 14:46 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class MediaPlayerActivity extends AppCompatActivity {
    // HeadImageView
    private ImageView headImageView;
    // musicNameTextView
    private TextView musicNameTextView;
    // musicSeekBar
    private SeekBar musicSeekBar;
    // currentTimeTextView
    private TextView currentTimeTextView;
    // wholeTimeTextView
    private TextView wholeTimeTextView;
    // nextButton
    private Button nextButton;
    // playPauseButton
    private Button playPauseButton;
    // stopButton
    private Button stopButton;
    // previousButton
    private Button previousButton;
    // 音乐列表
    private ArrayList<Music> musicArrayList = new ArrayList<Music>();
    private int musicList[] = {R.raw.attraction, R.raw.brandxmusic, R.raw.countdown, R.raw.firedearthmusic,
            R.raw.garethcoker, R.raw.gargantuanmusic, R.raw.hifinesse, R.raw.hifinesseii, R.raw.inonzur,
            R.raw.johndreamer, R.raw.markpetrie, R.raw.positionmusic, R.raw.stevenburke, R.raw.twostepsfromheii,
            R.raw.various, R.raw.variousartists, R.raw.variousartistsii, R.raw.williamjoseph};
    // 创建多媒体
    private MediaPlayer mediaPlayer = null;
    // 记录当前播放的音乐
    private int currentMusic = 0;
    // 记录当前播放(true)或是暂停(false)状态
    private boolean flag = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        getView();
        initView();
        initEvent();
    }

    private void initEvent() {
        // 上一首点击事件
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null){
                    previousMusic();
                }
            }
        });
        // 开始暂停点击事件
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    pauseMusic();
                }
                else{
                    flag = true;
                    if (mediaPlayer == null){
                        initMediaPlayer(0);
                    }
                    startMusic();
                }
            }
        });
        // 中止点击事件
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null){
                    stopMusic();
                }
            }
        });
        // 下一首点击事件
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null){
                    nextMusic();
                }
            }
        });
        // 进度条拖动事件
        musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(run);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                handler.postDelayed(run,100);
            }
        });
    }

    private void nextMusic() {
//        stopMusic();
        mediaPlayer.reset();
        if(currentMusic == musicList.length - 1){
            currentMusic = 0;
        }else {
            currentMusic++;
        }
        initMediaPlayer(currentMusic);
        startMusic();
    }

    private void previousMusic() {
//        stopMusic();
        mediaPlayer.reset();
        if(currentMusic==0){
            currentMusic = musicList.length - 1;
        }else {
            currentMusic--;
        }
        initMediaPlayer(currentMusic);
        startMusic();
    }

    private void stopMusic(){
        mediaPlayer.stop();
        mediaPlayer.release();
        handler.removeCallbacks(run);
        mediaPlayer = null;
        flag = false;
        playPauseButton.setText("播放");
        musicNameTextView.setText(getResources().getString(R.string.music_name));
        currentTimeTextView.setText(getResources().getString(R.string.curren_time));
        wholeTimeTextView.setText(getResources().getString(R.string.whole_time));
    }
    private void pauseMusic(){
        mediaPlayer.pause();
        playPauseButton.setText("播放");
    }
    private void startMusic(){
        playPauseButton.setText("暂停");
        mediaPlayer.start();
        handler.post(run);
    }
    private void initMediaPlayer(int currentMusic){
        // 当前第一首音乐准备
//        mediaPlayer = MediaPlayer.create(this, musicList.get(currentMusic));
//        currentMusic = 0;
//        mediaPlayer = MediaPlayer.create(this, musicArrayList.get(currentMusic).getPath());
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        musicSeekBar.setMax(mediaPlayer.getDuration());
//        musicNameTextView.setText(musicArrayList.get(currentMusic).getName());
//        playPauseButton.setText("暂停");
        mediaPlayer = MediaPlayer.create(this, musicArrayList.get(currentMusic).getPath());
        // 自动播放下一首
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextMusic();
            }
        });
        musicSeekBar.setMax(mediaPlayer.getDuration());
        musicNameTextView.setText(musicArrayList.get(currentMusic).getName());
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            musicSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            currentTimeTextView.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
            wholeTimeTextView.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
            if (mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition() == 0){
                nextMusic();
            }
            handler.postDelayed(run, 100);
        }
    };

    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        //Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours == 0) {
            //finalTimerString = hours + ":";
        }

        // Pre appending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    private void initView() {
        // 初始化显示顶部图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.okmnbv);
        headImageView.setImageDrawable(new CircleDrawable(bitmap));
        // 初始化音乐名称
        musicNameTextView.setText(getResources().getString(R.string.music_name));
        // 初始化音乐时间
        currentTimeTextView.setText(getResources().getString(R.string.curren_time));
        wholeTimeTextView.setText(getResources().getString(R.string.whole_time));
        // 初始化音乐列表
        for (short i = 0; i < musicList.length; i++){
            initMusicArrayList(musicList[i]);
        }
//        musicList.add(R.raw.attraction);
//        musicList.add(R.raw.brandxmusic);
//        musicList.add(R.raw.countdown);
//        musicList.add(R.raw.dannymccarthy);
//        musicList.add(R.raw.firedearthmusic);
//        musicList.add(R.raw.garethcoker);
//        musicList.add(R.raw.gargantuanmusic);
//        musicList.add(R.raw.hifinesse);
//        musicList.add(R.raw.hifinesseii);
//        musicList.add(R.raw.inonzur);
//        musicList.add(R.raw.johndreamer);
//        musicList.add(R.raw.markpetrie);
//        musicList.add(R.raw.positionmusic);
//        musicList.add(R.raw.stevenburke);
//        musicList.add(R.raw.twostepsfromheii);
//        musicList.add(R.raw.various);
//        musicList.add(R.raw.variousartists);
//        musicList.add(R.raw.variousartistsii);
//        musicList.add(R.raw.williamjoseph);
    }

    private void initMusicArrayList(int path) {
        Music music = new Music();
        music.setPath(path);
        String[] strs=getResources().getResourceName(path).split("/");
        music.setName(strs[strs.length-1]);
        musicArrayList.add(music);
    }

    private void getView() {
        headImageView = findViewById(R.id.headImageView);
        musicNameTextView = findViewById(R.id.musicNameTextView);
        musicSeekBar = findViewById(R.id.musicSeekBar);
        currentTimeTextView = findViewById(R.id.currentTimeTextView);
        wholeTimeTextView = findViewById(R.id.wholeTimeTextView);
        previousButton = findViewById(R.id.previousButton);
        playPauseButton = findViewById(R.id.playPauseButton);
        stopButton = findViewById(R.id.stopButton);
        nextButton = findViewById(R.id.nextButton);
    }

}
