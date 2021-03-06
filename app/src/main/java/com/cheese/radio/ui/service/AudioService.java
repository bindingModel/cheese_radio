package com.cheese.radio.ui.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaButtonReceiver;
import android.text.TextUtils;

import com.binding.model.App;
import com.binding.model.util.BaseUtil;

import java.io.IOException;

import timber.log.Timber;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:19
 * modify developer：  admin
 * modify time：13:19
 * modify remark：
 *
 * @version 2.0
 */


public class AudioService extends Service
        implements
        MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnErrorListener,
        MediaServiceController {
    private MediaPlayer player;
    public static final int Reset = 0;
    public static final int Play = 1;
    public static final int Pause = 2;
    public static final int Release = 3;
    public static final int Prepared = 4;
    public static final int Error = 5;
    public static final int Wait = 6;
    private int focusState = Reset;
    private int state = Reset;
    private boolean live = false;
    private String uri;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener changeListener;
    private boolean looping = false;//是否循环播放

    public class ServiceBinder extends Binder {
        public AudioService getService() {
            return AudioService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        state = Reset;
        player = new MediaPlayer();
        player.setOnBufferingUpdateListener(this);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnSeekCompleteListener(this);
        player.setOnErrorListener(this);
    }

    public void addListener(Object listener) {
        if (listener instanceof MediaPlayer.OnBufferingUpdateListener) {
            player.setOnBufferingUpdateListener((MediaPlayer.OnBufferingUpdateListener) listener);
        } else if (listener instanceof MediaPlayer.OnPreparedListener) {
            player.setOnPreparedListener((MediaPlayer.OnPreparedListener) listener);
        } else if (listener instanceof MediaPlayer.OnCompletionListener) {
            player.setOnCompletionListener((MediaPlayer.OnCompletionListener) listener);
        } else if (listener instanceof MediaPlayer.OnSeekCompleteListener) {
            player.setOnSeekCompleteListener((MediaPlayer.OnSeekCompleteListener) listener);
        } else if (listener instanceof MediaPlayer.OnErrorListener) {
            player.setOnErrorListener((MediaPlayer.OnErrorListener) listener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null)
            player.release();
        state = Release;
        player = null;
        if (audioManager != null)
            audioManager.abandonAudioFocus(changeListener);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.seekTo(0);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Timber.i("code:%1d  extra%2d", what, extra);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        state = Pause;
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        mp.start();
        state = Play;
    }

    @Override
    public void setStatus(int reset) {
        state = reset;
    }

    @Override
    public int getStatus() {
        return state;
    }

    @Override
    public int start(String uri) throws IOException {
        if (player != null) {
            Timber.w(state
                    +">>>>>"+uri);
            switch (state) {
                case Reset:
                    requestAudioFocus();
                    this.uri = uri;
                    live = BaseUtil.isLive(uri);
                    player.reset();
                    player.setDataSource(uri);
                    player.prepareAsync();
                    state = Prepared;
                    break;
                case Play:
                    if (!uri.equals(this.uri)) {
                        state = Reset;
                        return start(uri);
                    }
                    if (player.getCurrentPosition() == player.getDuration())
                        player.seekTo(0);
                case Pause:
                    if (!uri.equals(this.uri)) {
                        state = Reset;
                        return start(uri);
                    }
                case Prepared:
                    player.start();
                    state = Play;
                    break;
            }
        }
        return state;
    }

    @Override
    public boolean play() {
        if (player != null) {
            if (focusState == Release) requestAudioFocus();
            switch (state) {
                case Pause:
                case Prepared:
                    player.start();
                    state = Play;
                    break;
                default:
                    if (!TextUtils.isEmpty(this.uri))
                        try {
                            state = start(this.uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
            }

        }
        return state == Play;
    }

    @Override
    public void pause() {
        if (player != null) {
            player.pause();
            state = Pause;
        }
    }

    @Override
    public long getCurrentPosition() {
        if (player != null) {
            return player.getCurrentPosition();
        }
        return live ? -2 : -1;
    }
    int lastDurationg = 0 ;
    @Override
    public long getDuration() {
        if (player != null &&player.isPlaying()) {
            return lastDurationg=player.getDuration();
        }
        return lastDurationg;
//        return live ? -2 : -1;
    }

    @Override
    public boolean isPlaying() {
        if (player != null) {
            return player.isPlaying();
        }
        return false;
    }

    @Override
    public void seekTo(long changeProgress) {
        if (player.isPlaying()) {
            player.seekTo((int) changeProgress);
            player.start();
        } else {
            player.seekTo((int) changeProgress);
        }
    }


    public AudioManager getAudioManager() {
        if (audioManager == null)
            audioManager = (AudioManager) App.getCurrentActivity().getSystemService(Context.AUDIO_SERVICE);
        return audioManager;
    }

    private boolean requestAudioFocus() {
        ComponentName mbCN = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());
        if (changeListener == null)
            changeListener = (focusChange) -> {
                if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT) {
                    // Pause playback
                    if (state != Play) focusState = Pause;
                    else focusState = Play;
                    pause();
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // Resume playback
                    if (focusState != Play) return;
                    play();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    focusState = Release;
                    pause();
                }
            }
                    ;

        int result = getAudioManager().requestAudioFocus(changeListener,
// Use the music stream.
                AudioManager.STREAM_MUSIC,
// Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            getAudioManager().unregisterMediaButtonEventReceiver(mbCN);
            return true;
        }
        return false;
    }
}
