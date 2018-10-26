package com.cheese.radio.ui.media.play;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.cheese.radio.BuildConfig;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityPlayBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.CheeseApplication;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.audio.AudioModel;
import com.cheese.radio.ui.media.play.popup.PopupPlayModel;
import com.cheese.radio.ui.media.play.popup.SelectPlayTimeEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.ui.user.params.AddFavorityParams;
import com.cheese.radio.ui.user.params.FabulousParams;
import com.cheese.radio.util.MyBaseUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.binding.model.adapter.AdapterType.select;
import static com.cheese.radio.ui.Constant.ACTION_BUTTON;
import static com.cheese.radio.ui.Constant.BUTTON_CANCEL_ID;
import static com.cheese.radio.ui.Constant.BUTTON_NEXT_ID;
import static com.cheese.radio.ui.Constant.BUTTON_PALY_ID;
import static com.cheese.radio.ui.Constant.INTENT_BUTTONID_TAG;

/**
 * Created by 29283 on 2018/3/17.
 */
@ModelView(value = R.layout.activity_play, model = true)
public class PlayModel extends AudioModel<PlayActivity, ActivityPlayBinding, PlayEntity> implements UMShareListener {
    @Inject
    PlayModel() {
    }

    @Inject
    RadioApi api;
    @Inject
    PopupPlayModel popupPlayModel;
    //    public final List<PlayEntity> list = new ArrayList<>();
    private Integer id;
    private Integer playTime, totalTime;
    public ObservableField<String> currentText = new ObservableField<>();//存放定时播放的剩余时间。如果需要，去界面绑定
    public ObservableBoolean clockCheck = new ObservableBoolean(false);
    private SelectPlayTimeEntity timeEntity;
    private AudioServiceUtil util;
    private FutureTarget<Bitmap> notifyImage;
    private NotificationManager mNotificationManager;
    private PlayInOrderParams nextMusic = new PlayInOrderParams("playInOrder");
    private int position = 0;

    @Override
    public void attachView(Bundle savedInstanceState, PlayActivity activity) {
        super.attachView(savedInstanceState, activity);
        initTimes();
        initID();
        initEntity();
        initPopupPlayModel(savedInstanceState);
//        getDataBinding().appVideoSeekBar.setEnabled(false)
      /*  try {
            util.start("", (MediaPlayer.OnCompletionListener)this::onCompletion);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * 如果有list 优先播放list
     * 如果有id则播放，没有则随机（随机值可以为0）；
     */
    private void initEntity() {
        List<PlayEntity> playList = getT().getIntent().getParcelableArrayListExtra(Constant.playList);
        int position = getT().getIntent().getIntExtra(Constant.indexOf,0);
        if (playList!=null&&!playList.isEmpty()) {
            setEntities(playList,position);
        } else if (id != 0) addDisposable(api.getContentInfo(new PlayParams("contentInfo", id))
                .compose(new RestfulTransformer<>()).subscribe(
                        this::setSingelEntity, BaseUtil::toast));
        else onNextClick(getDataBinding().getRoot());
    }


    @Override
    public RadioButton getPlayView() {
        if (getDataBinding() != null)
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
        if (entity.getId() != id) id = entity.getId();
        if (!getList().contains(entity))
            addEntity(entity);
        playTime = 0;
        util.pause();
        AudioServiceUtil.getInstance().setImage(entity.getImage());
        AudioServiceUtil.getInstance().setFileId(entity.getFileId());
        getDataBinding().setEntity(entity);
        getDataBinding().html5Desc.setText(Html.fromHtml(entity.getAnchorBrief()));
        play(entity);
        if (!getList().isEmpty()) {
            Model.dispatchModel(Constant.images, entity);
            addDisposable(Observable.just(Glide.with(getT()).asBitmap().load(entity.getImage()).submit())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(image -> {
                        notifyImage = image;
                        showButtonNotify();
                    }));
        }
    }

    public void onSelectClick(View view) {
        popupPlayModel.show(window -> window.showAtLocation(getDataBinding().getRoot(), Gravity.BOTTOM, 0, 0));
    }

    private void initPopupPlayModel(Bundle savedInstanceState) {
        popupPlayModel.attachContainer(getT(), (ViewGroup) getDataBinding().getRoot(), false, savedInstanceState);
        popupPlayModel.getWindow().setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupPlayModel.getWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupPlayModel.getWindow().setAnimationStyle(R.style.contextMenuAnim);
        //设置播放时长
        popupPlayModel.addEventAdapter((position, entity, type, view) -> {
            //判断如果不是同一个，则将上一个状态取反,单选操作
            if (type == select) {
                if (timeEntity != null && !timeEntity.equals(entity)) {
                    timeEntity.checked.set(false);
                }
                timeEntity = entity;
                util.setDuration(timeEntity.getTime(), (current, duration) -> {
                    if (current != 0)
                        playTime = current;
                    totalTime = duration;
                });
                popupPlayModel.getWindow().dismiss();
            }
            return true;
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

    public void initTimes() {
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

    private void initID() {
        id = getT().getIntent().getIntExtra(Constant.id, 0);
        if (id != 0) util.setId(id);
        else if (util.getId() != 0)
            id = util.getId();
    }

    public void onAddFavorityClick(View view) {
        if (id == 0 || !CheeseApplication.isLogin(false)) {
            view.setEnabled(false);
            ((CheckBox) view).setChecked(false);
            BaseUtil.toast("登陆后才能收藏");
            return;
        }

        AddFavorityParams params = new AddFavorityParams("addFavority");
        params.setId(util.getId());
        api.addFavority(params).compose(new RestfulTransformer<>()).subscribe(
        );
    }

    public void onFabuClick(View view) {
        if (id == 0 || !CheeseApplication.isLogin(false)) {
            view.setEnabled(false);
            ((CheckBox) view).setChecked(false);
            BaseUtil.toast("登陆后才能点赞");
            return;
        }

        FabulousParams params = new FabulousParams("addFabulous");
        params.setId(util.getId());
        addDisposable(api.addFabulous(params).compose(new RestfulTransformer<>()).subscribe(
                entity -> {
                    PlayEntity playEntity = getEntity();
                    playEntity.setFabu(entity.getFabu());
                    if (entity.getFabu() != null) {
                        playEntity.addFabuCount(1);
                    } else playEntity.addFabuCount(-1);
                    getDataBinding().setEntity(playEntity);
                }, BaseUtil::toast));
    }

    public void onBackClick(View view) {
        getT().onBackClick(view);
    }

    //UM分享
    public void onShareClick(View view) {
        String musicUR = null;
        if (getEntity() == null) return;
        PlayEntity entity = getEntity();
        addDisposable(Observable.create((ObservableOnSubscribe<UMusic>) e -> {
                    UMusic music = new UMusic(entity.getShareUrl());
                    UMImage image = new UMImage(getT(), entity.getImage());
                    music.setTitle(entity.getTitle());
                    music.setThumb(image);
                    music.setDescription(entity.getSubTitle());
                    music.setmTargetUrl(entity.getShareLandingUrl());
                    e.onNext(music);
                }
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe((uMusic -> {
            ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig
            //          config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);//设置位置
            config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR);
            config.setCancelButtonVisibility(true);
            config.setCancelButtonText("取消");
            config.setCancelButtonBackground(Color.rgb(240, 240, 240));
            config.setIndicatorColor(Color.WHITE, Color.WHITE);
            config.setTitleVisibility(false);
            config.setShareboardBackgroundColor(Color.WHITE);
            new ShareAction(getT())
                    .withMedia(uMusic)
//                    .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA)
                    .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
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

    //通知栏
    public void showButtonNotify() {
        if (getEntity() == null) return;
        playRecord();//播放数的反馈
        PlayEntity entity = getEntity();
        int NOTIFICATION_ID = 234;
        String CHANNEL_ID = "cheese_channel_01";
        if (mNotificationManager == null)
            mNotificationManager = util.getNotManager();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "cheese_channel";
            String Description = "This is cheese channel";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(false);
            mChannel.setLightColor(Color.WHITE);
            mChannel.enableVibration(false);
            mChannel.setVibrationPattern(null);//震动
            mChannel.setShowBadge(false);
            mNotificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(App.getCurrentActivity(), CHANNEL_ID);
        RemoteViews mRemoteViews = new RemoteViews(BuildConfig.APPLICATION_ID, R.layout.notify_music);

        try {
            mRemoteViews.setImageViewBitmap(R.id.music_icon, notifyImage.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRemoteViews.setTextViewText(R.id.music_title, entity.getTitle());
        mRemoteViews.setTextViewText(R.id.music_subtitle, entity.getSubTitle());

//        //点击的事件处理
        Intent buttonIntent = new Intent(ACTION_BUTTON);
        upDataButton();
        //这里加了广播，所及INTENT的必须用getBroadcast方法
        /* 播放/暂停  按钮 */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PALY_ID);
        PendingIntent intent_play = PendingIntent.getBroadcast(App.getCurrentActivity(), 1, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_pause, intent_play);
//        mRemoteViews.setOnClickPendingIntent(R.id.music_stop,intent_play);
        /* 下一首 按钮  */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_NEXT_ID);
        PendingIntent intent_next = PendingIntent.getBroadcast(App.getCurrentActivity(), 2, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_next, intent_next);
        /* 取消 按钮  */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_CANCEL_ID);
        PendingIntent intent_cancel = PendingIntent.getBroadcast(App.getCurrentActivity(), 3, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.music_cancel, intent_cancel);

        mBuilder.setContent(mRemoteViews)
                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("正在播放")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher);

        Notification notify = mBuilder.build();
        notify.flags = Notification.FLAG_ONGOING_EVENT;
        mNotificationManager.notify(NOTIFICATION_ID, notify);

    }

    @Override
    public void cancelButtonNotiy() {
        util.getNotManager().cancel(234);
    }

    private void playRecord() {
        PlayRecordParams recordParams = new PlayRecordParams("playRecord");
        recordParams.setId(getEntity().getId());
        addDisposable(api.playRecord(recordParams)
                .compose(new RestfulTransformer<>())
                .subscribe(o -> { }, BaseUtil::toast)
        );
    }

    private PendingIntent getDefalutIntent(int flags) {
        return PendingIntent.getActivity(App.getCurrentActivity(), 1, new Intent(), flags);
    }

    public void onAnchorClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.authorId, getEntity().getAnchorId());
        ARouterUtil.navigation(ActivityComponent.Router.author, bundle);
    }

    public void upDataButton() {
        checked.set(util.isPlaying());
    }


    //这里是芝士第二期开工的地方；
    public void onLastClick(View view) {
        int index = getList().indexOf(getEntity());
        if (index > 0) {
            setSingelEntity(getList().get(index - 1));
        } else BaseUtil.toast("已经是第一首");
    }

    public void onNextClick(View view) {
        nextMusic.setId(id);
        nextMusic.setActionType("next");
        int index = getList().indexOf(getEntity());
        if (index == getList().size() - 1)
            addDisposable(api.playInOrder(nextMusic).compose(new RestfulTransformer<>()).subscribe(
                    this::setSingelEntity
                    , BaseUtil::toast));
        else setSingelEntity(getList().get(index + 1));
    }

    public void onCompletion(MediaPlayer mp) {
        Timber.w("onCompletion" + util.getMediaStatus());
        if (!mp.isPlaying() && util.getCurrentPosition() > 0) {
            if(!loop.get())
            onNextClick(null);
            else setSingelEntity(getEntity());
        }
        checked.set(!mp.isPlaying());
        onResume();//校准播放按钮
    }
}

