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

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(R.layout.activity_play)
public class PlayModel extends AudioModel<PlayActivity, ActivityPlayBinding,PlayEntity> {
    @Inject
    PlayModel() {}
    @Inject RadioApi api;
    private String id;
    @Override
    public void attachView(Bundle savedInstanceState, PlayActivity activity) {
        super.attachView(savedInstanceState, activity);
        id=getT().getIntent().getStringExtra(Constant.id);
        api.getGroupInfo(new PlayParams("groupInfo",id)).compose(new RestfulTransformer<>());
//                .subscribe(this::playFirst,throwable -> BaseUtil.toast(fmsActivity, throwable));
    }

    @Override
    public RadioButton getPlayView(){
        return getDataBinding().play;
    }

    public String transformUrl(PlayEntity entity){
        return "https://bookrental.oss-cn-shanghai.aliyuncs.com/audios/hxxdzg.mp3";
    }

    @Override
    public SeekBar getSeekBar() {
        return getDataBinding().appVideoSeekBar;
    }
}
