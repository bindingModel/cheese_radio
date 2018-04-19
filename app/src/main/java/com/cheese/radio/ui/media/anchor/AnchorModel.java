package com.cheese.radio.ui.media.anchor;

import android.Manifest;
import android.databinding.ObservableBoolean;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.android.databinding.library.baseAdapters.BR;
import com.binding.model.App;
import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.adapter.recycler.RecyclerAdapter;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityAnchorBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.anchor.entity.AnchorEntity;
import com.cheese.radio.ui.media.anchor.entity.play.item.AnchorSingleItem;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.util.DataStore;
import com.cheese.radio.util.models.AudioPagerModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(value = R.layout.activity_anchor, model = true)
public class AnchorModel extends AudioPagerModel<AnchorActivity, ActivityAnchorBinding, AnchorEntity, AnchorSingleItem> {

    @Inject
    AnchorModel(@ActivityFragmentManager FragmentManager manager) {
        super(new FragmentAdapter<>(manager));
    }

    @Inject
    RadioApi api;
    private Integer authorId;
    private AnchorParams params;
    private final List<AnchorEntity> list = new ArrayList<>();
    private AnchorData anchorData;
    private AudioServiceUtil util = AudioServiceUtil.getInstance();
    public transient ObservableBoolean checked = new ObservableBoolean();
    private List<PlayEntity> fmsEntities = new ArrayList<>();

    private Integer angle = 0;
    private Handler mHandler = new Handler();
    private final PlayEntity entity = new PlayEntity();
    private ImageView playImage;
    private static final long TIME_UPDATE = 50L;

    @Override
    public void attachView(Bundle savedInstanceState, AnchorActivity anchorActivity) {
        super.attachView(savedInstanceState, anchorActivity);
        authorId = getT().getIntent().getIntExtra(Constant.authorId, 0);
        params = new AnchorParams("info", authorId);
        playImage = getDataBinding().playImage;
        PlayEntity playEntity = new PlayEntity();
        playEntity.setImage(AudioServiceUtil.getInstance().getImage());
        images(playEntity);
        addDisposable(api.getAuthor(params).compose(new RestfulTransformer<>()).subscribe(anchorData -> {
            getDataBinding().setEntity(anchorData);
            setFragment(this.anchorData = anchorData);
            getDataBinding().anchorData.setText(String.format("作品（%1s）", anchorData.getSingle().getList().size()));
        }));

    }


    public void setFragment(AnchorData anchorData) {
        list.add(new AnchorEntity(anchorData));
        list.add(new AnchorEntity(anchorData));
        try {
            accept(list);
            setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String transformUrl(AnchorSingleItem anchorSingleItem) {
        return anchorSingleItem.getUrl();
    }

    @Override
    public RadioButton getPlayView() {
        return null;
    }

    @Override
    public SeekBar getSeekBar() {
        return null;
    }

    public void images(PlayEntity entity) {
        getDataBinding().setImage(entity.getImage());
        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(mRotationRunnable);
    }

    private Runnable mRotationRunnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying()) {
                playImage.setPivotX(playImage.getWidth() / 2);
                playImage.setPivotY(playImage.getHeight() / 2);
                playImage.setRotation(angle++);
            }
            angle = angle < 360 ? angle : 0;
            mHandler.postDelayed(this, TIME_UPDATE);
        }
    };

    public void onToPlayClick(View view) {
        ARouterUtil.navigation(ActivityComponent.Router.play);
    }
}
