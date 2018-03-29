package com.cheese.radio.ui.media.play;

import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;

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
import com.cheese.radio.util.MyBaseUtil;

import java.util.ArrayList;
import java.util.List;

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
    private Integer totalTime, playTime;
    public ObservableBoolean clockCheck = new ObservableBoolean(false);

    @Override
    public void attachView(Bundle savedInstanceState, PlayActivity activity) {
        super.attachView(savedInstanceState, activity);

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
            totalTime = Integer.parseInt(entity.getTime());
            if (totalTime != 0) clockCheck.set(true);
            else clockCheck.set(false);
            popupPlayModel.getWindow().dismiss();
            return false;
        });
    }

    @Override
    public void getTurn() {
        super.getTurn();
        if (totalTime != 0) {
            playTime++;
            getDataBinding().loop.setText(MyBaseUtil.getMinute(totalTime - playTime));
        }
        if (totalTime != 0 & playTime.equals(totalTime)) {
            pause();
            checked.set(false);
        }
    }
}
