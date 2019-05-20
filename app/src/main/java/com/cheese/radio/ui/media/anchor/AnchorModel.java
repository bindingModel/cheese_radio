package com.cheese.radio.ui.media.anchor;

import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.model.ModelView;
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
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.util.models.AudioPagerModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by 29283 on 2018/3/13.
 */
@ModelView(value = R.layout.activity_anchor, model = true)
public class AnchorModel extends AudioPagerModel<AnchorActivity, ActivityAnchorBinding, AnchorEntity, PlayEntity> {

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
        if (authorId==0){
            authorId = getT().getIntent().getIntExtra(Constant.id, 0);
        }
        params = new AnchorParams("info", authorId);
        playImage = getDataBinding().playImage;
        PlayEntity playEntity = new PlayEntity();
        playEntity.setImage(AudioServiceUtil.getInstance().getImage());
        images(playEntity);
        addDisposable(api.getAuthor(params).compose(new RestfulTransformer<>()).subscribe(anchorData -> {
            getDataBinding().setEntity(anchorData);
            setFragment(this.anchorData = anchorData);
            getDataBinding().anchorData.setText(String.format("作品（%1s）", anchorData.getCount()));
        },BaseUtil::toast));

    }


    public void setFragment(AnchorData anchorData) {
        list.add(new AnchorEntity(anchorData));
        list.add(new AnchorEntity(anchorData));
        try {
            onNext(list);
            setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String transformUrl(PlayEntity anchorSingleItem) {
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

    public void onShowPictureClick(View view){
        Bundle bundle=new Bundle();
        ArrayList<String> list=new ArrayList<>();
        list.add( anchorData.getImage());
        bundle.putStringArrayList(Constant.urlList,list);
        ARouterUtil.navigation(ActivityComponent.Router.picture,bundle);
    }


}
