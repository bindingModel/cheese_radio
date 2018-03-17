package com.cheese.radio.ui.service;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;


import com.binding.model.layout.rotate.TimeEntity;
import com.binding.model.layout.rotate.TimeUtil;

import java.io.IOException;

import static com.cheese.radio.ui.service.AudioService.Pause;
import static com.cheese.radio.ui.service.AudioService.Play;
import static com.cheese.radio.ui.service.AudioService.Reset;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:10
 * modify developer：  admin
 * modify time：17:10
 * modify remark：
 *
 * @version 2.0
 */


public class AudioServiceUtil implements TimeEntity {
    private static AudioServiceUtil instance = new AudioServiceUtil();
    private String uri;

    private AudioServiceUtil() {
    }

    public static AudioServiceUtil getInstance() {
        TimeUtil.getInstance().add(instance);
        return instance;
    }

    private MediaServiceController controller;
    private Handler handler = new Handler();


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (controller == null) return;
            long progress = controller.getCurrentPosition() % 1000;
            handler.postDelayed(runnable, progress == 0 ? 1000 : progress);
        }
    };


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof AudioService.ServiceBinder) {
                controller = ((AudioService.ServiceBinder) service).getService();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            controller = null;
        }
    };


    public void bindService(Context context) {
        Intent intent = new Intent(context, AudioService.class);
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        handler.post(runnable);
    }

    public void unBindService(Context context) {
        uri = "";
        context.unbindService(connection);
        handler.removeCallbacksAndMessages(null);
    }


    public int start(String uri, Object... listeners) throws IOException {
        if (controller != null) {
            for (Object listener : listeners) controller.addListener(listener);
            if (TextUtils.isEmpty(this.uri) || !this.uri.equals(uri)) {
                this.uri = uri;
            }
            return controller.start(uri);
        }
        return Reset;
    }

    @Override
    public void getTurn() {
        if(controller.getStatus() == Play&&!controller.isPlaying()){
            controller.setStatus(Reset);
            try {
                start(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(controller.getStatus() == Pause &&controller.isPlaying()){
            pause();
        }
    }

    public boolean play() {
        if (TextUtils.isEmpty(uri) || controller == null)
            return false;
        boolean play = controller.play();
        if (!play) {
            try {
                play = Reset != start(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return play;
    }

    public boolean pause() {
        if (controller == null) return false;
        controller.pause();
        return true;
    }

    public long getCurrentPosition() {
        if (controller != null) return controller.getCurrentPosition();
        return 0;
    }

    public long getDuration() {
        if (controller != null) return controller.getDuration();
        return 0;
    }

    public boolean isPlaying() {
        return controller != null && controller.isPlaying();
    }

    public void seekTo(long changeProgress) {
        if (controller != null) {
            controller.seekTo(changeProgress);
        }
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void play(int i) {
        controller.seekTo(i);
        play();
    }

}
