package com.cheese.radio.ui.home;

import android.Manifest;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.android.databinding.library.baseAdapters.BR;
import com.binding.model.adapter.ILayoutAdapter;
import com.binding.model.adapter.pager.FragmentStateAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.layout.rotate.TimeEntity;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Entity;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityHomeBinding;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.IkeApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.binding.model.adapter.AdapterType.refresh;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(R.layout.activity_home)
public class HomeModel extends PagerModel<HomeActivity, ActivityHomeBinding, HomeEntity>
        implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener, TimeEntity {
    public ObservableBoolean checked = new ObservableBoolean();
    private int position = 0;
    private List<Entity> fmsEntities = new ArrayList<>();
    private Entity entity;
    private final List<HomeEntity> list = new ArrayList<>();
    public ObservableInt currentItem = new ObservableInt();
    private int lastPosition;

    @Inject
    HomeModel(@ActivityFragmentManager FragmentManager fm) {
        super(new FragmentStateAdapter<>(fm));
    }

    @Override
    public void attachView(Bundle savedInstanceState, HomeActivity activity) {
        super.attachView(savedInstanceState, activity);
        if (list.isEmpty())
            for (int i = 0; i < 4; i++)
                list.add(new HomeEntity());
        getAdapter().setList(0, list, refresh);
        currentItem.set(0);
    }

    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

    }

    public void onForwardClick(View view) {
        if (position >= fmsEntities.size() - 1) {
            BaseUtil.toast(view, "已经到最后了");
        } else play(fmsEntities.get(++position));
    }

    private void play(Entity entity) {
    }

    public void onPlayClick(View view) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        checked.set(false);
        if (position == fmsEntities.size() - 1) {
            mediaPlayer.seekTo(0);
        } else {
            onForwardClick(null);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void getTurn() {

    }
}
