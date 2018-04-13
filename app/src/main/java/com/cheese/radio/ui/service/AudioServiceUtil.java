package com.cheese.radio.ui.service;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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
    private int duration = -1;
    private int current = 0;
    private String uri;
    private OnTimingListener onTimingListener;
    private String image;
    private Integer id;
    private Integer fileId;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id=id!=null?id:0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setDuration(int duration){
        setDuration(duration,null);
    }

    public void setDuration(int duration,OnTimingListener onTimingListener) {
        this.duration = duration;
        current = 0;
        this.onTimingListener = onTimingListener;
    }

    public void setOnTimingListener(OnTimingListener onTimingListener){
        this.onTimingListener=onTimingListener;
    }
    @Override
    public void getTurn() {
        if (controller == null) return;
        if (controller.isPlaying()) {
            if (controller.getStatus() == Pause) pause();
            else if (duration>-1 && controller.getStatus() == Play) {
                if(++current > duration){
                    controller.setStatus(Pause);
                    current = 0;
                    duration = -1;
                }
                if(onTimingListener!=null)onTimingListener.onTiming(current,duration);
            }
        } else if (controller.getStatus() == Play) {
            controller.setStatus(Reset);
            try {
                start(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
