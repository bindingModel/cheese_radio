package com.cheese.radio.ui.home;

   import android.databinding.ObservableInt;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.binding.model.cycle.DataBindingFragment;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Entity;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityHomeBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.media.audio.AudioModel;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.ui.startup.check.CheckUpdateModel;
import com.cheese.radio.ui.startup.check.VersionEntity;
import com.cheese.radio.ui.startup.check.VersionParams;
import com.cheese.radio.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/2/22.
 */
@ModelView(value = R.layout.activity_home, model = true)
public class HomeModel extends AudioModel<HomeActivity, ActivityHomeBinding, PlayEntity> implements RadioGroup.OnCheckedChangeListener {
    //    public ObservableBoolean playing_check = new ObservableBoolean();
    private static final long TIME_UPDATE = 50L;

    private int position = 0;
    private final List<Entity> fmsEntities = new ArrayList<>();
    private final List<HomeEntity> list = new ArrayList<>();
    public ObservableInt currentItem = new ObservableInt();
    private int currentTab = -1;
    private FragmentManager fm;
    private ImageView playImage;
    private Integer angle = 0;
    private Handler mHandler = new Handler();
    @Inject
    CheckUpdateModel popupUpdate;

    @Inject
    HomeModel() {
    }

    @Inject
    RadioApi api;
    VersionParams params;
    private VersionEntity versionEntity;

    @Override
    public void attachView(Bundle savedInstanceState, HomeActivity activity) {
        super.attachView(savedInstanceState, activity);
        NetUtil.checkNetType(getT());
        playImage = getDataBinding().playImage;
        initFragment();
        initPopup(savedInstanceState);
    }

    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int position = group.indexOfChild(group.findViewById(checkedId));
        checkFragment(position);
    }

    public void setCurrentItem(Integer position){
        currentItem.set(position);
    }

    private void checkFragment(Integer position) {
        if (position < 0 || position >= list.size() ||position ==currentTab) return;
        FragmentTransaction ft = fm.beginTransaction();
        //UI建议不要设置这个转场动画
//        if (position < currentTab)
//            ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out);
//        else ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out);
        if (currentTab >= 0) {
            DataBindingFragment beforeFragment = list.get(currentTab).getItem(position, getDataBinding().homeFrame);
            beforeFragment.onPause();
            ft.remove(beforeFragment);
        }
        DataBindingFragment fragment = list.get(position).getItem(position, getDataBinding().homeFrame);
        ViewGroup viewGroup = getDataBinding().homeFrame;
        for (int i = 0; i < viewGroup.getChildCount() - list.size(); i++)
            viewGroup.removeView(viewGroup.getChildAt(i));
        if (fragment.isAdded()) fragment.onResume();
        else ft.add(R.id.home_frame, fragment);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
        currentTab = position;
    }

    @Override
    protected String transformUrl(PlayEntity playEntity) {
        return playEntity.getUrl();
    }

    @Override
    public RadioButton getPlayView() {
        return null;
    }

    @Override
    public SeekBar getSeekBar() {
        return null;
    }

    @Override
    public TextView getLength() {
        return null;
    }

    //
//    public void onForwardClick(View view) {
//        if (position >= fmsEntities.size() - 1) {
//            BaseUtil.toast(view, "已经到最后了");
//        } else play(fmsEntities.get(++position));
//    }
//
//

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        checked.set(false);
        if (position == fmsEntities.size() - 1) {
            mediaPlayer.seekTo(0);
        } else {
            onForwardClick(null);
        }
    }

    private void initFragment() {
        if (list.isEmpty())
            for (int i = 0; i < 4; i++)
                list.add(new HomeEntity());
        fm = getT().getSupportFragmentManager();
        RadioGroup radioGroup = getDataBinding().radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(radioGroup.getChildAt(0).getId());//check the first button

        currentItem.set(0);
    }

    @Override
    public void getTurn() {
        super.getTurn();
        if (playImage != null && isPlaying()) {

        }
    }

    @Override
    public void showButtonNotify() {

    }

    @Override
    public void cancelButtonNotiy() {
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
            mHandler.hasMessages(0);
            mHandler.postDelayed(this, TIME_UPDATE);
        }
    };


    @Override
    public void onPlayClick(View view) {
        super.onPlayClick(view);
        if (isPlaying()) mHandler.post(mRotationRunnable);
        else mHandler.removeCallbacks(mRotationRunnable);
    }


    //跳转到播放界面
    public void onToPlayClick(View view) {
        ARouterUtil.navigation(ActivityComponent.Router.play);
    }

    public void images(PlayEntity entity) {
        getDataBinding().setEntity(entity);
        AudioServiceUtil.getInstance().setImage(entity.getImage());
        util.setId(entity.getId());
        getDataBinding().playBg.setVisibility(View.GONE);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(mRotationRunnable);
    }

    private void initPopup(Bundle savedInstanceState) {
        params = new VersionParams("version");
        params.setOs("android");
        params.setVersion(String.valueOf(BuildConfig.VERSION_CODE));
        popupUpdate.attachContainer(getT(), (ViewGroup) getDataBinding().getRoot(), false, savedInstanceState);
        popupUpdate.getWindow().setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupUpdate.getWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupUpdate.getWindow().setAnimationStyle(R.style.contextMenuAnim);
        addDisposable(api.version(params).compose(new RestfulTransformer<>()).subscribe((versionEntity -> {
            if (versionEntity.getUpdate() == 1) {
                if (!TextUtils.isEmpty(versionEntity.getMessage())) popupUpdate.message.set(versionEntity.getMessage());
                if(!TextUtils.isEmpty(versionEntity.getUrl())) popupUpdate.setURL(versionEntity.getUrl());
                if(versionEntity.getUpdate() == 1 )popupUpdate.setOnDismissListener(null);
                popupUpdate.show(window -> window.showAtLocation(getDataBinding().getRoot(), Gravity.CENTER, 0, 0));
            }
        }), BaseUtil::toast));
    }

}
