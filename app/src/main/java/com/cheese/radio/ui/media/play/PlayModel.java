package com.cheese.radio.ui.media.play;

import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(R.layout.activity_play)
public class PlayModel extends AudioModel<PlayActivity, ActivityPlayBinding,PlayEntity> {
    @Inject
    PlayModel() {}
    @Inject RadioApi api;
    public final List<PlayEntity> list = new ArrayList<>();
    private Integer id;
    @Override
    public void attachView(Bundle savedInstanceState, PlayActivity activity) {
        super.attachView(savedInstanceState, activity);
        id=getT().getIntent().getIntExtra(Constant.id,0);
        api.getContentInfo(new PlayParams("contentInfo",id)).compose(new RestfulTransformer<>()).subscribe(
                this::setSingelEntity,throwable -> BaseUtil.toast(getT(),throwable));
//        api.getGroupInfo(new PlayParams("groupInfo",id)).compose(new RestfulTransformer<>()).subscribe();
//                .subscribe(this::playFirst,throwable -> BaseUtil.toast(fmsActivity, throwable));
    }

    @Override
    public RadioButton getPlayView(){
        return getDataBinding().play;
    }

    public String transformUrl(PlayEntity entity){
        return entity.getUrl();
    }


    @Override
    public SeekBar getSeekBar() {
        return getDataBinding()==null?null:getDataBinding().appVideoSeekBar;
    }

    public  void setSingelEntity(PlayEntity entity){
        list.add(entity);
        if(isPlaying())setEntities(list);
        else playFirst(list);
        getDataBinding().setEntity(entity);
    }
}
