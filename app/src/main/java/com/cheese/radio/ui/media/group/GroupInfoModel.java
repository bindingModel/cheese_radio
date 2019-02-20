package com.cheese.radio.ui.media.group;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.binding.model.adapter.pager.FragmentAdapter;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityGroupInfoBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.group.fragment.GroupData;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.util.models.AudioPagerModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/21.
 */
@ModelView(value = R.layout.activity_group_info,model = true)
public class GroupInfoModel extends AudioPagerModel<GroupInfoActivity, ActivityGroupInfoBinding, GroupEntity, PlayEntity> {

    @Inject
    GroupInfoModel(@ActivityFragmentManager FragmentManager manager) {
        super(new FragmentAdapter<>(manager));
    }

    @Inject
    RadioApi api;
    private GroupInfoParams params = new GroupInfoParams("groupInfo");
    private final List<GroupEntity> list = new ArrayList<>();
    private ImageView playImage;
    private Integer angle = 0;
    private Handler mHandler = new Handler();
    private static final long TIME_UPDATE = 50L;

    @Override
    public void attachView(Bundle savedInstanceState, GroupInfoActivity activity) {
        super.attachView(savedInstanceState, activity);
        Integer groupInfoId = getT().getIntent().getIntExtra(Constant.id, -1);
        params.setId(groupInfoId);
        playImage = getDataBinding().playImage;
        PlayEntity playEntity = new PlayEntity();
        playEntity.setImage(AudioServiceUtil.getInstance().getImage());
        images(playEntity);
        addDisposable(api.getGroupInfo(params).compose(new RestfulTransformer<>()).subscribe(
                groupData -> {
                    getDataBinding().setEntity(groupData);
                    setFragment(groupData);
                    getDataBinding().storyTitle.setText(String.format("作品（%1s）", groupData.getContentList().size()));
                }
        ));

    }

    @Override
    protected String transformUrl(PlayEntity anchorSingleItem) {
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

    public void setFragment(GroupData groupData) {
        list.add(new GroupEntity(groupData));
        list.add(new GroupEntity(groupData));
        try {
            onNext(list);
            setCurrentItem(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void images(PlayEntity entity) {
        getDataBinding().setImage(entity.getImage());
        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(mRotationRunnable);
    }
    public void onToPlayClick(View view) {
        ARouterUtil.navigation(ActivityComponent.Router.play);
    }

}
