package com.cheese.radio.ui.home;

import android.app.AlertDialog;
import android.animation.ObjectAnimator;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.binding.model.adapter.pager.FragmentStateAdapter;
import com.binding.model.cycle.DataBindingFragment;
import com.binding.model.layout.pager.PagerModel;
import com.binding.model.layout.rotate.TimeEntity;
import com.binding.model.layout.rotate.TimeUtil;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Entity;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityHomeBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.inject.qualifier.manager.ActivityFragmentManager;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.media.audio.AudioModel;
import com.cheese.radio.ui.media.play.PlayEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.util.DataStore;
import com.cheese.radio.util.MyBaseUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static android.provider.Settings.Global.getString;
import io.reactivex.disposables.Disposable;

import static com.binding.model.adapter.AdapterType.refresh;
import static com.binding.model.util.BaseUtil.T;

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
    HomeModel() {
    }

    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, HomeActivity activity) {
        super.attachView(savedInstanceState, activity);
        //new Date()指定日期时，year need to minus 1900 ，month neet to minus 1,day just day，
        if(System.currentTimeMillis()> new Date(2018-1900,3,15+7).getTime()){
            TimeUtil.getInstance().add(this);
            new AlertDialog.Builder(getT())
                    .setCancelable(false)
                    .setTitle("版本已过期")
                    .setMessage("现在去更新？")
                    .setNegativeButton("取消", (dialog1, which) -> {
                        dialog1.dismiss();
                        finish();
                    })
                    .show()
                    .setOnDismissListener(dialog1 -> finish());
        } else {
            playImage = getDataBinding().playImage;
            api.getCanBook(new CanBookParams("canBook")).compose(new RestfulTransformer<>()).
                    subscribe(canBookData -> IkeApplication.getUser().setCanBookCheck(canBookData.isResult())
                    );
            initFragment();
        }
    }

    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int position = group.indexOfChild(group.findViewById(checkedId));
        checkFragment(position);
    }

    private void checkFragment(int position) {
        if (position < 0 || position >= list.size()) return;
        FragmentTransaction ft = fm.beginTransaction();
        if (position < currentTab)
            ft.setCustomAnimations(R.anim.push_left_in, R.anim.push_right_out);
        else ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out);
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

    public void images(PlayEntity entity) {
        getDataBinding().setEntity(entity);
        AudioServiceUtil.getInstance().setImage(entity.getImage());
        getDataBinding().playBg.setVisibility(View.GONE);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(mRotationRunnable);
    }

    @Override
    public void onPlayClick(View view) {
        super.onPlayClick(view);
        if (isPlaying()) mHandler.post(mRotationRunnable);
        else mHandler.removeCallbacks(mRotationRunnable);
    }

    public void onToPlayClick(View view){
        ARouterUtil.navigation(ActivityComponent.Router.play);
    }
}
