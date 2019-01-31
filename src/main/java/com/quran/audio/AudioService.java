package com.quran.audio;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.quran.audio.message.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

public class AudioService extends Service {
    private String TAG = AudioService.class.getSimpleName();
    private MediaPlayer mediaPlayer;
    private boolean isFirst;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "services onCreate: ==========");
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            isFirst = true;
        }
        EventBus.getDefault().register(AudioService.this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG, "services onTaskRemoved: ==========");
        exit();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "services onDestroy: ==========");
        super.onDestroy();
        exit();
    }
    private void exit() {
        EventBus.getDefault().unregister(this);
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void playPosition(MessageEvent event) {
        Log.d(TAG, "play: === " + event.getAction());
        if (event.getAction() == MessageEvent.PLAY_POSITION) {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
                isFirst = true;
            }
            if (!isFirst) mediaPlayer.reset();
            try {
                AssetFileDescriptor fileDescriptor = getAssets().openFd((event.getPosition() + 1) + ".mp3");
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        isFirst = false;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void stop() {
//        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//            isPause = true;
//        }
    }
    private void pause() {
//        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//            isPause = true;
//        }
    }

    private void resume() {
//        if (isPause) {
//            mediaPlayer.start();
//            isPause = false;
//        }
    }

}
