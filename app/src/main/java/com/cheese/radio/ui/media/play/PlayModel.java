package com.cheese.radio.ui.media.play;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityPlayBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.audio.AudioModel;
import com.cheese.radio.ui.media.play.popup.PopupPlayModel;
import com.cheese.radio.ui.media.play.popup.SelectPlayTimeEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.ui.user.addfavority.AddFavorityParams;
import com.cheese.radio.util.MyBaseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(R.layout.activity_play)
public class PlayModel extends AudioModel<PlayActivity, ActivityPlayBinding, PlayEntity> {
    @Inject
    PlayModel() {
    }

    @Inject
    RadioApi api;
    @Inject
    PopupPlayModel popupPlayModel;
    public final List<PlayEntity> list = new ArrayList<>();
    private Integer id;
    private Integer playTime, totalTime;
    public ObservableField<String> currentText = new ObservableField<>();
    public ObservableBoolean clockCheck = new ObservableBoolean(false);
    private SelectPlayTimeEntity timeEntity;
    private AudioServiceUtil util;

    @Override
    public void attachView(Bundle savedInstanceState, PlayActivity activity) {
        super.attachView(savedInstanceState, activity);
        intTimes();
        id = getT().getIntent().getIntExtra(Constant.id, 0);
        api.getContentInfo(new PlayParams("contentInfo", id)).compose(new RestfulTransformer<>()).subscribe(
                this::setSingelEntity, throwable -> BaseUtil.toast(getT(), throwable));
        initPopupPlayModel(savedInstanceState);
    }

    @Override
    public RadioButton getPlayView() {
        return getDataBinding().play;
    }

    public String transformUrl(PlayEntity entity) {
        return entity.getUrl();
    }


    @Override
    public SeekBar getSeekBar() {
        return getDataBinding() == null ? null : getDataBinding().appVideoSeekBar;
    }

    @Override
    public TextView getLength() {
        return getDataBinding() == null ? null : getDataBinding().length;
    }

    public void setSingelEntity(PlayEntity entity) {
        list.add(entity);
        if (isPlaying()) setEntities(list);
        else playFirst(list);
        getDataBinding().setEntity(entity);
    }

    public void onSelectClick(View view) {
        popupPlayModel.show(window -> window.showAtLocation(getDataBinding().getRoot(), Gravity.BOTTOM, 0, 0));
    }

    private void initPopupPlayModel(Bundle savedInstanceState) {
        popupPlayModel.attachContainer(getT(), (ViewGroup) getDataBinding().getRoot(), false, savedInstanceState);
        popupPlayModel.getWindow().setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupPlayModel.getWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置播放时长
        popupPlayModel.addEventAdapter((position, entity, type, view) -> {
            //判断如果不是同一个，则将上一个状态取反,单选操作
            if (timeEntity != null && !timeEntity.equals(entity)) {
                timeEntity.checked.set(false);
            }
            timeEntity = entity;
            util.setDuration(timeEntity.getTime(), (current, duration) -> {
                if (current != 0)
                    playTime = current;
                    totalTime=duration;
            });
            return false;
        });
    }

    @Override
    public void getTurn() {
        super.getTurn();
        if (totalTime != -1) {
            currentText.set(MyBaseUtil.getMinute(totalTime - playTime));
        }
        if (totalTime == -1) {
            clockCheck.set(false);
        }else clockCheck.set(true);
    }

    public void intTimes() {
        playTime = -1;
        totalTime = -1;
        timeEntity = new SelectPlayTimeEntity(null, -1);
        util = AudioServiceUtil.getInstance();
        util.setOnTimingListener((current, duration) -> {
            if (current != 0) {
                playTime = current;
                totalTime=duration;
            }
        });
    }

    private void iniView() {

    }

    public void onAddFavorityClick(View view) {
        api.addFavority(new AddFavorityParams("addFavority")).compose(new RestfulTransformer<>()).subscribe(
        );
    }
}

