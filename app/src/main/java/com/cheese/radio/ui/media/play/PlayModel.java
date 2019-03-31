package com.cheese.radio.ui.media.play;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.binding.model.App;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityPlayBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.CheeseApplication;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.broadcast.NotifyManager;
import com.cheese.radio.ui.media.audio.AudioModel;
import com.cheese.radio.ui.media.play.popup.PopupPlayModel;
import com.cheese.radio.ui.media.play.popup.SelectPlayTimeEntity;
import com.cheese.radio.ui.service.AudioServiceUtil;
import com.cheese.radio.ui.user.User;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.binding.model.adapter.AdapterType.select;

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
    private FutureTarget<Bitmap> notifyImage;
    private NotificationManager mNotificationManager;
    private PlayInOrderParams nextMusic = new PlayInOrderParams("playInOrder");
    private int position = 0;

    @Override
    public void attachView(Bundle savedInstanceState, PlayActivity activity) {
        super.attachView(savedInstanceState, activity);
        initTimes();
        initId();
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
        int position = getT().getIntent().getIntExtra(Constant.indexOf, 0);
        if (playList != null && !playList.isEmpty() && util.pause()) {
            setEntities(playList);
            setSingelEntity(playList.get(position));
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
        if (entity.getId() != id) {
            id = entity.getId();
            util.setId(id);
        }
        if (!getList().contains(entity))
            addEntity(entity);
        playTime = 0;
        util.pause();
        AudioServiceUtil.getInstance().setImage(entity.getImage());
        AudioServiceUtil.getInstance().setFileId(entity.getFileId());
        if (getDataBinding() != null) {
            getDataBinding().setEntity(entity);
            getDataBinding().html5Desc.setText(Html.fromHtml(entity.getAnchorBrief()));
        }
        play(entity);
        if (!getList().isEmpty()) {
            Model.dispatchModel(Constant.images, entity);
            showButtonNotify();
            playRecord();
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
        util.setOnTimingListener((current, duration) -> {
            if (current != 0) {
                playTime = current;
                totalTime = duration;
            }
        });
    }

    private void initId() {
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
        if (getEntity() == null) return;
        addDisposable(
                Observable.just(getEntity())
                        .flatMap(entity -> TextUtils.isEmpty(entity.getShareUrl()) ? api.getContentInfo(new PlayParams("contentInfo", entity.getId()))
                                .compose(new RestfulTransformer<>()) : Observable.just(entity))
                        .map(entity -> {
                            UMusic music = new UMusic(entity.getShareUrl());
                            UMImage image = new UMImage(getT(), entity.getImage());
                            music.setTitle(entity.getTitle());
                            music.setThumb(image);
                            music.setDescription(entity.getSubTitle());
                            music.setmTargetUrl(entity.getShareLandingUrl());
                            return music;
                        })
//                Observable.just(music).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(uMusic -> {
//                    if (uMusic.getU)
//                })
                        .subscribe((uMusic -> {
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
    private NotifyManager manager;

    public void showButtonNotify() {
        if (manager == null)
            manager = new NotifyManager();
        PlayEntity entity = getEntity();
        Disposable subscribe = Observable.just(Glide.with(getT()).asBitmap().load(entity.getImage()).submit())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap -> {
                    NotifyManager.builderNotification(bitmap.get(), entity.getTitle(), entity.getSubTitle());
                    upDataButton();
                }, BaseUtil::toast);
    }

    @Override
    public void cancelButtonNotiy() {
        util.getNotManager().cancel(NotifyManager.getMsgId());
    }

    private void playRecord() {
        PlayRecordParams recordParams = new PlayRecordParams("playRecord");
        recordParams.setId(getEntity().getId());
        addDisposable(api.playRecord(recordParams).compose(new ErrorTransform<>()).subscribe(o -> {
                    Model.dispatchModel("refreshPlayRecord", getEntity().getId());
                }, BaseUtil::toast)
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
        Timber.v("onNextClick1");
        nextMusic.setId(id);
        nextMusic.setActionType("next");
        int index = getList().indexOf(getEntity());
        if (index == getList().size() - 1) {
            Timber.v("onNextClick2");
            addDisposable(api.playInOrder(nextMusic).compose(new RestfulTransformer<>()).subscribe(
                    this::setSingelEntity
                    , BaseUtil::toast));
        } else setSingelEntity(getList().get(index + 1));
    }

    public void onCompletion(MediaPlayer mp) {
        Timber.w("onCompletion" + util.getMediaStatus());
        if (!mp.isPlaying() && util.getCurrentPosition() > 0) {
            if (!loop.get())
                onNextClick(null);
            else setSingelEntity(getEntity());
        }
        checked.set(!mp.isPlaying());
        onResume();//校准播放按钮
    }


    public void onManuClick(View view) {
        StringBuilder stringBuilder = new StringBuilder(RadioApi.htmlHost);
        stringBuilder.append("zhishidiantai/")
                .append(3)
                .append("/comment.html?aid=")
                .append(id)
                .append("&mid=")
                .append(CheeseApplication.getUser().getUserEntity().getUserId())
                .append("&sid=")
                .append("");


        ARouterUtil.navigation(RadioApi.htmlHost + "zhishidiantai/" + id + "/comment.html");
//        http://111.231.237.11:8085/zhishidiantai/3/comment.html?aid=65&mid=15&sid=1
    }
}

