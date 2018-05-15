package com.cheese.radio.ui.media.anchors;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityAnchorsBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.util.DataStore;
import com.cheese.radio.util.models.AudioRecycleModel;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/8.
 */
@ModelView(value = R.layout.activity_anchors,model = true)
public class AnchorsModel extends AudioRecycleModel<AnchorsActivity,ActivityAnchorsBinding,AnchorsItem> {

    private AnchorsParams params=new AnchorsParams();
    @Inject
    AnchorsModel() {
    }
    @Inject
    RadioApi api;

    private Integer angle = 0;
    private Handler mHandler = new Handler();
    private final PlayEntity entity=new PlayEntity();
    private ImageView playImage;
    private static final long TIME_UPDATE = 50L;

    @Override
    public void attachView(Bundle savedInstanceState, AnchorsActivity anchorsActivity) {
        super.attachView(savedInstanceState, anchorsActivity);
        getDataBinding().layoutRecycler.setVm(this);
        playImage=getDataBinding().playImage;
        entity.setImage(AudioServiceUtil.getInstance().getImage());
        images(entity);
        setPageFlag(false);
        setRcHttp((offset1, refresh) -> api.getAnchors(params).compose(new RestfulTransformer<>()));
    }

    @Override
    protected String transformUrl(AnchorsItem entity) {
        return null;
    }

    @Override
    public RadioButton getPlayView() {
        return null;
    }

    @Override
    public SeekBar getSeekBar() {
        return null;
    }


    private Runnable mRotationRunnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying()) {
                playImage.setPivotX(playImage.getWidth() / 2);
                playImage.setPivotY(playImage.getHeight() / 2);
                playImage.setRotation(angle++);
            }
            angle=angle<360?angle:0;
            mHandler.postDelayed(this, TIME_UPDATE);
        }
    };
    //跳转到播放界面
    public void onToPlayClick(View view){
        ARouterUtil.navigation(ActivityComponent.Router.play);
    }
    public void images(PlayEntity entity) {
        getDataBinding().setEntity(entity);
        AudioServiceUtil.getInstance().setImage(entity.getImage());
        getDataBinding().playBg.setVisibility(View.GONE);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(mRotationRunnable);
    }
}
