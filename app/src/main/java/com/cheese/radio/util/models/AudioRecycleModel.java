package com.cheese.radio.util.models;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.android.databinding.library.baseAdapters.BR;
import com.binding.model.App;
import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.cycle.Container;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.layout.rotate.TimeEntity;
import com.binding.model.layout.rotate.TimeUtil;
import com.binding.model.model.inter.Inflate;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.cheese.radio.ui.service.AudioService.Prepared;

/**
 * Created by 29283 on 2018/3/29.
 */

public abstract class AudioRecycleModel<C extends Container, Binding extends ViewDataBinding, E extends Inflate>
        extends RecyclerModel<C, Binding, E>implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener ,TimeEntity {
    public ObservableField<String> currentTime = new ObservableField<>();
    private int position = 0;
    private List<E> fmsEntities = new ArrayList<>();
    private E entity;
    private AudioServiceUtil util = AudioServiceUtil.getInstance();
    public transient ObservableBoolean checked = new ObservableBoolean();
    private boolean mDragging = false;

    public boolean isPlaying(){
        return util.isPlaying();
    }

    @Override
    public void attachView(Bundle savedInstanceState, C c) {
        super.attachView(savedInstanceState, c);
//        util.pause();
        TimeUtil.getInstance().add(this);
        if(getSeekBar()!=null)getSeekBar().setOnSeekBarChangeListener(this);
    }

    public void onForwardClick(View view) {
        if (position >= fmsEntities.size() - 1) {
            BaseUtil.toast(view, "已经到最后了");
        } else play(fmsEntities.get(++position));
    }

    public void onRewindClick(View view) {
        if (position == 0) BaseUtil.toast(view, "当前为第一个");
        else play(fmsEntities.get(--position));
    }

    public void onPlayClick(View view) {
        boolean playing = util.isPlaying();
        if (!playing) {
            if (!util.play()){
                if(entity!=null)play(entity);
            }else
                checked.set(true);
        } else {
            util.pause();
            checked.set(false);
        }
    }


    public void playFirst(List<E> entities) {
        fmsEntities.addAll(entities);
        for (E entity : entities) {
            play(entity);
            break;
        }
    }

    private final MediaPlayer.OnPreparedListener listener = this::onPrepared;

    public void onPrepared(MediaPlayer mediaPlayer){
        mediaPlayer.start();
        getPlayView().setEnabled(true);
        checked.set(true);
        util.setUri(transformUrl(fmsEntities.get(0)));
    }

    private void play(E entity) {
        this.entity = entity;
        BaseUtil.checkPermission(App.getCurrentActivity(), aBoolean -> {
            if (aBoolean && util.start(transformUrl(entity), this, listener)==Prepared)
                getPlayView().setEnabled(false);
        }, Manifest.permission.RECORD_AUDIO);
        getDataBinding().setVariable(BR.entity, entity);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        checked.set(false);
        if (position == fmsEntities.size() - 1) {
            mediaPlayer.seekTo(0);
        } else{
            onForwardClick(null);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        super.onResume();
        checked.set(util.isPlaying());
    }

    public void onDestroy() {
        super.onDestroy();
        TimeUtil.getInstance().remove(this);
    }

    protected abstract String transformUrl(E entity);

    public abstract RadioButton getPlayView();

    public static String stringForTime(long timeMs) {
        long totalSecond = timeMs / 1000;
        long second = totalSecond % 60;
        long minutes = (totalSecond / 60) % 60;
        long hour = totalSecond / 3600;
        if (hour > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d", hour, minutes, second);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d",  minutes, second);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if(!mDragging)return;
        long position = util.getDuration()*progress/1000L;
        util.seekTo(position);
        setProgress();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mDragging = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mDragging = false;
        setProgress();
    }

    public abstract SeekBar getSeekBar();

    private void setProgress() {
        if(mDragging)return;
        long duration = util.getDuration();
        if(duration==0)duration=1;
        if(getSeekBar()!=null)getSeekBar().setProgress((int)(1000*util.getCurrentPosition()/duration));
        currentTime.set(stringForTime(util.getCurrentPosition()));
    }

    @Override
    public void getTurn() {
        setProgress();
    }

    public void setEntities(List<E> entities) {
        this.fmsEntities.addAll(entities);
        for (E entity : entities) {
            this.entity = entity;
            getDataBinding().setVariable(BR.entity, entity);
            break;
        }
    }

}


