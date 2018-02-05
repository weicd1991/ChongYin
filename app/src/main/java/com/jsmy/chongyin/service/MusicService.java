package com.jsmy.chongyin.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.jsmy.chongyin.R;
import com.jsmy.chongyin.contents.DowloadEvent;
import com.jsmy.chongyin.contents.ServiceCode;
import com.jsmy.chongyin.utils.MyLog;
import com.jsmy.chongyin.utils.SharePrefUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class MusicService extends Service {
    private MediaPlayer player;

    @Override
    public void onCreate() {
        MyLog.showLog("NNN", "onCreateMusic");
        player = new MediaPlayer();
        prepareMusic();
        super.onCreate();
    }

    private void prepareMusic() {
        try {
            player = MediaPlayer.create(this, R.raw.backgroudmusic);
            player.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startPlayMusic() {
        try {
            if (player != null && player.isPlaying())
                return;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        prepareMusic();
        try {
            player.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                try {
                    player.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

            }
        });
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                try {
                    player.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    private void stopPlayMusic() {
        try {
            if (player != null) {
                player.stop();
                player.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            if ("Y".equals(intent.getStringExtra("play"))) {
                MyLog.showLog("NNN", "startPlayMusic1");
                startPlayMusic();
            } else if ("Y".equals(SharePrefUtil.getString(this, "play", "N"))) {
                MyLog.showLog("NNN", "startPlayMusic2");
                startPlayMusic();
            } else {
                MyLog.showLog("NNN", "stopPlayMusic");
                stopPlayMusic();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopPlayMusic();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
