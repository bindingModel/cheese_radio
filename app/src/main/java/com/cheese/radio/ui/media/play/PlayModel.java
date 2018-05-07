package com.cheese.radio.ui.media.play;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityPlayBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.audio.AudioModel;
import com.cheese.radio.ui.media.play.popup.PopupPlayModel;
import com.cheese.radio.ui.media.play.popup.SelectPlayTimeEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.ui.user.enroll.PayResult;
import com.cheese.radio.ui.user.params.AddFavorityParams;
import com.cheese.radio.ui.user.params.FabulousParams;
import com.cheese.radio.util.MyBaseUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.cheese.radio.util.MyBaseUtil.GetLocalOrNewBitmap;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(R.layout.activity_play)
public class PlayModel extends AudioModel<PlayActivity, ActivityPlayBinding, PlayEntity> implements UMShareListener {
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
    public ObservableField<String> currentText = new ObservableField<>();//存放定时播放的剩余时间。如果需要，去界面绑定
    public ObservableBoolean clockCheck = new ObservableBoolean(false);
    private SelectPlayTimeEntity timeEntity;
    private AudioServiceUtil util;


    @Override
    public void attachView(Bundle savedInstanceState, PlayActivity activity) {
        super.attachView(savedInstanceState, activity);
        intTimes();
        iniView();
        if (id != 0) addDisposable(api.getContentInfo(new PlayParams("contentInfo", id))
                .compose(new RestfulTransformer<>()).subscribe(
                        this::setSingelEntity, throwable -> BaseUtil.toast(getT(), throwable)));
        initPopupPlayModel(savedInstanceState);
    }

    @Override
    public RadioButton getPlayView() {
        if (getDataBinding()!=null)
        return getDataBinding().play;
        return null;
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
        AudioServiceUtil.getInstance().setImage(entity.getImage());
        AudioServiceUtil.getInstance().setFileId(entity.getFileId());
        if (isPlaying()) setEntities(list);
        else playFirst(list);
        getDataBinding().setEntity(entity);
        getDataBinding().html5Desc.setText(Html.fromHtml(entity.getAnchorBrief()));
        if (!list.isEmpty()) Model.dispatchModel(Constant.images, list.get(0));
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
                totalTime = duration;
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
        } else clockCheck.set(true);
    }

    public void intTimes() {
        playTime = -1;
        totalTime = -1;
        timeEntity = new SelectPlayTimeEntity(null, -1);
        util = AudioServiceUtil.getInstance();
        util.setOnTimingListener((current, duration) -> {
            if (current != 0) {
                playTime = current;
                totalTime = duration;
            }
        });
    }

    private void iniView() {
        id = getT().getIntent().getIntExtra(Constant.id, 0);
        if (id != 0) AudioServiceUtil.getInstance().setId(id);
        else if (AudioServiceUtil.getInstance().getId() != 0)
            id = AudioServiceUtil.getInstance().getId();
    }

    public void onAddFavorityClick(View view) {
        if (id == 0) return;
        AddFavorityParams params = new AddFavorityParams("addFavority");
        params.setId(AudioServiceUtil.getInstance().getId());
        api.addFavority(params).compose(new RestfulTransformer<>()).subscribe(
        );
    }

    public void onFabuClick(View view) {
        if (id == 0) return;
        FabulousParams params = new FabulousParams("addFabulous");
        params.setId(AudioServiceUtil.getInstance().getId());
        api.addFabulous(params).compose(new RestfulTransformer<>()).subscribe(

        );
    }

    public void onShareClick(View view) {
        String musicUR = null;
        if (list.size() == 0) return;
        PlayEntity entity = list.get(0);
//        Bitmap bipmap= MyBaseUtil.GetLocalOrNewBitmap(entity.getUrl());
        addDisposable(Observable.create((ObservableOnSubscribe<UMusic>) e -> {
                    UMusic music = new UMusic(entity.getUrl());
                    UMImage image = new UMImage(getT(), entity.getImage());
                    music.setTitle(entity.getTitle());
                    music.setThumb(image);
                    music.setDescription(entity.getSubTitle());
            {

            }
                    e.onNext(music);
                }
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe((uMusic -> {
            ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig               config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);//设置位置
            config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
            config.setCancelButtonVisibility(true);
            config.setTitleVisibility(false);
            config.setShareboardBackgroundColor(Color.WHITE);
            new ShareAction(getT())
                    .withMedia(uMusic)
                    .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.SINA)
                    .setCallback(this).open(config);
        }), BaseUtil::toast));


    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        Toast.makeText(getT(), "失                                            败" + throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }
}

