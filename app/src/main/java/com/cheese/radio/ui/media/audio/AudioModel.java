package com.cheese.radio.ui.media.audio;

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
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;
import com.binding.model.App;
import com.binding.model.cycle.Container;
import com.binding.model.layout.rotate.TimeEntity;
import com.binding.model.layout.rotate.TimeUtil;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.ui.service.AudioServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.cheese.radio.ui.service.AudioService.Prepared;

/**
 * Created by 29283 on 2018/3/17.
 */


public abstract class AudioModel<T extends Container, Binding extends ViewDataBinding, Entity>
        extends ViewModel<T, Binding>
        implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, TimeEntity {

    public ObservableField<String> currentTime = new ObservableField<>();
    private int position = 0;
    private List<Entity> fmsEntities = new ArrayList<>();
    private Entity entity;
    private AudioServiceUtil util = AudioServiceUtil.getInstance();
    public transient ObservableBoolean checked = new ObservableBoolean();
    private boolean mDragging = false;
    public static ObservableBoolean loop=new ObservableBoolean(false);
    public boolean isPlaying() {
        return util.isPlaying();
    }

    @Override
    public void attachView(Bundle savedInstanceState, T t) {
        super.attachView(savedInstanceState, t);
//        util.pause();
        TimeUtil.getInstance().add(this);
        if (getSeekBar() != null) getSeekBar().setOnSeekBarChangeListener(this);
    }

    //播放下一首
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
            if (!util.play()) {
                if (entity != null) play(entity);
            } else
                checked.set(true);
                showButtonNotify();
        } else {
            util.pause();
            checked.set(false);
        }

    }

    //无论是否在播放，都强制暂停
    public void pause() {
        boolean playing = util.isPlaying();
        util.pause();
        checked.set(false);
    }


    public void playFirst(List<Entity> entities) {
        fmsEntities.addAll(entities);
        for (Entity entity : entities) {
            play(entity);
            break;
        }
    }

    private final MediaPlayer.OnPreparedListener listener = this::onPrepared;

    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        if (getPlayView() != null)
            getPlayView().setEnabled(true);
        checked.set(true);
        util.setUri(transformUrl(fmsEntities.get(0)));
    }

    protected void play(Entity entity) {
        this.entity = entity;
        BaseUtil.checkPermission(App.getCurrentActivity(), aBoolean -> {
            if (aBoolean && util.start(transformUrl(entity), this, listener) == Prepared)
                getPlayView().setEnabled(false);
        }, Manifest.permission.RECORD_AUDIO);
        getDataBinding().setVariable(BR.entity, entity);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        checked.set(false);
        if (position == fmsEntities.size() - 1) {
            mediaPlayer.seekTo(0);
        } else {
            onForwardClick(null);
        }
        //校准播放按钮
        onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        checked.set(util.isPlaying());
    }

    public void onDestroy() {
        super.onDestroy();
        TimeUtil.getInstance().remove(this);
    }

    protected abstract String transformUrl(Entity entity);

    public abstract RadioButton getPlayView();

    public static String stringForTime(long timeMs) {
        long totalSecond = timeMs / 1000;
        long second = totalSecond % 60;
        long minutes = (totalSecond / 60) % 60;
        long hour = totalSecond / 3600;
        if (hour > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d", hour, minutes, second);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d", minutes, second);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        if (!mDragging) return;
        long position = util.getDuration() * progress / 1000L;
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

    public abstract TextView getLength();

    private void setProgress() {
        if (mDragging) return;
        long duration = util.getDuration();
        if (duration == 0) duration = 1;
        if (getSeekBar() != null)
            getSeekBar().setProgress((int) (1000 * util.getCurrentPosition() / duration));
        if (getLength() != null)
            getLength().setText(stringForTime(util.getDuration()));
        currentTime.set(stringForTime(util.getCurrentPosition()));
    }

    @Override
    public void getTurn() {
        setProgress();
    }

    public void setEntities(List<Entity> entities,int position) {
        this.fmsEntities.addAll(entities);
        int count = 0;
        for (Entity entity : entities) {
            if(count++!=position)continue;
            this.entity = entity;
            play(entity);
            getDataBinding().setVariable(BR.entity, entity);
            break;
        }
    }
    public abstract void showButtonNotify();
    public abstract void cancelButtonNotiy();
    protected void addEntity(Entity entity){
        fmsEntities.add(entity);
    }
    protected List<Entity> getList(){
        return fmsEntities;
    }
    protected Entity getEntity(){
        return entity;
    };

}
